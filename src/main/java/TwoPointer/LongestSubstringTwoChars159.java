package TwoPointer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringTwoChars159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ret = 0;
        int left = 0;
        Map<String, Integer> map = new HashMap<>(); //每个字符出现的次数
        for (int right = 0; right < s.length(); right++) {
            String r = s.substring(right, right+1);
            map.put(r, map.getOrDefault(r, 0) + 1);
            while (map.size() > 2) {
                String l = s.substring(left, left+1);
                map.put(l, map.get(l) - 1);
                if (map.get(l) == 0) {
                    map.remove(l);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }

    public int lengthOfLongestSubstringTwoDistinct1(String s) {
        if (s == null) {
            return 0;
        }

        int ret = 0;
        int left = 0;
        Map<String, Integer> map = new HashMap<>(); //每个字符最后一次出现的位置
        for (int right = 0; right < s.length(); right++) {
            map.put(s.substring(right, right + 1), right);
            if (map.size() > 2) {
                int lastPos = Collections.min(map.values());
                map.remove(s.substring(lastPos, lastPos+1));
                left = lastPos + 1;
            }
            ret = Math.max(ret, right - left + 1);
        }

        return ret;
    }

    public static void main(String[] args) {
        LongestSubstringTwoChars159 obj = new LongestSubstringTwoChars159();
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct1("eceba"));
    }
}
