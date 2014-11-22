// you can also use imports, for example:
// import java.util.*;

// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

class BalancedIndex {
	public static void main(String[] args){
		int[] input = {-1,-2,-4,-5,-1,-60,-12,-1};
		//int[] input = {-1, 3, -4, 5, 1, -6, 2, 1};
		
		BalancedIndex finder = new BalancedIndex();
		System.out.println("Solution is " + finder.solution(input));
	}
	
    public int solution(int[] input) {   	
    	int middle = (int) Math.floor((input.length - 1) / 2);
    	
    	int solution = 0;
    	
    	try {
    		solution = iterateSolutions(input, middle);
    	} catch(Exception ex){
    		System.out.println("Unable to find balanced index: " + ex.toString());
    	}
    	
    	return solution;
    }
    
    public int iterateSolutions(int[] input, int middle) throws Exception{
    	int lesser = this.countSlice(input, 0, middle);
    	int higher = this.countHigher(input, middle, input.length);
    	int solution = 0;
    	
    	if(lesser == higher){
    		System.out.println("Found balanced index at " + middle);
    		solution = middle;
    	} else if(lesser > higher) {
    		// decrease index
    		int newIndex = (int) Math.floor(middle / 2);
    		if(newIndex != middle){
    			solution = iterateSolutions(input, newIndex);
    		} else {
    			throw new Exception("Unable to find balanced index");
    		}
    		
    	} else if(higher < lesser){
    		// increase index
    		int newIndex = (int) Math.floor((input.length + middle) / 2);
    		
    		if((newIndex != middle) && (newIndex < input.length)){
    			solution = iterateSolutions(input, newIndex);
    		} else {
    			throw new Exception("Unable to find balanced index");
    		}
    	}
    	
    	return solution;
    }
    
    public int countSlice(int[] input, int start, int end){
		System.out.println("Counting lesser at " + start);
		int count = 0;
		
		for(int i = 0; i < start; i++){
			count += input[i];
		}		
		System.out.println("Totalled " + count);
		
		return count;
    }
    
    public int countHigher(int[] input, int index, int end){
    	System.out.println("Counting higher at " + index);
    	
		int count = 0;
				
		for(int i = index + 1; i < input.length; i++){
			count += input[i];
		}
		
		System.out.println("Totalled " + count);
		
		return count;
    }
		
}
