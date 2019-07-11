import handlers.StringHandler;

import java.io.File;

public class Main {
    public static void main(String[] args){
        StringHandler stringHandler = new StringHandler();
        stringHandler.parseFile(stringHandler.readFile(new File("data.txt")));
        stringHandler.maxGoodOccurrence(3);
        System.out.println("Total word count in the data.txt is - " + stringHandler.getTotalWordCount());
        System.out.println("Total good word count in the data.txt is - " + stringHandler.getGoodWordCount());
        System.out.println("Total bad word count in the data.txt is - " + stringHandler.getBadWordCount());
        System.out.println("Bad words: " + stringHandler.getBadWords());
    }
}
