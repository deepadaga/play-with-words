package wordrush.madclub.wordrush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by chanpreet on 3/11/16.
 */

public class WordRushDictionary {
    private ArrayList<String> wordList = new ArrayList<>();
    private HashMap<String, ArrayList<String>> hm = new HashMap<>();

    public WordRushDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);

            String sorted = sortedArray(word);
            if (hm.containsKey(sorted)) {
                ArrayList<String> temp = hm.get(sorted);
                temp.add(word);
                hm.put(sorted, temp);
            } else {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(word);
                hm.put(sorted, temp);
            }
        }
    }

    public String SelectRandomWord() {
        while (true) {
            Random r = new Random();
            int temp = r.nextInt(wordList.size());
            String tempWord = wordList.get(temp);
            if (tempWord.length() == 4)
                return tempWord;
            else
                continue;
        }
    }

    public String sortedArray(String word) {
        char temp[] = word.toCharArray();
        Arrays.sort(temp);
        String newstring = new String(temp);
        return newstring;

    }

    public char[] array(String word) {
        char temp[] = word.toCharArray();
        Arrays.sort(temp);
        return temp;
    }

    public boolean isword(String sorted, String word) {
        ArrayList list = hm.get(sorted);
        if (list.contains(word))
            return true;
        else
            return false;
    }
}
