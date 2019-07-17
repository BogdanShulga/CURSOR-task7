package edu.cursor;

import edu.cursor.handlers.StringHandler;

public class Main {
    public static void main(final String[] args) {
        String path = "src/main/java/edu/cursor/data.txt";
        StringHandler stringHandler = new StringHandler();
        stringHandler.handleTextOfSong(path);
        System.out.println("The text of the song:");
        stringHandler.printTextOfSong();
        System.out.println("First 3 good words with the "
                + "biggest repetition in the text of the song:");
        stringHandler.printMaxGoodWordRepetition(3);
        System.out.println("Total word count in the data.txt is - "
                + stringHandler.getTotalWordCount());
        System.out.println("Total good word count in the data.txt is - "
                + stringHandler.getGoodWordCount());
        System.out.println("Total bad word count in the data.txt is - "
                + stringHandler.getBadWordCount());
        System.out.println("Bad words: "
                + stringHandler.getBadWords());
    }
}
