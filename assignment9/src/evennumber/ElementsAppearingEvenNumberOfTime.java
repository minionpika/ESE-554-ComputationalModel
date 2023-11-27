package evennumber;

import java.io.*;
import java.util.ArrayList;

class Node {
	private int iData;
	private int count;
	public Node leftChild;
	public Node rightChild;

	public Node(int aKey) {
		iData = aKey;
		count = 1;
	}

	public int getKey() {
		return iData;
	}

	public int getCount() {
		return count;
	}

	public void updateCount() {
		count += 1;
	}
}

class Tree {
	private Node root;
	public static ArrayList<Integer> out = new ArrayList<Integer>();

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

	public void traverse() {
		inOrder(root);
	}

	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			// System.out.print(localRoot.getCount() + " ");
			if (localRoot.getCount() % 2 == 0) {
				// even count
				out.add(localRoot.getKey());
			}
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

	public void traverse() {
		for (int j = 0; j < arraySize; j++) {
			hashArray[j].traverse();
		}
	}

	public int hashFunc(int key) {
		return key % arraySize;
	}

	public void insert(int key) {
		int hashVal = hashFunc(key);
		Node tmp = find(key);
		if (tmp == null) {
			hashArray[hashVal].insert(new Node(key));
		} else {
			tmp.updateCount();
		}
	}

	public Node find(int key) {
		int hashVal = hashFunc(key);
		if (hashArray[hashVal] == null)
			return null;
		Node node = hashArray[hashVal].find(key);
		return node;
	}
}

public class ElementsAppearingEvenNumberOfTime {

	public static void main(String[] args) throws IOException {
		int n = 0;
		HashTable theHashTable = new HashTable(1000);
		System.out.println("Enter elements: ");
		String inputString = getString();
		String[] splitSpace = inputString.split(" ");

		for (String s : splitSpace) {
			n = Integer.parseInt(s);
			theHashTable.insert(n);
		}
		theHashTable.traverse();
		if (Tree.out.isEmpty())
			System.out.println("NONE");
		else
			System.out.println(Tree.out);
	}

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
