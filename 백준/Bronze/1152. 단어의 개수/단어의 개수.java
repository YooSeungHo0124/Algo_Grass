import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str= s.nextLine();
		String[] arr = str.split(" ");
		int cnt  = 0;
		for (int i=0; i<arr.length; i++) {
			if (arr[i] != "") {cnt++;}
		}
		System.out.println(cnt);
	}
}
