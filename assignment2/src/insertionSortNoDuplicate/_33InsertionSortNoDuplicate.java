package insertionSortNoDuplicate;


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

	public void insertionSort() {
		int hole;
		long value;
		
		System.out.print("Before Sort ");
		display(numberOfElement);

		for (int i=1; i<numberOfElement;  i++) {
			value = arr[i];
			hole = i;
			
			while (hole>0 && value<arr[hole-1]) {
				arr[hole] = arr[hole-1];
				hole = hole-1;
			}
			arr[hole] = value;
		}
		
		System.out.print("After Sort ");
		display(numberOfElement);
	}

	// In insertSort.java, adding noDups() to remove duplicates from a previously sorted array

	public void noDuplicate() {
		int lastNonrepeatedIdx = 0;
		
		for (int i=1; i<numberOfElement; i++) {
			if (arr[i] == arr[lastNonrepeatedIdx]) {
				continue;
			}
			else {
				lastNonrepeatedIdx++;
				arr[lastNonrepeatedIdx] = arr[i];
			}
		}
		
		// making rest of the array 0 after lastNonrepeatedIdx
		for (int i=lastNonrepeatedIdx+1; i<numberOfElement; i++) {
			arr[i] = 0;
		}
		
		System.out.print("Removed Duplicate ");
		display(lastNonrepeatedIdx+1);
	}
}

public class _33InsertionSortNoDuplicate {
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
		arr.noDuplicate();	// used in-place algo

	}
}
