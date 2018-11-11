package HashTable;

import java.util.HashMap;
import java.util.Map;

//mark bug
public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        StringBuilder buff = new StringBuilder();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (!map.containsKey(sc)) {
                map.put(sc, tc);
            }
            buff.append(map.get(sc));
        }

        return buff.toString().equals(t);
    }

    public static void main(String[] args) {
        IsomorphicStrings205 obj = new IsomorphicStrings205();
        System.out.println(obj.isIsomorphic("egg", "add"));
        System.out.println(obj.isIsomorphic("foo", "bar"));
    }
}
