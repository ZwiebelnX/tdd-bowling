public class ScoreManager {

    /**
     * 根据输入计算最终分数
     * @param pattern 输入规则如下：
     *                  1. 使用字符串输入，每轮之间使用英文分号;分隔，每轮中的击球情况使用空格 进行分隔
     *                  2. 特殊情况：英文大写字母X表示全中(strike)，斜杠/表示补中(spare)
     *                  3. 输入规则遵循保龄球规则
     * @return 根据规则计算的分数
     */
    public int calculateScore(String pattern) {
        int totalScore = 0;
        String[] roundStringList = pattern.split(";");
        for (String s : roundStringList) {
            String[] condition = s.split(" ");
            totalScore = totalScore + Integer.parseInt(condition[0]) + Integer.parseInt(condition[1]);
        }
        return totalScore;
    }
}
