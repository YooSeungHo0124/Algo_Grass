import java.util.*;
import java.io.*;

public class Main {
    static class CCTV {
        int x, y, type;
        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static int N, M, minBlindSpot;
    static int[][] map;
    static List<CCTV> cctvList;
    static int[][][] dirs = {
        {}, // 0번
        { {0}, {1}, {2}, {3} }, // 1번: 4방향
        { {0, 2}, {1, 3} }, // 2번: 2방향
        { {0, 1}, {1, 2}, {2, 3}, {3, 0} }, // 3번: 4방향
        { {0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1} }, // 4번: 4방향
        { {0, 1, 2, 3} } //5번: 1방향
    };
    static int[] dx = { -1, 0, 1, 0 }; // 상, 우, 하, 좌 (시계방향)
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        minBlindSpot = Integer.MAX_VALUE;
        dfs(0, map);
        System.out.println(minBlindSpot);
    }

    static void dfs(int idx, int[][] currentMap) {
        if (idx == cctvList.size()) {
            int blindSpot = calculateBlindSpot(currentMap);
            minBlindSpot = Math.min(minBlindSpot, blindSpot);
            return;
        }

        CCTV cctv = cctvList.get(idx);
        int type = cctv.type;

        for (int[] dir : dirs[type]) {
            int[][] newMap = copyMap(currentMap);
            for (int d : dir) {
                watch(cctv.x, cctv.y, d, newMap);
            }
            dfs(idx + 1, newMap);
        }
    }

    static void watch(int x, int y, int d, int[][] newMap) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
            if (newMap[nx][ny] == 6) break;
            if (newMap[nx][ny] == 0) newMap[nx][ny] = -1; // 감시 영역 표시
            nx += dx[d];
            ny += dy[d];
        }
    }

    static int calculateBlindSpot(int[][] currentMap) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (currentMap[i][j] == 0) count++;
            }
        }
        return count;
    }

    static int[][] copyMap(int[][] original) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(original[i], 0, newMap[i], 0, M);
        }
        return newMap;
    }
}