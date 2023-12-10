package student.db;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner; 

/*
 * StudentDatabaseApp displays an interface and interacts with the user
 * It manages a student information database and executes orders given by the user
 */

public class StudentDatabaseApp {
    private static MyLinkedList studentRecords;
    private static Scanner scan;

    //Check if the database of student records is empty
    public static boolean isEmpty() {
        return studentRecords.getSize() == 0;
    }

    //Insert a new student record in the database
    public static void insertStudentRecord() {
        String ID = InputValidator.validateID(scan);
        System.out.print("\nEnter a student's first name: ");
        String firstName = scan.nextLine();
        System.out.print("\nEnter a student's last name: ");
        String lastName = scan.nextLine();
        long telNumber = InputValidator.validateTelNumber(scan);
        System.out.print("\nEnter a student's major: ");
        String major = scan.nextLine();
        double GPA = InputValidator.validateGPA(scan);
        int yearOfBirth = InputValidator.validateYearOfBirth(scan);
        int monthOfBirth = InputValidator.validateMonthOfBirth(scan);
        LocalDate dateOfBirth = InputValidator.validateDateOfBirth(scan);
        System.out.print("\nEnter a student's home address (e.g. Street, City, State Postal Code): ");
        String homeAddress = scan.nextLine();
        studentRecords.addStudent(new StudentRecord(ID, firstName, lastName, telNumber, major, GPA, yearOfBirth, monthOfBirth, dateOfBirth, homeAddress));
        System.out.println("\nStudent succesfully added to the database!\n");
    }

    //Delete a student record from the database by ID
    public static void deleteStudentRecordByID() {
        if(isEmpty()) {
            System.out.println("\nStudent database is empty!\n");
            return;
        }
        String ID = InputValidator.validateID(scan);
        int deletedRecords = studentRecords.deleteStudentByID(ID);

        if(deletedRecords == 0) {
            System.out.println("\nStudent/students with ID " + ID + " not found in the database!\n");
        }
        else {
            System.out.println("\n" + deletedRecords + " student/student's with ID " + ID + " deleted from the database!\n");
        }
    }

    //List all of the student records from the database
    public static void listStudentRecords() {
        if(isEmpty()) {
            System.out.println("\nStudent database is empty!\n");
            return;
        }
        studentRecords.listAllRecords("All", "");
    }

    //Reorder database of student records
    public static void reorderStudentRecords() {
        if(isEmpty()) {
            System.out.println("\nStudent database is empty!\n");
            return;
        }
        String[] criteria = InputValidator.validateCriteria(scan);
        String sortBy = criteria[0]; //Either GPA, ID, or DateOfBirth
        int order = 1; //Ascending order

        if(criteria[1].equals("-1"))
            order = -1; //Descending order
        StudentRecordsSorter.sortRecords(studentRecords, order, sortBy);
        System.out.println("\nStudents succesfully sorted!\n");
    }

    //List all of the student records from database by ID or Major
    public static void listStudentRecordsByAttribute() {
        if(isEmpty()) {
            System.out.println("\nStudent database is empty!\n");
            return;
        }
        String attribute = InputValidator.validateAttribute(scan);

        //List ID
        if(attribute.equals("ID")) {
            String ID = InputValidator.validateID(scan);
            studentRecords.listAllRecords(attribute, ID);
        }
        //List by Major
        else if(attribute.equals("Major")) {
            System.out.print("\nEnter a student's major (e.g. Computer Science): ");
            String major = scan.nextLine(); 
            studentRecords.listAllRecords(attribute, major);
        }
    }

    //Display user interface and list of menagment options
    public static void displayInterface() {

        scan = new Scanner(System.in);
        outerloop : while(true) {
            System.out.println("\nSTUDENT INFORMATION DATABASE\n");
            System.out.println("1. Insert a new record in the database");
            System.out.println("2. Delete record/records with given student ID");
            System.out.println("3. List all student records");
            System.out.println("4. Reoder student records");
            System.out.println("5. List student records in a given major, or a given ID");
            System.out.println("6. Exit a student database\n");
            System.out.print("Enter an option (e.g. 1-6): ");

            innerLoop : while(true) {
                try {
                    int option = scan.nextInt();
                    scan.nextLine();
                    
                    switch (option) {
                        case 1:
                            insertStudentRecord();
                            break innerLoop;
                        case 2:
                            deleteStudentRecordByID();
                            break innerLoop;
                        case 3:
                            listStudentRecords();
                            break innerLoop;
                        case 4:
                            reorderStudentRecords();
                            break innerLoop;
                        case 5:
                            listStudentRecordsByAttribute(); 
                            break innerLoop;
                        case 6:
                            System.out.println("\nStudent database exited!\n"); //Exit a database
                            break outerloop;
                        default:
                            System.out.println("\nInvalid option. Please try again!");
                            System.out.print("\nEnter an option (e.g. 1-6): ");
                    }
                }
                catch(InputMismatchException a) {
                    System.out.println("\nInvalid option. Please try again!");
                    System.out.print("\nEnter an option (e.g. 1-6): ");
                    scan.nextLine();
                }
            }
        }
        scan.close(); 
        MyLinkedListSerialization.saveStudentRecords(studentRecords); //save student records
    }
    
    public static void main(String[] args) {
        studentRecords = MyLinkedListSerialization.loadStudentRecords(); //load student records
        displayInterface();
    }
}
