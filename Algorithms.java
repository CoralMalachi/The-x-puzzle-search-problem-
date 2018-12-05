/*
coral malachi
314882853
 */

import java.util.LinkedList;
import java.util.List;

//Algorithms class -
// implements the AlgoInterface interface - it's an abstract class
//if a class doesn't preform all methods of interface it must declare itself as abstract
public abstract class Algorithms implements AlgoInterface {

    //protected can be seen by subclasses or package member
    protected BoardGame finalBoard;
    protected BoardGame cure_board;
    protected List<BoardGame> outOpenList;

    //we wont implement this method here - each algo would have different implementation

    /**
     * running the search algorithm
     */
    public abstract void StartAlgorithm(BoardGame firstBoard);

    /**
     * @return int - the cost of path
     */
    public abstract int thirdValueToPrint();


    public Algorithms(){this.outOpenList = new LinkedList<>();}

    /**
     * @return int - the number of nodes were removed from the open list
     */
    public int numberOutOfOpenList(){
        return this.outOpenList.size();
    }

    /**
     * @return String - return a string which represents the best path to reach the goal state
     */
    public String findBestPath()
    {
        String s_direction;
        StringBuilder path = new StringBuilder();
        Enum.Direction current_direction = null;
        BoardGame current_board = this.finalBoard;
        //as long as we can go back (no null) keep going!
        while (current_board.getM_prev_state() != null &&
                current_board!=null)
        {
            current_direction = current_board.get_current_direction();
            s_direction = current_direction.name().substring(0,1);
            path.insert(0,s_direction);
            //go up to previous state
            current_board = current_board.getM_prev_state();
        }
        return path.toString();

    }
}
