package adventofcode.day7classes;

import java.util.Iterator;

public class FileSystemElement implements Iterable<FileSystemElement>{
    
    public static enum Type {
        DIRECTORY,
        FILE
    };
    
    private final FileSystem fileSystem;
    private final FileSystemElement parent;
    private final FileSystemElement root;
    private FileSystemElement next;
    private FileSystemElement first;
    private FileSystemElement last;
    private final String name;
    private final Type type;
    private final int size;
    private boolean hasDirectories;

    public FileSystemElement(FileSystem fileSystem, FileSystemElement parent, 
            FileSystemElement root, String name, Type type, int size) {
        this.fileSystem = fileSystem;
        this.parent = parent;
        this.root = root == null ? this : root;
        this.name = name;
        this.type = type;
        this.size = size;
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
        return hasDirectories;
    }
    
    public String getPath() {
        if (this == root)
            return "/";
        
        return parent.getPath() + name + "/";
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
        return fileSystem;
    }
    
    @Override
    public String toString() {
        return name + " - " + size + " - " + type;
    }

}
