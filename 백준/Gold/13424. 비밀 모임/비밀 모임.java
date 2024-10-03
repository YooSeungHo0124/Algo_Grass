import java.util.*;
public class Main {
	
	static class edge {
		int to,w;
		
		edge(int to, int w){
			this.to = to;
			this.w = w;
		}
	}
	
	static int N,M,K;
	static ArrayList<edge>[] node ;
	static int[][] dist;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt();
		
		while (test_case>0) {
			
			N = s.nextInt();
			M = s.nextInt();
			
			node = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				node[i] = new ArrayList<>();
			}
			
			for (int i=0; i<M; i++) {
				int a = s.nextInt();
				int b = s.nextInt();
				int w = s.nextInt();
				
				node[a].add(new edge(b,w));
				node[b].add(new edge(a,w));
				
			}
			
			K = s.nextInt();
			dist = new int[K][N+1];
			
			for (int i=0; i<K; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			for (int i=0; i<K; i++) {
				dij(i,s.nextInt());
			}
			
			int min = Integer.MAX_VALUE;
			int n =0; 
			for (int i=N; i>=1; i--) {
				int sum = 0;
				for (int k=0; k<K; k++) {
					sum += dist[k][i];
				}
				
				if ( sum <= min) {
					min = sum;
					n = i;
				}
			}
			
			System.out.println(n);
			test_case--;
		}
		
	}
	
	
	static void dij(int i, int start) {
		PriorityQueue<edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        dist[i][start] = 0; // 시작 지점은 가중치 0

        pq.offer(new edge(start, 0));

        while (!pq.isEmpty()) {
        	edge current = pq.poll();
            int currentNode = current.to;

            for (edge edge : node[currentNode]) {
                int nextNode = edge.to;
                int nextDist = dist[i][currentNode] + edge.w;

                if (nextDist < dist[i][nextNode]) {
                    dist[i][nextNode] = nextDist;
                    pq.offer(new edge(nextNode, nextDist));
                }
            }
        }
	}
	
}
