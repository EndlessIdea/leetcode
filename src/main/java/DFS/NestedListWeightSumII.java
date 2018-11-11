package DFS;

import java.util.ArrayList;
import java.util.List;

/*
    364. Nested List Weight Sum II
    Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

    Each element is either an integer, or a list -- whose elements may also be integers or other lists.

    Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

    Example 1:
    Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

    Example 2:
    Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0;
        if (nestedList == null || nestedList.size() == 0) {
            return res;
        }

        List<List<Integer>> record = new ArrayList<List<Integer>>();
        dfs(nestedList, record, 0);
        for (int i = 0; i < record.size(); i++) {
            int lv = record.size() - i;
            for (int j = 0; j < record.get(i).size(); j++) {
                res += record.get(i).get(j) * lv;
            }
        }

        return res;
    }

    private void dfs(List<NestedInteger> nestedList, List<List<Integer>> record, int depth) {
        for (int i = 0; i < nestedList.size(); i++) {
            if (nestedList.get(i).isInteger()) {
                if (record.size() < depth + 1 || record.get(depth) == null) {
                    record.add(depth, new ArrayList<Integer>());
                }
                record.get(depth).add(nestedList.get(i).getInteger());
            } else {
                dfs(nestedList.get(i).getList(), record, depth + 1);
            }
        }
    }
}
