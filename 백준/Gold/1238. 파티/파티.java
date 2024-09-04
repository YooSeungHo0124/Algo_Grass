import java.util.*;
public class Main {
	static class edge{
		int to,w;
		public edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
		
	}
	static ArrayList<edge>[] graph;
	static int N,M,X,max=Integer.MIN_VALUE;
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner (System.in);
		N = s.nextInt();
		M = s.nextInt();
		X = s.nextInt();
		
		graph = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=1; i<=M; i++) {
			graph[s.nextInt()].add(new edge(s.nextInt(), s.nextInt()));
		}
		
		for (int i=1; i<=N; i++) {
			if (i!=X) {
				
				
				max = Math.max(max,(dijkstra(i,X)+dijkstra(X,i)));
			}
		}
		System.out.println(max);
	}
	
	
	static int dijkstra(int start, int end) {
        PriorityQueue<edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0; // 시작 지점은 가중치 0

        pq.offer(new edge(start, 0));

        while (!pq.isEmpty()) {
        	edge current = pq.poll();
            int currentNode = current.to;

            for (edge edge : graph[currentNode]) {
                int nextNode = edge.to;
                int nextDist = dist[currentNode] + edge.w;

                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    pq.offer(new edge(nextNode, nextDist));
                }
            }
        }
        return dist[end];
    }
}
