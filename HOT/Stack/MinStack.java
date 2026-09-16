package HOT.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. 最小栈
 *
 * 设计一个支持入栈、出栈、获取栈顶和获取最小值的栈。
 * 题目保证 pop、top 和 getMin 都在非空栈上调用。
 */
class MinStack {
    private final Deque<Integer> values;
    private final Deque<Integer> minimums;

    public MinStack() {
        values = new ArrayDeque<>();
        minimums = new ArrayDeque<>();
    }

    /**
     * 两个栈始终保持相同的元素数量。
     * minimums 的每一层保存 values 到这一层为止的最小值。
     * push 的均摊时间复杂度为 O(1)，其余操作为 O(1)，空间复杂度为 O(n)。
     */
    public void push(int val) {
        values.push(val);

        // 即使最小值没有变化，也记录一次，保证两个栈可以同步弹出。
        int currentMin = minimums.isEmpty() ? val : Math.min(val, minimums.peek());
        minimums.push(currentMin);
    }

    public void pop() {
        values.pop();

        // 同步移除当前层的最小值，露出的就是上一层的最小值。
        minimums.pop();
    }

    public int top() {
        return values.peek();
    }

    public int getMin() {
        return minimums.peek();
    }
}
