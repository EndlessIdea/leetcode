package DFS;

import java.util.HashMap;
import java.util.Map;

public class RobotRoomCleaner489 {
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();
        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();
        public void turnRight();
        // Clean the current cell.
        public void clean();
    }

    public void cleanRoom(Robot robot) {
        Map<String, Boolean> map = new HashMap<>();
        int direction = 0;
        dfs(robot, direction, 0, 0, map);
    }

    private void dfs(Robot robot, int direction, int x, int y, Map<String, Boolean> map) {
        String pos = x + "_" + y;
        if (map.containsKey(pos)) {
            return;
        }

        robot.clean();
        map.put(pos, true);

        for (int i = 0; i <= 3; i++) {
            if (robot.move()) {
                int nextDirection = (direction + i) % 4;
                switch (nextDirection) {
                    case 0:
                        x -= 1;
                        break;
                    case 1:
                        y += 1;
                        break;
                    case 2:
                        x += 1;
                        break;
                    case 3:
                        y -= 1;
                        break;
                }
                dfs(robot, nextDirection, x, y, map);
            } else {
                robot.turnRight();
            }
        }
    }

    private void dfs1(Robot robot, int direction, int x, int y, Map<String, Boolean> map) {
        String pos = x + "_" + y;
        if (map.containsKey(pos)) {
            return;
        }

        robot.clean();
        map.put(pos, true);

        for (int i = 0; i <= 3; i++) {
            if (robot.move()) {
                int nx = x, ny = y;
                int nextDirection = (direction + i) % 4;
                switch (nextDirection) {
                    case 0:
                        nx -= 1;
                        break;
                    case 1:
                        ny += 1;
                        break;
                    case 2:
                        nx += 1;
                        break;
                    case 3:
                        ny -= 1;
                        break;
                }
                dfs(robot, nextDirection, nx, ny, map);

                //go back
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }

            robot.turnRight();
        }
    }
 
    public static void main(String[] args) {

    }
}
