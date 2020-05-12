import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Sujit Chavan
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int total_test = in.nextInt();
            for (int i = 0; i < total_test; i++) {
                int n = in.nextInt();
                int k = in.nextInt();
                if (n < k) {
                    System.out.println("NO");
                } else if (n % 2 == 1 && k % 2 == 0) {
                    System.out.println("NO");
                } else {
                    int ans = n / k;
                    if (ans == 0) {
                        System.out.println("YES");
                        for (int l = 0; l < k; l++) {
                            System.out.print(ans + " ");
                        }
                        System.out.println();
                    } else {
                        int que = n % k;
                        int one_ans = que + ans;
                        if (ans == 1 && one_ans % 2 == 0) {
                            System.out.println("NO");
                        } else if (ans == 1 && one_ans % 2 == 1) {
                            System.out.println("YES");
                            System.out.print(one_ans + " ");
                            for (int j = 0; j < k - 1; j++) {
                                System.out.print(ans + " ");
                            }
                            System.out.println();
                        } else if (one_ans % 2 == ans % 2) {
                            System.out.println("YES");
                            System.out.print(one_ans + " ");
                            for (int j = 0; j < k - 1; j++) {
                                System.out.print(ans + " ");
                            }
                            System.out.println();
                        } else if ((k - 1) % 2 == 1 && ans % 2 == 1) {
                            System.out.println("NO");
                        } else {
                            System.out.println("YES");
                            System.out.print(one_ans + " ");
                            for (int m = 0; m < (k - 1) / 2; m++) {
                                System.out.print((ans - 1) + " ");
                                System.out.print((ans + 1) + " ");
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void close() {
            writer.close();
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

