import java.util.*;
import java.io.*;

public class Main {

    public static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {
        int t = 1;
        // t = IO.i();
        while (t-- > 0) {
            solve();
        }
        IO.flush();
    }

    static void solve() throws IOException {
        int n = IO.i();
        int[] a = IO.iArr(n);

        DBG.log("n", n);
        DBG.log("a", Arrays.toString(a));

        IO.out.println(n);
    }
}

// ============================================================
// IO — Fast Input / Output Utilities
// ============================================================
class IO {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int i() throws IOException {
        return Integer.parseInt(next());
    }

    static long l() throws IOException {
        return Long.parseLong(next());
    }

    static double d() throws IOException {
        return Double.parseDouble(next());
    }

    static String line() throws IOException {
        return br.readLine();
    }

    static int[] iArr(int n) throws IOException {
        int[] arr = new int[n];
        for (int idx = 0; idx < n; idx++) arr[idx] = i();
        return arr;
    }

    static long[] lArr(int n) throws IOException {
        long[] arr = new long[n];
        for (int idx = 0; idx < n; idx++) arr[idx] = l();
        return arr;
    }

    static String[] sArr(int n) throws IOException {
        String[] arr = new String[n];
        for (int idx = 0; idx < n; idx++) arr[idx] = next();
        return arr;
    }

    static void flush() {
        out.flush();
    }
}

// ============================================================
// Mod — Modular Arithmetic (MOD = 1e9+7)
// ============================================================
class Mod {
    static final long MOD = 1_000_000_007L;

    static long add(long a, long b) {
        return ((a % MOD) + (b % MOD) + MOD) % MOD;
    }

    static long sub(long a, long b) {
        return ((a % MOD) - (b % MOD) + MOD) % MOD;
    }

    static long mul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static long inv(long a) {
        return Mth.pow(a, MOD - 2, MOD);
    }

    static long div(long a, long b) {
        return mul(a, inv(b));
    }
}

// ============================================================
// Mth — Mathematical Utilities
// ============================================================
class Mth {
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    static long pow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    static boolean prime(long n) {
        if (n < 2) return false;
        for (long p = 2; p * p <= n; p++) {
            if (n % p == 0) return false;
        }
        return true;
    }

    static boolean[] sieve(int n) {
        boolean[] isComposite = new boolean[n + 1];
        for (int p = 2; (long) p * p <= n; p++) {
            if (!isComposite[p]) {
                for (int m = p * p; m <= n; m += p) {
                    isComposite[m] = true;
                }
            }
        }
        return isComposite;
    }
}

// ============================================================
// Bit — Bit Manipulation Utilities
// ============================================================
class Bit {
    // Get kth bit -> 0 or 1
    static int get(int n, int k) {
        return (n >> k) & 1;
    }

    // Set kth bit -> bit k forced to 1
    static int set(int n, int k) {
        return n | (1 << k);
    }

    // Clear kth bit -> bit k forced to 0
    static int clear(int n, int k) {
        return n & ~(1 << k);
    }

    // Toggle kth bit -> bit k flipped
    static int toggle(int n, int k) {
        return n ^ (1 << k);
    }

    // Check if power of 2 -> true/false
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // Count set bits (Brian Kernighan) -> popcount
    static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    // Long overloads
    static long get(long n, int k) {
        return (n >> k) & 1L;
    }

    static long set(long n, int k) {
        return n | (1L << k);
    }

    static long clear(long n, int k) {
        return n & ~(1L << k);
    }

    static long toggle(long n, int k) {
        return n ^ (1L << k);
    }

    static boolean isPowerOfTwo(long n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    static int countSetBits(long n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}

// ============================================================
// Arr — Array Utilities
// ============================================================
class Arr {
    // First index where value >= x
    static int lb(int[] arr, int x) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] >= x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    // First index where value > x
    static int ub(int[] arr, int x) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] > x) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    static long[] pSum(int[] arr) {
        long[] pre = new long[arr.length + 1];
        for (int idx = 0; idx < arr.length; idx++) pre[idx + 1] = pre[idx] + arr[idx];
        return pre;
    }

    static long[] sSum(int[] arr) {
        int n = arr.length;
        long[] suf = new long[n + 1];
        for (int idx = n - 1; idx >= 0; idx--) suf[idx] = suf[idx + 1] + arr[idx];
        return suf;
    }

    static int[] pMin(int[] arr) {
        int[] pre = new int[arr.length];
        pre[0] = arr[0];
        for (int idx = 1; idx < arr.length; idx++) pre[idx] = Math.min(pre[idx - 1], arr[idx]);
        return pre;
    }

    static int[] sMin(int[] arr) {
        int n = arr.length;
        int[] suf = new int[n];
        suf[n - 1] = arr[n - 1];
        for (int idx = n - 2; idx >= 0; idx--) suf[idx] = Math.min(suf[idx + 1], arr[idx]);
        return suf;
    }

    static int[] pMax(int[] arr) {
        int[] pre = new int[arr.length];
        pre[0] = arr[0];
        for (int idx = 1; idx < arr.length; idx++) pre[idx] = Math.max(pre[idx - 1], arr[idx]);
        return pre;
    }

    static int[] sMax(int[] arr) {
        int n = arr.length;
        int[] suf = new int[n];
        suf[n - 1] = arr[n - 1];
        for (int idx = n - 2; idx >= 0; idx--) suf[idx] = Math.max(suf[idx + 1], arr[idx]);
        return suf;
    }

    static long[] pGcd(int[] arr) {
        long[] pre = new long[arr.length];
        pre[0] = arr[0];
        for (int idx = 1; idx < arr.length; idx++) pre[idx] = Mth.gcd(pre[idx - 1], arr[idx]);
        return pre;
    }

    static long[] sGcd(int[] arr) {
        int n = arr.length;
        long[] suf = new long[n];
        suf[n - 1] = arr[n - 1];
        for (int idx = n - 2; idx >= 0; idx--) suf[idx] = Mth.gcd(suf[idx + 1], arr[idx]);
        return suf;
    }
}

// ============================================================
// Stk — Monotonic Stack
// ============================================================
class Stk {
    // Next Greater Element values
    static long[] nge(int[] arr) {
        int n = arr.length;
        long[] res = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int idx = n - 1; idx >= 0; idx--) {
            while (!stack.isEmpty() && stack.peek() <= arr[idx]) stack.pop();
            res[idx] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[idx]);
        }
        return res;
    }

    // Next Greater Element indices
    static int[] ngei(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int idx = n - 1; idx >= 0; idx--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[idx]) stack.pop();
            res[idx] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(idx);
        }
        return res;
    }
}

// ============================================================
// Grd — Grid Algorithms
// ============================================================
class Grd {
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    static boolean ok(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    static void dfs(int r, int c, char[][] grid, boolean[][] vis) {
        int R = grid.length, C = grid[0].length;
        if (!ok(r, c, R, C) || vis[r][c]) return;
        vis[r][c] = true;
        for (int dir = 0; dir < 4; dir++) {
            dfs(r + DR[dir], c + DC[dir], grid, vis);
        }
    }

    static void bfs(int r, int c, char[][] grid, boolean[][] vis) {
        int R = grid.length, C = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        vis[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nr = cur[0] + DR[dir], nc = cur[1] + DC[dir];
                if (ok(nr, nc, R, C) && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }
}

// ============================================================
// DSU — Disjoint Set Union
// ============================================================
class DSU {
    int[] parent, rank_;

    DSU(int n) {
        parent = new int[n];
        rank_ = new int[n];
        for (int idx = 0; idx < n; idx++) parent[idx] = idx;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        if (rank_[ra] < rank_[rb]) { int tmp = ra; ra = rb; rb = tmp; }
        parent[rb] = ra;
        if (rank_[ra] == rank_[rb]) rank_[ra]++;
    }

    boolean same(int a, int b) {
        return find(a) == find(b);
    }
}

// ============================================================
// ST — Segment Tree (Range Sum, Point Update)
// ============================================================
class ST {
    int n;
    long[] tree;

    ST(long[] values) {
        n = values.length;
        tree = new long[4 * n];
        build(values, 1, 0, n - 1);
    }

    void build(long[] values, int node, int l, int r) {
        if (l == r) {
            tree[node] = values[l];
            return;
        }
        int mid = (l + r) >>> 1;
        build(values, 2 * node, l, mid);
        build(values, 2 * node + 1, mid + 1, r);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    void upd(int idx, long val) {
        upd(1, 0, n - 1, idx, val);
    }

    void upd(int node, int l, int r, int idx, long val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) >>> 1;
        if (idx <= mid) upd(2 * node, l, mid, idx, val);
        else upd(2 * node + 1, mid + 1, r, idx, val);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    long q(int l, int r) {
        return q(1, 0, n - 1, l, r);
    }

    long q(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) >>> 1;
        return q(2 * node, l, mid, ql, qr) + q(2 * node + 1, mid + 1, r, ql, qr);
    }
}

// ============================================================
// Gen — Test Case Generator
// ============================================================
class Gen {
    static Random rnd = new Random();

    static int i(int min, int max) {
        return min + rnd.nextInt(max - min + 1);
    }

    static long l(long min, long max) {
        return min + (long) (rnd.nextDouble() * (max - min + 1));
    }

    static int[] iArr(int size, int min, int max) {
        int[] arr = new int[size];
        for (int idx = 0; idx < size; idx++) arr[idx] = i(min, max);
        return arr;
    }

    static int[] edgeArr(int size) {
        int[] choices = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] arr = new int[size];
        for (int idx = 0; idx < size; idx++) arr[idx] = choices[rnd.nextInt(choices.length)];
        return arr;
    }

    static String str(int len) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < len; idx++) sb.append((char) ('a' + rnd.nextInt(26)));
        return sb.toString();
    }
}

// ============================================================
// DBG — Debug Utilities
// ============================================================
class DBG {
    static void log(String name, Object val) {
        if (Main.DEBUG) System.err.println(name + " = " + val);
    }

    static void grid(String name, char[][] grid) {
        if (!Main.DEBUG) return;
        System.err.println(name + " =");
        for (char[] row : grid) System.err.println(new String(row));
    }

    static void msg(String text) {
        if (Main.DEBUG) System.err.println(text);
    }
}
