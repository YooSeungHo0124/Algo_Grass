import java.util.*;
public class Solution {
	
	static int N,B,result=Integer.MAX_VALUE;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;
		
		while (test_case>0) {
			N = s.nextInt();
			B = s.nextInt();
			
			arr = new int[N];
			for (int i=0; i<N; i++) {
				arr[i] = s.nextInt();
			}
			
			func();	
			System.out.println("#"+result_cnt+" "+result);
			result =Integer.MAX_VALUE;
			test_case--;
			result_cnt++;
		}
	}
	
	static void func() {
		
		for (int i=0; i< 1<<N; i++) {
			int sum = 0; 
			for (int j=0; j<N; j++) {
				if ((i & (1<<j)) != 0) {
					sum += arr[j];
				}
			}
			if (sum >= B) {
				result = Math.min(result, sum-B);
			}
		}
	}
	
}
