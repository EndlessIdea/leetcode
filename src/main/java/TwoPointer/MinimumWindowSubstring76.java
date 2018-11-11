package TwoPointer;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {
    public String minWindow(String s, String t) {
        String ret = "";
        if (t.length() > s.length()) {
            return ret;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int min = s.length() + 1;
        for (int left = 0, right = 0; right < s.length() && left <= right; right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
            }
            while (isContains(map)) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    ret = s.substring(left, right + 1);
                }
                char k = s.charAt(left);
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + 1);
                }
                left++;
            }
        }

        return ret;
    }

    private boolean isContains(Map<Character, Integer> map) {
        for (Character c : map.keySet()) {
            if (map.get(c) > 0) {
                return false;
            }
        }
        return true;
    }

    //use two map to count the target chars of s and t
    public String minWindow1(String s, String t) {
        return "";
    }

    public static void main(String[] args) {
        MinimumWindowSubstring76 obj = new MinimumWindowSubstring76();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
    }
}
