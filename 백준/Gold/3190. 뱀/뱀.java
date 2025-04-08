import java.util.*;
import java.io.*;

public class Main {
    static int n, k, l;
    static int[][] board;
    static Map<Integer, Character> dirChanges = new HashMap<>();
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상 (시계방향)
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1]; // 1-based indexing
        
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1; // 사과 위치 표시
        }
        
        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            dirChanges.put(x, c);
        }
        
        System.out.println(simulate());
    }
    
    static int simulate() {
        Deque<Position> snake = new LinkedList<>();
        snake.add(new Position(1, 1));
        board[1][1] = 2; // 뱀의 몸은 2로 표시
        
        int dir = 0; // 초기 방향: 오른쪽
        int time = 0;
        
        while (true) {
            time++;
            
            // 머리 이동
            Position head = snake.peekFirst();
            int nx = head.x + dx[dir];
            int ny = head.y + dy[dir];
            
            // 벽이나 자기 몸에 부딪히면 종료
            if (nx <= 0 || nx > n || ny <= 0 || ny > n || board[nx][ny] == 2) {
                return time;
            }
            
            // 머리 이동
            snake.addFirst(new Position(nx, ny));
            
            // 사과가 없으면 꼬리 이동
            if (board[nx][ny] != 1) {
                Position tail = snake.removeLast();
                board[tail.x][tail.y] = 0;
            }
            
            board[nx][ny] = 2; // 새로운 머리 위치 표시
            
            // 방향 전환 확인
            if (dirChanges.containsKey(time)) {
                char c = dirChanges.get(time);
                if (c == 'L') {
                    dir = (dir + 3) % 4; // 왼쪽 90도 회전
                } else {
                    dir = (dir + 1) % 4; // 오른쪽 90도 회전
                }
            }
        }
    }
    
    static class Position {
        int x, y;
        
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}