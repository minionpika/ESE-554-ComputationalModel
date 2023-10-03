package firstNonRepeatChar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5FirstNonRepeatChar {

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static void main(String[] args) throws IOException {
		
		int arr[] = new int[26];
		String inputString = getString();
		
		for (int i=0; i<inputString.length(); i++) {
			int a = inputString.charAt(i)-'a';
			if (a<0 || a>25) {
				System.out.println(-1);
				return;
			}
			arr[a]++;
		}

		for (int i=0; i<inputString.length(); i++) {
			if(arr[inputString.charAt(i)-'a'] == 1) {
				System.out.println(inputString.charAt(i));
				return;
			}
		}
		System.out.println("NULL");
	}
}
