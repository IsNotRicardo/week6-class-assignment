import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    void testAddTrainingSessionInvalid() {
        try {
            aikido.addTrainingSession("01/02/2025", 1.5);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid date format. Please use dd-mm-yyyy", e.getMessage());
        }

        try {
            aikido.addTrainingSession("01-02-2025", 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Duration must be greater than 0", e.getMessage());
        }
    }

    @Test
    void testGetTotalTrainingTime() {
        aikido.addTrainingSession("01-03-2025", 2.5);
        aikido.addTrainingSession("02-03-2025", 4.0);
        aikido.addTrainingSession("20-01-2025", 2.0);
        assertEquals(8.5, aikido.getTotalTrainingTime(), 0);
    }

    @Test
    void testCheckGraduationEligibilityFalse() {
        assertFalse(aikido.checkGraduationEligibility());
        aikido.addTrainingSession("01-03-2025", 2.5);
        aikido.addTrainingSession("02-03-2025", 4.0);
        aikido.addTrainingSession("20-01-2025", 2.0);
        assertFalse(aikido.checkGraduationEligibility());
    }

    @Test
    void testCheckGraduationEligibilityMonths() {
        aikido.addTrainingSession("01-03-2025", 2.5);
        aikido.addTrainingSession("01-02-2026", 1.5);
        assertTrue(aikido.checkGraduationEligibility());
    }

    @Test
    void testCheckGraduationEligibilitySessions() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.of(2025, 3, 1);

        for (int i = 0; i < 100; i++) {
            aikido.addTrainingSession(date.format(dateFormatter), 1.0);
            date = date.plusDays(1);
        }
        assertTrue(aikido.checkGraduationEligibility());
    }
}
