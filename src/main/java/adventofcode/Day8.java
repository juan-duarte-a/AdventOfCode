package adventofcode;

import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Day8 implements Day {

    private String[] map;
    private short[][] trees;
    int[] bestTree;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD8");
        
        NewDay.newDayText(8);
        
        try {
            map = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }
        
        mapTreesToMatrix();
        bestTree = new int[2];
        
        NewDay.partText(1);
        
        System.out.println("Number of trees that are visible from outside of the grid: " + 
                ConsoleColors.WHITE + treesVisibleFromOutside() + ConsoleColors.RESET);

        NewDay.partText(2);
        
        System.out.println("Highest scenic score: " + 
                ConsoleColors.WHITE + highestScenicScore() + ConsoleColors.RESET);
        System.out.println("Highest scenic score tree: " + 
                ConsoleColors.WHITE + Arrays.toString(bestTree) + ConsoleColors.RESET);
    }
    
    private void mapTreesToMatrix() {
        trees = new short[map.length][map[0].length()];
        
        for (int i = 0; i < map.length; i++) {
            String row = map[i];
            
            for (int j = 0; j < row.length(); j++) {
                trees[i][j] = Short.parseShort(row.substring(j, j+1));
            }
        }
    }
    
    private int treesVisibleFromOutside() {
        int visibleTrees = trees[0].length * 2 + trees.length * 2 - 4;
        boolean visible;
        
        for (int i = 1; i < trees[0].length-1; i++) {
            for (int j = 1; j < trees.length-1; j++) {
                visible = false;
                
                for (int k = 0; k < i; k++) {
                     if (trees[i][j] <= trees[k][j])
                        break;
                    if (k == i-1)
                        visible = true;
                }
                
                if (visible) {
                    visibleTrees++;
                    continue;
                }

                for (int k = trees.length-1; k > i; k--) {
                    if (trees[i][j] <= trees[k][j])
                        break;
                    if (k == i+1)
                        visible = true;
                }

                if (visible) {
                    visibleTrees++;
                    continue;
                }

                for (int k = 0; k < j; k++) {
                    if (trees[i][j] <= trees[i][k])
                        break;
                    if (k == j-1)
                        visible = true;
                }

                if (visible) {
                    visibleTrees++;
                    continue;
                }

                for (int k = trees[j].length-1; k > j; k--) {
                    if (trees[i][j] <= trees[i][k])
                        break;
                    if (k == j+1)
                        visible = true;
                }

                if (visible) {
                    visibleTrees++;
                }
            }
        }
        
        return visibleTrees;
    }
    
    private int highestScenicScore() {
        int highestScore = 0;
        int left;
        int right;
        int up;
        int down;
        int score;
        
        for (int i = 1; i < trees[0].length-1; i++) {
            for (int j = 1; j < trees.length-1; j++) {
                up = 0;
                for (int k = i-1; k >= 0; k--) {
                    up++; 
                    if (trees[i][j] <= trees[k][j])
                        break;
                }
                
                down = 0;
                for (int k = i+1; k < trees.length; k++) {
                    down++;
                    if (trees[i][j] <= trees[k][j])
                        break;
                }

                left = 0;
                for (int k = j-1; k >= 0; k--) {
                    left++;
                    if (trees[i][j] <= trees[i][k])
                        break;
                }

                right = 0;
                for (int k = j+1; k < trees[i].length; k++) {
                    right++;
                    if (trees[i][j] <= trees[i][k])
                        break;
                }
                
                score = up * down * left * right;
                if (highestScore < score) {
                    highestScore = score;
                    bestTree[0] = i;
                    bestTree[1] = j;
                }
            }
        }
        
        return highestScore;
    }
    
}
