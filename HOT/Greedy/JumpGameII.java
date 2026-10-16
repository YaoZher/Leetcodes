package HOT.Greedy;

/**
 * 45. 跳跃游戏 II
 *
 * 从下标 0 出发，nums[i] 表示在位置 i 最多可以向前跳多远。
 * 返回到达最后一个下标的最少跳跃次数，题目保证一定可以到达终点。
 */
class Solution {
    /**
     * 贪心分层：扫描当前跳跃次数能覆盖的范围，寻找再跳一次的最远位置。
     * 每扫描完一层，就增加一次跳跃，并扩展到下一层的范围。
     * 时间复杂度为 O(n)，额外空间复杂度为 O(1)。
     */
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // 终点不需要再起跳；只有一个元素时，直接返回 0。
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // 当前层已扫描完，再增加一次跳跃才能扩展可达范围。
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}

//26.10.16
