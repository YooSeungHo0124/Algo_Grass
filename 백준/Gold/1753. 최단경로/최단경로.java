import java.util.*;
public class Main {
	
	static class edge{
		int to;
		int w;
		
		edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	
	static int V,E,K; 	// 점개수, 엣지개수, 시작점
	static ArrayList<edge>[] graph;
	static int[] distance;
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		V = s.nextInt();
		E = s.nextInt();
		K = s.nextInt();
		
		graph = new ArrayList[V+1];
		distance = new int[V+1];
		
		for (int i=0; i<=V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<E; i++) {
			int u = s.nextInt();
			int v = s.nextInt();
			int w = s.nextInt();
			graph[u].add(new edge(v,w));
		}
		
		for (int i=0; i<=V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[K]=0;  // 자기 자신으로 가는 거리는 0이다.
		
		PriorityQueue<edge> pq = new PriorityQueue<>((a,b)->(a.w-b.w)); // 가중치 적은거 오름차순 
		pq.offer(new edge(K,0));  
		
		while (!pq.isEmpty()) {
			 edge tmp = pq.poll();
			 int to = tmp.to;
			 int w = tmp.w;
			 
			 if (distance[to] < w) { continue; }  
			 
			 for (int i=0; i<graph[to].size(); i++) {
				 edge imsi = graph[to].get(i);
				 int next = imsi.to;
				 int weight = imsi.w;
				 
				 if (distance[next] > distance[to]+weight) {
					 distance[next] = distance[to]+weight;
					 pq.offer(new edge(next, distance[next]));
				 }
				 
			 }
		}
		
		// 각 점으로의 최단 경로
		for (int i=1; i<=V; i++) {
			int result = distance[i];
			if (result == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(result);
			}
		}
		
	}
	
}
