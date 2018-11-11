package HashTable;

import java.util.*;

public class SentenceSimilarity734 {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        //similar to itsSelf, can't pass similar, words length equals, similar can be reversed
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
//            if (map.containsKey(pair[0])) {
//                map.get(pair[0]).add(pair[1]);
//            } else {
//                map.put(pair[0], new HashSet<String>(new String[]{pair[1]}));
//            }
//            if (map.containsKey(pair[1])) {
//                map.get(pair[1]).add(pair[0]);
//            } else {
//                map.put(pair[1], Arrays.asList(pair[0]));
//            }
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new HashSet<>());
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new HashSet<>());
            }
            map.get(pair[0]).add(pair[1]);

            map.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (map.containsKey(words1[i]) && map.get(words1[i]).contains(words2[i])) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SentenceSimilarity734 obj = new SentenceSimilarity734();
        System.out.println(obj.areSentencesSimilar(
                new String[]{"great", "acting", "skills"},
                new String[]{"fine", "drama", "talent"},
                new String[][]{{"great", "fine"}, {"acting","drama"}, {"skills","talent"}}));
    }
}
