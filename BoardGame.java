/*
coral malachi
314882853
 */

import java.util.LinkedList;
import java.util.List;

public class BoardGame
{
    //declare class members
    private int insertion_time;
    private BoardGame m_prev_state;
    private Enum.Direction m_direction;
    private int[] m_missing_prev_state = {-1,-1};
    private int[] m_current_missing = {-1,-1};
    private int m_depth;
    private int m_size;
    private int[][] m_numbers;


    /**
     *   BoardGame Constructor
     * @param   size - sixe of board
     * @param  numbers - 2D array of board numbers
     * @param  prev_state: the previous state before moving
     * @param direction:where to move
     * the function create new BoardGame object
     */
    public BoardGame(int size,int[][] numbers,BoardGame prev_state,Enum.Direction direction){
        this.m_size = size;
        this.m_direction=direction;
        this.m_prev_state =prev_state;
        this.m_numbers = new int[size][size];
        //if this is son
        if (prev_state != null){
            this.m_depth = 1+prev_state.m_depth;
            InitBoard();
            //if this is the root
        }else{
            this.m_depth=0;
            this.m_numbers=numbers;
        }
        this.setCurrentMissingNumber();
    }

    public int getInsertion_time() {return this.insertion_time;}

    public void setInsertion_time(int n){this.insertion_time = n;}


    /**
     *   printBoard function

     * the function print BoardGame object
     */
    public void printBoard(){
        System.out.println("----------------");
        for (int i=0;i<this.m_size;++i)
        {
            System.out.println();//print enter
            for (int k=0;k<this.m_size;++k){
                System.out.print(this.m_numbers[i][k]);
                System.out.print(" ");
            }

        }
        System.out.println();
        System.out.println("---------------");
    }

    /**
     * @param
     * the function create new BoardGame object
     */

    /**
     * the function return the prev board object
     */
    public BoardGame getM_prev_state(){
        return this.m_prev_state;
    }


    /**
     * the function calculate the huristic value - Manahtan Distance
     */
    public int calculateHuristicValue()
    {
        int manhattan_dist_sum = 0;
        for (int i=0;i<m_size;++i)
        {
            for (int j=0;j<m_size;++j)
            {
                int value_at_pos = this.m_numbers[i][j];
                if (value_at_pos != 0)
                {
                    int col_target = (value_at_pos - 1)%m_size;
                    int row_target = (value_at_pos -1)/m_size;
                    int delta_col = j - col_target;
                    int delta_row = i - row_target;
                    manhattan_dist_sum += Math.abs(delta_row)+Math.abs(delta_col);//update manhattan dist
                }
            }
        }
        return manhattan_dist_sum;
    }

    //the function set the indexes of the zero number in the board
    private void setCurrentMissingNumber(){
        for(int raw=0;raw<this.m_size;++raw){
            for (int col=0;col<this.m_size;++col){
                if(this.m_numbers[raw][col] == 0){
                    this.m_current_missing[0]=raw;
                    this.m_current_missing[1] = col;
                    break;
                }
            }
        }
    }

    //the function return the numbers of the board
    private int[][] getNumbers(){
        return m_numbers;
    }

    //the function set the indexes of the zero in previous board
    private void set_missing_prev_state(int i,int j){
        this.m_missing_prev_state[0]=i;
        this.m_missing_prev_state[1]=j;
    }

    //the function return the depth of board
    public int getM_depth(){
        return this.m_depth;
    }

    //this function initial the board - update member values
    private void InitBoard(){
        if (m_prev_state == null){
            return;
        }

        int[][] originalArr = this.m_prev_state.getNumbers();
        for(int raw=0;raw<this.m_size;++raw){
            for (int col=0;col<this.m_size;++col){
                if(originalArr[raw][col] == 0){
                    this.set_missing_prev_state(raw,col);
                }
                this.m_numbers[raw][col] = originalArr[raw][col];
            }
        }
        this.move();

    }

    //this function implements the move action in the board
    private void move(){
        switch (this.m_direction){
            case Left:
                this.turn_left();
                break;
            case Right:
                this.turn_right();
                break;
            case Up:
                this.turn_up();
                break;
            case Down:
                this.turn_down();
                break;

        }
    }

    //return the direction
    public Enum.Direction get_current_direction(){
        return this.m_direction;
    }

    //function checks if we got to the Goal State
    public boolean isWon(){
        //check if the zero is at last place
        if (!(this.m_current_missing[0]==m_size-1 && m_current_missing[1]==m_size-1)){
            return false;
        }
        int correct_number=1;
        for(int i=0;i<m_size;++i){
            for (int k=0;k<m_size;++k)
            {
                if(this.m_numbers[i][k] != correct_number
                        && !(k == this.m_size -1 && i==this.m_size - 1))//check that the numbers in increasing order and that the last number is zero
                {
                    return false;
                }
                correct_number++;//update value of correct number according to position
            }
        }
        //if get here- pass all conditions
        return true;
    }


    //implement the turning left action
    private void turn_left(){
        if(this.m_missing_prev_state[1]<this.m_size -1)
        {
            //save into temp var the number we want to move left twards the 0
            int temp_num = this.m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]+1];
            m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]] = temp_num;
            m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]+1] = 0;
        }
    }

    //implement the turning right action
    private void turn_right(){
        if(this.m_missing_prev_state[1]>0){
            //save into temp var the number we want to move left twards the 0
            int temp_num = this.m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]-1];
            m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]] = temp_num;
            m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]-1] = 0;
        }
    }

    //implement the turning down action
    private void turn_down(){
        if(this.m_missing_prev_state[0]>0)
        {
            //save into temp var the number we want to move left twards the 0
            int temp_num = this.m_numbers[m_missing_prev_state[0]-1][m_missing_prev_state[1]];
            m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]] = temp_num;
            m_numbers[m_missing_prev_state[0]-1][m_missing_prev_state[1]] = 0;
        }

    }

    //implement the turning up action
    private void turn_up()
    {
        if(this.m_missing_prev_state[0]<this.m_size -1){
            //save into temp var the number we want to move left twards the 0
            int temp_num = this.m_numbers[m_missing_prev_state[0]+1][m_missing_prev_state[1]];
            m_numbers[m_missing_prev_state[0]][m_missing_prev_state[1]] = temp_num;
            m_numbers[m_missing_prev_state[0]+1][m_missing_prev_state[1]] = 0;
        }
    }

    //return the size of board
    public int size(){
        return this.m_size;
    }

    //return a list of optioanal directions we can go to in current state
    public List<Enum.Direction> OptionalNextMoves(){
        List<Enum.Direction> directions = new LinkedList<>();
        //it's important the order of check because this is the order we choose when all the successors have
        //the same parent : UP,DOWN,RIGHT,LEFT - according to Ex1 requirements
        if (this.m_current_missing[0] < this.m_size-1 )//if zero is no in the last row we can move up
        {
            directions.add(Enum.Direction.Up);
        }
        if (this.m_current_missing[0] > 0 )//if zero is no in the first row we can move down
        {
            directions.add(Enum.Direction.Down);
        }


        if (this.m_current_missing[1] < this.m_size - 1 )//if zero is no in the last col we can move left
        {
            directions.add(Enum.Direction.Left);
        }
        if (this.m_current_missing[1] > 0 )//if zero is no in the first col we can move right
        {
            directions.add(Enum.Direction.Right);
        }
        return directions;
    }
}
