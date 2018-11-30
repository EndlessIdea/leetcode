package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses301 {
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, res,  rmL, rmR);
        return new ArrayList<>(res);
    }

    public void dfs(String s, int start, Set<String> res, int rmL, int rmR) {

        if (rmL == 0 && rmR == 0) {
            if (isValid(s)) res.add(s);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(i) == s.charAt(i-1)) continue;

            if (s.charAt(i) == ')' || s.charAt(i) == '(') {
                if (s.charAt(i) == ')' && rmR > 0) {
                    dfs(s.substring(start, i) + s.substring(i+1), start, res, rmL, rmR - 1);
                } else if (s.charAt(i) == '(' && rmL > 0) {
                    dfs(s.substring(start, i) + s.substring(i+1), start, res, rmL - 1, rmR);
                }
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses301 obj = new RemoveInvalidParentheses301();
        List<String> ret = obj.removeInvalidParentheses("(()(");
        System.out.println(ret.toString());
    }
}
