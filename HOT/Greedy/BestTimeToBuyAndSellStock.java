package HOT.Greedy;

/**
 * 121. 买卖股票的最佳时机
 *
 * 最多进行一次交易：先买入，再在之后的某一天卖出。
 * 返回能获得的最大利润；无法获利时返回 0。
 * 题目保证价格数组至少包含一个元素。
 */
class Solution {
    /**
     * 贪心：确定卖出日后，应选择此前价格最低的一天买入。
     * 一次遍历维护历史最低价和最大利s润。
     * 时间复杂度为 O(n)，额外空间复杂度为 O(1)。
     */
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int day = 1; day < prices.length; day++) {
            
            // 此时 minPrice 只来自今天之前，保证买入日早于卖出日。
            maxProfit = Math.max(maxProfit, prices[day] - minPrice);

            // 将今天的价格纳入最低价，供之后的卖出日使用。
            minPrice = Math.min(minPrice, prices[day]);
        }

        return maxProfit;
    }
}
