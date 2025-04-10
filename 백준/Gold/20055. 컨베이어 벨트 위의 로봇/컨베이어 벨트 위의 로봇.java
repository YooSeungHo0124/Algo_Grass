import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        LinkedList<Integer> belt = new LinkedList<>();
        for (int i = 0; i < 2 * N; i++) {
            belt.add(sc.nextInt());
        }
        Deque<Integer> robots = new LinkedList<>();
        int step = 0;
        
        while (true) {
            step++;
            // Step A: 회전 및 로봇 위치 업데이트
            belt.addFirst(belt.removeLast());
            Deque<Integer> newRobots = new LinkedList<>();
            for (int pos : robots) {
                int newPos = (pos + 1) % (2 * N);
                if (newPos != N - 1) {
                    newRobots.add(newPos);
                }
            }
            robots = newRobots;
            
            // Step B: 로봇 이동
            Deque<Integer> movedRobots = new LinkedList<>();
            Set<Integer> occupied = new HashSet<>();
            for (int robotPos : robots) {
                int nextPos = (robotPos + 1) % (2 * N);
                if (!occupied.contains(nextPos) && belt.get(nextPos) >= 1) {
                    if (nextPos == N - 1) {
                        belt.set(nextPos, belt.get(nextPos) - 1);
                    } else {
                        movedRobots.add(nextPos);
                        occupied.add(nextPos);
                        belt.set(nextPos, belt.get(nextPos) - 1);
                    }
                } else {
                    movedRobots.add(robotPos);
                    occupied.add(robotPos);
                }
            }
            robots = movedRobots;
            
            // Step C: 새 로봇 추가
            if (belt.get(0) >= 1) {
                robots.add(0);
                belt.set(0, belt.get(0) - 1);
            }
            
            // Step D: 내구도 검사
            int count = 0;
            for (int d : belt) {
                if (d == 0) count++;
            }
            if (count >= K) break;
        }
        System.out.println(step);
    }
}
