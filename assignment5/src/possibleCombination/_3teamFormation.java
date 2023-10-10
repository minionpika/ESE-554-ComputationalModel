package possibleCombination;

import java.io.*;
import java.util.*;

public class _3teamFormation {
	static void findKCombinations(char[] alphabets, int n, int k) {
		List<Character> tmp = new ArrayList<>();
		List<List<Character>> output = new ArrayList<>();
		nckHelper(alphabets, output, tmp, 0, k);
		
		for (List<Character> l:output) {
			System.out.println(l);
		}
	}

	static void nckHelper(char[] alphabets, List<List<Character>> output, List<Character> tmp, int index, int k) {
		if (k == 0) {
			output.add(new ArrayList<>(tmp));
			return;
		}
		
		if(index == alphabets.length)
			return;
		
		tmp.add(alphabets[index]);
		nckHelper(alphabets, output, tmp, index+1, k-1);

		tmp.remove(tmp.size()-1);
		nckHelper(alphabets, output, tmp, index+1, k);
	}

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		char[] alphabets = {'A', 'B', 'C', 'D', 'E'};
		System.out.println("group size: ");
    	int n = Integer.parseInt(getString());
    	System.out.println("team size: ");
    	int k = Integer.parseInt(getString());
    	findKCombinations(alphabets, n, k);
	}

}
