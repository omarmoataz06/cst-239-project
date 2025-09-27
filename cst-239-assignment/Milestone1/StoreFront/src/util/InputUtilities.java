package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtilities {
    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        int result = 0;
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print(prompt);
                result = Integer.parseInt(scanner.nextLine().trim());
                ok = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
        return result;
    }

    public int readInt(String prompt, int min, int max) {
        int result = 0;
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print(prompt);
                result = Integer.parseInt(scanner.nextLine().trim());
                if (result < min || result > max) {
                    System.out.printf("Enter a number between %d and %d.%n", min, max);
                } else {
                    ok = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
        return result;
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt + " (true/false): ");
            String s = scanner.nextLine().trim().toLowerCase();
            if ("true".equals(s) || "false".equals(s)) {
                return Boolean.parseBoolean(s);
            }
            System.out.println("Please enter 'true' or 'false'.");
        }
    }

    public LocalDate readLocalDate(String prompt, String pattern) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        while (true) {
            try {
                System.out.print(prompt + " (format: " + pattern + "): ");
                return LocalDate.parse(scanner.nextLine().trim(), fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        }
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}

