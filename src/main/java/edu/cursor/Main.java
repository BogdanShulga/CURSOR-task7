package edu.cursor;

import edu.cursor.handlers.StringHandler;

public class Main {
    public static void main(String[] args) {
        StringHandler stringHandler = new StringHandler();
        stringHandler.parseFile(stringHandler.readFile(
                "src/main/java/edu/cursor/data.txt"));
        System.out.println("The text of the song:\n" + stringHandler.getText());
        stringHandler.maxGoodOccurrence(3);
        System.out.println("Total word count in the data.txt is - "
                + stringHandler.getTotalWordCount());
        System.out.println("Total good word count in the data.txt is - "
                + stringHandler.getGoodWordCount());
        System.out.println("Total bad word count in the data.txt is - "
                + stringHandler.getBadWordCount());
        System.out.println("Bad words: " + stringHandler.getBadWords());
    }
}
