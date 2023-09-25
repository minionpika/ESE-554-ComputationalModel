package insertionSortNoDuplicateDuringSorting;


class InsertionS {
	int arraySize = 0, numberOfElement = 0;
	long arr[];

	public InsertionS(int size) {
		arraySize = size;
		numberOfElement = 0;
		arr = new long[arraySize];
	}

	public void insert(long v) {
		arr[numberOfElement++] = v;
	}

	public void display(int u) {
		for (int i=0; i<u; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	/* idea is to put -1 when a match is found and move it all the way to the front of the array */
	public void insertionSort() {
		int hole;
		long value;
		int cnt = 0;
		long ans[];

		System.out.print("Before Sort ");
		display(numberOfElement);

		for (int i=1; i<numberOfElement;  i++) {
			value = arr[i];
			hole = i;
			
			while (hole>0 && value<=arr[hole-1]) {
				if (value == arr[hole-1]) {
					value = -1;
				}
				arr[hole] = arr[hole-1];
				hole = hole-1;
			}
			arr[hole] = value;
		}
		
		System.out.print("After Sort ");
		display(numberOfElement);
		
		// resize and print nonDuplicate array
		for (int i=numberOfElement-1; i>=0; i--) {
			if (arr[i] != -1) cnt++;
		}
		
		ans = new long[cnt];
		for (int i=0; i<cnt; i++) {
			 ans[i] = arr[i+numberOfElement-cnt];
		}
		
		System.out.print("Removed Duplicate ");
		for (int i=0; i<cnt; i++) {
			 System.out.print(ans[i]+" ");;
		}
		System.out.println();
	}
}

public class _36InsertionSortNoDuplicateDuringSorting {
	public static void main(String[] args)
	{
		int maxSize = 100;
		InsertionS arr;
		arr = new InsertionS(maxSize);
		
		// inserting numbers twice to make it duplicate
		for (int i=0; i<2; i++) {
			arr.insert(77); arr.insert(77); arr.insert(77);
			arr.insert(99);
			arr.insert(44);
			arr.insert(55);
			arr.insert(22);
			arr.insert(88);
			arr.insert(11);
			arr.insert(00);
			arr.insert(66);
			arr.insert(33);
		}

		arr.insertionSort();

	}
}
