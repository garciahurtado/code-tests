import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Problem from http://www.codechef.com/problems/RBTREE
 */
public class Main {
	public static int numInstructions;
	public static final int RED = 0;
	public static final int BLACK = 1;
	public static	int rootColor = BLACK; 

	public static void main(String args[]) throws Exception {
			String param1;
			String param2;
			int count = 1;

			Pattern pattern = Pattern.compile("^([A-z]{2})(\\s(\\d+)\\s(\\d+))?");
			BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
			numInstructions = Integer.parseInt( buffer.readLine()); 

			while (true) {
				String line = buffer.readLine();
				Matcher match = pattern.matcher(line); 
				match.find();
				String command = match.group(1);

				if(command.equals("Qi")){
					switchColor();
				} else if(command.equals("Qb")){
					param1 = match.group(3);
					param2 = match.group(4);
					int result = countBlack(Integer.parseInt(param1), Integer.parseInt(param2));
					System.out.println(result);
				} else if(command.equals("Qr")){
					param1 = match.group(3);
					param2 = match.group(4);
					int result = countRed(Integer.parseInt(param1), Integer.parseInt(param2));
					System.out.println(result);
				}

				if(++count > numInstructions){
					break;
				}
			} // end while

			// System.out.println("*** Ending program ***");
	}

	public static void switchColor(){
		rootColor = (rootColor ^ (1 << 0)); // only first bit is significant, flip it
		// System.out.println("Root color is now " + rootColor);
	}

	public static int countNodes(int start, int end){
		int count = 0;

		if(start == end){
			return 1;
		}

		int commonRoot = getCommonRoot(start, end);
		List pathUp = getPath(start, commonRoot);
		List pathDown = getPath(end, commonRoot);
		int countUp = pathUp.size();
		int countDown = pathDown.size(); 

		if(countDown < 0) countDown = 0; // avoid negative values

		// System.out.println("Common root " + commonRoot );
		// System.out.println("Total node count in path: up: " + countUp + " - Down: " + countDown);

		// Edge cases: avoid counting the start node twice, if it is also the common ancenstor
		if(start != commonRoot){
			count += countUp;
		}
		if(end != commonRoot){
			count += countDown;
		}

		// If it is a double path, avoid counting the common root
		if(start != commonRoot && end != commonRoot){
			count--;
		}

		// System.out.println("Total node count for both paths: " + count);

		return count;
	}

	public static int countRed(int start, int end){
		int nodeCount = countNodes(start, end);
		boolean isEven = (nodeCount % 2 == 0);

		int startColor = getColor(start);

		int halfSteps = (int) Math.floor((float) nodeCount / 2);
		int count = halfSteps;

		// If the start node color is red, AND the total count is odd, add one
		if((startColor == RED) && !isEven){
			// System.out.println("Added RED bonus");
			count = count + 1;
		}

		return count;
	}

	public static int countBlack(int start, int end){
		int nodeCount = countNodes(start, end);
		boolean isEven = (nodeCount % 2 == 0);
		
		int startColor = getColor(start);
		// System.out.println("Color of start node: " + startColor);

		int halfSteps = (int) Math.floor((float) nodeCount / 2);
		int count = halfSteps;
		
		// If the start node color is black, AND the total count is odd, add one
		if((startColor == BLACK) && !isEven){
			count = count + 1;
		}

		// System.out.println("Total black: " + count);
		return count;
	}

	/**
	 * Figure out what color a node is, based on the height of the tree that contains it. Even heights yield a red node for
	 * a black root node, and odd heights yield a black node. The colors are reversed if the root node color is red instead.
	 */
	public static int getColor(int node){
		int color;

		if(node == 1){ // root node
			color = rootColor;
		}

		int height = (int) Math.floor(log2(node)) + 1;
		// System.out.println("Height is: " + height);

		if(height < 1){
			height = 1;
		}

		if((height % 2) == 0){ // even height 
 			color = (rootColor == RED) ? BLACK : RED; // opposite of the root color
		} else { // odd height
			color = rootColor;
		}

		return color;
	}

	/**
	 * @ref http://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
	 */
	public static double log2(int n){
    if(n <= 0) throw new IllegalArgumentException();
    return 31 - Integer.numberOfLeadingZeros(n);
	}

	/*
	 * Find the closest root that these two nodes have in common
	 */
	public static int getCommonRoot(int start, int end){
		if((start == 1) || (end == 1)){
			return 1; // root node
		}

		List<Integer> startPath = getRootPath(start);
		List<Integer> endPath = getRootPath(end);

		// System.out.println("Start path size: " + startPath.size());
		// System.out.println("End path size: " + endPath.size());

		int common;
		Integer currentStart = 1;
		Integer currentEnd = 1;
		int i = 0;

		if((startPath.get(i) == null) || (endPath.get(i) == null)){
			return 1;
		}

		// Start iterating through each of the paths, one node at a time, until the current "start" 
		// node path is no longer the same as the current "end" node path
		while(startPath.get(i) == endPath.get(i)){
			
			if(currentStart == null) return currentEnd;
			if(currentEnd == null) return currentStart;

			currentStart = startPath.get(i);
			currentEnd = endPath.get(i);

			// System.out.println("Current start node: " + currentStart);
			// System.out.println("Current end node: " + currentEnd);

			// get the next value from each array
			i++;

			// TODO: refactor to use a for loop up to the max index of the shortest path
			if((i >= startPath.size()) || (i >= endPath.size())){
				break;
			}
		}

		return currentStart; // or current end, shouldn't matter, since they should be the same
	}

	/**
	 * Get all the ancestor nodes up to the root node for a specific 
	 * starting node
	 */
	public static List<Integer> getRootPath(int node){
		// System.out.println("Getting root path of " + node);
		return getPath(node, 1); // node 1 is the root node
	}

	/**
	 * Get the list of node keys representing the path between two nodes 
	 * (both nodes included)
	 */
	public static List<Integer> getPath(int start, int end){

		Integer next;
		List<Integer> ancestors = new ArrayList<Integer>();
		ancestors.add(start);

		if(end == start){ // bail early
			return ancestors;
		}
		
		// Flip the values so that the start is always higher than the end
		// (we want traverse the tree via the parent, ie: upwards)
		if(end > start){
			int temp;
			temp = start;
			start = end;
			end = temp;
		}

		next = getParent(start);

		while(next != null){
			if(next == end){
				break;
			}

			ancestors.add(next);
			next = getParent(next);
		}

		ancestors.add(end);

 		Collections.reverse(ancestors);
		return ancestors;
	}

	/**
	 * Given a specific node ID, return the direct parent
	 */
	public static Integer getParent(int node){
		if(node == 1){
			return null;
		}

		if(node % 2 > 0){ // odd number
			node = node - 1; // make it even, since it will have the same parent
		}

		// System.out.println("Parent of " + node + " is " + node / 2);
		return node / 2;
	}
}