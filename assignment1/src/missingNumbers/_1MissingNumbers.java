package missingNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _1MissingNumbers {

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(getString());
		int numbers[] = new int[n+1];
		boolean isMissing = false;

		String inputString = getString();
		String[] splitSpace = inputString.split(" ");

		for (String s:splitSpace) {
			numbers[Integer.parseInt(s)] = 1;
		}
	
		for (int i=1; i<=n; i++) {
			if (numbers[i] != 1) {
				System.out.print(i +" ");
				isMissing = true;
			}
		}
		
		if (isMissing == false)
			System.out.print("No missing elements");
		
		System.out.println();
	}

}
