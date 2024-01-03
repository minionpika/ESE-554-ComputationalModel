package knightpuzzle;

import java.util.*;


class StackX {
	private final int SIZE;
	private int[] st;
	private int top;

	public StackX(int size) {
		SIZE = size;
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
	
	public int currSize() {
		return top;
	}
}

class Vertex {
	public char label;
	public boolean wasVisited;
	public int parent;

	public Vertex(char lab) {
		label = lab;
		wasVisited = false;
		parent = -1;
	}
	
	public void reset() {
		label = '-';
		wasVisited = false;
		parent = -1;
	}
}

class Graph {
	private final int BOARD_SIZE;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	private StackX theStack;
	private static final int[][] MOVE_OFFSETS = {
            {-1, -2}, {1, -2},
            {-2, -1}, {2, -1},
            {-2, 1}, {2, 1},
            {-1, 2}, {1, 2}
    };

	public Graph(int boardsize) {
		BOARD_SIZE = boardsize;
		vertexList = new Vertex[boardsize * boardsize];
		adjMat = new int[boardsize * boardsize][boardsize * boardsize];
		nVerts = boardsize * boardsize;
		for (int y = 0; y < boardsize; y++)
			for (int x = 0; x < boardsize; x++)
				adjMat[x][y] = 0;
		for (int y = 0; y < boardsize * boardsize; y++)
			vertexList[y] = new Vertex('.');
		theStack = new StackX(boardsize * boardsize);
		buildBoard();
	}

	public void buildBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
            	// currentRow = row * BOARD_SIZE, currentCol = col;
                int currentVertex = row * BOARD_SIZE + col;
                for (int[] moveOffset : MOVE_OFFSETS) {
                	int moveRow = row + moveOffset[0];
                    int moveCol = col + moveOffset[1];
                    if (isSafe(moveRow, moveCol)) {
                        addEdge(currentVertex, moveRow * BOARD_SIZE + moveCol);
                    }
                }
            }
		}
	}

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end) {
		//System.out.println(start+" "+end);
		adjMat[start][end] = 1; // DGraph
	}

	public boolean isSafe(int row, int col) {
        return 0 <= row && row < BOARD_SIZE && 0 <= col && col < BOARD_SIZE;
    }

	public void dfs(int vertex) {
		vertexList[vertex].wasVisited = true; // mark it
		theStack.push(vertex); // push it
		System.out.println("Current Index (" + vertex / BOARD_SIZE + ", " + vertex % BOARD_SIZE + ")");
		
		while (!theStack.isEmpty()) {
			// if stack is full, you won
			if (theStack.currSize() == BOARD_SIZE * BOARD_SIZE - 1) {
                System.out.println("Knight's Tour Completed!");
                printBoard();
                printPathway();
                break;
            }
			// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex(theStack.peek());
			if (v == -1) { // if no such vertex,
				int vertexWithNoChild = theStack.pop();
				vertexList[vertexWithNoChild].reset();
			}
			else
			{
				vertexList[v].wasVisited = true; // mark it
				vertexList[theStack.peek()].label = '#';
				vertexList[theStack.peek()].parent = v;
				theStack.push(v); // push it
			}
		}

		// stack is empty, so we're done
		for (int j = 0; j < nVerts; j++) // reset flags
			vertexList[j].reset();
	}
	
	// returns an unvisited vertex adj to v
	public int getAdjUnvisitedVertex(int v) {
		for (int j = vertexList[v].parent + 1; j < nVerts; j++)
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
				return j;
		return -1;
	}

	public void printBoard() {
		// print matrix
		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 0; x < BOARD_SIZE; x++)
				System.out.print(vertexList[y * BOARD_SIZE + x].label + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public void printPathway() {
		// print vertices in order at which they need to be visited
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		while (!theStack.isEmpty()) {
			tmp.add(theStack.pop());
		}
		Collections.reverse(tmp);
		System.out.println("Sequencial visit: " + tmp);
	}
}

public class KnightPuzzle {
	public static void main(String[] args) {
		System.out.println("Board size?: ");
		Scanner s = new Scanner(System.in);
		int boardsize = s.nextInt();
		Graph theGraph = new Graph(boardsize);

		for(int i = 0; i < boardsize * boardsize; i++)
			theGraph.dfs(i);

		s.close();
	}
}
