package DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph133 {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
         return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return  dfsClone(map, node);
    }

    private UndirectedGraphNode dfsClone(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node) {
         if (map.containsKey(node)) {
             return map.get(node);
         }
         UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
         map.put(node, cloneNode);
         for (UndirectedGraphNode subNode : node.neighbors) {
             cloneNode.neighbors.add(dfsClone(map, subNode));
         }
         return map.get(node);
    }

    public static void main(String[] args) {

    }
}
