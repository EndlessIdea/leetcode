package TwoPointer;

import java.util.*;

public class LongestWordInDict524 {
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) {
            return "";
        }

        HashMap<Integer, List<String>> map = new HashMap<>();
        for (String sub : d) {
            if (isMatch(s, sub)) {
                List<String> l = map.getOrDefault(sub.length(), new ArrayList<>());
                l.add(sub);
                map.put(sub.length(), l);
            }
        }

        if (map.size() == 0) {
            return "";
        }

        int max = Collections.max(map.keySet());
        List<String> ret = map.get(max);
        if (ret.size() == 1) {
            return ret.get(0);
        }

        ret.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len = Math.min(o1.length(), o2.length());
                for (int i = 0; i < len; i++) {
                    if (o1.charAt(i) != o2.charAt(i)) {
                        return o1.charAt(i) - o2.charAt(i);
                    } else {
                        continue;
                    }
                }
                return 0;
            }
        });

        return ret.get(0);
    }

    public String findLongestWord1(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) {
            return "";
        }

        String ret = "";
        int max = 0;
        for (String sub : d) {
            if (isMatch(s, sub)) {
                if (sub.length() > max) {
                    max = sub.length();
                    ret = sub;
                } else if (sub.length() == max && ret.compareTo(sub) > 0) {
                    ret = sub;
                }
            }
        }

        return ret;
    }

    public boolean isMatch(String s, String t) {
        int i = 0, j = 0;
        for ( ; i < s.length() && j < t.length(); ) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return j == t.length();
    }

    public static void main(String[] args) {
        LongestWordInDict524 obj = new LongestWordInDict524();
        List<String> l1 = new ArrayList<>();
        l1.add("ale");
        l1.add("apple");
        l1.add("monkey");
        l1.add("plea");
        System.out.println(obj.findLongestWord1("abpcplea", l1));
        List<String> l2 = new ArrayList<>();
        l2.add("a");
        l2.add("b");
        l2.add("c");
        System.out.println(obj.findLongestWord1("abpcplea", l2));
    }
}
