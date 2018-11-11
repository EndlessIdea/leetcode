package TwoPointer;

import java.util.HashMap;

public class LongestSubstringWithNoRepeated3 {
    public int lengthOfLongestSubstring(String s) {
        int ret = 0;
        if (s == null || s.length() == 0) {
            return ret;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
//                left = map.get(c) + 1;
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        LongestSubstringWithNoRepeated3 obj = new LongestSubstringWithNoRepeated3();
//        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(obj.lengthOfLongestSubstring("bbbbbb"));
//        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
        System.out.println(obj.lengthOfLongestSubstring("abba"));
    }
}
