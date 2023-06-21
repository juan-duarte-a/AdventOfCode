package adventofcode;

import adventofcode.day7classes.FileSystem;
import adventofcode.day7classes.FileSystemElement;
import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7 implements Day {
    
    private String[] fileSystem;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD7");
        
        NewDay.newDayText(7);
        
        try {
            fileSystem = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }
        
        NewDay.partText(1);
        System.out.println("Sum of the total sizes of directories with a total size of at most 100000: " + 
                ConsoleColors.WHITE + "" + ConsoleColors.RESET);
        
        test();
    }
    
    public void test() {
        FileSystem fs = new FileSystem();
        fs.addDirectory("dir1");
        fs.addDirectory("dir2");
        fs.addFile("file1", FileSystemElement.Type.FILE, 123);
        fs.addFile("file2", FileSystemElement.Type.FILE, 456);
        fs.printCurrentDirectoryContents();
        
        System.out.println();
        fs.changeDirectory("dir2");
        fs.addFile("file3", FileSystemElement.Type.FILE, 1230);
        fs.addFile("file4", FileSystemElement.Type.FILE, 4560);
        fs.printCurrentDirectoryContents();
        
        System.out.println();
        fs.goToParentDirectory();
        fs.printCurrentDirectoryContents();
    }
    
}
