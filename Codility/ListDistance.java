// you can also use imports, for example:
import java.util.*;

// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

class ListDistance {
    public static void main(String[] args) {
        int[] list = {7, 21, 3, 42, 3, 7};
		Arrays.sort(list);
		
		int distance = 1000000; // start at max distance possible between two ints
		int currentDistance = 0;
		
		for(int i = 0; i < list.length - 1; i++){
		    currentDistance = list[i] - list[i + 1];
		    currentDistance = Math.abs(currentDistance);
		    if(currentDistance == 0){
		        System.out.println("0");
		    } else if(distance > currentDistance){
		        distance = currentDistance;
		    }
		}
		
		 System.out.println(distance);
    }
}
