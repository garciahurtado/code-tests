import java.io.*;

/**
 * Problem from http://www.codechef.com/problems/CIELAB
 */
public class Main {
	public static void main(String args[]) throws Exception {
			String line;

			BufferedReader buffer = new BufferedReader (new InputStreamReader (System.in));
			line = buffer.readLine();

			int answer = Integer.parseInt(
										line.substring(0, line.indexOf(" ")))
									-
									Integer.parseInt(
										line.substring(line.indexOf(" ")+1)
									);
									
			PrintWriter out = new PrintWriter(System.out);

			if(answer == 0){
				answer = 1;
			} else if(answer == 1){
				answer = 2;
			} else if(answer == 2){
				answer = 3;
			} else if(answer == 3){
				answer = 4;
			} else if(answer == 4){
				answer = 5;
			} else if(answer == 5){
				answer = 6;
			} else if(answer == 6){
				answer = 7;
			} else if(answer == 7){
				answer = 8;
			} else if(answer == 8){
				answer = 9;
			} else if((answer % 10) == 9){ // is the last digit a 9?
				answer--; 
			} else { // anything other than a 9, so let's add one
				answer++; 
			}

  		out.println(answer);
  		out.flush();
	}
}