package student.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * MyLinkedListSerialization class is responsible for serializing and deserializing
 * student records, allowing the program to maintain student data after halting a program
 */

class MyLinkedListSerialization {

    //Save list of student records to a file as a stream of bytes
    static void saveStudentRecords(MyLinkedList studentRecords) {
        try {
            FileOutputStream fos = new FileOutputStream("saved-records/student-records-database.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(studentRecords); //Save student records object
            fos.close(); //Close FileOutputStream
            oos.close(); //Close ObjectOutputStream
        }
        catch(IOException e) {
            System.out.println("\nError occured while saving student records!\n");
        }
    }

    //Load a list of student records from file 
    static MyLinkedList loadStudentRecords() {
        try {
            FileInputStream fis = new FileInputStream("saved-records/student-records-database.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            MyLinkedList loadedStudentRecords = (MyLinkedList) ois.readObject(); //Load student records

            fis.close(); //Close FileInputStream
            ois.close(); //Close ObjectInputStream
            return loadedStudentRecords; //Return saved student records
        }
        catch(FileNotFoundException e) {
            System.out.println("\nStudent records couldn't be loaded. File not found!\n");
        }
        catch(Exception e) {
            System.out.println("\nStudent records couldn't be loaded. Error occured!\n");
        }

        //If error occurs, return a new MyLinkedList thus empty student record database
        return new MyLinkedList(); 
    }
}