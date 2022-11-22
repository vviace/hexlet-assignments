package exercise;

import java.util.HashMap;
import java.util.Map;
class App {
    public static Map<String, Integer> getWordCount(String letters) {
        String[] words = letters.split(" ");
        Map<String, Integer> map = new HashMap<>();
        if (letters == "") {
            return map;
        }

        for (String word : words) {
            int count = map.getOrDefault(word, 0);
            count += 1;
            map.put(word, count);
        }
        return map;
    }

    public static String toString(Map<String, Integer> letters) {
        if (letters.isEmpty()) {
            return "{}";
        }
        String res = "{\n";
        for (Map.Entry<String, Integer> letter : letters.entrySet()) {
            String result = "  " + letter.getKey() + ": "
                    + letter.getValue() + "\n";
            res += result;
        }
        return res + "}";
    }

}
//END
