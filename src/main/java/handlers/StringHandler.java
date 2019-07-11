package handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StringHandler {

    private List<String> badWords = new ArrayList<>();
    private Map<String, Integer> occurrences = new HashMap<>();
    private int totalWordCount = 0;
    private int badWordCount = 0;
    private int goodWordCount = 0;
    private String[] swearing = {"пизд", "ебен"};

    public void parseFile(String s) {

        StringTokenizer st = new StringTokenizer(s, ",:;.\n -");
        totalWordCount = st.countTokens();

        while (st.hasMoreTokens()) {

            String word = st.nextToken();

            boolean swear = false;
            for (String h : swearing) {
                if (word.contains(h)) {
                    swear = true;
                    break;
                }
            }

            if (swear | word.length() < 3) {
                badWords.add(word);
                badWordCount++;
            } else {
                goodWordCount++;
                Integer oldCount = occurrences.get(word);
                if (oldCount == null) {
                    oldCount = 0;
                }
                occurrences.put(word, oldCount + 1);
            }
        }
    }

    public void maxGoodOccurrence(int n) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.addAll(occurrences.values());

        Set<String> set = new HashSet<>();

        Set<Map.Entry<String, Integer>> entries = occurrences.entrySet();

        System.out.println("The first " + n + " good words with the biggest occurrence:");

        for (int i = 0; i < n; i++) {

            Integer max = queue.poll();
            boolean stop = false;
            Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
            while (!stop && iterator.hasNext()) {
                Map.Entry<String, Integer> e = iterator.next();
                String key = e.getKey();
                Integer value = e.getValue();

                if (value.equals(max) && !set.contains(key)) {
                    set.add(key);
                    System.out.println(key + " = " + value);
                    stop = true;
                }
            }
        }
    }


    public String readFile(File file) {
        String st;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((st = br.readLine()) != null) {
                sb.append(st);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public int getTotalWordCount() {
        return totalWordCount;
    }

    public int getBadWordCount() {
        return badWordCount;
    }

    public int getGoodWordCount() {
        return goodWordCount;
    }

    public List<String> getBadWords() {
        return badWords;
    }
}
