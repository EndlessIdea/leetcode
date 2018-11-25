package DFS;

import java.util.*;

public class ReconstructItinerary332 {
    static String departure = "JFK";
    static int trips = 0;

    public List<String> findItinerary(String[][] tickets) {
        List<String> ret = new ArrayList<>();
        //check
        if (tickets == null || tickets.length == 0) {
            return ret;
        }
        trips = tickets.length;

        //create graph
        Map<String, List<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(end);
        }

        //sort graph
        for (String city : map.keySet()) {
            Collections.sort(map.get(city));
        }

        //from JFK to search
        dfs(map, departure, ret);

        return ret;
    }

    private boolean dfs(Map<String, List<String>> map, String start, List<String> ret) {
        ret.add(start);
        if (ret.size() >= trips + 1) {
            return true;
        }
//        if (map.get(start).size() == 0) {
        if (!map.containsKey(start) || map.get(start).isEmpty()) {
            return false;
        }

//        for (String dest : map.get(start)) {
//            dfs(map, dest, ret);
//        }
        List<String> dests = map.get(start);
        for (int i = 0; i < dests.size(); i++) {
            String dest = dests.remove(i);
            if (dfs(map, dest, ret)) {
                return true;
            }
            ret.remove(ret.size() - 1);
            dests.add(i, dest);
        }
//        return true; //bug
        return false;
    }

    public static List<String> findItinerary1(String[][] tickets) {
        // construct graph
        trips = tickets.length;
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        ArrayList<String> al = null;
        for (String[] ticket : tickets) {
            if (!graph.containsKey(ticket[0])) {
                graph.put(ticket[0], new ArrayList<>());
            }
            graph.get(ticket[0]).add(ticket[1]);
        }
        for (ArrayList<String> curr : graph.values()) {
            Collections.sort(curr);
        }
        ArrayList<String> ans = new ArrayList<>();
        itineraryHelper("JFK", ans, graph);
        return ans;
    }

    // n is how many stops totally should contain
    public static boolean itineraryHelper(String curr, List<String> ans, HashMap<String, ArrayList<String>> graph) {
        ans.add(curr);
        if (ans.size() >= trips+1) {
            return true;
        }
        if (!graph.containsKey(curr) || graph.get(curr).isEmpty()) {
            return false;
        }
        ArrayList<String> arrivals = graph.get(curr);
        for (int i = 0; i < arrivals.size(); i++) { // iterate each arrival point
            String arrival = arrivals.remove(i);
            if (itineraryHelper(arrival, ans, graph)) {
                return true;
            }
            ans.remove(ans.size() - 1); // backtrack
            arrivals.add(i, arrival);
        }
        return false;
    }

    public static void main(String[] args) {
        
        ReconstructItinerary332 obj = new ReconstructItinerary332();
        String[][] tickets = new String[][] {
                {"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},{"ANU","JFK"},{"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},{"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}
        };
        System.out.println(tickets.length);
        List<String> ret = obj.findItinerary(tickets);
        System.out.println(ret);
        System.out.println(ret.size());
    }
}
