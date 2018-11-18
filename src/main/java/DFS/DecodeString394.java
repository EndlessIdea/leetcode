package DFS;

import java.util.Stack;

public class DecodeString394 {
    class Sign {
        String s;
        int num;

        public Sign(String s, int num) {
            this.s = s;
            this.num = num;
        }
    }

    public String decodeString(String s) {
        if (s == null || s.equals("")) {
            return s;
        }

        //遇到数字等中括号
        String ret = "";
        Stack<Sign> stack = new Stack<>();
        int num = -1;
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                //bug ignore str that is before this num
                if (buff.length() > 0) {
                    stack.push(new Sign(buff.toString(), 0));
                    buff.delete(0, buff.length());
                }
                if (num > 0) {
                    num = num * 10 + (s.charAt(i) - '0');
                } else {
                    num = (s.charAt(i) - '0');
                }
            } else if (s.charAt(i) == '[') {
                if (num > 0) {
                    stack.push(new Sign("", num));
                    num = -1;
                }
            } else if (s.charAt(i) == ']') {
                stack.push(new Sign(buff.toString(), 0));
                buff.delete(0, buff.length());
                String tmp = "";
                while (!stack.empty()) {
                    Sign top = stack.pop();
                    if (top.num > 0) {
                        stack.push(top);
                        break;
                    } else {
                        top.s += tmp;
                        tmp = top.s;
                    }
                }

                String sum = "";
                Sign k = stack.pop();
                for (int j = 0; j < k.num; j++) {
                    sum += tmp;
                }
                if (stack.empty()) {
                    ret += sum;
                } else {
                    stack.push(new Sign(sum, 0));
                }
            } else {
                if (stack.empty()) {
                    ret += s.charAt(i);
                } else {
                    buff.append(s.charAt(i));
                }
            }
        }

        if (!stack.empty()) {
            ret += stack.pop().s;
        }

        return ret;
    }

    class DecodeRet {
        int pos;
        String str;
        public DecodeRet(int pos) {
            this.pos = pos;
            this.str = "";
        }
    }

    public String decodeString1(String s) {
        return decodeHelper(s, 0).str;
    }

    private DecodeRet decodeHelper(String s, int pos) {
        DecodeRet ret = new DecodeRet(pos);
        int num = 0;
        while (pos < s.length()) {
            if (s.charAt(pos) >= '0' && s.charAt(pos) <= '9') {
                num = num * 10 + s.charAt(pos) - '0';
            } else if (s.charAt(pos) == '[') {
                DecodeRet next = decodeHelper(s, ++pos);
                for (int i = 0; i < num; i++) {
                    ret.str += next.str;
                }
                num = 0;
                pos = next.pos;
            } else if (s.charAt(pos) == ']') {
                ret.pos = pos;
                return ret;
            } else {
                ret.str += s.charAt(pos);
            }
            pos++;
        }
        return ret;
    }

    public static void main(String[] args) {
        DecodeString394 obj = new DecodeString394();
        System.out.println(obj.decodeString1("3[a]2[bc]"));
        System.out.println(obj.decodeString1("3[a2[c]]"));
        System.out.println(obj.decodeString1("2[abc]3[cd]ef"));
    }
}
