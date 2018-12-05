
/*
coral malachi
314882853
 */

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Astar Class.
 * extends from the abstract class algorithms and implements its functions
 */
public class Astar extends Algorithms {
    private PriorityQueue<BoardGame> m_open_list = new PriorityQueue<BoardGame>(new Comparator<BoardGame>() {
        @Override
        /**
         * runs the search algorithm
         * @return number positive - if f(a) is bigger than f(b)
         * else return negative number
         */
        public int compare(BoardGame a, BoardGame b) {
            int comp_a = a.calculateHuristicValue()+ a.getM_depth();
            int comp_b = b.calculateHuristicValue()+b.getM_depth();
            if (comp_a != comp_b)
            {
                return comp_a-comp_b;
            }else {
                //if two boards have the same f value we'll use the requested order in assignment
                if(a.getInsertion_time() <b.getInsertion_time()){
                    return -1;
                }else {
                    return 1;
                }
            }
        }
    });

    /**
     * running the A star algorithm
     */
    public void StartAlgorithm(BoardGame firstBoard)
    {
        int timeCount = 0;
        super.cure_board = firstBoard;
        cure_board.setInsertion_time(timeCount);
        timeCount++;
        m_open_list.add(cure_board);
        while (m_open_list.isEmpty()!=true)
        {
            //since we set the open list as priority queue it will remove the node with the lowest cost
            cure_board = m_open_list.remove();
            super.outOpenList.add(cure_board);
            if (cure_board.isWon() ){
                super.finalBoard = cure_board;

                break;
            }else {
                //get list of all optional move (valid) in the order of UP,DOWN,RIGHT,LEFT
                List<Enum.Direction> directions = cure_board.OptionalNextMoves();
                for(Enum.Direction direction: directions)
                {
                    BoardGame neighber = new BoardGame(super.cure_board.size(),null,super.cure_board,direction);
                    neighber.setInsertion_time(timeCount);
                    m_open_list.add(neighber);//add neighbor to the open list in required order
                    timeCount++;
                    //System.out.println("child:");
                    //neighber.printBoard();
                }
            }
        }
    }

    /**
     * @return int - the cost of path   -length of path (edges wights considered one for each
     */
    public int thirdValueToPrint(){
        return this.finalBoard.getM_depth();//the cost of path is the length of path (edges wights considered one for each

    }
}
