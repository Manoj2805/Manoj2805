package training;

import java.io.IOException;

public final class FoodStamps {
    private static long countTermsAtLeast(long[] v, long[] d, long threshold) {
        long total = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] >= threshold) {
                total += (v[i] - threshold) / d[i] + 1;
            }
        }
        return total;
    }

    private static long sumTermsAtLeast(long[] v, long[] d, long threshold) {
        long sum = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] >= threshold) {
                long cnt = (v[i] - threshold) / d[i] + 1;
                sum += cnt * (2L * v[i] - (cnt - 1) * d[i]) / 2L;
            }
        }
        return sum;
    }

    static long solve(long[] v, long[] d, long meals) {
        if (meals <= 0) {
            return 0;
        }

        long positiveTerms = countTermsAtLeast(v, d, 1);
        long picks = Math.min(meals, positiveTerms);
        if (picks == 0) {
            return 0;
        }

        long maxV = 0;
        for (long value : v) {
            maxV = Math.max(maxV, value);
        }

        long lo = 1;
        long hi = maxV;
        while (lo < hi) {
            long mid = (lo + hi + 1) >>> 1;
            if (countTermsAtLeast(v, d, mid) >= picks) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        long threshold = lo;
        long strictlyGreaterCount = countTermsAtLeast(v, d, threshold + 1);
        long strictlyGreaterSum = sumTermsAtLeast(v, d, threshold + 1);
        return strictlyGreaterSum + (picks - strictlyGreaterCount) * threshold;
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        long m = fs.nextLong();
        long[] v = new long[n];
        long[] d = new long[n];

        for (int i = 0; i < n; i++) {
            v[i] = fs.nextLong();
        }
        for (int i = 0; i < n; i++) {
            d[i] = fs.nextLong();
        }

        System.out.println(solve(v, d, m));
    }
}
