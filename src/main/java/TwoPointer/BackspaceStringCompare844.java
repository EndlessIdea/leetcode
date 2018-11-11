package TwoPointer;

public class BackspaceStringCompare844 {
    public boolean backspaceCompare(String S, String T) {
        StringBuilder bs = new StringBuilder();
        StringBuilder bt = new StringBuilder();

        String fs = filter(S);
        String ft = filter(T);

        return fs.equals(ft);
    }

    public String filter(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#' && b.length() > 0) {
                b.deleteCharAt(b.length() - 1);
            } else if (c != '#') {
                b.append(c);
            }
        }
        return b.toString();
    }

    public String filterBug(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#' && b.length() > 0) {
                b.deleteCharAt(b.length() - 1);
            } else {
                b.append(c);
            }
        }
        return b.toString();
    }

    public boolean backspaceCompare1(String S, String T) {
        int is = S.length() - 1;
        int it = T.length() - 1;
        while (true) {
            for (int backCnt = 0; is >= 0 && (backCnt > 0 || S.charAt(is) == '#'); is--) {
                backCnt += S.charAt(is) == '#' ? 1 : -1;
            }
//            for (int backCnt = 0; it >= 0 && (backCnt > 0 || S.charAt(it) == '#'); it--) { S.charAt bug
            for (int backCnt = 0; it >= 0 && (backCnt > 0 || T.charAt(it) == '#'); it--) {
                backCnt += T.charAt(it) == '#' ? 1 : -1;
            }
            if (is >= 0 && it >= 0 && S.charAt(is) == T.charAt(it)) {
                is--;
                it--;
            } else {
                return is == -1 && it == -1;
            }
        }
    }

    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (true) {
            for (int back = 0; i >= 0 && (back > 0 || S.charAt(i) == '#'); --i)
                back += S.charAt(i) == '#' ? 1 : -1;
            for (int back = 0; j >= 0 && (back > 0 || T.charAt(j) == '#'); --j)
                back += T.charAt(j) == '#' ? 1 : -1;
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--; j--;
            } else
                return i == -1 && j == -1;
        }
    }

    public static void main(String[] args) {
        BackspaceStringCompare844 obj = new BackspaceStringCompare844();
        System.out.println(obj.backspaceCompare("y#fo##f", "y#f#o##f"));
    }
}
