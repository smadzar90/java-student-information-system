package student.db;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Represents a student record, containing the information about the student
 */

class StudentRecord implements Serializable  {
    private String id;
    private String firstName;
    private String lastName;
    private long telNumber;
    private String major;
    private double GPA;
    private int yearOfBirth;
    private int monthOfBirth;
    private LocalDate dateOfBirth;
    private String homeAddress;
    
    StudentRecord(String id, String firstName, String lastName, long telNumber, String major, 
            double GPA, int yearOfBirth, int monthOfBirth, LocalDate dateOfBirth, String homeAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.major = major;
        this.GPA = GPA;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
    }

    //Return student's ID
    String getID() {
        return id;
    }

    //Return student's GPA
    double getGPA() {
        return GPA;
    }

    //Return student's date of birth
    LocalDate getDate() {
        return dateOfBirth;
    }

    //Return student's major
    String getMajor() {
        return major;
    }

    //Display a student record
    void printStudentRecord() {
        System.out.printf(" %-20s %s%n", "ID", id);
        System.out.printf(" %-20s %s%n", "First Name", firstName);
        System.out.printf(" %-20s %s%n", "Last Name", lastName);
        System.out.printf(" %-20s %s%n", "Tel. Number", telNumber);
        System.out.printf(" %-20s %s%n", "Major", major);
        System.out.printf(" %-20s %s%n", "GPA", GPA);
        System.out.printf(" %-20s %s%n", "Year of Birth", yearOfBirth);
        System.out.printf(" %-20s %s%n", "Month of Birth", monthOfBirth);
        System.out.printf(" %-20s %s%n", "Date of Birth", dateOfBirth);
        System.out.printf(" %-20s %s%n", "Home Address", homeAddress);
        System.out.println("-------------------------------------------------------");
    }
}
