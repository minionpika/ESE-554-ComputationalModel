package doublyLLfromInorderBT;

import java.util.Stack;

class TreeNode
{
	public char iData;
	public TreeNode leftChild;
	public TreeNode rightChild;
	
	public TreeNode(char data)
	{
		iData = data;
		leftChild = null;
		rightChild = null;
	}
}

class ListNode
{
	public char iData;
	public ListNode prev;
	public ListNode next;
	
	public ListNode(char data)
	{
		iData = data;
		next = null;
		prev = null;
	}
}

class DoublyLinkedList {
	private ListNode first;
	private ListNode last;

	public DoublyLinkedList()
	{
		first = null;
		last = null;
	}
	
	public ListNode getFirst()
	{
		return first;
	}
	
	public void insert(char data)
	{
		ListNode newNode = new ListNode(data);
		if (last == null) {
			first = newNode;
			last = newNode;
		}
		else {
			last.next = newNode; // old last --> newLink
			newNode.prev = last; // newLink --> old first
			last = newNode;      // last = last.next;
		}
	}
}

class Tree
{
	private TreeNode root;
	DoublyLinkedList dll;
	
	public Tree()
	{
		root = null;
		this.dll = new DoublyLinkedList();
	}
	
	public TreeNode getRoot()
	{
		return root;
	}

	public void makeRandomBinaryTree()
	{
		// creating tree
		TreeNode tmpRoot = new TreeNode('a');
		tmpRoot.leftChild = new TreeNode('b');
		tmpRoot.rightChild = new TreeNode('c');
		tmpRoot.leftChild.leftChild = new TreeNode('1');
		tmpRoot.leftChild.rightChild = new TreeNode('2');
		tmpRoot.rightChild.leftChild = new TreeNode('3');
		tmpRoot.rightChild.rightChild = new TreeNode('4');
		tmpRoot.rightChild.rightChild.leftChild = new TreeNode('5');
		tmpRoot.rightChild.rightChild.leftChild.rightChild = new TreeNode('6');
		root = tmpRoot;
	}
	
	public void displayTree(TreeNode root)
	{
		Stack<TreeNode> globalStack = new Stack<TreeNode>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("............................................................................................................");

		while (isRowEmpty == false) {
			Stack<TreeNode> localStack = new Stack<TreeNode>();
			isRowEmpty = true;
			
			for (int j=0; j<nBlanks; j++)
				System.out.print(' ');
			
			while (globalStack.isEmpty() == false) {
				TreeNode temp = (TreeNode)globalStack.pop();
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
	
	public void inOrder(TreeNode localRoot)
	{
		if (localRoot != null)
		{
			inOrder(localRoot.leftChild);
			dll.insert(localRoot.iData);	// insert the node one by one to the DLL(Doubly link list)
			inOrder(localRoot.rightChild);
		}
	}

	public void displayDLL() {
		System.out.print("\nList (first-->last): ");
		ListNode current = this.dll.getFirst(); // start at beginning
		
		while (current != null) {
			System.out.print(current.iData + " ");
			current = current.next;
		}
		System.out.println();
	}
}


public class DoublyLLfromInorder 
{
	public static void main(String[] args)
	{
		Tree t = new Tree();
		t.makeRandomBinaryTree();
		System.out.println("Main tree:\n");
		t.displayTree(t.getRoot());
		
		t.inOrder(t.getRoot());
		t.displayDLL();
	}
}
