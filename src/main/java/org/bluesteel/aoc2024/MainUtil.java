package org.bluesteel.aoc2024;

public class MainUtil {

    public static void main(String[] args) {
        Puzzle1 pz1 = new Puzzle1();
        System.out.println("distance: " + pz1.calculateListDistance());
        System.out.println("similarity: " + pz1.calculateSimilarityScore());
    }

}
