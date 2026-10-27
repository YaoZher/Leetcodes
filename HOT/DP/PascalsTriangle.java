package HOT.DP;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 *
 * 生成杨辉三角的前 numRows 行，每行两端为 1，中间元素为上一行相邻两数之和。
 * 题目保证 1 <= numRows <= 30。
 */
class Solution {
    /**
     * 动态规划：第 i 行第 j 个数由上一行的第 j - 1 个数与第 j 个数相加得到。
     * 时间复杂度为 O(numRows^2)，包含返回结果的空间复杂度为 O(numRows^2)。
     * 除返回结果外，只需 O(1) 的额外空间。
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>(numRows);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            // 每一行都创建独立的列表，避免不同行共用同一个对象。
            List<Integer> row = new ArrayList<>(rowIndex + 1);

            for (int column = 0; column <= rowIndex; column++) {
                if (column == 0 || column == rowIndex) {
                    row.add(1);
                } else {
                    List<Integer> previousRow = triangle.get(rowIndex - 1);
                    row.add(previousRow.get(column - 1) + previousRow.get(column));
                }
            }

            triangle.add(row);
        }

        return triangle;
    }
}
