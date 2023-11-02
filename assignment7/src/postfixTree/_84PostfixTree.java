package postfixTree;

import java.util.Stack;

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

class PostfixTree
{
	private String pString;
	private Node root;
	
	public PostfixTree(String str)
	{
		pString = str;
	}
	
	public void makeTree()
	{
		Stack<Node> st = new Stack<Node>();

		for (int i=0; i<pString.length(); i++) {
			char c = pString.charAt(i);
			if (c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
				st.push(new Node(c));
			}
			else {
				Node char1 = st.pop();
				Node char2 = st.pop();
				Node tmpRoot = new Node(c);
				tmpRoot.leftChild = char2;
				tmpRoot.rightChild = char1;
				st.push(tmpRoot);
			}
		}
		
		root = st.pop();
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

	private void preOrder(Node localRoot)
	{
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void inOrder(Node localRoot)
	{
		if (localRoot != null) {
			if (localRoot.iData>='A' && localRoot.iData<='Z' || localRoot.iData>='0' && localRoot.iData<='9') { }
			else System.out.print("( ");
			
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);

			if (localRoot.iData>='A' && localRoot.iData<='Z' || localRoot.iData>='0' && localRoot.iData<='9') { }
			else System.out.print(" )");
		}
	}

	private void postOrder(Node localRoot)
	{
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}
}

public class _84PostfixTree {

	public static void main(String[] args) {
		String postfixString = "ABC+*DEF+/-";
		PostfixTree btree = new PostfixTree(postfixString);
    	btree.makeTree();
    	btree.displayTree();
    	
    	btree.traverse(1);
    	btree.traverse(2);
    	btree.traverse(3);
	}

}
