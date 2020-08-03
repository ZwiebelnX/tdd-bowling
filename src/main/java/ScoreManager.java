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
     */
    public int calculateScore(String pattern) {
        int totalScore = 0;
        String[] roundPatternList = pattern.split(";");

        for (int round = 0; round < roundPatternList.length; round++) {
            String[] roundPattern = roundPatternList[round].split(" ");
            if (round == 9) {
                if (roundPattern.length == 2) {
                    totalScore = totalScore + Integer.parseInt(roundPattern[0]) + Integer.parseInt(roundPattern[1]);
                } else {
                    for (int i = 0; i < roundPattern.length; i++) {
                        if (roundPattern[i].equals("X")) {
                            totalScore += 10;
                        } else if (roundPattern[i].equals("/")) {
                            totalScore += (10 - Integer.parseInt(roundPattern[i - 1]));
                        }
                    }
                }
            } else {
                if (roundPattern.length == 2) {
                    totalScore = totalScore + Integer.parseInt(roundPattern[0]) + Integer.parseInt(roundPattern[1]);
                } else {
                    totalScore += 10 + calculateBonus(roundPatternList, round, 2);
                }
            }
        }



        return totalScore;
    }

    int calculateBonus(String[] roundPattern, int currentRound, int bonusBalls) {
        int result = 0;
        String[] subRoundPatternStrings = Arrays.copyOfRange(roundPattern, currentRound + 1, roundPattern.length);
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
}
