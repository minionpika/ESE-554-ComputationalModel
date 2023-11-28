package treeheap;

import java.io.*;
import java.util.Stack;

class Node {
	public int iData;
	boolean isLeftChild;
	public Node leftChild;
	public Node rightChild;
	public Node parent;

	public Node(int key) {
		iData = key;
		isLeftChild = false;
	}

	public void displayNode() {
		System.out.print("{");
		System.out.print(iData);
		System.out.print("} ");
	}
}

class TreeHeap {
	private Node root;
	private int size;

	public TreeHeap() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(int key)
	{
		if (root == null)
			root = new Node(key);
		else {
			Node current = root;
			int j = 0, n = size + 1; // root is numbered 1
			int[] path = new int[32];
			while (n >= 1) {
				path[j++] = n % 2;
				n /= 2;
			}

			// Remove the initial 1
			Node nn = new Node(key);
			for (int k = j - 2; k > 0; k--) {
				if (path[k] == 1) {
					current = current.rightChild;
				} else {
					current = current.leftChild;
				}
			}
			if (current.leftChild != null) {
				current.rightChild = nn;
				nn.isLeftChild = false;
			} else {
				current.leftChild = nn;
				nn.isLeftChild = true;
			}

			nn.parent = current;
			trickleUp(nn);
		}
		size++;
	}

	public Node remove() {
		Node maxHeap = root;

		if (root == null || size == 1) {
			root = null;
		} else {
			Node current = root;
			int j = 0, n = size;
			int[] path = new int[32];
			while (n >= 1) {
				path[j++] = n % 2;
				n /= 2;
			}

			for (int k = j - 2; k >= 0; k--) {
				if (path[k] == 1)
					current = current.rightChild;
				else
					current = current.leftChild;
			}

			if (current.isLeftChild)
				current.parent.leftChild = null;
			else
				current.parent.rightChild = null;
			root.iData = current.iData;
			trickleDown(root);
		}
		size--;
		return maxHeap;
	}

	private void trickleUp(Node node) {
		int d = node.iData;
		Node current = node;
		while (current.parent != null && current.parent.iData < d) {
			current.iData = current.parent.iData;
			current = current.parent;
		}
		current.iData = d;
	}
	
	public void trickleDown(Node node)
	{
		Node current = node;
		Node maxChild;
		int d = node.iData;
		
		while (hasChild(current))
		{
			if (current.rightChild == null || (current.leftChild != null && current.leftChild.iData > current.rightChild.iData)) {
				maxChild = current.leftChild;
			} else {
				maxChild = current.rightChild;
			}

			if(d >= maxChild.iData )
				break;

			current.iData = maxChild.iData;
			current = maxChild;
		}
		current.iData = d;
	}

	private boolean hasChild(Node node) {
		return node.leftChild != null || node.rightChild != null;
	}

	private void swapNode(Node node1, Node node2) {
		int temp = node1.iData;
		node1.iData = node2.iData;
		node2.iData = temp;
	}

	public boolean change(int index, int newValue) {
		if (index < 0 || index >= size)
			return false;

		Node current = root;
		int j = 0, n = index;
		int[] path = new int[32];
		while (n >= 1) {
			path[j++] = n % 2;
			n /= 2;
		}

		for (int k = j - 2; k >= 0; k--) {
			if (path[k] == 1) {
				current = current.rightChild;
			} else {
				current = current.leftChild;
			}
		}

		int oldValue = current.iData;
		current.iData = newValue;

		if (oldValue < newValue)
			trickleUp(current);
		else
			trickleDown(current);

		return true;
	}

	public void displayTreeHeap() {
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");

		while (isRowEmpty == false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;
			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			}
			System.out.println();
			nBlanks /= 2;

			while (localStack.isEmpty() == false) {
				globalStack.push(localStack.pop());
			}
			System.out.println("......................................................");
		}
	}
}

public class HeapwithTree {
	public static void main(String[] args) throws IOException {
		int value, value2;
		TreeHeap theHeap = new TreeHeap();
		boolean success;

		theHeap.insert(70); // insert 10 items
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);

		while (true) // until [Ctrl]-[C]
		{
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, remove, change: ");
			int choice = getChar();
			switch (choice) {
			case 's':
				theHeap.displayTreeHeap();
				;
				break;
			case 'i':
				System.out.print("Enter value to insert: ");
				value = getInt();
				theHeap.insert(value);
				break;
			case 'r':
				if (!theHeap.isEmpty())
					theHeap.remove();
				else
					System.out.println("Can't remove; heap empty");
				break;
			case 'c':
				System.out.print("Enter current index of item: ");
				value = getInt();
				System.out.print("Enter new key: ");
				value2 = getInt();
				success = theHeap.change(value, value2);
				if (!success)
					System.out.println("Invalid index");
				break;
			default:
				System.out.println("Invalid entry\n");
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

}
