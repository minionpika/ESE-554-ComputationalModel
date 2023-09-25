package iHateEvenSubarrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Stack
{
	private int maxSize, top;
	private char[] stackArray;

	public Stack(int s) {
		maxSize = s;
		stackArray = new char[maxSize];
		top = -1;
	}

	public void push(char j) {
		if (isFull())
			return;
		stackArray[++top] = j;
	}

	public char pop() {
		if (isEmpty())
			return '0';
		return stackArray[top--];
	}

	public char peek() {
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

public class _3IHateEvenSubarrays {

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		String in = "", out = "";
		char ch;
		int stackSize = 100000;

		Stack stack = new Stack(stackSize);
		int testCases = Integer.parseInt(getString());
		String minimalStrings[] = new String[testCases];
		
		/* if at any time stack.top() == current char, that means we got a 00 or 11, so pop the top and 
		 * not pushing current one */

		for (int t=0; t<testCases; t++) {
			in = getString();
			out = "";

			for (int i=0; i<in.length(); i++) {
				ch = in.charAt(i);
				
				if (!stack.isEmpty() && stack.peek() == ch) {
					stack.pop();
					continue;
				}
				stack.push(ch);
			}
			
			if (stack.isEmpty()) {
				out = "EMPTY";
			}
			else {		
				while (!stack.isEmpty()) {
					out += stack.pop();
				}
				out = (new StringBuffer(out)).reverse().toString();	// because of the stack, out string is reversed
			}
			
			minimalStrings[t] = out;
		}
		
		for (int t=0; t<testCases; t++) {
			System.out.println(minimalStrings[t]);
		}
	}
}