import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {

    private ScoreManager scoreManager;

    @BeforeEach
    void setUp() {
        scoreManager = new ScoreManager();
    }

    @Test
    void should_return_correct_score_when_calculate_score_given_normal_score() throws Exception {
        int result = scoreManager.calculateScore("1 2;3 5;6 1;0 1;3 1;2 2;0 0;1 6;9 0;3 5");
        assertEquals(51, result);
    }

    @Test
    void should_return_correct_score_when_calculate_score_given_all_zero() throws Exception {
        int result = scoreManager.calculateScore("0 0;0 0;0 0;0 0;0 0;0 0;0 0;0 0;0 0;0 0");
        assertEquals(0, result);
    }

    @Test
    void should_return_correct_score_when_calculate_score_given_strike_score() throws Exception {
        int result = scoreManager.calculateScore("1 2;3 5;6 1;X;3 1;2 2;0 0;1 6;9 0;3 5");
        assertEquals(64, result);
        result = scoreManager.calculateScore("X;X;X;X;X;X;X;X;X;X X X");
        assertEquals(300, result);
    }

    @Test
    void should_return_correct_score_when_calculate_score_given_all_strike() throws Exception {
        int result = scoreManager.calculateScore("X;X;X;X;X;X;X;X;X;X X X");
        assertEquals(300, result);
    }

    @Test
    void should_return_correct_score_when_calculate_score_given_spare_score() throws Exception {
        int result = scoreManager.calculateScore("1 7;2 /;1 /;3 5;4 2;5 4;1 1;5 2;3 1;5 / 2");
        assertEquals(80, result);
    }

    @Test
    void should_return_correct_score_when_calculate_score_given_multi_case_score() throws Exception {
        int result = scoreManager.calculateScore("1 /;X;0 0;3 5;4 2;5 /;1 1;X;3 1;X 5 /");
        assertEquals(95, result);
    }

    @Test
    void should_throw_error_when_calculate_score_given_illegal_pattern() {
        assertThrows(Exception.class, () -> scoreManager.calculateScore("Hello World"));
    }

    @Test
    void should_throw_error_when_calculate_score_given_one_round_score_out_of_9() {
        assertThrows(Exception.class, () -> scoreManager.calculateScore("1 7;2 /;1 /;3 5;10 2;5 4;1 1;5 2;3 1;5 / 2"));
    }
}
