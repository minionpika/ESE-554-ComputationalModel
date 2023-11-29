package connectivityTableAndWarshall;

// What vertices can you reach if you start on a particular vertex?

class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;

	public StackX() {
		st = new int[SIZE];
		top = -1;
	}

	public void push(int j) {
		st[++top] = j;
	}

	public int pop() {
		return st[top--];
	}

	public int peek() {
		return st[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}
}

class Vertex {
	public char label;
	public boolean wasVisited;

	public Vertex(char lab) {
		label = lab;
		wasVisited = false;
	}
}

class Graph {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	private StackX theStack;

	public Graph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int y = 0; y < MAX_VERTS; y++)
			for (int x = 0; x < MAX_VERTS; x++)
				adjMat[x][y] = 0;
		theStack = new StackX();
	}

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end) {
		adjMat[start][end] = 1; // DGraph
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	public void dfs(int vertex) {
		vertexList[vertex].wasVisited = true; // mark it
		displayVertex(vertex); // display it
		theStack.push(vertex); // push it

		while (!theStack.isEmpty()) {
			// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex(theStack.peek());
			if (v == -1) // if no such vertex,
				theStack.pop();
			else
			{
				vertexList[v].wasVisited = true; // mark it
				displayVertex(v); // display it
				theStack.push(v); // push it
			}
		}

		// stack is empty, so we're done
		for (int j = 0; j < nVerts; j++) // reset flags
			vertexList[j].wasVisited = false;
	}

	// returns an unvisited vertex adj to v
	public int getAdjUnvisitedVertex(int v) {
		for (int j = 0; j < nVerts; j++)
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
				return j;
		return -1;
	}

	public void connectivityTable() {
		for (int j = 0; j < nVerts; j++) {
			dfs(j);
			System.out.println();
		}
	}

	public void warshallAdjMatrix() {
		for (int y = 0; y < nVerts; y++) { // at each row
			for (int x = 0; x < nVerts; x++) { // at each cell in a row
				// (y,x) = 1, y --> x
				if (adjMat[y][x] == 1) {
					for (int z = 0; z < nVerts; z++) {
						// (z,y) == 1, z --> y
						if (adjMat[z][y] == 1) {
							// (z,x) = 1, z --> x
							adjMat[z][x] = 1;
						}
					}
				}
			}
		}
		
		// print matrix
		for (int y = 0; y < nVerts; y++) {
			for (int x = 0; x < nVerts; x++) {
				System.out.print(adjMat[y][x]+" ");
			}
			System.out.println();
		}
	}
}

public class ConnectivityTableAndWarhsallDG {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A');
		theGraph.addVertex('B');
		theGraph.addVertex('C');
		theGraph.addVertex('D');
		theGraph.addVertex('E');

//		theGraph.addEdge(0, 1); // AB
//		theGraph.addEdge(1, 2); // BC
//		theGraph.addEdge(0, 3); // AD
//		theGraph.addEdge(3, 4); // DE
		
		theGraph.addEdge(0, 2); // AC
		theGraph.addEdge(1, 0); // BA
		theGraph.addEdge(4, 2); // EC
		theGraph.addEdge(1, 4); // BE
		theGraph.addEdge(3, 4); // DE

		System.out.println("Connectivity Table:");
		theGraph.connectivityTable();
		System.out.println("\nWarshall Adj Matrix:");
		theGraph.warshallAdjMatrix();
	}
}
