package HOT.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 *
 * 支持不断加入整数，并随时查询已加入元素的中位数。
 * 题目保证调用 findMedian 时至少已经加入一个元素。
 */
class MedianFinder {
    // 较小的一半使用最大堆，堆顶是这一半中最大的数。
    private final PriorityQueue<Integer> lower;
    // 较大的一半使用最小堆，堆顶是这一半中最小的数。
    private final PriorityQueue<Integer> upper;

    public MedianFinder() {
        lower = new PriorityQueue<>(Collections.reverseOrder());
        upper = new PriorityQueue<>();
    }

    /**
     * 保持 lower 中的所有数都不大于 upper 中的所有数。
     * 两个堆的大小相同，或者 lower 比 upper 多一个元素。
     * 设已加入的元素个数为 n，单次插入的均摊时间为 O(log n)。
     */
    public void addNum(int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
        } else {
            upper.offer(num);
        }

        // 每次只加入一个数，因此最多移动一个堆顶即可恢复大小平衡。
        if (lower.size() > upper.size() + 1) {
            upper.offer(lower.poll());
        } else if (lower.size() < upper.size()) {
            lower.offer(upper.poll());
        }
    }

    /**
     * 查询时间为 O(1)，两个堆占用的总空间为 O(n)。
     */
    public double findMedian() {
        if (lower.size() > upper.size()) {
            return lower.peek();
        }

        // 先转为 long 再相加，避免整数溢出；除以 2.0 保留小数部分。
        return ((long) lower.peek() + upper.peek()) / 2.0;
    }
}
