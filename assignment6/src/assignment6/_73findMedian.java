package assignment6;

public class _73findMedian {
	int midIdx;
	int[] arr;
	
	public _73findMedian(int[] arr) {
		midIdx = arr.length/2;
		this.arr = arr;
	}

    private void swap(int x, int y) {
    	int t = arr[x];
    	arr[x] = arr[y];
    	arr[y] = t;
    }

    private int partitionIt(int left, int right) {
    	int leftPtr = left-1;
    	int rightPtr = right;
    	if (rightPtr <= leftPtr)
    		return -1;
    	int pivot = arr[rightPtr];
    	
    	while (true) {
	    	while (arr[++leftPtr] < pivot); // find bigger
	    	while (arr[--rightPtr] > pivot); // find smaller
	
	    	if (leftPtr >= rightPtr) // if pointers cross, partition done
	    		break;
	    	else
	    		swap(leftPtr, rightPtr);
    	}
    
    	swap(leftPtr, right); // restore pivot
    	return leftPtr;
	}

	public int findMedian(int low, int high) {
		if (high <= low)
			return arr[midIdx];
		int partitionIdx = partitionIt(low, high);

		if (partitionIdx == midIdx)
			return arr[midIdx];
		if (partitionIdx < midIdx)
			return findMedian(partitionIdx+1, high);
		else
			return findMedian(low, partitionIdx-1);
	}

	public static void main(String[] args) {
		int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

		_73findMedian fm = new _73findMedian(arr);
		int median = fm.findMedian(0, arr.length-1);
        System.out.println("Median: " + median);
	}
}
