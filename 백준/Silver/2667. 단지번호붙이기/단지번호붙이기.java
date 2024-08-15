

import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] visit;

    static int N;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();

        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] c = s.next().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = Character.getNumericValue(c[j]);
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] != 0 && !visit[i][j]) {
                    int cnt = dfs(i, j, 0);  // 시작할 때 cnt 0
                    arr.add(cnt);  // cnt 끝난 값 추가
                }
            }
        }
        arr.sort(Comparator.naturalOrder());
        System.out.println(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

    }

    static int dfs(int x, int y, int cnt) {
        visit[x][y] = true;
        cnt++;  // 현재 집을 카운트

        for (int k = 0; k < 4; k++) {
            int nr = x + dr[k];
            int nc = y + dc[k];

            if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] != 0 && !visit[nr][nc]) {
                cnt = dfs(nr, nc, cnt);
            }
        }
        return cnt;
    }

}
