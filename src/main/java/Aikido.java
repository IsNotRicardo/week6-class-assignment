import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Aikido {
    private static final int MINIMUM_TRAINING_DAYS = 180;
    private static final int MINIMUM_TRAINING_SESSIONS = 100;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final List<TrainingSession> trainingSessions = new ArrayList<>();

    public void addTrainingSession(String date, double duration) {
        trainingSessions.add(new TrainingSession(LocalDate.parse(date, dateFormatter), duration));
    }

    public double getTotalTrainingTime() {
        return trainingSessions.stream().mapToDouble(TrainingSession::getDuration).sum();
    }

    public boolean checkGraduationEligibility() {
        return getDaysBetweenDates() >= MINIMUM_TRAINING_DAYS || trainingSessions.size() >= MINIMUM_TRAINING_SESSIONS;
    }

    // TODO: Optimise the calculation of days between dates
    private double getDaysBetweenDates() {
        LocalDate smallestDate = trainingSessions.getFirst().getDate();
        LocalDate largestDate = trainingSessions.getFirst().getDate();

        for (TrainingSession trainingSession : trainingSessions) {
            String[] dateSplit = trainingSession.getDate().format(dateFormatter).split("-");
            String[] smallestTempSplit = smallestDate.format(dateFormatter).split("-");
            String[] largestTempSplit = largestDate.format(dateFormatter).split("-");

            if (dateSplit[2].compareTo(smallestTempSplit[2]) < 0) {
                smallestDate = trainingSession.getDate();
            } else if (dateSplit[2].compareTo(largestTempSplit[2]) > 0) {
                largestDate = trainingSession.getDate();
            } else {
                if (dateSplit[1].compareTo(smallestTempSplit[1]) < 0) {
                    smallestDate = trainingSession.getDate();
                } else if (dateSplit[1].compareTo(largestTempSplit[1]) > 0) {
                    largestDate = trainingSession.getDate();
                } else {
                    if (dateSplit[0].compareTo(smallestTempSplit[0]) < 0) {
                        smallestDate = trainingSession.getDate();
                    } else if (dateSplit[0].compareTo(largestTempSplit[0]) > 0) {
                        largestDate = trainingSession.getDate();
                    }
                }
            }
        }

        String[] smallestDateSplit = smallestDate.format(dateFormatter).split("-");
        String[] largestDateSplit = largestDate.format(dateFormatter).split("-");

        return (Integer.parseInt(largestDateSplit[2]) - Integer.parseInt(smallestDateSplit[2])) * 365 +
                (Integer.parseInt(largestDateSplit[1]) - Integer.parseInt(smallestDateSplit[1])) * 30 +
                (Integer.parseInt(largestDateSplit[0]) - Integer.parseInt(smallestDateSplit[0]));
    }
}
