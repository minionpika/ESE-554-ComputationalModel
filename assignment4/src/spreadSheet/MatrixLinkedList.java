package spreadSheet;
//
///**
// * Problem 5.6 and MatrixLinkedListIterator
// *
// * @author anubhav tomar (ID: 112268905)
// */
//public class MatrixLinkedList {
//
//    final int ROWS;
//    final int COLUMNS;
//    Row[] rows;
//    ListNode head;
//    MatrixLinkedListIterator matrixLinkedListIterator;
//
//    MatrixLinkedList(int ROWS, int COLUMNS) {
//
//        this.ROWS = ROWS;
//        this.COLUMNS = COLUMNS;
//        head = null;
//        rows = new Row[ROWS];
//
//        generateMatrix();
//    }
//
//    public void insert(int row, int column, int key) {
//
//        ListNode listNode = head;
//
//        if (row <= 0 || column <= 0 || row > ROWS || column > COLUMNS) {
//            System.out.println("row " + row + " and column " + column + " are out of bounds");
//            return;
//        }
//
//        for (int i = 1; i < row; i++) {
//            listNode = listNode.downLink;
//        }
//
//        for (int i = 1; i < column; i++) {
//            listNode = listNode.forwardLink;
//        }
//
//        System.out.println("Inserting " + key + " at row "+ row +" and column " + column + " in the matrix list");
//        listNode.key = key;
//    }
//
//    public void delete(int key) {
//
//        ListNode listNode = head;
//
//        for (int i = 1; i <= ROWS; i++) {
//            ListNode rowStart = listNode;
//            for (int j = 1; j <= COLUMNS; j++) {
//                if (listNode.key == key) {
//                    System.out.println("Deleting " + key + " from the matrix list");
//                    listNode.key = -1;
//                    return;
//                }
//                listNode = listNode.forwardLink;
//            }
//            listNode = rowStart.downLink;
//        }
//
//        System.out.println("key " + key + " is not present in the matrix list");
//    }
//
//    public void display() {
//
//        ListNode row = head;
//
//        while (row != null) {
//            ListNode listNode = row;
//            while (listNode != null) {
//                System.out.print(listNode.key + " ");
//                listNode = listNode.forwardLink;
//            }
//            System.out.println();
//            row = row.downLink;
//        }
//
//        System.out.println();
//    }
//
//    public MatrixLinkedListIterator getMatrixLinkedListIterator() {
//        return matrixLinkedListIterator;
//    }
//
//    public static void main(String[] args) {
//
//        MatrixLinkedList matrixLinkedList1 = new MatrixLinkedList(3, 3);
//
//        System.out.println("Implementation of MatrixLinkedList without using MatrixLinkedListIterator");
//        System.out.println();
//
//        matrixLinkedList1.insert(1, 1, 1);
//        matrixLinkedList1.insert(1, 2, 2);
//        matrixLinkedList1.insert(1, 3, 3);
//        matrixLinkedList1.insert(2, 1, 4);
//        matrixLinkedList1.insert(2, 2, 5);
//        matrixLinkedList1.insert(2, 3, 6);
//        matrixLinkedList1.insert(3, 1, 7);
//        matrixLinkedList1.insert(3, 2, 8);
//        matrixLinkedList1.insert(3, 3, 9);
//
//        matrixLinkedList1.display();
//
//        matrixLinkedList1.delete(0);
//
//        matrixLinkedList1.display();
//
//        matrixLinkedList1.delete(9);
//
//        matrixLinkedList1.display();
//
//        matrixLinkedList1.delete(5);
//
//        matrixLinkedList1.display();
//        System.out.println();
//
//        System.out.println("Implementation of MatrixLinkedList sing MatrixLinkedListIterator");
//        System.out.println();
//        MatrixLinkedList matrixLinkedList2 = new MatrixLinkedList(3, 3);
//
//        MatrixLinkedListIterator matrixLinkedListIterator = matrixLinkedList2.getMatrixLinkedListIterator();
//        matrixLinkedListIterator.display();
//
//        matrixLinkedListIterator.insert(1);
//
//        matrixLinkedListIterator.right();
//        matrixLinkedListIterator.insert(2);
//
//        matrixLinkedListIterator.right();
//        matrixLinkedListIterator.insert(3);
//
//        matrixLinkedListIterator.down();
//        matrixLinkedListIterator.insert(6);
//
//        matrixLinkedListIterator.left();
//        matrixLinkedListIterator.insert(5);
//
//        matrixLinkedListIterator.left();
//        matrixLinkedListIterator.insert(4);
//
//        matrixLinkedListIterator.down();
//        matrixLinkedListIterator.insert(7);
//
//        matrixLinkedListIterator.right();
//        matrixLinkedListIterator.insert(8);
//
//        matrixLinkedListIterator.right();
//        matrixLinkedListIterator.insert(9);
//
//        matrixLinkedListIterator.display();
//
//        matrixLinkedListIterator.right();
//        matrixLinkedListIterator.delete();
//        matrixLinkedListIterator.display();
//
//        matrixLinkedListIterator.up();
//        matrixLinkedListIterator.left();
//        matrixLinkedListIterator.delete();
//        matrixLinkedListIterator.display();
//    }
//
//    private void generateMatrix() {
//
//        for (int i = 0; i < ROWS; i++) {
//            rows[i] = new Row(COLUMNS);
//        }
//
//        head = rows[0].getHead();
//        matrixLinkedListIterator = new MatrixLinkedListIterator(this);
//
//        for (int i = 0; i < ROWS - 1; i++) {
//            ListNode listNode1 = rows[i].getHead();
//            ListNode listNode2 = rows[i+1].getHead();
//
//            for (int j = 0; j < COLUMNS; j++) {
//                listNode1.downLink = listNode2;
//                listNode1 = listNode1.forwardLink;
//                listNode2 = listNode2.forwardLink;
//            }
//        }
//    }
//}
//
//class ListNode {
//
//    int key;
//    ListNode forwardLink;
//    ListNode downLink;
//
//    ListNode(int key) {
//
//        this.key = key;
//        forwardLink = null;
//        downLink = null;
//    }
//}
//
//class Row {
//
//    final int COLUMNS;
//    ListNode head;
//
//    public Row(int COLUMNS) {
//
//        this.COLUMNS = COLUMNS;
//        generateRow();
//    }
//
//    private void generateRow() {
//
//        ListNode listNode = new ListNode(-1);
//        head = listNode;
//        ListNode ptr = head;
//
//        for (int i = 1; i < COLUMNS; i++) {
//            ListNode listNode1 = new ListNode(-1);
//            ptr.forwardLink = listNode1;
//            ptr = listNode1;
//        }
//    }
//
//    public ListNode getHead() {
//        return head;
//    }
//}
//
//class MatrixLinkedListIterator {
//
//    int row;
//    int column;
//    ListNode current;
//    MatrixLinkedList matrixLinkedList;
//
//    MatrixLinkedListIterator(MatrixLinkedList matrixLinkedList) {
//
//        row = column = 1;
//        current = matrixLinkedList.head;
//        this.matrixLinkedList = matrixLinkedList;
//    }
//
//    public ListNode up() {
//        current = goToNode(row - 1, column);
//        return current;
//    }
//
//    public ListNode down() {
//        current = goToNode(row + 1, column);
//        return current;
//    }
//
//    public ListNode left() {
//        current = goToNode(row, column - 1);
//        return current;
//    }
//
//    public ListNode right() {
//        current = goToNode(row, column + 1);
//        return current;
//    }
//
//    public int get() {
//        return current.key;
//    }
//
//    public void insert(int key) {
//        System.out.println("Inserting " + key + " at row "+ row +" and column " + column + " in the matrix list");
//        current.key = key;
//    }
//
//    public void delete() {
//        System.out.println("Deleting " + current.key + " at row "+ row +" and column " + column +
//                " from the matrix list");
//        current.key = -1;
//    }
//
//    public void display() {
//        matrixLinkedList.display();
//    }
//
//    private ListNode goToNode(final int row, final int column) {
//
//        if (row <= 0 || column <= 0 || row > matrixLinkedList.ROWS || column > matrixLinkedList.COLUMNS) {
//            System.out.println("row " + row + " and column " + column + " are out of bounds");
//            return current;
//        }
//
//        ListNode listNode = matrixLinkedList.head;
//
//        for (int i = 1; i < row; i++) {
//            listNode = listNode.downLink;
//        }
//
//        for (int i = 1; i < column; i++) {
//            listNode = listNode.forwardLink;
//        }
//
//        current = listNode;
//        this.row = row;
//        this.column = column;
//
//        return current;
//    }
//}

class Node1 {
    int data;
    Node1 right;
    Node1 down;

    public Node1(int data) {
        this.data = data;
        this.right = null;
        this.down = null;
    }
}

class MatrixLinkedList1 {
    Node1 head;

    public MatrixLinkedList1() {
        this.head = null;
    }

    public void insert(int row, int col, int value) {
        Node1 newNode = new Node1(value);

        // Insertion into an empty list
        if (head == null) {
            head = newNode;
            return;
        }

        Node1 currentRow = head;
        Node1 prevRow = null;

        // Traverse to the correct row
        for (int i = 0; i < row; i++) {
            prevRow = currentRow;
            currentRow = currentRow.down;
            if (currentRow == null) {
                prevRow.down = new Node1(-1); // Special value to indicate an empty row
                currentRow = prevRow.down;
            }
        }

        Node1 currentCol = currentRow;
        Node1 prevCol = null;

        // Traverse to the correct column
        for (int j = 0; j < col; j++) {
            prevCol = currentCol;
            currentCol = currentCol.right;
            if (currentCol == null) {
                prevCol.right = new Node1(-1); // Special value to indicate an empty column
                currentCol = prevCol.right;
            }
        }

        // Insert the new node
        newNode.right = currentCol.right;
        currentCol.right = newNode;
        newNode.down = currentRow.down;
        currentRow.down = newNode;
    }

    public void display() {
        Node1 rowNode = head;
        while (rowNode != null) {
            Node1 current = rowNode;
            while (current != null) {
                System.out.print(current.data + "\t");
                current = current.right;
            }
            System.out.println();
            rowNode = rowNode.down;
        }
    }
}

public class MatrixLinkedList {
    public static void main(String[] args) {
        MatrixLinkedList1 matrixLinkedList1 = new MatrixLinkedList1();

        // Insert values into the linked list at runtime
        matrixLinkedList1.insert(0, 0, 1);
        matrixLinkedList1.insert(0, 1, 2);
        matrixLinkedList1.insert(1, 2, 3);
        matrixLinkedList1.insert(2, 3, 4);
        matrixLinkedList1.insert(3, 4, 5);

        // Display the linked list
        System.out.println("Initial Linked List:");
        matrixLinkedList1.display();
    }
}
