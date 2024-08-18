
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] map;
    static int[] dm = { -1, 1, 0, 0, 0, 0 };
    static int[] dn = { 0, 0, -1, 1, 0, 0 };
    static int[] dh = { 0, 0, 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        M = s.nextInt();
        N = s.nextInt();
        H = s.nextInt();

        map = new int[H][N][M];
        Queue<int[]> q = new LinkedList<>();
        boolean zeroExist = false;

        
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    map[h][n][m] = s.nextInt();       // map 에 토마토 정보 넣기
                    if (map[h][n][m] == 1) {
                        q.offer(new int[]{h, n, m});  // 익은 토마토 정보 넣기
                    } else if (map[h][n][m] == 0) {
                        zeroExist = true;
                    }
                }
            }
        }

        if (!zeroExist) {
            System.out.println(0);
            return;
        }

        
        int days = bfs(q);

        
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (map[h][n][m] == 0) {       // 0 즉 안익은 토마토 있으면 -> -1 출력
                        System.out.println(-1);    
                        return;
                    }
                }
            }
        }

        System.out.println(days);    // 며칠 걸리는지 depth 역할
    }

    static int bfs(Queue<int[]> q) {
        int days = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            days++;

            for (int i = 0; i < size; i++) {
                int[] current = q.poll();     //   q.offer(new int[]{h, n, m}) 위에서 넣었던 정보 빼내기 
                int z = current[0], y = current[1], x = current[2];

                for (int k = 0; k < 6; k++) {
                    int nz = z + dh[k];
                    int ny = y + dn[k];
                    int nx = x + dm[k];
                    
                    
                    // 안익은 것 찾아서, 익었다고 표기하고, offer 하기
                    if (0 <= nz && nz < H && 0 <= ny && ny < N && 0 <= nx && nx < M && map[nz][ny][nx] == 0) {
                        map[nz][ny][nx] = 1; 
                        q.offer(new int[]{nz, ny, nx});
                    }
                }
            }
        }

        return days;
    }
}
