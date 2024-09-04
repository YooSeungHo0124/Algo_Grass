import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = s.nextInt();
		}

		int[] sortedArr = arr.clone();
		Arrays.sort(sortedArr);

		Map<Integer, Integer> map = new HashMap<>();
		int rank = 0;

		for (int x : sortedArr) {
			if (!map.containsKey(x)) {
				map.put(x, rank);
				rank++;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int x : arr) {
			sb.append(map.get(x)).append(" ");
		}
		System.out.println(sb.toString());
	}
}
