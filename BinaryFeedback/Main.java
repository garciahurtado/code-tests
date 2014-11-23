package BinaryFeedback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Problem from http://www.codechef.com/problems/ERROR
 */
public class Main {
	public static void main(String args[]) throws Exception {
		Integer num;
		Integer currentLine = 0;
		String line;
		Boolean currentBit = null;
		Boolean previousBit = null;
		boolean flipped = false;
		boolean previousFlipped = false;
		String answer = "";

		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		line = buffer.readLine();
		num = Integer.parseInt(line);

		while (currentLine++ < num) {

			line = buffer.readLine();
			answer = "Bad";
			previousBit = null;
			flipped = false;
			previousFlipped = false;

			for (int i = 0; i < line.length(); i++) {
				currentBit = (((int) line.charAt(i)) - 48 == 0) ? false : true;

				if (previousBit != null) {
					flipped = previousBit != currentBit;
				}

				if (flipped && previousFlipped) {
					answer = "Good";
					break;
				}

				if (flipped) {
					previousFlipped = true;
				} else {
					previousFlipped = false;
				}
				previousBit = currentBit;
			}

			out.println(answer);
		}
		out.flush();
	}
}