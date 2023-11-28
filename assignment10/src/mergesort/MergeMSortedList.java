package mergesort;

import java.io.*;
import java.util.*;


class Node {
	public int listIdx;
	public int value;
	public int index;

	public Node(int listIdx, int index, int value) {
		this.listIdx = listIdx;
		this.value = value;
		this.index = index;
	}
}

class MinHeap {
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public MinHeap(int mx) {
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean insert(Node node) {
		if (currentSize == maxSize)
			return false;

		heapArray[currentSize] = node;
		trickleUp(currentSize++);
		return true;
	}

	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];

		while (index > 0 && heapArray[parent].value > bottom.value) {
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		heapArray[index] = bottom;
	}

	public Node remove() {
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	public void trickleDown(int index) {
		int smallerChild;
		Node top = heapArray[index];
		while (index < currentSize / 2) {
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			// find smaller child
			if (rightChild < currentSize && heapArray[leftChild].value > heapArray[rightChild].value)
				smallerChild = rightChild;
			else
				smallerChild = leftChild;

			if (top.value <= heapArray[smallerChild].value)
				break;
			heapArray[index] = heapArray[smallerChild];
			index = smallerChild;
		}
		heapArray[index] = top;
	}
}

public class MergeMSortedList {
    public static List<Integer> mergeLists(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        MinHeap minHeap = new MinHeap(lists.size());
        Node nn;
        	
        // Add the first element from each list to the min heap
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
            	nn = new Node(i, 0, lists.get(i).get(0));
                minHeap.insert(nn);
            }
        }

        while (!minHeap.isEmpty()) {
            Node node = minHeap.remove();
            result.add(node.value);

            // Move to the next element in the same list
            if (node.index + 1 < lists.get(node.listIdx).size()) {
            	nn = new Node(node.listIdx, node.index + 1, lists.get(node.listIdx).get(node.index + 1));
                minHeap.insert(nn);
            }
        }

        return result;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.out.println("Enter the number of lists:");
        int m = Integer.parseInt(getString());

        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            System.out.println("Enter sorted list:");
            List<Integer> list = new ArrayList<>();
            String[] elements = getString().split(" ");
            for (String element : elements) {
                list.add(Integer.parseInt(element));
            }
            lists.add(list);
        }

        List<Integer> mergedList = mergeLists(lists);
        System.out.println("Output:" + mergedList);
    }
    
    public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
