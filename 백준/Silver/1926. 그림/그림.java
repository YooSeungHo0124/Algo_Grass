import java.util.*;
public class Main {
	
	static int[][] map;
	static boolean[][] visit;
	static int N;
	static int M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int cnt =1;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j]=s.nextInt();
			}
		}
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==1 && !visit[i][j]) {
					dfs(i,j);	
					arr.add(cnt);
					cnt=1;
				}
			}
		}
		if (arr.size()>0) {
			
		arr.sort(Comparator.reverseOrder());
		System.out.println(arr.size());
		System.out.println(arr.get(0));
		}
		else {
			System.out.println(0);
			System.out.println(0);
		}
	}
	
	
	static void dfs(int x, int y) {
		visit[x][y]=true;
		for (int k=0; k<4; k++) {
			int nr = x+dr[k];
			int nc = y+dc[k];
			
			if (0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]==1 && !visit[nr][nc] ) {
				dfs(nr,nc);
				cnt++;
			}
		}
	}
}
