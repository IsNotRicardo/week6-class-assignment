import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Aikido aikido = new Aikido();

        while (true) {
            System.out.println("\n===== Aikido Practice Tracker =====\n");

            System.out.println("1. Add Practice Session");
            System.out.println("2. View Total Practice Time");
            System.out.println("3. Check Graduation Eligibility");
            System.out.println("4. Exit");

            System.out.print("\nChoose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    String date;
                    while (true) {
                        System.out.print("\nEnter the date of the practice session (dd-mm-yyyy): ");
                        date = scanner.nextLine();

                        if (date.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            break;
                        } else {
                            System.out.println("Invalid date format. Please try again.");
                        }
                    }

                    double duration;
                    while (true) {
                        System.out.print("\nEnter the duration of the practice session (in hours): ");
                        try {
                            duration = Double.parseDouble(scanner.nextLine());
                            aikido.addTrainingSession(date, duration);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid duration. Please try again.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nTotal practice time: " + aikido.getTotalTrainingTime() + " hours");
                    break;
                case 3:
                    System.out.println("\nGraduation eligibility: " + (aikido.checkGraduationEligibility() ? "Eligible" : "Not eligible"));
                    break;
                case 4:
                    System.out.println("\nExiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
}
