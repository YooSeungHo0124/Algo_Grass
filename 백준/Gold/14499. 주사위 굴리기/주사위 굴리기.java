import java.util.*;
import java.io.*;

public class Main {

    static int N, M, x, y, K;
    static int[][] map;
    static Dice dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        dice = new Dice();

        while (K-- > 0) {
            int direction = Integer.parseInt(st.nextToken());
            if (moveDice(direction)) {
                // 주사위를 굴린 후 윗면의 값을 출력
                System.out.println(dice.getTop());
            }
        }
    }

    // 주사위를 이동시키는 메서드
    public static boolean moveDice(int direction) {
        int newX = x, newY = y;

        switch (direction) {
            case 1: // 동쪽
                newY++;
                break;
            case 2: // 서쪽
                newY--;
                break;
            case 3: // 북쪽
                newX--;
                break;
            case 4: // 남쪽
                newX++;
                break;
            default:
                return false;
        }

        // 이동이 유효한지 확인
        if (newX < 0 || newX >= N || newY < 0 || newY >= M) {
            return false; // 이동 불가
        }

        // 주사위를 굴림
        dice.roll(direction);

        // 이동한 칸의 값을 처리
        if (map[newX][newY] == 0) {
            map[newX][newY] = dice.getBottom(); // 바닥면의 값을 칸에 복사
        } else {
            dice.setBottom(map[newX][newY]); // 칸의 값을 바닥면에 복사
            map[newX][newY] = 0; // 칸의 값을 0으로 설정
        }

        // 현재 위치 업데이트
        x = newX;
        y = newY;
        return true;
    }



    public static class Dice {
        private int[] faces; // 주사위의 면을 저장하는 배열
    
        public Dice() {
            faces = new int[6]; // 모든 면을 0으로 초기화
        }
    
        public void roll(int direction) {
            int[] newFaces = faces.clone(); // 현재 상태를 복사
    
            switch (direction) {
                case 1: // 동쪽
                    newFaces[0] = faces[3]; // Top = West
                    newFaces[1] = faces[0]; // East = Top
                    newFaces[2] = faces[2]; // South = South
                    newFaces[3] = faces[5]; // West = Bottom
                    newFaces[4] = faces[4]; // North = North
                    newFaces[5] = faces[1]; // Bottom = East
                    break;
                case 2: // 서쪽
                    newFaces[0] = faces[1]; // Top = East
                    newFaces[1] = faces[5]; // East = Bottom
                    newFaces[2] = faces[2]; // South = South
                    newFaces[3] = faces[0]; // West = Top
                    newFaces[4] = faces[4]; // North = North
                    newFaces[5] = faces[3]; // Bottom = West
                    break;
                case 3: // 북쪽
                    newFaces[0] = faces[4]; // Top = North
                    newFaces[1] = faces[1]; // East = East
                    newFaces[2] = faces[0]; // South = Top
                    newFaces[3] = faces[3]; // West = West
                    newFaces[4] = faces[5]; // North = Bottom
                    newFaces[5] = faces[2]; // Bottom = South
                    break;
                case 4: // 남쪽
                    newFaces[0] = faces[2]; // Top = South
                    newFaces[1] = faces[1]; // East = East
                    newFaces[2] = faces[5]; // South = Bottom
                    newFaces[3] = faces[3]; // West = West
                    newFaces[4] = faces[0]; // North = Top
                    newFaces[5] = faces[4]; // Bottom = North
                    break;
            }
    
            faces = newFaces; // 상태 업데이트
        }
    
        public int getTop() {
            return faces[0]; // 윗면의 값 반환
        }
    
        public int getBottom() {
            return faces[5]; // 바닥면의 값 반환
        }
    
        public void setBottom(int value) {
            faces[5] = value; // 바닥면의 값을 설정
        }
    }

}

