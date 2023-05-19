/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventofcode.day1classes;

/**
 *
 * @author juan
 */
public class Elf {
    private final int calories;
    private final int number;

    public Elf(int calories, int number) {
        this.calories = calories;
        this.number = number;
    }
    
    public int getCalories() {
        return calories;
    }

    public int getNumber() {
        return number;
    }
}