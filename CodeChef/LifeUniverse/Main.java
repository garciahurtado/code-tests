import java.util.*;
import java.io.*;

/**
 * Problem from http://www.codechef.com/problems/TEST
 */
public class Main {

	public static void main(String args[]) throws Exception {

			BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
			Integer num = Integer.parseInt( buffer.readLine()); 

			while (num != 42) {
				System.out.println(num);
				num = Integer.parseInt( buffer.readLine()); 
				if(num == null){
					break;
				}
			}
	}
}