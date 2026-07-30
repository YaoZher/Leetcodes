package HOT.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739. 每日温度
 *
 * 对于每天的温度，找出未来第一个比今天更高的温度，
 * 返回需要等待的天数；如果未来没有更高温度，答案就是 0。
 */
class Solution {
    /**
     * 使用单调递减栈解决问题。
     *
     * 栈中保存的是数组下标，而不是温度值。
     * 这样找到更高温度时，可以用当前下标减去之前的下标，
     * 得到需要等待的天数。
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 没有找到更高温度的元素，答案默认就是 0。
        int[] answer = new int[temperatures.length];

        // Deque 是双端队列，这里把它当作栈使用：
        // push 入栈，pop 出栈，peek 查看栈顶。
        // 栈中下标对应的温度从栈底到栈顶保持递减。
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            // 当前温度比栈顶那一天的温度高，
            // 说明当前这一天就是栈顶元素等待的答案。
            while (!stack.isEmpty()
                    && temperatures[i] > temperatures[stack.peek()]) {
                int previous = stack.pop();
                answer[previous] = i - previous;
            }

            // 当前温度暂时还没有找到更高温度，先记录它的下标。
            stack.push(i);
        }

        return answer;
    }
}
