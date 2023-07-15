package adventofcode;

import adventofcode.day7classes.FileSystem;
import adventofcode.day7classes.FileSystemElement;
import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.StreamSupport;

public class Day7 implements Day {
    
    private String[] commands;
    private FileSystem fileSystem;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD7");
        
        NewDay.newDayText(7);
        
        try {
            commands = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }
        
        NewDay.partText(1);
        
        createFileSystem();
        
        System.out.println("Sum of the total sizes of directories with a total size of at most 100000: " + 
                ConsoleColors.WHITE + sumDirectoriesSizeAtMost(100000) + ConsoleColors.RESET);
        
        NewDay.partText(2);
        
        System.out.println("Smallest directory size that would free needed space: " + 
                ConsoleColors.WHITE + smallestDirectorySizeToFreeSpace(30000000) + ConsoleColors.RESET);
    }
    
    private void createFileSystem() {
        fileSystem = new FileSystem();
        
        for (String command : commands) {
            String[] c = command.split(" ");
            
            switch (c[0]) {
                case "$" -> {
                    if (c[1].equals("cd")) {
                        fileSystem.changeDirectory(c[2]);
                    }
                }
                case "dir" -> fileSystem.addDirectory(c[1]);
                default -> fileSystem.addFile(c[1], Integer.parseInt(c[0]));
            }
        }
    }
    
    private int sumDirectoriesSizeAtMost(int size) {
        return StreamSupport.stream(fileSystem.spliterator(), false)
                .filter(e -> e.getType() == FileSystemElement.Type.DIRECTORY)
                .filter(d -> d.getSize() <= size)
                .mapToInt(FileSystemElement::getSize).sum();
    }
    
    private int smallestDirectorySizeToFreeSpace(int neededSpace) {
        fileSystem.goToRoot();
        int spaceToFree = neededSpace - (70000000 - fileSystem.getCurrentDirectory().getSize());

        List<FileSystemElement> directories
                = StreamSupport.stream(fileSystem.spliterator(), false)
                        .filter(e -> e.getType() == FileSystemElement.Type.DIRECTORY)
                        .filter(d -> d.getSize() >= spaceToFree).sorted()
                        .toList();
        
        return directories.get(0).getSize();
    }
    
    public void test() {
        FileSystem fs = new FileSystem();
        fs.addDirectory("dir1");
        fs.addDirectory("dir2");
        fs.addFile("file1", 123);
        fs.addFile("file2", 456);
        fs.printCurrentDirectoryContents();
        
        System.out.println();
        fs.changeDirectory("dir1");
        fs.addFile("file3", 230);
        fs.addFile("file4", 560);
        fs.addDirectory("dir3");
        System.out.println(fs.getCurrentDirectory().getPath());
        fs.printCurrentDirectoryContents();
        
        System.out.println();
        fs.changeDirectory("dir3");
        fs.addFile("file5", 100);
        fs.addFile("file6", 100);
        System.out.println(fs.getCurrentDirectory().getPath());
        fs.printCurrentDirectoryContents();
        
        System.out.println();
        fs.changeDirectory("..");
        System.out.println(fs.getCurrentDirectory().getPath());
        fs.printCurrentDirectoryContents();

        System.out.println();
        fs.changeDirectory("..");
        System.out.println(fs.getCurrentDirectory().getPath());
        fs.printCurrentDirectoryContents();

        System.out.println();
        fs.changeDirectory("dir2");
        fs.addFile("file7", 1230);
        fs.addFile("file8", 4560);
        System.out.println(fs.getCurrentDirectory().getPath());
        fs.printCurrentDirectoryContents();
        
        System.out.println();
        fs.goToRoot();
        System.out.println(fs.getCurrentDirectory().getPath());
        fs.printCurrentDirectoryContents();
        
        System.out.println("\n/////////////////////////////////\n");
        fs.forEach(System.out::println);
    }
    
}
