package adventofcode.day7classes;

import java.util.Iterator;

public class FileSystem implements Iterable<FileSystemElement> {
    
    private FileSystemElement currentDirectory;
    private final FileSystemElement root;

    public FileSystem() {
        root = new FileSystemElement(
                null, null, "root", FileSystemElement.Type.DIRECTORY, 0);
            
        currentDirectory = root;
    }
    
    public void addDirectory(String name) {
        FileSystemElement directory = new FileSystemElement(
                currentDirectory, root, name, FileSystemElement.Type.DIRECTORY, 0);

        updateCurrentElement(directory);
    }
    
    public void addFile(String name, int size) {
        FileSystemElement file = new FileSystemElement(
                currentDirectory, root, name, FileSystemElement.Type.FILE, size);

        updateCurrentElement(file);
    }
    
    private void updateCurrentElement(FileSystemElement element) {
        if (currentDirectory.getFirst() == null) {
            currentDirectory.setFirst(element);
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
        
        return true;
    }
    
    public void goToParentDirectory() {
        if (currentDirectory.getParent() != null) {
            currentDirectory = currentDirectory.getParent();
        }
    }
    
    public void goToRoot() {
        currentDirectory = root;
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
 
        return directory;
    }
    
    public void printCurrentDirectoryContents() {
        currentDirectory.forEach(element -> System.out.println(element));
    }

    @Override
    public Iterator<FileSystemElement> iterator() {
        return new FileSystemIterator(root);
    }
    
}
