

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] gears = new int[4][8];

        // 톱니바퀴 상태 입력
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = input.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1; // 0-indexed
            int direction = Integer.parseInt(st.nextToken());

            // 회전 로직
            rotateGears(gears, gearNum, direction);
        }

        // 점수 계산
        int score = calculateScore(gears);
        System.out.println(score);
    }

    private static void rotateGears(int[][] gears, int gearNum, int direction) {
        // 회전할 톱니바퀴의 상태를 저장
        int[] rotations = new int[4];
        rotations[gearNum] = direction;

        // 왼쪽 톱니바퀴 회전 결정
        for (int i = gearNum; i > 0; i--) {
            if (gears[i][6] != gears[i - 1][2]) { // 6시 방향과 2시 방향 비교
                rotations[i - 1] = -rotations[i]; // 반대 방향으로 회전
            } else {
                break; // 더 이상 회전하지 않음
            }
        }

        // 오른쪽 톱니바퀴 회전 결정
        for (int i = gearNum; i < 3; i++) {
            if (gears[i][2] != gears[i + 1][6]) { // 2시 방향과 6시 방향 비교
                rotations[i + 1] = -rotations[i]; // 반대 방향으로 회전
            } else {
                break; // 더 이상 회전하지 않음
            }
        }

        // 톱니바퀴 회전 적용
        for (int i = 0; i < 4; i++) {
            if (rotations[i] != 0) {
                rotate(gears[i], rotations[i]);
            }
        }
    }

    // 톱니바퀴 회전 메서드
    private static void rotate(int[] gear, int direction) {
        if (direction == 1) { // 시계 방향
            int temp = gear[7];
            for (int i = 7; i > 0; i--) {
                gear[i] = gear[i - 1];
            }
            gear[0] = temp;
        } else { // 반시계 방향
            int temp = gear[0];
            for (int i = 0; i < 7; i++) {
                gear[i] = gear[i + 1];
            }
            gear[7] = temp;
        }
    }

    private static int calculateScore(int[][] gears) {
        int score = 0;
        if (gears[0][0] == 1) score += 1;
        if (gears[1][0] == 1) score += 2;
        if (gears[2][0] == 1) score += 4;
        if (gears[3][0] == 1) score += 8;
        return score;
    }
}
