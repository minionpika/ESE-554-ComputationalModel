package oddEvenSort;


class BubbleS {
	int arraySize = 0, numberOfElement = 0;
	long arr[];

	public BubbleS(int size) {
		arraySize = size;
		numberOfElement = 0;
		arr = new long[arraySize];
	}

	public void insert(long v) {
		arr[numberOfElement++] = v;
	}

	public void display() {
		for (int i=0; i<numberOfElement; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Replacing the bubbleSort() method in bubbleSort.java with an oddEvenSort() method

	public void oddEvenSort() {
		long tmp;
		boolean isSwapped = false;

		for (int i=0; i<numberOfElement; i++) {
			isSwapped = false;
			for (int j=1; j<numberOfElement-1; j+=2) {
				if (arr[j] > arr[j+1]) {
					tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					isSwapped = true;
				}
				//display();
			}
			for (int j=0; j<numberOfElement-1; j+=2) {
				if (arr[j] > arr[j+1]) {
					tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					isSwapped = true;
				}
				//display();
			}
			if (isSwapped == false)
				break;
		}
	}
}

public class _34OddEvenSort {
	public static void main(String[] args)
	{
		int maxSize = 100;
		BubbleS arr;
		arr = new BubbleS(maxSize);
		
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		
		System.out.print("Before sort ");
		arr.display();
		arr.oddEvenSort();
		System.out.print("After sort ");
		arr.display();
	}
}
