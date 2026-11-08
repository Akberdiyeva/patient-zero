import java.util.*;

public class Outbreak {

    public static boolean[] cheapestTests(
            int n,
            int[][] nb,
            int[] cost
    ) {

        boolean[] tested = new boolean[n];

        if (n == 0) {
            return tested;
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -2);

        int[] order = new int[n];
        int size = 0;

        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {

            int v = stack[--top];

            order[size++] = v;

            for (int u : nb[v]) {

                if (u == parent[v]) continue;
                if (parent[u] != -2) continue;

                parent[u] = v;
                stack[top++] = u;
            }
        }

        long[] in = new long[n];
        long[] out = new long[n];

        for (int index = size - 1; index >= 0; index--) {

            int v = order[index];

            in[v] = cost[v];
            out[v] = 0L;

            for (int u : nb[v]) {

                if (parent[u] == v) {

                    in[v] += Math.min(in[u], out[u]);

                    out[v] += in[u];
                }
            }
        }

        boolean[] mustTake = new boolean[n];

        mustTake[0] = in[0] <= out[0];

        top = 0;
        stack[top++] = 0;

        while (top > 0) {

            int v = stack[--top];

            tested[v] = mustTake[v];

            for (int u : nb[v]) {

                if (parent[u] == v) {

                    mustTake[u] =
                            !tested[v] || in[u] <= out[u];

                    stack[top++] = u;
                }
            }
        }

        return tested;
    }

    public static int longestChain(int n, int[][] nb) {

        if (n <= 1) {
            return 0;
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -2);

        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int size = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {

            int v = stack[--top];

            order[size++] = v;

            for (int u : nb[v]) {

                if (u == parent[v] || parent[u] != -2) {
                    continue;
                }

                parent[u] = v;
                stack[top++] = u;
            }
        }

        int[] longestDown = new int[n];

        int diameter = 0;

        for (int index = size - 1; index >= 0; index--) {

            int v = order[index];

            int first = 0;
            int second = 0;

            for (int u : nb[v]) {

                if (parent[u] == v) {

                    int path = longestDown[u] + 1;

                    if (path > first) {

                        second = first;
                        first = path;

                    } else if (path > second) {

                        second = path;
                    }
                }
            }

            longestDown[v] = first;

            diameter = Math.max(
                    diameter,
                    first + second
            );
        }

        return diameter;
    }
}