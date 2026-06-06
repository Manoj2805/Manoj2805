package training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class LayerSplitPathMaximization {
    private static final class NodeInfo {
        final int layer;
        final long value;

        NodeInfo(int layer, long value) {
            this.layer = layer;
            this.value = value;
        }
    }

    private static final class Edge {
        final int to;
        final long penalty;

        Edge(int to, long penalty) {
            this.to = to;
            this.penalty = penalty;
        }
    }

    static long solve(NodeInfo[] nodes, int[][] undirectedEdges) {
        int n = nodes.length;
        List<List<Edge>> dag = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dag.add(new ArrayList<>());
        }

        for (int[] e : undirectedEdges) {
            int u = e[0];
            int v = e[1];
            int lu = nodes[u].layer;
            int lv = nodes[v].layer;

            if (lu < lv || (lu == lv && u < v)) {
                long jump = (long) lv - lu;
                dag.get(u).add(new Edge(v, jump * jump));
            } else {
                long jump = (long) lu - lv;
                dag.get(v).add(new Edge(u, jump * jump));
            }
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        java.util.Arrays.sort(order, Comparator
                .comparingInt((Integer idx) -> nodes[idx].layer)
                .thenComparingInt(idx -> idx));

        long[] dp = new long[n];
        long best = Long.MIN_VALUE;

        for (int u : order) {
            dp[u] = Math.max(dp[u], nodes[u].value);
            best = Math.max(best, dp[u]);
            for (Edge edge : dag.get(u)) {
                long candidate = dp[u] + nodes[edge.to].value - edge.penalty;
                if (candidate > dp[edge.to]) {
                    dp[edge.to] = candidate;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt(); // K is part of input format, but transitions here depend on per-node layer values.
        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive.");
        }

        NodeInfo[] nodes = new NodeInfo[n];
        for (int i = 0; i < n; i++) {
            int layer = fs.nextInt();
            long value = fs.nextLong();
            nodes[i] = new NodeInfo(layer, value);
        }

        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            edges[i][0] = fs.nextInt();
            edges[i][1] = fs.nextInt();
        }

        System.out.println(solve(nodes, edges));
    }
}
