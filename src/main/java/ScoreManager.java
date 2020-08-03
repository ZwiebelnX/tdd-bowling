import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreManager {

    /**
     * 根据输入计算最终分数
     * @param pattern 输入规则如下：
     *                  1. 使用字符串输入，每轮之间使用英文分号;分隔，每轮中的击球情况使用空格 进行分隔
     *                  2. 特殊情况：英文大写字母X表示全中(strike)，斜杠/表示补中(spare)
     *                  3. 输入规则遵循保龄球规则
     * @return 根据规则计算的分数
     * @throws Exception 输入不符合规范
     */
    public int calculateScore(String pattern) throws Exception {
        if (!pattern.matches("((X|[0-9]\\s[0-9]|[0-9]\\s/);){9}" +
            "(X\\sX\\sX|X\\sX\\s[0-9]|X\\s[0-9]\\s/|X\\s[0-9]\\s[0-9]|[0-9]\\s/\\s[0-9]|[0-9]\\s[0-9])")) {
            throw new Exception("pattern error!");
        }

        int totalScore = 0;
        String[] roundPatternList = pattern.split(";");

        for (int round = 0; round < roundPatternList.length; round++) {
            String[] roundPattern = roundPatternList[round].split(" ");
            if (round == 9) {
                totalScore += calculateLastRound(roundPattern);
            } else {
                if (roundPattern.length == 2) {
                    if (roundPattern[1].equals("/")) {
                        totalScore += 10 + calculateBonus(roundPatternList, round, 1);
                    } else {
                        totalScore = totalScore + Integer.parseInt(roundPattern[0]) + Integer.parseInt(roundPattern[1]);
                    }
                } else {
                    totalScore += 10 + calculateBonus(roundPatternList, round, 2);
                }
            }
        }
        return totalScore;
    }

    /**
     * 计算X或/带来的奖励分数
     * @param roundPatternList 含有每轮比分表达式的数组
     * @param currentRound 当前轮数
     * @param bonusBalls 奖励球数
     * @return 奖励分数
     */
    private int calculateBonus(String[] roundPatternList, int currentRound, int bonusBalls) {
        int result = 0;
        String[] subRoundPatternStrings = Arrays.copyOfRange(roundPatternList, currentRound + 1, roundPatternList.length);
        List<String> subBallsPattern = Arrays.stream(subRoundPatternStrings)
                                        .map(s -> s.split(" "))
                                        .flatMap(Arrays::stream)
                                        .collect(Collectors.toList());
        for (int i = 0; i < bonusBalls; i++) {
            if (subBallsPattern.get(i).equals("X")) {
                result += 10;
            } else if (subBallsPattern.get(i).equals("/")) {
                result = result + (10 - Integer.parseInt(subBallsPattern.get(i - 1)));
            } else {
                result += Integer.parseInt(subBallsPattern.get(i));
            }
        }
        return result;
    }

    /**
     * 计算最后一轮的分数
     * @param lastRoundPattern 最后一轮的分数表达式
     * @return 最后一轮分数
     */
    private int calculateLastRound(String[] lastRoundPattern) {
        int result = 0;
        if (lastRoundPattern.length == 2) {
            result = Integer.parseInt(lastRoundPattern[0]) + Integer.parseInt(lastRoundPattern[1]);
        } else {
            for (int i = 0; i < lastRoundPattern.length; i++) {
                if (lastRoundPattern[i].equals("X")) {
                    result += 10;
                } else if (lastRoundPattern[i].equals("/")) {
                    result += (10 - Integer.parseInt(lastRoundPattern[i - 1]));
                } else {
                    result += Integer.parseInt(lastRoundPattern[i]);
                }
            }
        }
        return result;
    }
}
