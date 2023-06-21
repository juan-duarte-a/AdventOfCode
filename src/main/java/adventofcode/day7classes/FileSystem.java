package adventofcode.day7classes;

import java.util.Iterator;

public class FileSystem implements Iterator<FileSystemElement> {
    
    private FileSystemElement currentDirectory;
    private FileSystemElement currentElement;
    private final FileSystemElement root;

    public FileSystem() {
        root = new FileSystemElement(this, 
                null, null, "root", FileSystemElement.Type.DIRECTORY, 0);
            
        currentDirectory = root;
    }
    
    public void addDirectory(String name) {
        FileSystemElement directory = new FileSystemElement(this, 
                currentDirectory, root, name, FileSystemElement.Type.DIRECTORY, 0);
        updateCurrentElement(directory);
    }
    
    public void addFile(String name, FileSystemElement.Type type, int size) {
        FileSystemElement file = new FileSystemElement(this, 
                currentDirectory, root, name, type, size);
        updateCurrentElement(file);
    }
    
    private void updateCurrentElement(FileSystemElement element) {
        if (currentDirectory.getFirst() == null) {
            currentDirectory.setFirst(element);
            currentElement = element;
        }

        if (currentDirectory.getLast() != null)
            currentDirectory.getLast().setNext(element);
        currentDirectory.setLast(element);
    }
    
    public FileSystemElement getCurrentDirectory() {
        return currentDirectory;
    }
    
    public boolean changeDirectory(String directoryName) {
        switch (directoryName) {
            case "/" -> goToRoot();
            case ".." -> goToParentDirectory();
            default -> {
                FileSystemElement directory = currentDirectoryContainsDirectory(directoryName);
                if (directory != null)
                    currentDirectory = directory;
                else
                    return false;
            }
        }
        
        resetIterator();
        return true;
    }
    
    public void goToParentDirectory() {
        if (currentDirectory.getParent() != null) {
            currentDirectory = currentDirectory.getParent();
            resetIterator();
        }
    }
    
    public void goToRoot() {
        currentDirectory = root;
        resetIterator();
    }
    
    private FileSystemElement currentDirectoryContainsDirectory(String directoryName) {
        FileSystemElement directory = null;
        
        for (FileSystemElement element : currentDirectory) {
            if (element.getName().equals(directoryName)
                    && element.getType() == FileSystemElement.Type.DIRECTORY) {
                directory = element;
                break;
            }
        }
 
        resetIterator();
        return directory;
    }
    
    public void printCurrentDirectoryContents() {
        currentDirectory.forEach(element -> System.out.println(element));
        resetIterator();
    }
    
    private void resetIterator() {
        currentElement = currentDirectory.getFirst();
    }

    @Override
    public boolean hasNext() {
        return currentElement != null;
    }

    @Override
    public FileSystemElement next() {
        FileSystemElement element = currentElement;
        currentElement = currentElement.next();
        return element;
    }
    
}
