import java.util.*;

public class Main {
	static int N, R = 6;
	static int[] arr;
	static int[] sel;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			N = s.nextInt();
			if (N == 0) {
				break;
			}
			arr = new int[N];
			sel = new int[R];
			for (int i = 0; i < N; i++) {
				arr[i] = s.nextInt();
			}
			func(0,0);
			System.out.println();
		}
	}
	
	private static void func(int idx, int selectIndex) {
		if (selectIndex == R) {
			Arrays.sort(sel);
			for (int i=0; i<sel.length; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}
		
		if (idx==N) {
			return;
		}
		
		sel[selectIndex] = arr[idx];
		func(idx+1,selectIndex+1);
		func(idx+1,selectIndex);
		
		
	}
	
}
