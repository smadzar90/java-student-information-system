package student.db;
/*
 * This class implements merge sort algorithm on the singly linked list data structure
 * It sorts the list of students records by either GPA, ID, or date of birth
 * Accepts sorting in ascending and descending order
 */

class StudentRecordsSorter {
    private static final String SORT_BY_GPA = "GPA";
    private static final String SORT_BY_ID = "ID";

    //Recursively devide list into left and right halves
    static void sortRecords(MyLinkedList list, int order, String sortBy) {
        MyLinkedList.Node head = list.getHead();

        //Exit if there is none or one element in the list
        if(head == null || !head.hasNext()) {
            return;
        }
        MyLinkedList splitedList[] = list.splitList();
        //Left halve
        MyLinkedList left = splitedList[0];
        //Right halve
        MyLinkedList right = splitedList[1];

        sortRecords(left, order, sortBy);
        sortRecords(right, order, sortBy);
        merge(list, left, right, order, sortBy);
    }

    //Merge two lists by ID, GPA, or DateOfBirth
    static void merge(MyLinkedList list, MyLinkedList left, MyLinkedList right, int order, String sortBy) {
        MyLinkedList.Node leftCurrent = left.getHead();
        MyLinkedList.Node rightCurrent = right.getHead();
        MyLinkedList.Node listCurrent = list.getHead();
    
        while(leftCurrent != null && rightCurrent != null) {
            boolean toCompare = compareNodes(leftCurrent, rightCurrent, sortBy);
            //Sort in ascending order
            if(order == 1) {
                if(toCompare) {
                    listCurrent.setStudent(leftCurrent.getStudent());
                    leftCurrent = leftCurrent.getNext();
                }
                else {
                    listCurrent.setStudent(rightCurrent.getStudent()); 
                    rightCurrent = rightCurrent.getNext();
                }
            }
            //Sort in descending order
            else if(order == -1) {
                if(!toCompare) {
                    listCurrent.setStudent(leftCurrent.getStudent());
                    leftCurrent = leftCurrent.getNext();
                }
                else {
                    listCurrent.setStudent(rightCurrent.getStudent()); 
                    rightCurrent = rightCurrent.getNext();
                }
            }
            listCurrent = listCurrent.getNext();
        }
        populateList(listCurrent, leftCurrent, rightCurrent);
    }

    //Populate rest of the list 
    static void populateList(MyLinkedList.Node liNode, MyLinkedList.Node lNode, MyLinkedList.Node rNode) {
        //Add remaining Students from the left halve
         while(lNode != null) {
           liNode.setStudent(lNode.getStudent()); 
           lNode = lNode.getNext();
           liNode = liNode.getNext();
        }
        //Add remaining Students from the right halve
        while(rNode != null) {
            liNode.setStudent(rNode.getStudent()); 
            rNode = rNode.getNext();
            liNode = liNode.getNext();
        }
    }

    //Compare students by their GPA, ID, or DateOfBirth
    static boolean compareNodes(MyLinkedList.Node lNode, MyLinkedList.Node rNode, String sortBy) {
        switch (sortBy) {
            case SORT_BY_GPA: //Compare by GPA
                return lNode.getStudent().getGPA() <= rNode.getStudent().getGPA();
            case SORT_BY_ID: //Compare by ID
                return Integer.valueOf(lNode.getStudent().getID()) <= Integer.valueOf(rNode.getStudent().getID());
            default: //compare by DateOfBirth as default
                return lNode.getStudent().getDate().compareTo(rNode.getStudent().getDate()) <= 0;
        }
    }
}
