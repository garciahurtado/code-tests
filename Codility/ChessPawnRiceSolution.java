/**
 * Originally taken as a timed coding test on Codility.com. This was one of two
 * problems that had to be solved in the space of 80 minutes. I completed the test
 * successfully, with correct answers for both exercises and plenty of time to spare
 *
 * Problem Definition:
 * There is a chess board of arbitrary number of rows and columns. On each square of the
 * board, a number of grains of rice have been placed. The arrangement of the squares, 
 * including how many grains of rice are in each, is provided via the input array.
 *
 * A pawn, starts at the top left corner of the board. It can move one square at a time,
 * but only left or down. 
 *
 * Write a program which returns the optimal path that the pawn must follow in order to collect
 * the maximum amount of grains of rice.
 */
class ChessPawnRiceSolution {
    public int solution(int[][] board) {
        int[] pawnLoc = {0,0};
        int totalGrains = 0;
        int totalRow = 0;
        int totalCol = 0;
        int boardWidth = board[0].length;
        int boardHeight = board.length;
        
        while((pawnLoc[1] < boardWidth) && ((pawnLoc[0]) < boardHeight)){ 
            totalRow = 0;
            totalCol = 0;
            
            // add grains in current square
            totalGrains += board[pawnLoc[0]][pawnLoc[1]];
            
            // count the total grains in pawns untravelled column
            for(int i = pawnLoc[0]; i < board.length; i++){
                totalCol += board[i][pawnLoc[1]];
            }
            
            // count the total grains in pawns untravelled row
            for(int i = pawnLoc[1]; i < board[0].length; i++){
                totalRow += board[pawnLoc[0]][i];
            }
            
            if(totalRow > totalCol){ // move horizontally
                pawnLoc[1] = pawnLoc[1] + 1;
            } else {                // move vertically
                pawnLoc[0] = pawnLoc[0] + 1;
            }
        }
        
        return totalGrains;
    }
}
