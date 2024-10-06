import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		while (T-- > 0) {
			int K = scanner.nextInt();
			PriorityQueue<Long> pq = new PriorityQueue<>();

			for (int i = 0; i < K; i++) {
				pq.offer(scanner.nextLong());
			}

			long totalCost = 0;
			while (pq.size() > 1) {
				long file1 = pq.poll();
				long file2 = pq.poll();
				long mergedSize = file1 + file2;
				totalCost += mergedSize;
				pq.offer(mergedSize);
			}

			System.out.println(totalCost);
		}

		scanner.close();
	}
}
