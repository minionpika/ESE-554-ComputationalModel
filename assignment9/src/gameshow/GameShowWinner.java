package gameshow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Participant {
	private String name;
	private int score;

	public Participant(String n, int s) {
		name = n;
		score = s;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public void update(int newscore) {
		score += newscore;
	}
}

public class GameShowWinner {
	final static int maxNumberOfParticipants = 10000;

	public static void main(String[] args) throws IOException {
		System.out.println("Enter string: "); // jerry,65;bob,91;jerry,23;Eric,83
		String input = getString();
		calculateWinner(input);
	}

	private static void calculateWinner(String input) {

		Participant[] hashTable = new Participant[maxNumberOfParticipants];

		// format input
		String[] allPersonsAndScore = input.split(";"); // [[jerry,65],[bob,91],[jerry,23],[Eric,83]]

		for (String i : allPersonsAndScore) {
			String[] person = i.split(","); // [jerry,65]
			String name = person[0]; // jerry
			int score = Integer.parseInt(person[1]); // 65

			// update hash table
			int hashVal = hashFunc(name);
			if (hashTable[hashVal] == null)
				hashTable[hashVal] = new Participant(name, score);
			else
				hashTable[hashVal].update(score);
		}

		// calculate winner
		int maxScore = Integer.MIN_VALUE;
		String winnerName = null;
		for (Participant p : hashTable) {
			if (p != null) {
				if (p.getScore() > maxScore) {
					maxScore = p.getScore();
					winnerName = p.getName();
				}
			}
		}
		System.out.println("Winner: " + winnerName + ", " + maxScore);
	}

	private static int hashFunc(String key) {
		int hashVal = 0;
		key = key.toLowerCase();
		for (int j = 0; j < key.length(); j++) {
			int letter = key.charAt(j) - 96;
			hashVal = (hashVal * 27 + letter) % maxNumberOfParticipants;
		}
		return hashVal;
	}

	private static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
