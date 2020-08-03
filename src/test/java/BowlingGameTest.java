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
    void should_return_correct_score_when_calculate_score_given_strike_score() throws Exception {
        int result = scoreManager.calculateScore("1 2;3 5;6 1;X;3 1;2 2;0 0;1 6;9 0;3 5");
        assertEquals(64, result);
    }

    @Test
    void should_throw_error_when_calculate_score_given_illegal_pattern() {
        assertThrows(Exception.class, () -> scoreManager.calculateScore("Hello World"));
    }
}
