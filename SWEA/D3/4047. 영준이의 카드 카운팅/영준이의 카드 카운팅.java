/*
 	<CARD DECK> 
 	S : 스페이드
 	D : 다이아
 	H : 하트
 	C : 클로버
 	
 	A : 01
 	02 ~ 10
 	J : 11
 	Q : 12
 	K : 13
 * */
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		int result_cnt = 1;
		while (test_case > 0) {
			boolean error = false;

			String str = sc.next();
			char[] arr = str.toCharArray();
			String[] cards = new String[arr.length / 3];
			for (int i = 0; i < arr.length; i = i + 3) {
				cards[i / 3] = Character.toString(arr[i]) + Character.toString(arr[i + 1])
						+ Character.toString(arr[i + 2]);
			}

			for (int i = 0; i < cards.length; i++) {
				for (int j = i + 1; j < cards.length; j++) {
					if (cards[i].equals(cards[j])) {
						System.out.println("#" + result_cnt + " ERROR");
						error = true;
						break;
					}
				}
			}
			if (error == false) {

				int s = 13;
				int d = 13;
				int c = 13;
				int h = 13;
				for (int i = 0; i < cards.length; i++) {
					if (cards[i].contains("S")) {
						s--;
					} else if (cards[i].contains("D")) {
						d--;
					} else if (cards[i].contains("C")) {
						c--;
					} else {
						h--;
					}
				}
				System.out.println("#" + result_cnt + " " + s + " " + d + " " + h + " " + c);
			}

			test_case--;
			result_cnt++;
		}

	}
}
