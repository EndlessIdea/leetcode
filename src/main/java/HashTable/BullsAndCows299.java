package HashTable;

import java.util.*;

public class BullsAndCows299 {
    public String getHint(String secret, String guess) {
        Map<Character, Integer> map = new HashMap<>();
        int bulls = 0, cows = 0, len = secret.length();
        for (int i = 0; i < len; i++) {
            map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bulls++;
                map.put(secret.charAt(i), map.get(secret.charAt(i)) - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (guess.charAt(i) != secret.charAt(i) && map.containsKey(guess.charAt(i)) && map.get(guess.charAt(i)) > 0) {
                cows++;
//                map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
                map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
            }
        }

        return String.format("%dA%dB", bulls, cows);
    }

    public String getHint1(String secret, String guess) {
        int[] record = new int[10];
        int len = secret.length();
        int bulls = 0, cows = 0;
        for (int i = 0; i < len; i++) {
            int si = secret.charAt(i) - '0';
            int gi = guess.charAt(i) - '0';
            if (si == gi) {
                bulls++;
            } else {
                if (record[si] < 0) cows++;
                if (record[gi] > 0) cows++;
                record[si]++;
                record[gi]--;
            }
        }

        return String.format("%dA%dB", bulls, cows);
    }

    public static void main(String[] args) {
        BullsAndCows299 obj = new BullsAndCows299();
        System.out.println(obj.getHint1("1123", "0111"));
    }
}
