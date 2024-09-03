/*
 * 다익스트라를 이용해서도 풀수 있지만
 * -> 경로 가중치가 오직 0 또는 1이라면 deque 을 이용해서 0-1 bfs 문제로 판단하고 풀수 있으면 좋다.
 * 
 * 아직 모르니까 그냥 다익스트라 써야겠다..
 * 
 * https://kwin0825.tistory.com/125 
 * 0-1 bfs 설명 사이트
 * */

import java.util.*;

public class Main {

	static class edge{
		int x;
		int y;
		int w;
		edge(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
	static int[][] graph;
	static int[][] distance;
	static int N,M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		M = s.nextInt();
		N = s.nextInt();
		graph = new int[N][M];
		for (int i=0; i<N; i++) {
			char[] c = s.next().toCharArray();
			for (int j=0; j<M; j++) {
				graph[i][j] = Character.getNumericValue(c[j]);
			}
		}
		
		distance = new int[N][M];
		for (int i=0; i<N; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		distance[0][0] = 0; // 시작 지점은 비용 0
		
		
		PriorityQueue<edge> pq = new PriorityQueue<>((a,b)->(a.w-b.w));
		pq.offer(new edge(0,0,0));  // 첫달 요금
		
		while (!pq.isEmpty()) {
			edge tmp = pq.poll();
			int x = tmp.x;
			int y = tmp.y;
			int w = tmp.w;
			
			if (distance[x][y] < w ) {continue;}
			
			for (int k=0; k<4; k++) {
				int nr = x+dr[k];
				int nc = y+dc[k];
				
				if (0<=nr && nr<N && 0<=nc && nc<M) {
					if (distance[nr][nc] > w + graph[nr][nc]) {
						distance[nr][nc] = w + graph[nr][nc];
						pq.offer(new edge(nr,nc,distance[nr][nc]));
					}
				}
			}
		}
		System.out.println(distance[N-1][M-1]);
		
		
	}
}


