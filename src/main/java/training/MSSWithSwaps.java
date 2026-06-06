package training;

import java.io.IOException;
import java.util.Arrays;

public final class MSSWithSwaps {
    private static final int SHIFT = 1000;
    private static final int RANGE = 2001;

    static long solve(int[] a, int k) {
        int n = a.length;
        int[] total = new int[RANGE];
        for (int value : a) {
            total[value + SHIFT]++;
        }

        long best = Long.MIN_VALUE;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + a[i];
        }

        for (int l = 0; l < n; l++) {
            int[] inside = new int[RANGE];
            int[] outside = Arrays.copyOf(total, RANGE);

            for (int r = l; r < n; r++) {
                int idx = a[r] + SHIFT;
                inside[idx]++;
                outside[idx]--;

                long base = prefix[r + 1] - prefix[l];
                int len = r - l + 1;
                int outsideSize = n - len;
                int limit = Math.min(k, Math.min(len, outsideSize));

                long gain = 0;
                if (limit > 0) {
                    int[] smallestInside = new int[limit];
                    int[] largestOutside = new int[limit];

                    int p = 0;
                    for (int i = 0; i < RANGE && p < limit; i++) {
                        int cnt = inside[i];
                        while (cnt > 0 && p < limit) {
                            smallestInside[p++] = i - SHIFT;
                            cnt--;
                        }
                    }

                    p = 0;
                    for (int i = RANGE - 1; i >= 0 && p < limit; i--) {
                        int cnt = outside[i];
                        while (cnt > 0 && p < limit) {
                            largestOutside[p++] = i - SHIFT;
                            cnt--;
                        }
                    }

                    for (int i = 0; i < limit; i++) {
                        if (largestOutside[i] > smallestInside[i]) {
                            gain += (long) largestOutside[i] - smallestInside[i];
                        } else {
                            break;
                        }
                    }
                }

                best = Math.max(best, base + gain);
            }
        }
        return best;
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int k = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextInt();
        }
        System.out.println(solve(a, k));
    }
}
