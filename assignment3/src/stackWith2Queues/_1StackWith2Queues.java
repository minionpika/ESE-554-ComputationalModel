package stackWith2Queues;


class Queue
{
	private int maxSize, front, rear, nItems;
	private long[] queArray;

	public Queue(int s) {
		maxSize = s;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	public void insert(long j) {
		if (isFull()) return;
		if (rear == maxSize-1)
			rear = -1;
		queArray[++rear] = j;
		nItems++;
	}
	
	public long remove() {
		if (isEmpty()) return -1;
		long temp = queArray[front++];
		if (front == maxSize)
			front = 0;
		nItems--;
		return temp;
	}
	
	public long peekFront() {
		if (isEmpty()) return -1;
		return queArray[front];
	}
	
	public boolean isEmpty() {
		return (nItems==0);
	}
	
	public boolean isFull() {
		return (nItems==maxSize);
	}
	
	public int size() {
		return nItems;
	}
}
	
class StackDemo
{
	Queue q1, q2, q;	// q1 = main, q2 = processing
	
	public StackDemo(int s) {
		q1 = new Queue(s);
		q2 = new Queue(s);
	}
	
	public void push(long j) {
		if (isFull()) return;
		q2.insert(j);
		
		while (!q1.isEmpty()) {
			q2.insert(q1.remove());
		}
		q = q1;
		q1 = q2;
		q2 = q;
	}
	
	public long pop() {
		if (isEmpty()) return -1;
		return q1.remove();
	}
	
	public long peek() {
		return q1.peekFront();
	}
	
	public boolean isEmpty() {
		return q1.isEmpty();
	}
	
	public boolean isFull() {
		return q1.isFull();
	}
	
	public int size() {
		return q1.size();
	}
}

public class _1StackWith2Queues
{
	public static void main(String[] args) {
		StackDemo stack = new StackDemo(100000);	// random size
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(100);
		stack.pop();
		stack.pop();
		stack.push(50);
		stack.push(60);
		stack.push(70);
		stack.push(80);
		
		while(!stack.isEmpty()) {
			long n = stack.pop();
			System.out.print(n);
			System.out.print(" ");
		}
	}
}
