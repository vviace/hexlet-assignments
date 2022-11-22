package exercise;

import java.util.ArrayList;
import java.util.Arrays;

// BEGIN
class App {
    public static boolean scrabble(String letter, String search) {
        String[] letters = letter.toLowerCase().split("");
        String[] searchWord = search.toLowerCase().split("");
        ArrayList<String> collLetters = new ArrayList<>(Arrays.asList(letters));
        int count = 0;
        for (String s : searchWord) {
            if (collLetters.remove(s)) {
                count++;
            }
        }

        if (count == searchWord.length) {
            return true;
        }
        return false;
    }
}
//END
