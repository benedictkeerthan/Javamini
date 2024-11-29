import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class minipro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'DOB=dd-MM-yyyy' or 'AGE=yy-MM-dd': ");
        String input = scanner.nextLine();

        System.out.println("Enter the reference date (e.g., 27-02-2024): ");
        String referenceDateStr = scanner.nextLine();

        System.out.println("Enter date format (e.g., dd-MM-yyyy): ");
        String format = scanner.nextLine();
        System.out.println("Enter delimiter character (e.g., '-'): ");
        String delimiter = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.replace("-", delimiter));
        LocalDate referenceDate = LocalDate.parse(referenceDateStr, formatter);

        if (input.startsWith("DOB=")) {
            String dobStr = input.substring(4);
            LocalDate dob = LocalDate.parse(dobStr, formatter);
            printAge(dob, referenceDate);
        } else if (input.startsWith("AGE=")) {
            String[] ageParts = input.substring(4).split(delimiter);
            int years = Integer.parseInt(ageParts[0]);
            int months = Integer.parseInt(ageParts[1]);
            int days = Integer.parseInt(ageParts[2]);
            LocalDate dob = referenceDate.minusYears(years).minusMonths(months).minusDays(days);
            System.out.println("DOB is: " + dob.format(formatter));
        } else {
            System.out.println("Invalid input. Use 'DOB=' or 'AGE=' format.");
        }

        scanner.close();
    }

    private static void printAge(LocalDate dob, LocalDate referenceDate) {
        Period period = Period.between(dob, referenceDate);
        System.out.printf("Age is: %d years, %d months, %d days%n", period.getYears(), period.getMonths(), period.getDays());
    }
}
