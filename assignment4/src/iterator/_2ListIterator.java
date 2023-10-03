package iterator;


class Node
{
	public int data;
	public Node right;
	public Node bottom;

	public Node(int d) {
		data = d;
	}

	public void displayLink() {
		System.out.print("{" + data + ", " + "}");
	}
}

class LinkList
{
	private int row, col;
	private Node first;
	//Iterator itr;

	public LinkList(int r, int c) {
		first = null;
		row = r;
		col = c;
		
		generateMatrix();
	}
	
	public void generateMatrix() {
		Node[] rowHeads = new Node[row];

		for (int i=0; i<row; i++) {
			rowHeads[i] = new Node(-1);
			Node tmp = rowHeads[i];
	
	        for (int j = 1; j < col; j++) {
	            Node n = new Node(-1);
	            tmp.right = n;
	            tmp = tmp.right;
	        }
		}

		first = rowHeads[0];
		
		for (int i=0; i<row-1; i++) {
			Node tmp = rowHeads[i];
			Node tmp1 = rowHeads[i+1];
			
			for (int j=0; j<col; j++) {
				tmp.bottom = tmp1;
				tmp = tmp.right;
				tmp1 = tmp1.right;
			}
		}
	}

	public void insert(int r, int c, int key) {
		Node tmp = first;

		for (int i=1; i<r; i++)
			tmp = tmp.bottom;

		for (int j=1; j<c; j++)
			tmp = tmp.right;

		if (tmp.data != -1)
			System.out.println("a cell already exists in that location");
		else
			tmp.data = key;
	}

	public Node delete(int r, int c) {
		Node top = first;
		Node left = first;
		
		for (int i=1; i<r; i++) {
			
			left = left.bottom;
		}
		for (int j=1; j<c-1; j++) {
			
			left = left.right;
		}
		for (int i=1; i<r-1; i++) {
			
			top = top.bottom;
		}
		for (int j=1; j<c; j++) {
			
			top = top.right;
		}
		
		Node del = null;
		if (top.bottom != null || left.right != null) {
			del = left.right;
			System.out.println("deleting "+del.data);
			del.data = -1;
			left.right = del.right;
			top.bottom = del.bottom;
			//System.out.println(left.data+" "+top.data);
		}
		else {
			System.out.println("no link exist in this location");
		}

		return del;
	}
	
	public void displayList() {
		Node r = first;

        while (r != null) {
            Node c = r;
            while (c != null) {
                System.out.print(c.data + " ");
                c = c.right;
            }
            System.out.println();
            r = r.bottom;
        }
        System.out.println();
	}

	public Iterator getIterator() {
		return new Iterator(this);
	}
	
	public Node getFirst() {
		return first;
	}
	
	public boolean isValidPosition(int r, int c) {
		return (0 <= r && r <= this.row) && (0 <= c && c <= this.col);
	}
}

class Iterator
{
	private int rowIdx, colIdx;
	Node current;
	LinkList list;
	
	Iterator(LinkList linkList) {
		this.list = linkList;
		reset();
	}
	
	public void reset() {
		this.rowIdx = 1;
		this.colIdx = 1;
		current = list.getFirst();
	}

	public void up() {
		current = traverse(rowIdx-1, colIdx);
	}

	public void down() {
		current = traverse(rowIdx+1, colIdx);
	}

	public void left() {
		current = traverse(rowIdx, colIdx-1);
	}

	public void right() {
		current = traverse(rowIdx, colIdx+1);
	}

	public int getValue() {
		return current.data;
	}

	public void changeValue(int key) {
		current.data = key;
	}

	public void removeValue() {
		current.data = -1;
	}

	public void display() {
		list.displayList();
	}
	
	public Node traverse(int r, int c) {
		rowIdx = r;
		colIdx = c;
		
		Node tmp = list.getFirst();
		for (int i=1; i<r; i++)
			tmp = tmp.bottom;

		for (int j=1; j<c; j++)
			tmp = tmp.right;

		current = tmp;
		return current;
	}
}

public class _2ListIterator
{
	public static void main(String[] args) {
		int r = 7, c = 10, k = 10;
		LinkList theList = new LinkList(r, c);
		
		System.out.println("insert:");
		for (int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				theList.insert(i, j, k);
				k++;
			}
		}
		theList.displayList();
		
		System.out.println("delete:");
		theList.delete(2, 4);
		theList.displayList();
		
		theList.insert(1, 4, 111);
		theList.displayList();
		
		/////////////////////////////////////////////////////////////
		
		LinkList itrList = new LinkList(r, c);
		Iterator iter = itrList.getIterator();
		
		//iter.up();
		iter.down();
		//iter.left();
		iter.right();
		iter.getValue();
		iter.changeValue(1000);
		//iter.removeValue();
		iter.display();
		
		//iter.up();
		iter.down();
		//iter.left();
		iter.right();
		iter.getValue();
		iter.changeValue(1001);
		//iter.removeValue();
		iter.display();
		
	}
}

