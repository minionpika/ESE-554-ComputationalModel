package queueWith2Stacks;


class Stack
{
	private int maxSize, top;
	private long[] stackArray;
	
	public Stack(int s) {
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}
	
	public void push(long j) {
		stackArray[++top] = j;
	}
	
	public long pop() {
		return stackArray[top--];
	}
	
	public long peek() {
		return stackArray[top];
	}
	
	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
		return (top == maxSize-1);
	}
	
	public int size() {
		return (top+1);
	}
}

class QueueDemo
{
	Stack s1, s2;

	public QueueDemo(int s) {
		s1 = new Stack(s);
		s2 = new Stack(s);
	}

	public void insert(long j) {
		if (isFull()) return;
		
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		s1.push(j);
		while (!s2.isEmpty()) {
			s1.push(s2.pop());
		}
	}
	
	public long remove() {
		if (isEmpty()) return -1;
		return s1.pop();
	}
	
	public long peekFront() {
		if (isEmpty()) return -1;
		return s1.peek();
	}
	
	public boolean isEmpty() {
		return s1.isEmpty();
	}
	
	public boolean isFull() {
		return s1.isFull();
	}
	
	public int size() {
		return s1.size();
	}
}

public class _2QueueWith2Stacks {
	public static void main(String[] args) {
		QueueDemo queue = new QueueDemo(100000);	// random size
		queue.insert(10);
		queue.insert(20);
		queue.insert(30);
		queue.insert(40);
		queue.insert(100);
		queue.remove();
		queue.remove();
		queue.remove();
		queue.insert(50);
		queue.insert(60);
		queue.insert(70);
		queue.insert(80);
		
		while(!queue.isEmpty()) {
			long n = queue.remove();
			System.out.print(n);
			System.out.print(" ");
		}
	}
}
