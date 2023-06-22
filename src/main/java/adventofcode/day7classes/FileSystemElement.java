package adventofcode.day7classes;

import java.util.Iterator;

public class FileSystemElement implements Iterable<FileSystemElement>, Comparable<FileSystemElement>{
    
    public static enum Type {
        DIRECTORY,
        FILE
    };
    
    private final FileSystemElement parent;
    private final FileSystemElement root;
    private FileSystemElement next;
    private FileSystemElement first;
    private FileSystemElement last;
    private final String name;
    private final Type type;
    private int size;

    public FileSystemElement(FileSystemElement parent, 
            FileSystemElement root, String name, Type type, int size) {
        this.parent = parent;
        this.root = root == null ? this : root;
        this.name = name;
        this.type = type;
        setSize(size);
    }

    public FileSystemElement getParent() {
        return parent;
    }

    public FileSystemElement getRoot() {
        return root;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size += size;
        
        if (parent != null)
            parent.setSize(size);
    }

    public FileSystemElement getFirst() {
        return first;
    }

    public void setFirst(FileSystemElement first) {
        this.first = first;
    }

    public FileSystemElement getLast() {
        return last;
    }

    public void setLast(FileSystemElement last) {
        this.last = last;
    }

    public boolean hasDirectories() {
        for (FileSystemElement element : this) {
            if (element.getType() == Type.DIRECTORY)
                return true;
        }
        
        return false;
    }
    
    public String getPath() {
        if (this == root)
            return "/";
        
        return parent.getPath() + (type == Type.DIRECTORY ? name : "") + "/";
    }
    
    public String getFullPath() {
        if (this == root)
            return "/";
        
        return parent.getPath() + name + (type == Type.DIRECTORY ? "/" : "");
    }
    
    @Override
    public int compareTo(FileSystemElement element) {
        return this.size - element.size;
    }
    
    public void setNext(FileSystemElement nexElement) {
        if (!hasNext())
            next = nexElement;
    }

    public boolean hasNext() {
        return next != null;
    }

    public FileSystemElement next() {
        return next;
    }

    @Override
    public Iterator<FileSystemElement> iterator() {
        return new DirectoryIterator(this);
    }
    
    @Override
    public String toString() {
        return name + " - " + size + " - " + type;
    }

}
