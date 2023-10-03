package find2ndMax2ndMin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4FindSecondMaxSecondMin {

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	static void printSecondMax(int arr[], int m) {
		int mx=Integer.MIN_VALUE, secMx=Integer.MIN_VALUE;

		for (int i=0; i<m; i++) {
			if (arr[i]>mx) { secMx=mx; mx=arr[i]; }
			else if (arr[i]>secMx && arr[i]<mx) { secMx=arr[i]; }
		}

		System.out.print("Second Max: ");

		if (secMx == Integer.MIN_VALUE)
			System.out.println("NULL");
		else
			System.out.println(secMx);
	}
	
	static void printSecondMin(int arr[], int m) {
		int mn=Integer.MAX_VALUE, secMn=Integer.MAX_VALUE;

		for (int i=0; i<m; i++) {
			if (arr[i]<mn) { secMn=mn; mn=arr[i]; }
			else if (arr[i]<secMn && arr[i]>mn) { secMn=arr[i]; }
		}

		System.out.print("Second Min: ");

		if (secMn == Integer.MAX_VALUE)
			System.out.println("NULL");
		else
			System.out.println(secMn);
	}
	
	public static void main(String[] args) throws IOException {
		int N = 100000;	// random size
		int numbers[] = new int[N];
		int arrSize=0;
		
		String inputString = getString();
		String[] splitSpace = inputString.split(" ");
		
		for (String s:splitSpace) {
			numbers[arrSize] = Integer.parseInt(s);
			arrSize++;
		}

		printSecondMax(numbers, arrSize);
		printSecondMin(numbers, arrSize);

	}
}
