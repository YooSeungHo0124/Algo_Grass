import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt(); 
		int[] arr = new int[N]; 

		for (int i = 0; i < N; i++) {
			arr[i] = s.nextInt(); 
		}

		ArrayList<Integer> lis = new ArrayList<>(); // 결과 리스트

		for (int i = 0; i < N; i++) {
			int num = arr[i]; 

			// 이진 탐색으로 LIS에서 현재 숫자가 들어갈 위치를 찾음
			int pos = Collections.binarySearch(lis, num);

			// 이진 탐색 결과 음수이면 삽입해야 할 위치를 찾음
			// binarySearch가 음수 값을 반환하는 것은 그 위치에 값이 없다는 뜻
			// lis에 값이 없기때문에 lis 올바른 위치에 값을 추가해야겠다. -> 밑의 과정에서
			if (pos < 0) {
				pos = -(pos + 1); // 삽입할 위치를 정확하게 찾기 위해 음수 인덱스를 양수로 변환
			}

			// 위치가 lis의 길이와 같으면 리스트 끝에 숫자를 추가
			// <-> 새로운 큰 값이 들어왔다
			// <-> 큰값 lis에 추가
			if (pos >= lis.size()) {
				lis.add(num);
			}
			// 그렇지 않으면 그 위치의 숫자를 현재 숫자로 대체
			else {
				lis.set(pos, num);
			}
		}

		System.out.println(lis.size()); // LIS 리스트의 크기가 가장 긴 증가하는 부분 수열의 길이
	}
}
