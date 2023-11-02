package checkIfSubTree;

import java.util.Stack;

/* Given two binary trees, check if the first tree is a subtree of the second one. 
 * A subtree of a tree consists of a node in Main tree and all of its descendants in Main tree. */

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

class Tree
{
	private Node root1, root2;
	private String inOrderString = "", postOrderString = "";
	
	public Tree()
	{
		root1 = null;
		root2 = null;
	}
	
	public Node getMainTree()
	{
		return root1;
	}
	
	public Node getSubTree()
	{
		return root2;
	}
	
	public void makeRandomBinaryTree()
	{
		// creating main tree
		Node tmpRoot = new Node('a');
		tmpRoot.leftChild = new Node('b');
		tmpRoot.rightChild = new Node('c');
		tmpRoot.leftChild.leftChild = new Node('1');
		tmpRoot.leftChild.rightChild = new Node('2');
		tmpRoot.rightChild.leftChild = new Node('3');
		tmpRoot.rightChild.rightChild = new Node('4');
		root1 = tmpRoot;
		
		// creating random tree, which needs to be checked against Main tree
		tmpRoot = new Node('c');
		tmpRoot.leftChild = new Node('3');
		tmpRoot.rightChild = new Node('4');
		//tmpRoot.rightChild.rightChild = new Node('4');	// for checking purpose
		root2 = tmpRoot;
	}
	
	public void displayTree(Node root)
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
	
	private void inOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			inOrder(localRoot.leftChild);
			inOrderString = inOrderString + localRoot.iData;
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			postOrderString = postOrderString + localRoot.iData;
		}
	}
	
	public boolean isSubtree()
	{
		boolean inOrderMatched, postOrderMatched;
		
		if (root1 == null) return true; // main tree is null
		if (root2 == null) return false; // no tree to be checked against main tree
		
		inOrder(root1);
		String inMain = inOrderString;
		inOrderString = "";
		inOrder(root2);
		String inSub = inOrderString;
		inOrderString = "";
		inOrderMatched = inMain.contains(inSub);
		
		postOrder(root1);
		String postMain = postOrderString;
		postOrderString = "";
		postOrder(root2);
		String postSub = postOrderString;
		postOrderString = "";
		postOrderMatched = postMain.contains(postSub);

		return inOrderMatched && postOrderMatched;
	}
}

public class CheckIfSubtree
{
	public static void main(String[] args)
	{
		Tree t = new Tree();
		t.makeRandomBinaryTree();
		System.out.println("Main tree:\n");
		t.displayTree(t.getMainTree());
		System.out.println("\nRandom tree:\n");
		t.displayTree(t.getSubTree());
		System.out.println("\nIs sub tree? " + t.isSubtree());
	}
}
