package app.util;


import java.util.Scanner;

public class InputReader {

    private Scanner sc;

    public InputReader(Scanner sc) {
        this.sc = sc;
    }

    public int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } 
            	catch (NumberFormatException e) {
            		System.out.println("Invalid input. Please enter an integer number.");
            }
        }
    }

    public double readDouble(String message) {
        while (true) {
            System.out.print(message);

            try {
                double value = Double.parseDouble(sc.nextLine().replace(",", "."));
                return value;
            } 
            	catch (NumberFormatException e) {
            		System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public String readNonBlankString(String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Invalid input. This field cannot be empty.");
        }
    }
}