package dp;

//568
/*
LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules and restrictions you need to follow.

Rules and restrictions:
You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical), called flights representing the airline status from the city i to the city j. If there is no flight from the city i to the city j, flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and can only take flights on each week's Monday morning. Since flight time is so short, we don't consider the impact of flight time.
For each city, you can only have restricted vacation days in different weeks, given an N*K matrix called days representing this relationship. For the value of days[i][j], it represents the maximum days you could take vacation in the city i in the week j.
You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.

Example 1:
Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
Output: 12
Explanation:
Ans = 6 + 3 + 3 = 12.

One of the best strategies is:
1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
(Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
3rd week : stay at city 2, and play 3 days and work 4 days.
 */
public class MaximumVacationDays {
    public static int maxVacationDays(int[][] flights, int[][] days) {
        if (flights.length == 0 || days.length == 0) {
            return 0;
        }
        int[][] memory = new int[flights.length][days[0].length];

//        return dfs(flights, days, 0, 0);
//        return memorizeDfs(flights, days, memory, 0, 0);
        return dp(flights, days);
    }

    public static int dfs(int[][] flights, int[][] days, int curCity, int curWeek) {
        if (curWeek == days[0].length) {
            return 0;
        }

        int max = 0;
        for (int city = 0; city < flights.length; city++) {
            if (flights[curCity][city] == 1 || curCity == city) {
                max = Math.max(max, days[city][curWeek] + dfs(flights, days, city, curWeek + 1));
            }
        }

        return max;
    }

    public static int memorizeDfs(int[][] flights, int[][] days, int[][] memory, int curCity, int curWeek) {
        if (memory[curCity][curWeek] != 0) {
            return memory[curCity][curWeek];
        }

        int max = 0;
        for (int city = 0; city < flights.length; city++) {
            if (flights[curCity][city] == 1 || curCity == city) {
                max = Math.max(max, days[city][curWeek] + dfs(flights, days, city, curWeek + 1));
            }
        }
        memory[curCity][curWeek] = max;

        return max;
    }

    public static int dp(int[][] flights, int[][] days) {
        int[][] counts = new int[flights.length][days[0].length + 1];

        for (int i = 0; i < flights.length; i++) {
            for (int j = 1; j <= days[0].length; j++) {
                for (int k = 0; k < flights.length; k++) {
                    if (k == i || flights[i][k] == 1) {
                        counts[i][j] = Math.max(counts[i][j], counts[k][j-1] + days[i][j-1]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < flights.length; i++) {
            max = Math.max(max, counts[i][days[0].length]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] flights = {{0,1,1},{1,0,1},{1,1,0}};
        int[][] days = {{1,3,1},{6,0,3},{3,3,3}};
        int ret = maxVacationDays(flights, days);
        System.out.println(ret);
    }
}
