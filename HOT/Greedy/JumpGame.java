package HOT.Greedy;

/**
 * 55. 跳跃游戏
 *
 * 从数组下标 0 出发，nums[i] 表示在下标 i 最多可以向前跳多远。
 * 判断能否到达最后一个下标；题目保证数组非空，且元素都是非负整数。
 */
class Solution {
    /**
     * 贪心：维护从起点出发最远能够到达的下标。
     * 只用已经能够到达的位置扩展范围，不需要枚举每一条跳跃路线。
     * 时间复杂度为 O(n)，额外空间复杂度为 O(1)。
     */
    public boolean canJump(int[] nums) {
        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {
            // 当前位置已经超出可达范围，后面的元素也无法帮助跨过这个缺口。
            if (i > farthest) {
                return false;
            }

            farthest = Math.max(farthest, i + nums[i]);

            // 可以选择跳跃长度，因此最远范围覆盖终点时，就能到达终点。
            if (farthest >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}
