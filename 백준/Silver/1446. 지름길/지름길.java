import java.util.*;
public class Main {
	
	static class edge{
		int to;
		int w;
		
		edge(int to, int w){
			this.to = to;
			this.w = w;
		}
	}

	static int N,D;
	static ArrayList<edge>[] graph;
	static int[] distance;

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		D = s.nextInt();
		
		distance = new int[D+1];
		for (int i=0; i<=D; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[0] = 0; // 시작 위치는 0
		
		graph = new ArrayList[D+1];
		for (int i=0; i<=D; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 일단 거리 다 1로 채우기
		for (int i = 0; i<D; i++) {
			graph[i].add(new edge (i+1,1));  
		}
		
		// 지름길들은 표시
		for (int i=0; i<N; i++) {
			int start = s.nextInt();
			int end = s.nextInt();
			int weight = s.nextInt();
			
			if (end > D) {
				continue; // D를 넘어서는 도착점은 무시
			}
			
			graph[start].add(new edge(end,weight));
		}
		
		PriorityQueue<edge> pq = new PriorityQueue<>((a,b)->(a.w-b.w)); // weight로 정렬
		pq.offer(new edge(0,0)); // 0에서 시작
		
		while (!pq.isEmpty()) {
			edge tmp = pq.poll();
			int to = tmp.to;
			int w = tmp.w;
			
			if (distance[to]<w) {
				continue;
			}
			
			for (int i=0; i<graph[to].size(); i++) {
				edge imsi = graph[to].get(i);
				int next = imsi.to;
				int weight = imsi.w;
				
				if (next == D+1) {continue;}
				if (distance[next] > distance[to]+weight) {
					distance[next] = distance[to]+weight;
					pq.offer(new edge(next, distance[next]));
				}
			}
			
		}
		System.out.println(distance[D]);
	}
}












