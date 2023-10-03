package findMissingNumberSortedArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3FindMissingNumberSortedArray {

	static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	static int binarySearch(int l, int h, int[] arr) {
		int mid, p, q, r;
		
		if (arr[l]-l==2) return 1; // i.e {2 3 4 5 6}
		if (arr[h]-h==1) return -1; // i.e {1 2 3 4 5}
		
		while (l<=h) {
			if (h-l==1) break;
			mid=l+(h-l)/2;
			p = arr[l]-l;
			q = arr[mid]-mid;
			r = arr[h] - h;
			
			if (p==q) l=mid; // l~mid sorted
			else if (q==r) h=mid; // mid~h sorted
			else h=mid; // if more than 1 number missing
		}

		return arr[h]-1;
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

		int ans = binarySearch(0, arrSize-1, numbers);
		System.out.println(ans);
		
	}
}