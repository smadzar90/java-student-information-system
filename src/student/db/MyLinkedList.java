package student.db;
/*
 * Implementation of a singly linked list data structure 
 * Used as a database for student records
 */

import java.io.Serializable;

class MyLinkedList implements Serializable {
    private int size;
    private Node head;
    private Node tail;
    static final String ALL = "All";
    static final String MAJOR = "Major";
    static final String ID = "ID";

    MyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    //Node of a list containg a student record and reference to the next node
    static class Node implements Serializable  {
        private StudentRecord student;
        private Node next;

        Node(StudentRecord student) {
            this.student = student;
            next = null;
        }

        //Return next node
        Node getNext() {
            return next;
        }

        //Check if next node exists
        boolean hasNext() {
            if(this.next != null)
                return true;
            return false;
        }

        //Set the next node
        void setNext(Node next) {
            this.next = next;
        }

        //Return a node's student record
        StudentRecord getStudent() {
            return student;
        }

        //Set the node's student record
        void setStudent(StudentRecord student) {
            this.student = student;
        }
    }

    //Return size of the list
    int getSize() {
        return size;
    }

    //Return head of the list
    Node getHead() {
        return head;
    }

    //Add a student record into the list
    void addStudent(StudentRecord student) {
        if(this.head == null) {
            this.head = new Node(student);
            this.tail = head;
        }
        else {
            this.tail.next = new Node(student);
            this.tail = tail.getNext();
        }
        size++;
    }

    //Delete all student records by ID from the list, return number of student records deleted
    int deleteStudentByID(String ID) {
        int deletedRecords = 0;
        Node currNode = getHead(); 
        Node prevNode = null;
       
        //Traverse a list of student records until reaching null
        while(currNode != null) {
            if(currNode.getStudent().getID().equals(ID)) {
                if(currNode == getHead()) { 
                    //Delete if node is at head's position
                    if(size == 1) {
                        head = null;
                        tail = null;
                        currNode.setNext(null); //Remove next reference from deleted node (for cleanup)
                        currNode = null;
                    }
                    else {
                        head = currNode.getNext();
                        if(!head.hasNext()) {
                            tail = head;
                        }
                        currNode.setNext(null); //Remove next reference from deleted node (for cleanup)
                        currNode = head;
                    }
                }
                //Delete if node is not at head's position
                else {
                    prevNode.setNext(currNode.getNext());
                    if(currNode.getNext() == null) {
                        tail = prevNode;
                    }
                    currNode.setNext(null); //Remove next reference from deleted node (for cleanup)
                    currNode = prevNode.getNext();
                }
                size--;
                deletedRecords++;
            }
            else {
                prevNode = currNode; //Set prev node to current node
                currNode = currNode.getNext(); //Increment current node
            }
        }
        return deletedRecords;
    }

    //Split the linked list into two halves while maintaining the original list 
    MyLinkedList[] splitList() {

        MyLinkedList left = new MyLinkedList();
        MyLinkedList right = new MyLinkedList();
        int leftSize = this.size / 2;
        int rightSize = this.size / 2;
        Node originalCurrent = this.getHead();

        if(this.size % 2 != 0) {
            leftSize = (this.size / 2) + 1; //Increment leftSize by 1 if original list has odd number of nodes
        }

        //Copy the records to the left halve 
        for(int i = 0; i < leftSize; i++) {
            left.addStudent(originalCurrent.student);
            originalCurrent = originalCurrent.getNext();
        }

        //Copy the records to the right halve
        for(int j = 0; j < rightSize; j++) {
            right.addStudent(originalCurrent.student);
            originalCurrent = originalCurrent.getNext();
        }
    
        MyLinkedList[] splited = {left, right};
        return splited;
    }

    //List all the student records based on criteria such as ALL, MAJOR, and ID
    void listAllRecords(String criteria, String attribute) {
        Node currNode = head;
        System.out.println("\n-------------------------------------------------------\n");

        if(criteria.equals(ALL)) {
            System.out.println("All Student Records");
        }
        else if(criteria.equals(ID)) {
            System.out.println("All Student Records with ID " + attribute);
        }
        else if(criteria.equals(MAJOR)) {
            System.out.println("All Student Records in " + attribute + " Major");
        }

        System.out.println("\n-------------------------------------------------------");

        while(currNode != null) {
            switch (criteria) {
                case ALL: //Display all student records
                    currNode.getStudent().printStudentRecord();
                    break;
                case ID: //Display student records by ID
                    if(currNode.getStudent().getID().equals(attribute)) {
                        currNode.getStudent().printStudentRecord();
                    }
                    break;
                case MAJOR: //Display student records by Major
                    if(currNode.getStudent().getMajor().equals(attribute)) {
                        currNode.getStudent().printStudentRecord();
                    }
                    break;
            }
            currNode = currNode.getNext(); //Traverse to next student record
        }
        System.out.println();
    }
}