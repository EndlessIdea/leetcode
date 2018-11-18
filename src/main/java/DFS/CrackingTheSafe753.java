package DFS;

import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe753 {
    Set<String> seen;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; ++i)
            sb.append("0");
        String start = sb.toString();

        dfs(start, k);
        ans.append(start);
        return new String(ans);
    }

    public void dfs(String node, int k) {
        for (int x = 0; x < k; ++x) {
            String nei = node + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                System.out.printf("start %s\n", nei);
                dfs(nei.substring(1), k);
                ans.append(x);
                System.out.printf("end %s\n", ans.toString());
            }
        }
    }

    public static void main(String[] args) {
        CrackingTheSafe753 obj = new CrackingTheSafe753();
        obj.crackSafe(3, 3);
    }
}
