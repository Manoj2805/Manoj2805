package training;

import java.io.BufferedInputStream;
import java.io.IOException;

final class FastScanner {
    private final BufferedInputStream in = new BufferedInputStream(System.in);
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0;
    private int len = 0;

    private int read() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) {
                return -1;
            }
        }
        return buffer[ptr++];
    }

    public long nextLong() throws IOException {
        int c;
        do {
            c = read();
        } while (c <= ' ' && c != -1);

        long sign = 1;
        if (c == '-') {
            sign = -1;
            c = read();
        }

        long value = 0;
        while (c > ' ') {
            value = value * 10 + (c - '0');
            c = read();
        }
        return value * sign;
    }

    public int nextInt() throws IOException {
        return (int) nextLong();
    }
}
