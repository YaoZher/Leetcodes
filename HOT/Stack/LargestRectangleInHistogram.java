package HOT.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 *
 * 给定每根柱子的高度，求柱状图中能够组成的最大矩形面积。
 */
class Solution {
    /**
     * 使用单调递增栈。
     * 栈中保存柱子的下标，并且这些下标对应的高度从栈底到栈顶递增。
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        // 多遍历一次，并把最后一根“虚拟柱子”的高度设为 0，
        // 用来强制结算栈中剩余的所有柱子。
        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = i == heights.length ? 0 : heights[i];

            // 当前柱子更矮，说明栈顶柱子的右边界已经确定为 i - 1。
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];

                // 弹栈后新的栈顶是左边第一个更矮的柱子。
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftBoundary - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            // 当前柱子暂时还没有找到右边更矮的柱子，记录它的下标。
            stack.push(i);
        }

        return maxArea;
    }
}
