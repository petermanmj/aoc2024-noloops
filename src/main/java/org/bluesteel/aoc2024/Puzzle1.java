package org.bluesteel.aoc2024;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle1 {

    // input file.
    private final String inputFileName = "pz1_input.txt";

    public int calculateListDistance() {

        int result = 0;

        // open the file and get a reader
        try (InputStream inputStream = getClass().getResourceAsStream("/" + inputFileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            // read the file into a map from left to right pairs as key,value
            var map = br.lines().map(this::parseLine).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            // generate left and right lists from the map.
            List<Integer> left = map.keySet().stream().sorted(Integer::compareTo).toList();
            List<Integer> right = map.values().stream().sorted(Integer::compareTo).toList();

            // derive the differences from the lists and sum them.
            result = IntStream.range(0,left.size()).map(i -> (left.get(i) - right.get(i))).map(Math::abs).sum();

        } catch (Exception ex) {
            System.err.println("that went bad: " + ex.getMessage());
        }

        return result;
    }

    public int calculateSimilarityScore() {
        int result = 0;

        // open the file and get a reader
        try (InputStream inputStream = getClass().getResourceAsStream("/" + inputFileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            // read the file into a map from left to right pairs as key,value
            var map = br.lines().map(this::parseLine).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            // generate left and right lists from the map.
            List<Integer> left = map.keySet().stream().sorted(Integer::compareTo).toList();
            List<Integer> right = map.values().stream().sorted(Integer::compareTo).toList();

            // count items in the right list
            Map<Integer, Long> countMap = right.stream()
                    .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

            long rslt = left.stream()
                    .map(x -> x * (Optional.ofNullable(countMap.get(x)).orElse(0L)))
                    .reduce(0L, Long::sum);
            result = (int)rslt;

        } catch (Exception e) {
            System.err.println("exception calculating similarity: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    private Map.Entry<Integer, Integer> parseLine(String line) {
        String[] items = line.split("\s");
        items = Arrays.stream(items).filter(x -> !x.isBlank()).toArray(String[]::new);

        return new AbstractMap.SimpleEntry<>(Integer.valueOf(items[0]), Integer.valueOf(items[1]));
    }



}
