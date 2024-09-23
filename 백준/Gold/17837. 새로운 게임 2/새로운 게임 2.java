import java.util.*;

public class Main {
    static int N, K;
    static int[][] colorMap;             // 체스판 색 정보 (흰, 빨, 파)
    static int[][] coords;               // 각 말의 좌표와 방향
    static ArrayList<Integer>[][] map;   // 각 칸에 쌓인 말들 정보

    static int[] dx = {0, 0, 0, -1, 1};  // 1~4번 : 우, 좌, 상, 하
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();  // 체스판 크기
        K = s.nextInt();  // 말의 개수

        // colorMap : 체스판 색 정보
        colorMap = new int[N + 2][N + 2];
        for (int i = 0; i <= N + 1; i++) {
            Arrays.fill(colorMap[i], 2);  // 파란색 벽 패딩
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                colorMap[i][j] = s.nextInt();
            }
        }

        // map : 각 칸에 쌓인 말들의 정보
        map = new ArrayList[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        // coords : 각 말의 좌표와 방향 정보
        coords = new int[K + 1][3];
        for (int i = 1; i <= K; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            int dir = s.nextInt();
            coords[i][0] = x;  // x좌표
            coords[i][1] = y;  // y좌표
            coords[i][2] = dir; // 방향
            map[x][y].add(i);   // 해당 좌표에 말 추가
        }

        // 최대 1000턴 진행
        for (int turn = 1; turn <= 1000; turn++) {
            for (int i = 1; i <= K; i++) {
                if (moveHorse(i)) { // 종료
                    System.out.println(turn);
                    return;
                }
            }
        }

        // 1000번 안에 끝나지 않으면 -1 출력
        System.out.println(-1);
    }

    // 말을 이동시키고 게임이 끝났는지 여부 반환
    static boolean moveHorse(int horse) {
        int x = coords[horse][0];
        int y = coords[horse][1];
        int dir = coords[horse][2];

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 파란색이거나 벽을 만난 경우
        if (colorMap[nx][ny] == 2) {
            dir = reverseDirection(dir);  // 방향 반대로 변경
            coords[horse][2] = dir;       // 방향 업데이트
            nx = x + dx[dir];
            ny = y + dy[dir];
            if (colorMap[nx][ny] == 2) { // 반대 방향으로 갔는데 파란색인 경우 : 이동 x
                return false;  
            }
        }

        // movingHorese : 이동 할 말들, 나머지는 제자리
        ArrayList<Integer> movingHorses = new ArrayList<>();
        int index = map[x][y].indexOf(horse);
        for (int i = index; i < map[x][y].size(); i++) {  // 위에 있는 말들은 다 데려간다
            movingHorses.add(map[x][y].get(i));
        }
        for (int h : movingHorses) {
            map[x][y].remove((Integer) h);
        }

        // 이동할 칸이 흰색인 경우
        if (colorMap[nx][ny] == 0) {
            map[nx][ny].addAll(movingHorses);
        }
        // 빨간색인 경우 순서를 뒤집어서 쌓기
        else if (colorMap[nx][ny] == 1) {
            Collections.reverse(movingHorses);
            map[nx][ny].addAll(movingHorses);
        }

        // 이동한 말들의 좌표 업데이트
        for (int h : movingHorses) {
            coords[h][0] = nx;
            coords[h][1] = ny;
        }

        // 말이 4개 이상 쌓인 경우 종료
        return map[nx][ny].size() >= 4;
    }

    // 방향 반대로 변경
    static int reverseDirection(int dir) {
        if (dir == 1) return 2; 
        if (dir == 2) return 1;  
        if (dir == 3) return 4;  
        if (dir == 4) return 3;  
        return dir;
    }
}
