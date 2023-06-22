package adventofcode.day7classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileSystemIterator implements Iterator<FileSystemElement> {
    
    private final List<FileSystemElement> elements;
    private int current;

    public FileSystemIterator(FileSystemElement root) {
        elements = new ArrayList<>();
        elements.add(root);
        iterate(root);
        current = 0;
    }
    
    @Override
    public boolean hasNext() {
        return current < elements.size();
    }

    @Override
    public FileSystemElement next() {
        return elements.get(current++);
    }
    
    private void iterate(FileSystemElement element) {
        for (FileSystemElement e : element) {
            if (e.getType() == FileSystemElement.Type.DIRECTORY)
                iterate(e);
            elements.add(elements.size() - 1, e);
        }
    }
    
}
