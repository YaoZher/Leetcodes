package HOT.Greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 *
 * 将字符串划分为尽可能多的连续片段，同一个字母只能出现在一个片段中。
 * 返回各片段的长度；题目保证字符串非空，且只包含小写英文字母。
 */
class Solution {
    /**
     * 贪心：当前片段必须包含其中每个字母的最后一次出现，满足后立即切分。
     * 时间复杂度为 O(n)，不计返回结果的额外空间复杂度为 O(1)。
     */
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> lengths = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // 当前字母在后面还有出现时，片段必须延伸到它最后出现的位置。
            end = Math.max(end, last[s.charAt(i) - 'a']);

            // 扫描到边界，说明片段内所有字母都不会再出现在后面。
            if (i == end) {
                lengths.add(end - start + 1);
                start = i + 1;
            }
        }

        return lengths;
    }
}
