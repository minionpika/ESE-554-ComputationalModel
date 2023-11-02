package balancedTree;

import java.io.*;
import java.util.*;

class Node
{
	public char iData;
	public Node leftChild;
	public Node rightChild;
	
	public Node(char data)
	{
		iData = data;
		leftChild = null;
		rightChild = null;
	}
}

class BalancedTree
{
	private String str;
	private Node root;
	
	public BalancedTree(String inputString)
	{
		str = inputString;
		root = null;
	}
	
	public void makeTreeBalanced()
	{
		ArrayList<Node> nodelist = new ArrayList<Node>();
		
		for (int i=0; i<str.length(); i++) {
			nodelist.add(new Node(str.charAt(i)));
		}

		while (nodelist.size() > 1) {
			Node char1 = nodelist.get(0);
			Node char2 = nodelist.get(1);
			Node tmpRoot = new Node('+');
			tmpRoot.leftChild = char1;
			tmpRoot.rightChild = char2;
			nodelist.remove(0);
			nodelist.trimToSize();
			nodelist.remove(0);
			nodelist.trimToSize();
			nodelist.add(tmpRoot);
		}

		root = nodelist.get(0);
	}
	
	public void displayTree()
	{
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("............................................................................................................");

		while (isRowEmpty == false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;
			
			for (int j=0; j<nBlanks; j++)
				System.out.print(' ');
			
			while (globalStack.isEmpty() == false) {
				Node temp = (Node)globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				}
				else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			}
			System.out.println();
			nBlanks /= 2;

			while (localStack.isEmpty() == false) {
				globalStack.push(localStack.pop());
			}
			System.out.println("............................................................................................................");
		}
	}
	
	public void traverse(int traverseType)
	{
		switch (traverseType)
		{
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

	private void preOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void inOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}
}

public class _82BalancedTree
{
	static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("Enter a string:");
		String inputString = getString();
		BalancedTree btree = new BalancedTree(inputString);
    	btree.makeTreeBalanced();
    	btree.displayTree();
    	
    	btree.traverse(1);
    	btree.traverse(2);
    	btree.traverse(3);
	}
}
