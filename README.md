## Student Information System (Java)

### Description

The Student Information System is a Java-based project designed to manage and organize student records. It provides user-friendly interface and the system executes various student records management operations based on user input. User can insert a new records, delete records by ID, list all records, sort records, and list records by attributes. The system guarantees record persistence, saving them after each run. It uses a singly-linked list for storage and merge sort algorithm for efficient sorting, ensuring a good program efficiency.


### Skills Used

- Java Programming
- Object-Oriented Programming
- Singly-linked list
- Merge sort algorithm on a singly-linked list 
- Serialization and deserialization 
- Input validation 
- User interface design


### Project Structure

- `StudentDatabaseApp` - Displays a user interface, interprets user input, and processes the menagment operations
- `InputValidator` - Validates user inputs and ensures that user enters valid properties
- `StudentRecord` - Stores all the student information 
- `MyLinkedList` - Implementation of a singly-linked list data structure that stores student records
- `StudentRecordsSorter` - Sorts a singly-linked list of student records using a merge sort algorithm 
- `MyLinkedListSerialization` - Saves and loads student records through serialization and deserialization 


### How to Use

When you run the program, the user interface will be displayed featuring options representing various operations to manage a student records database. Upon selecting a desired option, follow the steps guided by the user interface. In case an invalid option or input is provided, the interface will prompt you to re-enter until valid input is provided. To exit a student record database and halt the program enter '6' in the main options prompt. The modifications in the database will be saved and loaded on the next run.

#### Example 

```
STUDENT INFORMATION DATABASE

1. Insert a new record in the database
2. Delete record/records with given student ID
3. List all student records
4. Reoder student records
5. List student records in a given major, or a given ID
6. Exit a student database

Enter an option (e.g. 1-6): 
```


### How to Run

```
1. cd path_to/java-student-information-system
2. javac -d bin src/student/db/*.java
3. java -cp bin student.db.StudentDatabaseApp
```

### Author
- Stipan Madzar










