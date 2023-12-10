package student.db;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Input validator to validate user inputs in case of wrong inputs entered
 */

class InputValidator {

    //Validate student ID
    static String validateID(Scanner scan) {
        while(true) {
            System.out.print("\nEnter a student's ID (e.g. 8-digit number): ");
            String ID = scan.nextLine(); 

            //If contains only numbers and length is a 8, return an ID
            if(ID.matches("[0-9]+") && ID.length() == 8) {
                return ID;
            }
            System.out.println("\nInvalid ID. Please try again!");
        }
    }

    //Validate student telephone number
    static long validateTelNumber(Scanner scan) {
        while(true) {
            try {
                System.out.print("\nEnter a student's phone number (e.g. 6261882331): ");
                long telNum = scan.nextLong();

                //If number's length is 10, return a telephone number
                if(String.valueOf(telNum).length() == 10) {
                    scan.nextLine();
                    return telNum;
                }
                System.out.println("\nInvalid phone number. Please try again!");
            }
            catch(InputMismatchException a) {
                System.out.println("\nInvalid phone number. Please try again!");
                scan.nextLine();
            }
        }
    }

    //Validate student GPA
    static double validateGPA(Scanner scan) {
         while(true) {
            try {
                System.out.print("\nEnter a student's GPA (e.g. 3.7): ");
                double GPA = scan.nextDouble();

                //If GPA is less or equal to 4.0, and greater or equals to 0.0, return a GPA
                if(GPA <= 4.0 && GPA >= 0.0) {
                    scan.nextLine();
                    return GPA;
                }
                System.out.println("\nInvalid GPA. Please try again!");
            }
            catch(InputMismatchException a) {
                System.out.println("\nInvalid GPA. Please try again!");
                scan.nextLine();
            }
        }
    }

    //Validate student year of birth
    static int validateYearOfBirth(Scanner scan) {
        while(true) {
            try {
                System.out.print("\nEnter a student's year of birth (e.g. 1999): ");
                int year = scan.nextInt();

                //If students year of birth is between 1900 and 2015, return a year of birth
                if(year >= 1900 && year <= 2015) {
                    scan.nextLine();
                    return year;
                }
                System.out.println("\nInvalid year of birth. Please try again!");
            }
            catch(InputMismatchException a) {
                System.out.println("\nInvalid year of birth. Please try again!");
                scan.nextLine();
            }
        }
    }

    //Validate student month of birth
    static int validateMonthOfBirth(Scanner scan) {
         while(true) {
            try {
                System.out.print("\nEnter a student's month of birth (e.g. 2): ");
                int month = scan.nextInt();

                //If month is between 1 and 12, return a month of birth
                if(month >= 1 && month <= 12) {
                    scan.nextLine();
                    return month;
                }
                System.out.println("\nInvalid month of birth. Please try again!");
            }
            catch(InputMismatchException a) {
                System.out.println("\nInvalid month of birth. Please try again!");
                scan.nextLine();
            }
        }
    }

    //Validate student date of birth
    static LocalDate validateDateOfBirth(Scanner scan) {
        while(true) {
            try {
                System.out.print("\nEnter a student's date of birth (e.g. yyyy-MM-dd): ");
                String date = scan.nextLine();

                //If a scanned string can be parsed to LocalDate, return a date of birth
                LocalDate dateOfBirth = LocalDate.parse(date);
                return dateOfBirth;
            }
            catch(InputMismatchException a) {
                System.out.println("\nInvalid date of birth. Please try again!");
            }
            catch(DateTimeException a) {
                System.out.println("\nInvalid date of birth. Please try again!");
            }
        }
    }

    //Validate sorting criteria
    static String[] validateCriteria(Scanner scan) {
        System.out.println("\n1. Sort by ID in ascending order");
        System.out.println("2. Sort by ID in descending order");
        System.out.println("3. Sort by GPA in ascending order");
        System.out.println("4. Sort by GPA in descending order");
        System.out.println("5. Sort by Date of Birth in ascending order");
        System.out.println("6. Sort by Date of Birth in descending order");

        while(true) {
            System.out.print("\nEnter an option (e.g. 1-6): ");
            String criteria = scan.nextLine();
            
            //Check if the inputed string is between 1 and 6
            switch (criteria) {
                case "1":
                    return new String[]{"ID", "1"};
                case "2":
                    return new String[]{"ID", "-1"};
                case "3":
                    return new String[]{"GPA", "1"};
                case "4":
                    return new String[]{"GPA", "-1"};
                case "5":
                    return new String[]{"DateOfBirth", "1"};
                case "6":
                    return new String[]{"DateOfBirth", "-1"};
                default:
                    System.out.println("\nInvalid option. Please try again!");
            }
        }
    }

    //Validate record attribute
    static String validateAttribute(Scanner scan) {
        System.out.println("\n1. Display student records by ID");
        System.out.println("2. Display student records by Major");

        while(true) {
            System.out.print("\nEnter an option (e.g. 1 or 2): ");
            String criteria = scan.nextLine();
            
            //Check if input is 1 or 2
            switch (criteria) {
                case "1":
                    return "ID";
                case "2":
                    return "Major";
                default:
                    System.out.println("\nInvalid option. Please try again!");
            }
        }
    }
}
