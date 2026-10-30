package HOT.DP;

/**
 * 198. 打家劫舍
 *
 * 每间房屋存有非负金额，不能同时选择相邻房屋，求能够获得的最大金额。
 * 题目保证 1 <= nums.length <= 100，0 <= nums[i] <= 400。
 */
class Solution {
    /**
     * 动态规划：不选当前房屋，沿用前一间为止的最优结果；
     * 选择当前房屋，则用前两间为止的最优结果加上当前金额，取两者最大值。
     * 只保留前两个状态，时间复杂度为 O(n)，额外空间复杂度为 O(1)。
     */
    public int rob(int[] nums) {
        // 尚未考虑任何房屋时，能够获得的金额为 0。
        int previous = 0;
        int current = 0;

        for (int money : nums) {
            // previous 对应前两间为止的最优结果，current 对应前一间为止。
            int next = Math.max(current, previous + money);
            // 先计算新结果，再依次移动状态，避免覆盖仍要使用的旧值。
            previous = current;
            current = next;
        }

        return current;
    }
}
