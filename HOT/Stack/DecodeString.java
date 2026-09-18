package HOT.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 394. 字符串解码
 *
 * 将 k[encoded_string] 解码为括号内字符串重复 k 次的结果，支持嵌套。
 * 题目保证输入格式有效，数字只表示重复次数。
 */
class Solution {
    /**
     * 使用两个栈分别保存外层的重复次数和已经拼接的字符串。
     * 设 n 为输入长度，m 为输出长度，d 为最大括号嵌套深度。
     * 时间复杂度上界为 O(n + m * d)，空间复杂度为 O(m + d)。
     * 嵌套时，内层字符串可能在合并到每一层外层时被再次复制。
     */
    public String decodeString(String s) {
        Deque<Integer> repeatCounts = new ArrayDeque<>();
        Deque<StringBuilder> prefixes = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                // 重复次数可能有多位，例如 12[a] 中的 12。
                count = count * 10 + c - '0';
            } else if (c == '[') {
                // 保存外层状态，开始处理新一层括号内的内容。
                repeatCounts.push(count);
                prefixes.push(current);
                current = new StringBuilder();
                count = 0;
            } else if (c == ']') {
                int repeat = repeatCounts.pop();
                StringBuilder prefix = prefixes.pop();

                // 内层已经解码完成，将它重复后接到外层已有内容后面。
                for (int j = 0; j < repeat; j++) {
                    prefix.append(current);
                }
                current = prefix;
            } else {
                current.append(c);
            }
        }

        return current.toString();
    }
}
