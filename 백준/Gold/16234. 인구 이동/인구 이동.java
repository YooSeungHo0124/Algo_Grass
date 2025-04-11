import java.util.*;
import java.io.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static int days = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean moved = false;
            boolean[][] visited = new boolean[N][N];
            List<List<int[]>> unions = new ArrayList<>();

            // 1. 모든 국가 탐색하며 연합 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<int[]> union = new ArrayList<>();
                        int sum = bfs(i, j, visited, union); // BFS로 연합 찾기
                        if (union.size() > 1) {
                            moved = true;
                            int avg = sum / union.size();
                            unions.add(union);
                        }
                    }
                }
            }

            // 2. 모든 연합의 인구 동시 업데이트
            for (List<int[]> union : unions) {
                int sum = 0;
                for (int[] pos : union) {
                    sum += map[pos[0]][pos[1]];
                }
                int avg = sum / union.size();
                for (int[] pos : union) {
                    map[pos[0]][pos[1]] = avg;
                }
            }

            if (!moved) break;
            days++;
        }

        System.out.println(days);
    }

    static int bfs(int x, int y, boolean[][] visited, List<int[]> union) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        union.add(new int[]{x, y});
        int sum = map[x][y];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(map[current[0]][current[1]] - map[nx][ny]);
                    if (diff >= L && diff <= R) {
                        visited[nx][ny] = true;
                        union.add(new int[]{nx, ny});
                        sum += map[nx][ny];
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return sum;
    }
}