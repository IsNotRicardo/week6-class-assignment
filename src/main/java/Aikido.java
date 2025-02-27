import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aikido {
    private static final int MINIMUM_TRAINING_DAYS = 180;
    private static final int MINIMUM_TRAINING_SESSIONS = 100;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final List<TrainingSession> trainingSessions = new ArrayList<>();

    public void addTrainingSession(String date, double duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-mm-yyyy");
        }
        trainingSessions.add(new TrainingSession(LocalDate.parse(date, dateFormatter), duration));
    }

    public double getTotalTrainingTime() {
        return trainingSessions.stream().mapToDouble(TrainingSession::getDuration).sum();
    }

    public boolean checkGraduationEligibility() {
        return getDaysBetweenDates() >= MINIMUM_TRAINING_DAYS || trainingSessions.size() >= MINIMUM_TRAINING_SESSIONS;
    }

    private long getDaysBetweenDates() {
        if (trainingSessions.isEmpty()) {
            return 0;
        }

        List<LocalDate> dates = trainingSessions.stream()
                .map(TrainingSession::getDate)
                .toList();

        LocalDate earliestDate = Collections.min(dates);
        LocalDate latestDate = Collections.max(dates);

        return ChronoUnit.DAYS.between(earliestDate, latestDate);
    }
}
