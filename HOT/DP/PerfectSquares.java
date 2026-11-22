package HOT.DP;

/**
 * 279. 完全平方数
 *
 * 给定 1 到 10000 之间的整数 n，求和为 n 的完全平方数的最少个数。
 * 同一个平方数可以重复使用。
 */
class Solution {
    /**
     * dp[sum] 表示凑出 sum 所需的最少个数。枚举最后加上的平方数，
     * 剩余部分的最优解已经算好，再加 1 就得到一种候选结果。
     * 时间复杂度为 O(n√n)，额外空间复杂度为 O(n)。
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // dp[0] = 0，凑出 0 需要 0 个数。

        for (int sum = 1; sum <= n; sum++) {
            dp[sum] = sum; // 全部使用 1，一定可以凑出 sum。
            for (int root = 1; root * root <= sum; root++) {
                int square = root * root;
                dp[sum] = Math.min(dp[sum], dp[sum - square] + 1);
            }
        }

        return dp[n];
    }
}
