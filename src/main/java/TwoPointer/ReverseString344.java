package TwoPointer;

public class ReverseString344 {
    public String reverseString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }

        return new String(chars);
//        return chars.toString();
    }

    public static void main(String[] args) {
        ReverseString344 obj = new ReverseString344();
        System.out.println(obj.reverseString("A man, a plan, a canal: Panama"));
    }
}
