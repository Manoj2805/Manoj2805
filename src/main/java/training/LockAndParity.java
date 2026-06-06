package training;

import java.io.IOException;

public final class LockAndParity {
    static long solve(int[] locks) {
        long answer = Long.MAX_VALUE;
        int n = locks.length;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (locks[i] == locks[j]) {
                    continue;
                }
                long cost = Math.abs((long) locks[i] - locks[j]);
                if ((cost & 1L) == 0L) {
                    answer = Math.min(answer, cost);
                }
            }
        }
        return answer == Long.MAX_VALUE ? -1 : answer;
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int[] locks = new int[n];
        for (int i = 0; i < n; i++) {
            locks[i] = fs.nextInt();
        }
        System.out.println(solve(locks));
    }
}
