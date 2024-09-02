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

	static int M,N,start,end;
	static ArrayList<edge>[] graph;
	static int[] distance;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		graph = new ArrayList[N+1];
		distance = new int[N+1];
		for (int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0; i<M; i++) {
			graph[s.nextInt()].add(new edge(s.nextInt(),s.nextInt()));
		}
		
		start=s.nextInt();
		end = s.nextInt();
		for (int i=0; i<distance.length; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[start] = 0; // 시작 지점에서 자기 자신으로의 거리는 0이다.
		
		
		PriorityQueue<edge> pq = new PriorityQueue<>((a,b)->(a.w-b.w));
		pq.offer(new edge(start,0));
		while (!pq.isEmpty()) {
			edge tmp = pq.poll();
			int to = tmp.to;
			int w = tmp.w;
			
			if (distance[to] < w) {continue;}
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
		
		System.out.println(distance[end]);
		
	}
}
