public class TrainingSession {
    private String date;
    private double duration;

    public TrainingSession(String date, double duration) {
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public double getDuration() {
        return duration;
    }
}
