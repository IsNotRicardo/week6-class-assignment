import java.time.LocalDate;

public class TrainingSession {
    private LocalDate date;
    private double duration;

    public TrainingSession(LocalDate date, double duration) {
        this.date = date;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getDuration() {
        return duration;
    }
}
