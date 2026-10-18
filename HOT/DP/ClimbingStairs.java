package HOT.DP;

/**
 * 70. 爬楼梯
 *
 * 每次可以爬 1 阶或 2 阶，返回到达第 n 阶的不同方法总数。
 * 题目保证 1 <= n <= 45，结果可以用 int 保存。
 */
class Solution {
    /**
     * 动态规划：最后一步来自前一阶或前两阶，两类方法相加。
     * 即 f(n) = f(n - 1) + f(n - 2)，其中 f(1) = 1，f(2) = 2。
     * 只保留前两个状态，时间复杂度为 O(n)，额外空间复杂度为 O(1)。
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int previous = 1;
        int current = 2;

        for (int step = 3; step <= n; step++) {
            // 循环开始时，两个变量分别保存 f(step - 2) 和 f(step - 1)。
            int next = previous + current;
            previous = current;
            current = next;
        }

        return current;
    }
}
