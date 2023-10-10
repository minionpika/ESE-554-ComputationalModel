package binaryTree;

import java.io.*;
import java.util.Arrays;

public class _1drawBinaryTree {
	char[][] binaryTree;
	int rows, lineLength;

	public _1drawBinaryTree(int lineLength) {
		rows = (int) (Math.log(lineLength)/Math.log(2) + 1);
		binaryTree = new char[rows][lineLength];
		for (char[] r:binaryTree) {
			Arrays.fill(r, '-');
		}
		this.lineLength = lineLength;
	}

    public void makeBranches(int row, int left, int right) {
    	if (row >= rows)
    		return;
   
    	int mid = (left+right)/2; 	
    	binaryTree[row][mid] = 'X';
    	makeBranches(row+1, left, mid);
    	makeBranches(row+1, mid, right);
    }

    public void displayTree() {
    	for (int i=0; i<rows; i++) {
    		for (int j=0; j<lineLength; j++)
    			System.out.print(binaryTree[i][j]);
    		System.out.println();
    	}
    }
    
    static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

    public static void main(String[] args) throws IOException {
    	System.out.println("Line length: ");
    	int spread = Integer.parseInt(getString());	// line length of the display
    	_1drawBinaryTree btree = new _1drawBinaryTree(spread);
    	btree.makeBranches(0, 0, spread);
    	btree.displayTree();
    }
}
