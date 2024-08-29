import java.util.*;

public class Solution {

	static int[] arr_A = new int[9];
	static int[] arr_B = new int[9];
	static int N = 9,aSum=0,bSum=0,aWin=0,bWin=0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;

		while (test_case > 0) {
			for (int i = 0; i < arr_A.length; i++) {
				arr_A[i] = s.nextInt();
			}
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				boolean inarr = false;
				for (int j = 0; j < arr_A.length; j++) {
					if (i == arr_A[j]) {
						inarr = true;
						break;
					}

				}
				if (!inarr)
					arr_B[idx++] = i;
			}

//			System.out.println("A "+Arrays.toString(arr_A));
//			System.out.println("B "+Arrays.toString(arr_B));
			
			permutation(0);
			System.out.println("#"+result_cnt+" "+aWin+" "+bWin);
			//초기화
			aWin=0; bWin=0;
			
			result_cnt++;
			test_case--;
		}

	}

	
	static void permutation(int idx) {
		if (idx == N) {
			// 계산 하는 로직
			for (int j=0; j<N; j++) {
				if (arr_A[j]> arr_B[j]) {aSum+=arr_A[j]+arr_B[j];}
				else {bSum+=arr_A[j]+arr_B[j];}
			}
			if (aSum>bSum) {aWin++;}
			else if (aSum<=bSum) {bWin++;}
			
			// 초기화
			aSum=0; bSum=0;
			return;
		}
		
		for (int i = idx; i < N; i++) {
			swap(i, idx);
			permutation(idx + 1);
			swap(i, idx); // 다음 과정을 위해서 원상 복구
		}
	}

	static void swap(int a, int b) {
		int tmp = arr_B[a];
		arr_B[a] = arr_B[b];
		arr_B[b] = tmp;
	}

}
