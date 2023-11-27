package hashChain;

import java.io.*;

class Node {
	private int iData;
	public Node leftChild;
	public Node rightChild;

	public Node(int aKey) {
		iData = aKey;
	}

	public int getKey() {
		return iData;
	}
}

class Tree {
	private Node root;

	public Tree() {
		root = null;
	}

	public Node find(int key) {
		Node current = root;
		if (current == null)
			return null;
		while (current.getKey() != key) {
			if (key < current.getKey())
				current = current.leftChild;
			else
				current = current.rightChild;
			
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(Node node) {
		if (root == null)
			root = node;
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (node.getKey() < current.getKey()) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = node;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = node;
						return;
					}
				}
			}
		}
	}

	public void displayList() {
		inOrder(root);
	}

	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.getKey() + " ");
			inOrder(localRoot.rightChild);
		}
	}
}

class HashTable {
	private Tree[] hashArray;
	private int arraySize;

	public HashTable(int size) {
		arraySize = size;
		hashArray = new Tree[arraySize];
		for (int j = 0; j < arraySize; j++)
			hashArray[j] = new Tree();
	}

	public void displayTable() {
		for (int j = 0; j < arraySize; j++) {
			System.out.print(j + ". ");
			hashArray[j].displayList();
			System.out.println();
		}
	}

	public int hashFunc(int key) {
		return key % arraySize;
	}

	public void insert(Node node) {
		int key = node.getKey();
		int hashVal = hashFunc(key);
		if (find(key) == null)
			hashArray[hashVal].insert(node);
		else
			System.out.println("duplicate found");
	}

	public Node find(int key) {
		int hashVal = hashFunc(key);
		if (hashArray[hashVal] == null)
			return null;
		Node node = hashArray[hashVal].find(key);
		return node;
	}
}

public class _115HashChainWithTree {

	public static void main(String[] args) throws IOException {
		int aKey;
		Node aDataItem;
		int size, n, keysPerCell = 100;
		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		HashTable theHashTable = new HashTable(size);
		for (int j = 0; j < n; j++) {
			aKey = (int) (java.lang.Math.random() * keysPerCell * size);
			aDataItem = new Node(aKey);
			theHashTable.insert(aDataItem);
		}
		while (true) {
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, or find: ");
			char choice = getChar();
			switch (choice) {
			case 's':
				theHashTable.displayTable();
				break;
			case 'i':
				System.out.print("Enter key value to insert: ");
				aKey = getInt();
				aDataItem = new Node(aKey);
				theHashTable.insert(aDataItem);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getInt();
				aDataItem = theHashTable.find(aKey);
				if (aDataItem != null)
					System.out.println("Found " + aKey);
				else
					System.out.println("Could not find " + aKey);
				break;
			default:
				System.out.print("Invalid entry\n");
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
