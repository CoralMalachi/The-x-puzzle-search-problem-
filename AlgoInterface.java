/*
coral malachi
314882853
 */

//collection of abstract methods used in our Algorithms.
public interface AlgoInterface {

    /**
     * @return int - the number of nodes were removed from the open list
     */
    public int numberOutOfOpenList();

    /**
     * @return String - return a string which represents the best path to reach the goal state
     */
    public String findBestPath();

    //bfs - 0, A* - path cost, IDS - depth
    /**
     * @return int - the cost of path
     */
    public int thirdValueToPrint();

    /**
     * running the search algorithm
     */
    public void StartAlgorithm(BoardGame firstBoard);

}
