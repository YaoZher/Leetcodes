package HOT.Arrays;

/**
 * 215. 数组中的第 K 个最大元素
 *
 * 返回数组按从大到小排列后的第 k 个元素，重复值也计入名次。
 * 题目保证 1 <= k <= nums.length，且元素范围为 [-10000, 10000]。
 */
class Solution {
    /**
     * 利用题目给定的有限值域统计频次，不需要排序，也不会修改原数组。
     * 设 n 为数组长度，R 为值域大小，时间复杂度为 O(n + R)，空间为 O(R)。
     * 本题 R 固定为 20001，因此相对于 n，时间为 O(n)，额外空间为 O(1)。
     */
    public int findKthLargest(int[] nums, int k) {
        final int offset = 10000;
        int[] frequencies = new int[2 * offset + 1];

        // 将 [-10000, 10000] 映射到合法下标 [0, 20000]。
        for (int num : nums) {
            frequencies[num + offset]++;
        }

        // 从最大值向最小值遍历，每个值占据的名次数量等于它的出现次数。
        for (int index = frequencies.length - 1; index >= 0; index--) {
            k -= frequencies[index];

            // 扣除当前值的次数后跨过目标名次，说明答案就是当前值。
            if (k <= 0) {
                return index - offset;
            }
        }

        // 题目保证 k 合法，因此有效输入不会执行到这里。
        throw new IllegalArgumentException("k exceeds the number of elements");
    }
}
