import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AikidoTest {
    private Aikido aikido;

    @BeforeEach
    void setUp() {
        aikido = new Aikido();
    }

    @Test
    void testAddTrainingSession() {
        aikido.addTrainingSession("01-02-2025", 1.5);
        aikido.addTrainingSession("01-03-2025", 2.0);
        aikido.addTrainingSession("20-02-2025", 1.0);
        assertEquals(4.5, aikido.getTotalTrainingTime(), 0);
    }

    @Test
    void testGetTotalTrainingTime() {
        aikido.addTrainingSession("01-03-2025", 2.5);
        aikido.addTrainingSession("02-03-2025", 4.0);
        aikido.addTrainingSession("20-01-2025", 2.0);
        assertEquals(8.5, aikido.getTotalTrainingTime(), 0);
    }

    @Test
    void testCheckGraduationEligibility() {
        aikido.addTrainingSession("01-03-2025", 2.5);
        aikido.addTrainingSession("02-03-2024", 4.0);
        aikido.addTrainingSession("20-01-2025", 2.0);
        assertFalse(aikido.checkGraduationEligibility());
        aikido.addTrainingSession("01-02-2026", 1.5);
        assertTrue(aikido.checkGraduationEligibility());
    }
}
