/*
coral malachi
314882853
 */
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class java_ex1 {

    /**
     * main function - read input.txt file and following the orders - run
     * the requested algorithm on the given input

     */
    public static void main(String[] args) {
        // write your code here
        //System.out.println("hi");
        BufferedReader buff;
        int pazzel_size = 0;
        int pazzel[][] = null;
        //create a new file - find the input file in directory - assumes the file exists
        File file = new File("input.txt");
        Enum.Algorithm algo = null;

        //set algo user choice
        //Enum.Algorithm algo = null;//not define yet
        try (FileReader input_file = new FileReader(file)) {
            buff = new BufferedReader(input_file);
            int count_lines = 0;
            String read_line;
            //as long as we didnt read an empty line
            while ((read_line = buff.readLine()) != null) {
                if (count_lines == 0) {
                    //switch algo according to user input
                    System.out.println(Integer.valueOf(read_line.trim()));
                    //set the right algorithm according to the input
                    switch (Integer.valueOf(read_line.trim())){
                        case 1://IDS
                            algo = Enum.Algorithm.IDS;
                            break;
                        case 2://BFS
                            algo = Enum.Algorithm.BFS;
                            break;
                        case 3:
                            algo = Enum.Algorithm.ASTAR;
                            break;
                    }
                } else if (count_lines == 1) {
                    pazzel_size = Integer.valueOf(read_line.trim());
                    System.out.println(Integer.valueOf(read_line.trim()));
                } else if ((count_lines == 2)) {
                    pazzel = new int[pazzel_size][pazzel_size];

                    //System.out.println((read_line.trim()));
                    String s =read_line.trim();
                    initFirstPazelBoard(s,pazzel_size,pazzel);
                    System.out.println(Arrays.deepToString(pazzel));

                }
                count_lines++;
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }

        //create first board
        BoardGame first_board = new BoardGame(pazzel_size,pazzel,null,null);
        first_board.printBoard();
        AlgoInterface algorithm=null;
        switch (algo){
            case BFS:
                algorithm = new BFSAlgo();
                break;
            case IDS:
                algorithm = new  IterativeDFS();
                break;
            case ASTAR:
                algorithm = new Astar();
                break;
        }
        algorithm.StartAlgorithm(first_board);
        // System.out.print(algorithm.findBestPath());
        //System.out.print(algorithm.numberOutOfOpenList());
        //System.out.print(algorithm.thirdValueToPrint());
        try{
            create_output_file(algorithm.findBestPath(),algorithm.numberOutOfOpenList(),algorithm.thirdValueToPrint());

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    //static methods in Java can be called without creating an object of the class.
    // a program execution begins from main and no object has been created yet
    /**
    @param arr - the number of board
    @param size - the size of board
    @param pazelNumber  - the string of board number from input.txt file
     * @return number positive - if f(a) is bigger than f(b)
     * else return negative number
     */
    public static void initFirstPazelBoard(String pazelNumber, int size,int arr[][]) {
        //int[][] pazzel = new int[size][size];
        //Array.ConvertAll<string, int>(value.Split(','), Convert.ToInt32);
        List<String> nums = Arrays.asList(pazelNumber.split("-"));
        for (int i=0;i<size;++i){
            for (int j=0;j<size;++j){
                int pos = j+i*size;
                arr[i][j] = Integer.parseInt(nums.get(pos));
            }
        }

    }

    /**
     * the function create the output file according to required in assignment
     * @return number positive - if f(a) is bigger than f(b)
     * else return negative number
     */
    public static void create_output_file(String path,int number_out_of_open_list,int third) throws IOException
    {
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.print(path + " "+String.valueOf(number_out_of_open_list)+" "+String.valueOf(third));
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());}
    }
}
