import java.util.*;
public class Solution {
	static int N,L,max=Integer.MIN_VALUE;	
	static int[] score;
	static int[] kal;
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;
		while(test_case>0) {
			N = s.nextInt();
			L = s.nextInt();
			score = new int[N];
			kal = new int[N];
			for (int i=0; i<N; i++) {
				score[i]=s.nextInt(); // 점수
				kal[i]=s.nextInt(); // 칼로리
			}
			
			func(0,0,0);
			System.out.println("#"+result_cnt+" "+max);
			max=0;
			test_case--;
			result_cnt++;
		}
		
	}
	
	static void func(int i, int s, int k) { 
		if (k>L) return; // 허용 칼로리 넘는다 -> return
		if (i==N) {
			max=Math.max(max, s); return; // 점수 계산
		}
		
		func(i+1, s+score[i], k+kal[i]);
		func(i+1, s, k);
		
	}
}
