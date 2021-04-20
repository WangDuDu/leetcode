package sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuyang on 2021-4-20.
 */
public class SortCharactersByFrequency {
    public static String frequencySort(String s) {
        char[] chars = s.toCharArray();

        Map<Character, Integer> charMapFrequency = new HashMap<>();
        for (char c : chars) {
            Integer frequency = charMapFrequency.getOrDefault(c, 0);
            charMapFrequency.put(c, frequency + 1);
        }

        List<Character>[] frequencyBucket = new ArrayList[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : charMapFrequency.entrySet()) {
            List<Character> bucketElements = frequencyBucket[entry.getValue()];
            if (null == bucketElements) {
                bucketElements = new ArrayList<>();
            }
            bucketElements.add(entry.getKey());
            frequencyBucket[entry.getValue()] = bucketElements;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = frequencyBucket.length - 1; i > 0; i--) {
            List<Character> characters = frequencyBucket[i];
            if (null == characters) {
                continue;
            }

            for (Character character : characters) {
                for (int j = 0; j < i; j++) {
                    stringBuilder.append(character);
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }
}
