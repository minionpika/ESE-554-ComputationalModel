package findRange;

import java.io.*;


public class _2FindRange {

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	static int getMax(int arr[], int m) {
		int mx=Integer.MIN_VALUE;
		for (int i=0; i<m; i++) {
			if (arr[i]>mx) mx=arr[i];
		}
		return mx;
	}
	
	static int getMin(int arr[], int m) {
		int mn=Integer.MAX_VALUE;
		for (int i=0; i<m; i++) {
			if (arr[i]<mn) mn=arr[i];
		}
		return mn;
	}
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(getString());
		int numbers[] = new int[n];
		int i=0;

		String inputString = getString();
		String[] splitSpace = inputString.split(" ");

		for (String s:splitSpace) {
			numbers[i] = Integer.parseInt(s);
			i++;
		}
		
		System.out.println(getMax(numbers, n)-getMin(numbers, n));
	}

}
