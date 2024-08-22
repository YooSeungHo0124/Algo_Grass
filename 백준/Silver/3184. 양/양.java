import java.util.*;

public class Main {
	    static int R, C;
	    static char[][] map;
	    static boolean[][] visit;
	    
	    static int[] dr = {-1, 1, 0, 0};
	    static int[] dc = {0, 0, -1, 1};
	    
	    static int totalSheep = 0;
	    static int totalWolves = 0;

	    public static void main(String[] args) {
	        Scanner s = new Scanner(System.in);
	        R = s.nextInt();
	        C = s.nextInt();
	        s.nextLine();
	        
	        map = new char[R][C];
	        visit = new boolean[R][C];
	        
	        for (int r = 0; r < R; r++) {
	            String str = s.nextLine();
	            for (int c = 0; c < C; c++) {
	                map[r][c] = str.charAt(c);
	            }
	        }
	        
	        for (int i = 0; i < R; i++) {
	            for (int j = 0; j < C; j++) {
	                if (map[i][j] != '#' && !visit[i][j]) {
	                    int[] result = dfs(i, j);
	                    int sheep = result[0];
	                    int wolves = result[1];
	                    if (wolves >= sheep) {
	                        totalWolves += wolves;
	                    } else {
	                        totalSheep += sheep;
	                    }
	                }
	            }
	        }
	        
	        System.out.println(totalSheep + " " + totalWolves);
	    }
	    
	    static int[] dfs(int r, int c) {
	        visit[r][c] = true;
	        
	        int sheep = 0;
	        int wolves = 0;
	        
	        if (map[r][c] == 'o') sheep++;
	        if (map[r][c] == 'v') wolves++;
	        
	        for (int k = 0; k < 4; k++) {
	            int nr = r + dr[k];
	            int nc = c + dc[k];
	            
	            if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != '#' && !visit[nr][nc]) {
	                int[] result = dfs(nr, nc);
	                sheep += result[0];
	                wolves += result[1];
	            }
	        }
	        
	        return new int[]{sheep, wolves};
	    }
	}
