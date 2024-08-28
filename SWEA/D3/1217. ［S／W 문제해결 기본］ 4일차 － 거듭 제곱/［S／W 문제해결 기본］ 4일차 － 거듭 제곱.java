import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = 10;
		while (test_case > 0 ) {
			int result_cnt = s.nextInt();
			System.out.println("#"+result_cnt+" "+pow2(s.nextInt(),s.nextInt()));
			test_case--;
		}
	}
	
	static int pow2(int C, int N) {
//		기저 조건
		if (N==1) return C;
		
//		재귀 부분 : 짝수인 경우, 홀수인 경우
		if (N % 2 == 0) {
			int tmp = pow2(C,N/2);  // 같은 연산을 2번 해야하니까
			return tmp*tmp;
		}
		else {
			int tmp = pow2(C,(N-1)/2);
			return tmp * tmp * C;
		}
	}
}
