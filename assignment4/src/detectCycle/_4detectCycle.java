package detectCycle;


class Link
{
	public int iData;
	public double dData;
	public Link next;

	public Link(int id, double dd) {
		iData = id;
		dData = dd;
	}
	
	public void displayLink() {
		System.out.print("{" + iData + ", " + dData + "} ");
	}
}

class LinkList
{
	private Link first;
	
	public LinkList() {
		first = null;
	}
	
	public boolean isEmpty() {
		return (first==null);
	}

	public void insertFirst(int id, double dd) {
		Link newLink = new Link(id, dd);
		newLink.next = first;
		first = newLink;
	}
	
	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}

	public void displayList() {
		Link current = first;
		while(current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}

	// making cycle for checking purpose
	public void makeCycle() {
		Link current = first, lastNode = first;
		while (current != null) {
			lastNode = current;
			current = current.next;
		}
		lastNode.next = first;
	}

	// floyds cycle algo
	public boolean detectCycle() {
		Link slowPointer = first, fastPointer = first;
		while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
			if (slowPointer == fastPointer)
				return true;
		}
		return false;
	}
}

public class _4detectCycle {
	public static void main(String[] args) {
		LinkList theList = new LinkList();
		theList.insertFirst(22, 2.99);
		theList.insertFirst(44, 4.99);
		theList.insertFirst(66, 6.99);
		theList.insertFirst(88, 8.99);
		theList.insertFirst(2, 2.99);
		theList.insertFirst(4, 4.99);
		theList.insertFirst(6, 6.99);
		theList.insertFirst(8, 8.99);
		theList.makeCycle();	// you may comment or uncomment it to check both cases
		
		System.out.println("Cycle exists? " + theList.detectCycle());
		
	}
}
