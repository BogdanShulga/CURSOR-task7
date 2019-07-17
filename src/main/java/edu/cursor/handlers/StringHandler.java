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

    public void handleTextOfSong(final String path) {
        readFileToString(path);
        parseTextOfSong();
        filterSongsWords();
    }


    private void parseTextOfSong() {
        String regex = "[\\p{Punct}\\p{Space}]+";
        this.songWords = Arrays
                .asList(this.text.split(regex));
    }

    private void filterSongsWords() {
        this.goodWords = this.songWords.stream()
                .filter(word -> {
                    boolean swear = false;
                    for (String h : this.swearing) {
                        if (word.contains(h)) {
                            swear = true;
                            break;
                        }
                    }
                    boolean result = true;
                    if (swear | word.length() < 3) {
                        this.badWords.add(word);
                        result = false;
                    } else {
                        //counting the number of repetitions
                        // of words in the text of the song
                        Integer oldCount = this.occurrences.get(word);
                        if (oldCount == null) {
                            oldCount = 0;
                        }
                        this.occurrences.put(word, oldCount + 1);
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    public void printMaxGoodWordRepetition(final int n) {
        occurrences.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(n)
            .forEach(System.out::println);
    }

    private void readFileToString(final String path) {
        try {
            this.text = Files.lines(Paths.get(path))
                    .map(s1 -> s1 += "\n")
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTextOfSong() {
        System.out.println(this.text);
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
}
