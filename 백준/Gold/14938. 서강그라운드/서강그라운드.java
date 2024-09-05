import java.util.*;
public class Main {
	static class edge{
		int to,w;
		
		public edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	static int[] vertex,distance;
	static ArrayList<edge>[] graph;
	static int N,M,R,max=Integer.MIN_VALUE; // N: 점개수, M:범위, R:길의 개수
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		R = s.nextInt();
		vertex = new int[N+1]; // 1번부터 점이 시작하니까 
		for (int i=1; i<=N; i++) {
			 vertex[i] = s.nextInt();
		}
		graph = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0; i<R; i++) {
			int a = s.nextInt();
			int b= s.nextInt();
			int w= s.nextInt();
			graph[a].add(new edge(b,w));  // 양방향이기 때문에
			graph[b].add(new edge(a,w));
		}
	
		// 전체 노드를 돌면서, 각 케이스마다 수색범위 M 보다 작은 값들을 알고, 그때의 아이템수를 합 한다.
		for (int i=1; i<=N; i++) {
			int[] tmp = dijkstra(i);
			int sum =0;
			for (int j=1; j<tmp.length; j++) {
				if ( M >= tmp[j] ) {
					sum += vertex[j];
				}
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
	
	static int[] dijkstra(int start) {
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
        return dist;
    }
	
}
