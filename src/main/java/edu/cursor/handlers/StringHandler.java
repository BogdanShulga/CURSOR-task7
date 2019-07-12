package edu.cursor.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StringHandler {

    private List<String> badWords = new ArrayList<>();
    private List<String> goodWords;
    private List<String> songWords;
    private Map<String, Integer> occurrences = new HashMap<>();
    private String[] swearing = {"пизд", "ебен"};
    private String text;

    public void parseFile(final String s) {
        songWords = Arrays
                .asList(s.split("[\\p{Punct}\\p{Space}]+"));
        goodWords = songWords.stream().filter(word -> {
            boolean swear = false;
            for (String h : swearing) {
                if (word.contains(h)) {
                    swear = true;
                    break;
                }
            }
            if (swear | word.length() < 3) {
                badWords.add(word);
                return false;
            } else {
                Integer oldCount = occurrences.get(word);
                if (oldCount == null) {
                    oldCount = 0;
                }
                occurrences.put(word, oldCount + 1);
                return true;
            }
        }).collect(Collectors.toList());
    }

    public void maxGoodOccurrence(final int n) {
        System.out.println("The first " + n
                + " good words with the biggest occurrence:");
        occurrences.entrySet().stream().
                sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                limit(n).forEach(System.out::println);
    }


    public String readFile(final String path) {
        String s = "";
        try {
            s = Files.lines(Paths.get(path)).map(s1 -> s1 += "\n").
                    collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = s;
        return s;
    }

    public int getTotalWordCount() {
        return songWords.size();
    }

    public int getBadWordCount() {
        return badWords.size();
    }

    public int getGoodWordCount() {
        return goodWords.size();
    }

    public List<String> getBadWords() {
        return badWords;
    }

    public String getText() {
        return text;
    }
}
