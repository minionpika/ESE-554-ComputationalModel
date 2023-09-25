package arithmaticCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class StackChar
{
	private int maxSize, top;
	private char[] stackArray;

	public StackChar(int s) {
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

class StackDouble
{
	private int maxSize;
	private double[] stackArray;
	private int top;

	public StackDouble(int s) {
		maxSize = s;
		stackArray = new double[maxSize];
		top = -1;
	}

	public void push(double j) {
		if (isFull())
			return;
		stackArray[++top] = j;
	}

	public double pop() {
		if (isEmpty())
			return -1;
		return stackArray[top--];
	}

	public double peek() {
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

class InfixToPostfix
{
	private StackChar theStack;
	private String input, output = "";

	public InfixToPostfix(String in) {
		input = in;
		theStack = new StackChar((int)in.length());
	}

	public String doTransform() {
		char ch, ch1;
		boolean expectingOperand = true;	// unary will come starting of an eqn or after an operator

		for (int i=0; i<input.length(); i++) {
			ch = input.charAt(i);

			if (ch == '+' || ch == '-') {
				if (expectingOperand) {
					if (ch == '-')
						theStack.push('~');		// unary has the highest precedence
				}
				else
					gotOper(ch, 1);

				expectingOperand = true;
			}
			else if (ch == '*' || ch == '/') {
				gotOper(ch, 2);
				expectingOperand = true;
			}
			else if (ch == '(') {
				theStack.push(ch);
				expectingOperand = true;
			}
			else if (ch == ')') {
				gotParen();
				expectingOperand = false;
			}
			else {
				if (i != 0) {
					ch1 = input.charAt(i-1);
	
					if (Character.isDigit(ch1) || ch1 == '.')
						output = output + ch;	// continuous digit, no need to insert space
					else
						output = output + ' ' + ch;		// new digit, insert space
				}
				else
					output = output + ch;	// i==0 no space needed
				
				expectingOperand = false;
			}
		}

		while (!theStack.isEmpty()) {
			output = output + theStack.pop();
		}

		return output;
	}

	public void gotOper(char opThis, int prec1) {
		int prec2;
		char opTop;

		while (!theStack.isEmpty()) {
			opTop = theStack.pop();
			
			if (opTop == '(') {
				theStack.push(opTop);
				break;
			}
			else {
				if (opTop == '+' || opTop == '-')
					prec2 = 1;
				else
					prec2 = 2;

				if (prec2 < prec1) {
					theStack.push(opTop);
					break;
				}
				else
					output = output + opTop;
			}
		}
		theStack.push(opThis);
	}

	public void gotParen() {
		char chx;

		while (!theStack.isEmpty()) {
			chx = theStack.pop();
			if (chx == '(')
				break;
			else
				output = output + chx;
		} 
	}
} 


class ParsePost
{
	private StackDouble theStack;
	private String input;
	private int postfixLength;
	
	public ParsePost(String s) {
		input = s;
		postfixLength = s.length();
	}
	
	public double doParse() {
		int i=0, j=0;
		double num1, num2, interAns;
		char ch, ch1;
		String number;
		theStack = new StackDouble(100000);		// random size

		for (j=0; j<postfixLength; j++) {
			ch = input.charAt(j);

			if (Character.isDigit(ch) || ch == '.') {	// here we're making the full number
				number = "";
				number += ch;

				for (i=j+1; i<postfixLength; i++) {
					ch1 = input.charAt(i);
					if (Character.isDigit(ch1) || ch1 == '.')
						number += ch1;
					else
						break;
				}
				
				j=i-1;
				theStack.push(Double.parseDouble(number));			
			}
			else if (ch == ' ') {	// no need to put that space in the stack
				continue;
			}
			else if (ch == '~') {
				num1 = theStack.pop();
				theStack.push(num1*(-1));
			}
			else {
				num2 = theStack.pop();
				num1 = theStack.pop();

				switch(ch)
				{
					case '+':
						interAns = num1 + num2;
						break;
					case '-':
						interAns = num1 - num2;
						break;
					case '*':
						interAns = num1 * num2;
						break;
					case '/':
						interAns = num1 / num2;
						break;
					default:
						interAns = 0;
				}
	
				theStack.push(interAns);
			}
		}

		interAns = theStack.pop();
		return interAns;
	}
}


public class _4ArithmaticCalculator {
	
	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static void main(String[] args) throws IOException {
		String input, output;

		input = getString();
		
		InfixToPostfix ifpf = new InfixToPostfix(input);
		output = ifpf.doTransform();
		// System.out.println("Postfix= " + output);
		
		ParsePost aParser = new ParsePost(output);
		System.out.println(aParser.doParse());	// do the evaluation

	}
}

/* https://saturncloud.io/blog/how-to-modify-the-shuntingyard-algorithm-to-accept-unary-operators/
https://www.cse.psu.edu/~kxc104/ee324/02f/hw472/hw7.html
https://stackoverflow.com/questions/1593080/how-can-i-modify-my-shunting-yard-algorithm-so-it-accepts-unary-operators
https://www.andrew.cmu.edu/course/15-200/s06/applications/ln/junk.html

100+(1+250/100)*3
Evaluates to: 110.5
1+2*3/(6*6+5*4)*3
Evaluates to: 1.3214285714285714
100.01+(1.22+250.34/100)*3.5
Evaluates to: 113.0419
-4-9
Evaluates to: -13.0
-(-4)-9
Evaluates to: -5.0
-(-4)+9
Evaluates to: 13.0
(3+6)*(2-4)+7
Evaluates to: -11.0
3+5-2
Evaluates to: 6.0
10*2/5
Evaluates to: 4.0
4+6*2-8/2
Evaluates to: 12.0
(4+6)*2-(8/2)
Evaluates to: 16.0
-5+3.56
Evaluates to: -1.44
-(5+3)
Evaluates to: -8.0
-5-3
Evaluates to: -8.0
1000000*1000000
Evaluates to: 1.0E12
3+2*4-6/2
Evaluates to: 8.0
1.23456789+9.87654321
Evaluates to: 11.111111099999999
(2+(3*(4+1)))/2
Evaluates to: 8.5
3*(2+5)/(1+1)
Evaluates to: 10.5
-(-(-5))
Evaluates to: -5.0
5
Evaluates to: 5.0
9/0
Evaluates to: Infinity
5
Evaluates to: 5.0
0/4
Evaluates to: 0.0
(6+6)
Evaluates to: 12.0
(3-5-8)
Evaluates to: -10.0
((9-0-4)/6+9)
Evaluates to: 9.833333333333334
((1-8-2)/4+2*7)
Evaluates to: 11.75
(90-8-5)/(1+6)
Evaluates to: 11.0
2*2+2*(2-3)
Evaluates to: 2.0
(-6+2*2+2*(2-3)/4)
Evaluates to: -2.5
(5-(-4)-9)
Evaluates to: 0.0
(5-(-2)-9)
Evaluates to: -2.0
-88
Evaluates to: -88.0
*/