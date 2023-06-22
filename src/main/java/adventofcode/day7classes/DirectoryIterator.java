package adventofcode.day7classes;

import java.util.Iterator;

public class DirectoryIterator implements Iterator<FileSystemElement> {

    private FileSystemElement current;

    public DirectoryIterator(FileSystemElement currentDirectory) {
        current = currentDirectory.getFirst();
    }
    
    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public FileSystemElement next() {
        FileSystemElement element = current;
        current = current.next();
        return element;
    }
    
}
