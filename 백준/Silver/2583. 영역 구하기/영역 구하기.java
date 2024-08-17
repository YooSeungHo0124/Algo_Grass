
import java.util.*;

public class Main {

	    static int M;
	    static int N;
	    static int[][] map;
	    static boolean[][] visit;

	    static int[] dr = {-1, 1, 0, 0};
	    static int[] dc = {0, 0, -1, 1};

	    static int cnt = 0;

	    public static void main(String[] args) {

	        Scanner s = new Scanner(System.in);
	        M = s.nextInt();
	        N = s.nextInt();
	        int K = s.nextInt();

	        map = new int[M][N];
	        visit = new boolean[M][N];

	        // 맵 설정
	        for (int k = 0; k < K; k++) {
	            int x1 = s.nextInt();
	            int y1 = s.nextInt();
	            int x2 = s.nextInt();
	            int y2 = s.nextInt();
	            for (int r = y1; r < y2; r++) {
	                for (int c = x1; c < x2; c++) {
	                    map[r][c]++;
	                }
	            }
	        }

	        ArrayList<Integer> arr = new ArrayList<>();
	        for (int i = 0; i < M; i++) {
	            for (int j = 0; j < N; j++) {
	                if (map[i][j] == 0 && !visit[i][j]) {
	                    cnt = 1;  // 시작점 포함 , 초기화
	                    dfs(i, j);
	                    arr.add(cnt);
	                }
	            }
	        }

	        arr.sort(Comparator.naturalOrder());
	        System.out.println(arr.size());
	        for (int area : arr) {
	            System.out.print(area + " ");
	        }
	    }

	    static void dfs(int x, int y) {
	        visit[x][y] = true;
	        for (int k = 0; k < 4; k++) {
	            int nr = x + dr[k];
	            int nc = y + dc[k];

	            if (0 <= nr && nr < M && 0 <= nc && nc < N 
	                && map[nr][nc] == 0 && !visit[nr][nc]) {
	                cnt++;
	                dfs(nr, nc);
	            }
	        }
	    }
	}
