package rehash;

import java.io.*;

class DataItem {
	private int iData;

	public DataItem(int ii) {
		iData = ii;
	}

	public int getKey() {
		return iData;
	}
}

class HashTable {
	private DataItem[] hashArray; // array holds hash table
	private int arraySize;
	private DataItem nonItem; // for deleted items
	private int numOfItems;

	public HashTable(int size) // constructor
	{
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); // deleted item key is -1
	}

	public void displayTable() {
		System.out.print("Table: ");
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}

	public int hashFunc(int key, int arrSize) {
		return key % arrSize;
	}

	public void insert(DataItem item) // (assumes table not full)
	{
		int key = item.getKey();
		int hashVal = hashFunc(key, arraySize);

		while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
			++hashVal;
			hashVal %= arraySize; // wraparound if necessary
		}
		hashArray[hashVal] = item;
		numOfItems++;

		if (loadFactor() > 0.5) {
			rehash();
		}
	}

	// returns 1st prime > min
	private int getPrime(int min) {
		for (int j = min + 1; true; j++)
			if (isPrime(j))
				return j;
	}

	private boolean isPrime(int n) {
		for (int j = 2; j * j <= n; j++)
			if (n % j == 0)
				return false;
		return true;
	}

	private double loadFactor() {
		return (double) numOfItems / arraySize;
	}

	private void rehash() {
		System.out.println("\nRehashing the array:\n");
		int newSize = getPrime(arraySize * 2);
		DataItem[] newHasharray = new DataItem[newSize];

		for (DataItem i : hashArray) {
			if (i != null && i.getKey() != -1) {
				int key = i.getKey();
				int hashVal = hashFunc(key, newSize);

				while (newHasharray[hashVal] != null && newHasharray[hashVal].getKey() != -1) {
					++hashVal;
					hashVal %= newSize; // wraparound if necessary
				}
				newHasharray[hashVal] = i;
				// numOfItems++;
			}
		}
		arraySize = newSize;
		hashArray = newHasharray;
	}

	public DataItem delete(int key) {
		int hashVal = hashFunc(key, arraySize);
		while (hashArray[hashVal] != null) {
			if (hashArray[hashVal].getKey() == key) {
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				numOfItems--;
				return temp;
			}
			++hashVal;
			hashVal %= arraySize;
		}
		return null;
	}

	public DataItem find(int key) {
		int hashVal = hashFunc(key, arraySize);
		while (hashArray[hashVal] != null) {
			if (hashArray[hashVal].getKey() == key)
				return hashArray[hashVal];
			++hashVal;
			hashVal %= arraySize;
		}
		return null;
	}
}

public class _114Rehashing {

	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		int aKey, size, n, keysPerCell;
		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		keysPerCell = 10;

		HashTable theHashTable = new HashTable(size);
		for (int j = 0; j < n; j++) {
			aKey = (int) (java.lang.Math.random() * keysPerCell * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}
		while (true) {
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, delete, or find: ");
			char choice = getChar();
			switch (choice) {
			case 's':
				theHashTable.displayTable();
				break;
			case 'i':
				System.out.print("Enter key value to insert: ");
				aKey = getInt();
				aDataItem = new DataItem(aKey);
				theHashTable.insert(aDataItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getInt();
				theHashTable.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getInt();
				aDataItem = theHashTable.find(aKey);
				if (aDataItem != null) {
					System.out.println("Found " + aKey);
				} else
					System.out.println("Could not find " + aKey);
				break;
			default:
				System.out.print("Invalid entry\n");
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}
