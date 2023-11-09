/*
 * Some important links:
 * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 * https://www.cs.auckland.ac.nz/software/AlgAnim/red_black.html
 * https://github.com/SvenWoltmann/binary-tree/blob/main/src/main/java/eu/happycoders/binarytree/RedBlackTree.java
*/

package assignment8;

import java.io.*;
import java.util.*;


class Node {
	public int iData;
	public Node leftChild;
	public Node rightChild;
	public Node parent;
	public boolean color;

	public Node(int d) {
		iData = d;
	}
}

class BinaryTree {

	static final boolean RED = false;
	static final boolean BLACK = true;
	protected Node root;

	public BinaryTree() {
		root = null;
	}

	public Node find(int key) {
		Node current = root;
		while (current.iData != key) {
			if (key < current.iData)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current; // found it
	}

	public Node insert(int id) {
		Node newNode = new Node(id);

		// All inserted nodes are red
		newNode.color = RED;

		// no node in root
		if (root == null) {
			root = newNode;
			newNode.parent = null;
			return root;
		}
		else {
			Node current = root; // start at root
			Node parent;
			while (true) {
				parent = current;
				if (id < current.iData) {
					current = current.leftChild;
					// insert on left
					if (current == null) {
						parent.leftChild = newNode;
						newNode.parent = parent;
						return newNode;
					}
				} else {
					current = current.rightChild;
					// insert on right
					if (current == null) {
						parent.rightChild = newNode;
						newNode.parent = parent;
						return newNode;
					}
				}
			}
		}
	}

	public void traverse(int traverseType) {
		switch (traverseType) {
		case 1:
			System.out.print("\nPreorder traversal: ");
			preOrder(root);
			break;
		case 2:
			System.out.print("\nInorder traversal: ");
			inOrder(root);
			break;
		case 3:
			System.out.print("\nPostorder traversal: ");
			postOrder(root);
			break;
		}
		System.out.println();
	}

	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}

	public void displayTree() {
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("..........................................................................");

		while (isRowEmpty == false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;
			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData + "," + ((temp.color == true) ? "B" : "R"));
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
			System.out.println("..........................................................................");
		}
	}

	public Node getRoot() {
		return root;
	}
}

public class RedBlackTree extends BinaryTree {

	public Node insert(int id) {
		// basic BST insertion
		Node n = super.insert(id);

		// adjust color by rotation and flip
		adjustAfterInsertion(n);
		return n;
	}

	public void adjustAfterInsertion(Node node) {

		// Reached the root
		// Rule 2: Root is always black
		if (node == getRoot()) {
			node.color = BLACK;
			return;
		}

		Node parent = node.parent;
		
		// No red-red conflict + black height same
		if (parent.color == BLACK) {
			return;
		}

		Node grandParent = parent.parent;
		Node uncle = null;
		
		// this is actually the root
		if (grandParent == null) {
			parent.color = BLACK;
			return;
		}

		if (grandParent.leftChild == parent) {
			uncle = grandParent.rightChild;
		}
		else if (grandParent.rightChild == parent) {
			uncle = grandParent.leftChild;
		}

		// Parent is red, inserted child is red, red-red conflict
		// Case A: Color flip, go up and fix color until it is adjusted
		if (uncle != null && uncle.color == RED) {
			grandParent.color = RED;
			parent.color = BLACK;
			uncle.color = BLACK;
			adjustAfterInsertion(grandParent);
		}

		// Case B2(a): Parent is left child of grand parent and X is outside
		else if (grandParent.leftChild != null && node == grandParent.leftChild.leftChild) {
			parent.color = BLACK;
			grandParent.color = RED;
			rightRotate(grandParent);
		}
		
		// Case B2(b): Parent is right child of grand parent and X is outside
		else if (grandParent.rightChild != null && node == grandParent.rightChild.rightChild) {
			parent.color = BLACK;
			grandParent.color = RED;
			leftRotate(grandParent);
		}
		
		// Case B3(a): Parent is left child of grand parent and X is inside
		else if (grandParent.leftChild != null && node == grandParent.leftChild.rightChild) {
			node.color = BLACK;
			grandParent.color = RED;
			leftRotate(parent);
			rightRotate(grandParent);
		}
		
		// Case B3(b): Parent is right child of grand parent and X is inside
		else if (grandParent.rightChild != null && node == grandParent.rightChild.leftChild) {
			node.color = BLACK;
			grandParent.color = RED;
			rightRotate(parent);
			leftRotate(grandParent);
		}
	}

	/*		 node			      X
			/    \			       \
		   X      75	==>		 node
			\                   /    \
			Y                  Y     75
	 */
	private void rightRotate(Node node) {
		if (node.leftChild != null) {
			Node P = node.parent;	// P is needed to reconnect the sub tree after rotation
			Node X = node.leftChild;
			Node Y = X.rightChild;
			node.parent = X;
			X.rightChild = node;
			node.leftChild = Y;
			if (Y != null) {
				Y.parent = node;
			}
			if (P == null)
				root = X;
			else if (P.leftChild == node)
				P.leftChild = X;
			else if (P.rightChild == node)
				P.rightChild = X;
			if (X != null)
				X.parent = P;
		}
	}

	/*		 node				   X
			/    \			      /
		   25     X	    ==>		 node
			     /              /    \
			    Y              25     Y
	 */
	private void leftRotate(Node node) {
		if (node.rightChild != null) {
			Node P = node.parent;	// P is needed to reconnect the sub tree after rotation
			Node X = node.rightChild;
			Node Y = X.leftChild;
			node.parent = X;
			X.leftChild = node;
			node.rightChild = Y;
			if (Y != null) {
				Y.parent = node;
			}
			if (P == null)
				root = X;
			else if (P.leftChild == node)
				P.leftChild = X;
			else if (P.rightChild == node)
				P.rightChild = X;
			if (X != null)
				X.parent = P;
		}
	}

	public static void main(String[] args) throws IOException {
		RedBlackTree theTree = new RedBlackTree();
		theTree.insert(50);
		theTree.insert(25);
		theTree.insert(75);
		theTree.insert(12);
		theTree.insert(37);
		theTree.insert(31);
		theTree.insert(43);
		theTree.insert(28);
		theTree.insert(33);
		theTree.insert(87);
		theTree.insert(93);
		theTree.insert(97);
		theTree.insert(1);
		theTree.insert(2);
		theTree.insert(35);
		theTree.displayTree();
	}
}
