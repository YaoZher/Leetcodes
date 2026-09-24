package HOT.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 * 返回数组中出现次数最多的 k 个不同元素，答案顺序不限。
 * 题目保证 k 合法，且答案集合唯一。
 */
class Solution {
    /**
     * 哈希表统计频次，再按频次分桶，从高频桶向低频桶收集答案。
     * 设 n 为数组长度，平均时间复杂度为 O(n)，空间复杂度为 O(n)。
     * 每个不同元素只进入一个桶，因此遍历所有桶内元素的总次数不超过 n。
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }

        // 一个元素最多出现 nums.length 次，桶下标就是出现次数。
        // 先保留空桶位置，只为实际出现的频次创建列表。
        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(null);
        }

        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int frequency = entry.getValue();
            if (buckets.get(frequency) == null) {
                buckets.set(frequency, new ArrayList<>());
            }
            buckets.get(frequency).add(entry.getKey());
        }

        int[] answer = new int[k];
        int size = 0;
        for (int frequency = nums.length; frequency >= 1; frequency--) {
            List<Integer> bucket = buckets.get(frequency);
            if (bucket == null) {
                continue;
            }

            for (int num : bucket) {
                answer[size++] = num;
                // 同频元素无需排序，收集满 k 个即可结束。
                if (size == k) {
                    return answer;
                }
            }
        }

        // 题目保证 k 合法，因此有效输入会在收集满 k 个元素时返回。
        throw new IllegalArgumentException("k exceeds the number of distinct elements");
    }
}
