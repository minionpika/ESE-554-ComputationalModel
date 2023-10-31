package assignment6;

public class _74findKth {
	int kthLargestIdx;
	int[] arr;
	
	public _74findKth(int[] arr, int kthIdx) {
		kthLargestIdx = kthIdx;
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

	public int findKth(int low, int high) {
		if (high <= low)
			return arr[kthLargestIdx];
		int partitionIdx = partitionIt(low, high);

		if (partitionIdx == kthLargestIdx)
			return arr[kthLargestIdx];
		if (partitionIdx < kthLargestIdx)
			return findKth(partitionIdx+1, high);
		else
			return findKth(low, partitionIdx-1);
	}

	public static void main(String[] args) {
		int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};

		_74findKth fk = new _74findKth(arr, 9);
		int k = fk.findKth(0, arr.length-1);
        System.out.println("Kth: " + k);
	}
}
