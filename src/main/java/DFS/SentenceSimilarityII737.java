package DFS;

import HashTable.SentenceSimilarity734;

import java.util.HashMap;

public class SentenceSimilarityII737 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }

        HashMap<String, String> relation = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            relation.put(pairs[i][0], pairs[i][0]);
            relation.put(pairs[i][1], pairs[i][1]);
        }

        for (int i = 0; i < pairs.length; i++) {
            String p0 = getParent(relation, pairs[i][0]);
            String p1 = getParent(relation, pairs[i][1]);
//            relation.put(pairs[i][1], p0);
            relation.put(p1, p0);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (relation.containsKey(words1[i]) || !relation.containsKey(words2[i])) {
                return false;
            }
            String p1 = getParent(relation, words1[i]);
            String p2 = getParent(relation, words2[i]);
            if (!p1.equals(p2)) {
                return false;
            }
        }

        return true;
    }

    private String getParent(HashMap<String, String> relation, String word) {
        if (!relation.get(word).equals(word)) {
            String parent = getParent(relation, relation.get(word));
            relation.put(word, parent);
        }

        return relation.get(word);
    }


    public static void main(String[] args) {
        SentenceSimilarityII737 obj = new SentenceSimilarityII737();
        String[] words1 = {"great", "acting", "skills"};
        String[] words2 = {"fine", "drama", "talent"};
        String[][] pairs = {
                {"great", "good"},
                {"fine", "good"},
                {"acting", "drama"},
                {"skills", "talent"},
        };

        System.out.println(obj.areSentencesSimilarTwo(words1, words2, pairs));
    }
}
