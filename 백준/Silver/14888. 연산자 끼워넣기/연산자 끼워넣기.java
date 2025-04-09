
import java.util.*;
import java.io.*;

public class Main {

    static int N, plus, minus, mul, div;
    static int[] numbers;
    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        div = Integer.parseInt(st.nextToken());

        calculate(1, numbers[0]); // Start calculation from the first number

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    static void calculate(int index, int currentResult) {
        if (index == N) {
            maxResult = Math.max(maxResult, currentResult);
            minResult = Math.min(minResult, currentResult);
            return;
        }

        // Try each operator if available
        if (plus > 0) {
            plus--;
            calculate(index + 1, currentResult + numbers[index]);
            plus++;
        }
        if (minus > 0) {
            minus--;
            calculate(index + 1, currentResult - numbers[index]);
            minus++;
        }
        if (mul > 0) {
            mul--;
            calculate(index + 1, currentResult * numbers[index]);
            mul++;
        }
        if (div > 0) {
            div--;
            // Handle integer division
            if (currentResult < 0) {
                calculate(index + 1, -(-currentResult / numbers[index]));
            } else {
                calculate(index + 1, currentResult / numbers[index]);
            }
            div++;
        }
    }
}
