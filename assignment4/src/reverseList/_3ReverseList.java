package reverseList;


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

	public void reverseList() {
		Link curr = first, currLeft = null, currRight = null;
		while (curr != null) {
			currRight = curr.next;
			curr.next = currLeft;
			currLeft = curr;
			curr = currRight;
		}
		first = currLeft;
	}
}

public class _3ReverseList {
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
		System.out.print("List: ");
		theList.displayList();
	
		theList.reverseList();
		System.out.print("Reverse List: ");
		theList.displayList();
		
	}
}
