import java.security.Key;
import java.util.*;
public class BreadthFirst
{
    
    public static int optimum = 0;
    public static int iterations = 0;
    public static int maxPositionsStored = 0;
    public static int [] masterHeuristic = new int [4];
    public static int gN = 0;
    public static HashMap<Integer, String> listOfStates = new HashMap<Integer, String>();
    public static HashMap<Integer, String> tempList = new HashMap<Integer, String>();

    public BreadthFirst()
    {
        
    }


    public static int [] convertState(String state)
    {
        int [] s = new int [9];
        for(int a = 0; a < 9; a++)
        {
            char temp = state.charAt(a);
            s[a] = Integer.parseInt(String.valueOf(temp));
        }
        return s;
    } //converts from a state string to and integer state array

    public static String revertState(int [][] state)
    {
        String stringState = "";
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                char temp = Character.forDigit(state[a][b], 10);
                stringState += temp;
            }
        }
        return stringState;
    } //reverts from a state string to and integer state array of [] (1d)

    public static void printPuzzle(int [][] grid)
    {
        System.out.println("");
        for(int a = 0; a < 3; a++)
        {
            System.out.print("|");
            for(int b = 0; b < 3; b++)
            {
                if(b == 2)
                {
                    System.out.println(grid[a][b] + "|");
                }
                else
                {
                    System.out.print(grid[a][b] + " ");
                }
            }
        }
        System.out.println("");
    } //print the puzzle on the terminal

    public static int[][] convertTo2D(int [] state)
    {
        int c = 0;
        int [][] grid = new int [3][3];
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                grid[a][b] = state[c];
                c++;
            }
        }
        return grid;
    } //convert a 1d array to a 2d array



    public static int[] calculateDistanceOutOfPlace(int[][][] initial, int[][] goal, int number)
    {
        int xInitial = 0;
        int yInitial = 0;
        int xGoal = 0;
        int yGoal = 0;
        int totalSum = 0;
        int [] sums = new int [number];

        for(int a = 0; a < number; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                for(int c = 0; c < 3; c++)
                {
                    for(int x = 0; x < 3; x++)
                    {
                        for(int y = 0; y < 3; y++)
                        {
                            if(initial[b][c][a] == goal[x][y])
                            {
                                if(initial[b][c][a] == 0)
                                {

                                }
                                else
                                {
                                    xInitial = b;
                                    yInitial = c;
                                    xGoal = x;
                                    yGoal = y;
                                    //int d = 0;
                                    int part1 = 0;
                                    int part2 = 0;

                                    //System.out.println("Start " + xInitial + "," + yInitial + " -> " + xGoal + "," + yGoal + " End");
                                    part1 = xInitial - xGoal;
                                    part2 = yInitial - yGoal;
                                    if(part1 <= 0)
                                    {
                                        part1 *= -1;
                                    }
                                    if(part2 <= 0)
                                    {
                                        part2 *= -1;
                                    }
                                    int temp = part1 + part2;
                                    totalSum += temp;
                                }
                                
                            }
                        }
                    }
                }
            }
            sums[a] = totalSum;
            totalSum = 0;
        }
        return sums;
    }


 //function that generates the next set of 8 puzzle steps using a string of the current state
    public static void generateNextFourSteps(String state)
    {
        String newState = state;



        char temp = '0';
        //move up
        state.replace("1","8");
        newState.replace('2',temp);
        System.out.println("Current: " + state);
        System.out.println("Up: " + newState);
    }


    
    

    public static int[] findNullCoords(int[][] initial)
    {
        int [] zeroCoords = new int [2];
        zeroCoords[0] = 1;
        zeroCoords[1] = 2;

        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                if(initial[a][b] == 0)
                {
                    zeroCoords[0] = a;
                    zeroCoords[1] = b;
                }
            }
        }

        return zeroCoords;
    }

    public static boolean checkIfGoal(int[][] initial, int[][] goal)
    {
        boolean tfGoal = true;
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                if(initial[a][b] != goal[a][b])
                {
                    tfGoal = false;
                    break;
                }
            }
        }
        return tfGoal;
    }

   
    //function that takes in a 3d array and an index and return that z axis array
    public static int[][] getZAxis(int[][][] initial, int index)
    {
        int[][] zAxis = new int[3][3];
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                zAxis[a][b] = initial[a][b][index];
            }
        }
        return zAxis;
    }

    

    public static boolean breadthFirstAlgorithm(puzzleNode initial, int[][] goal) //hill climb algorithm function
    {
        listOfStates.clear();
        tempList.clear();
        optimum = 0;

        int [] zeroCoords = new int [2];
        zeroCoords = findNullCoords(convertTo2D(convertState(initial.getString())));
        int x = zeroCoords[0];
        int y = zeroCoords[1];
        boolean state = false;
        int [][] stepGrid = new int [3][3];
        int [][] tempGrid = new int [3][3];
        stepGrid = convertTo2D(convertState(initial.getString()));
        listOfStates.put(0,initial.getString());
        int counterForMainHash = 0;
        int simpleCount = 0;
        String compareString = "";
        String goalString = revertState(goal);

        while(state != true)
        {
            optimum++;
            counterForMainHash = 0;
            
            for(Map.Entry m : listOfStates.entrySet())
            {

                stepGrid = convertTo2D(convertState(listOfStates.get(counterForMainHash)));
                counterForMainHash++;
                zeroCoords = findNullCoords(stepGrid);
                x = zeroCoords[0];
                y = zeroCoords[1];
               // System.out.println(m.getKey() + " " + m.getValue());
                if(x == 1 && y == 1)
                {
                    int [][][] next4steps = new int [3][3][4];
                    next4steps = generateNext4Steps(stepGrid);
                   // System.out.println("Next 4 Steps: ");
                    for(int a = 0; a < 4; a++)
                    {
                        iterations++;
                        tempGrid = null;
                        tempGrid = getZAxis(next4steps,a);
                        compareString = revertState(tempGrid);
                        if(tempList.containsValue(compareString))
                        {

                        }
                        else
                        {
                            maxPositionsStored++;
                            tempList.put(simpleCount, compareString);
                            simpleCount++;
                        }
                    } 
                }
                else if((x == 0 && y == 1 || x == 1 && y == 2 || x == 2 && y == 1 || x == 1 && y == 0))
                {
                    int [][][] next3steps = new int [3][3][3];
                    next3steps = generateNext3Steps(stepGrid);
                    //System.out.println("Next 3 Steps: ");
                    for(int a = 0; a < 3; a++)
                    {
                        iterations++;
                        tempGrid = null;
                        tempGrid = getZAxis(next3steps,a);
                        compareString = revertState(tempGrid);
                        if(tempList.containsValue(compareString))
                        {

                        }
                        else
                        {
                            maxPositionsStored++;
                            tempList.put(simpleCount, compareString);
                            simpleCount++;
                        }
                    }
                }
                else
                {
                    int [][][] next2steps = new int [3][3][2];
                    next2steps = generateNext2Steps(stepGrid);
                    //System.out.println("Next 2 Steps: ");
                    for(int a = 0; a < 2; a++)
                    {
                        iterations++;
                        tempGrid = null;
                        tempGrid = getZAxis(next2steps,a);
                        compareString = revertState(tempGrid);
                        if(tempList.containsValue(compareString))
                        {

                        }
                        else
                        {
                            maxPositionsStored++;
                            tempList.put(simpleCount, compareString);
                            simpleCount++;
                        }
                    }
                }

            }

            //System.out.println("Level: " + optimum);

            
            //String finalStateValue=null;

            if(tempList.containsValue(goalString))
            {
                //finalStateValue = tempList.get(goalString);
                //Key key = null;
                //key = listOfStates.(finalStateValue);
                state = true;
            }
            else
            {
                state = false;
            }

            simpleCount = 0;
            listOfStates.clear();
            listOfStates.putAll(tempList);
            tempList.clear();
        }
       return state;
    }

    public static int [][] copyGrid(int[][][] initial, int index)
    {
        int [][] returnGrid = new int [3][3];
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                returnGrid[a][b] = initial[a][b][index];
            }
        }
        return returnGrid;
    }

    public static int [][][] generateNext4Steps(int[][] initial)
    {
        int x = 1;
        int y = 1;
        int [][][] returnArray = new int[3][3][4];
        
        for(int c = 0; c < 4; c++)
        {
            for(int a = 0; a < 3; a++)
            {
                for(int b = 0; b < 3; b++)
                {
                    returnArray[a][b][c] = initial[a][b];
                }
            }
        }

        for(int d = 0; d < 4; d++)
        {
           if(d == 0)
           {
                returnArray[x][y][d] = returnArray[x-1][y][d];
                returnArray[x-1][y][d] = 0;
           }
           else if(d == 1)
           {
                returnArray[x][y][d] = returnArray[x][y-1][d];
                returnArray[x][y-1][d] = 0;
           }
           else if(d == 2)
           {
                returnArray[x][y][d] = returnArray[x+1][y][d];
                returnArray[x+1][y][d] = 0;
           }
           else if(d == 3)
           {
                returnArray[x][y][d] = returnArray[x][y+1][d];
                returnArray[x][y+1][d] = 0;
           }
        }

        return returnArray;
    }

    public static void printNext4Steps(int [][][] initial)
    {
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 4; b++)
            {
                for(int c = 0; c < 3; c++)
                {
                    System.out.print(initial[a][c][b] + " ");
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static int [][][] generateNext3Steps(int[][] initial)
    {
        //int count = 3;
        int x = findNullCoords(initial)[0];
        int y = findNullCoords(initial)[1];
        //int [][] temp = initial;
        int [][][] returnArray = new int[3][3][3];
        
        for(int c = 0; c < 3; c++)
        {
            for(int a = 0; a < 3; a++)
            {
                for(int b = 0; b < 3; b++)
                {
                    returnArray[a][b][c] = initial[a][b];
                }
            }
        }



        if(x == 0 && y == 1)
        {
            for(int d = 0; d < 3; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x][y+1][d];
                    returnArray[x][y+1][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x+1][y][d];
                    returnArray[x+1][y][d] = 0;
                }
                else if(d == 2)
                {
                    returnArray[x][y][d] = returnArray[x][y-1][d];
                    returnArray[x][y-1][d] = 0;
                }
            }
        }
        else if(x == 1 && y == 2)
        {
            for(int d = 0; d < 3; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x+1][y][d];
                    returnArray[x+1][y][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x-1][y][d];
                    returnArray[x-1][y][d] = 0;
                }
                else if(d == 2)
                {
                    returnArray[x][y][d] = returnArray[x][y-1][d];
                    returnArray[x][y-1][d] = 0;
                }
            }
        }
        else if(x == 2 && y == 1)
        {
            for(int d = 0; d < 3; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x-1][y][d];
                    returnArray[x-1][y][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x][y+1][d];
                    returnArray[x][y+1][d] = 0;
                }
                else if(d == 2)
                {
                    returnArray[x][y][d] = returnArray[x][y-1][d];
                    returnArray[x][y-1][d] = 0;
                }
            }
        }
        else if(x == 1 && y == 0)
        {
            for(int d = 0; d < 3; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x-1][y][d];
                    returnArray[x-1][y][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x][y+1][d];
                    returnArray[x][y+1][d] = 0;
                }
                else if(d == 2)
                {
                    returnArray[x][y][d] = returnArray[x+1][y][d];
                    returnArray[x+1][y][d] = 0;
                }
            }
        }
        return returnArray;
    }

    public static void printNext3Steps(int [][][] initial)
    {
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                for(int c = 0; c < 3; c++)
                {
                    System.out.print(initial[a][c][b] + " ");
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static int [][][] generateNext2Steps(int[][] initial)
    {
        //int count = 2;
        int x = findNullCoords(initial)[0];
        int y = findNullCoords(initial)[1];
       // int [][] temp = initial;
        int [][][] returnArray = new int[3][3][2];
        
        for(int c = 0; c < 2; c++)
        {
            for(int a = 0; a < 3; a++)
            {
                for(int b = 0; b < 3; b++)
                {
                    returnArray[a][b][c] = initial[a][b];
                }
            }
        }



        if(x == 0 && y == 0)
        {
            for(int d = 0; d < 2; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x][y+1][d];
                    returnArray[x][y+1][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x+1][y][d];
                    returnArray[x+1][y][d] = 0;
                }
            }
        }
        else if(x == 0 && y == 2)
        {
            for(int d = 0; d < 2; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x+1][y][d];
                    returnArray[x+1][y][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x][y-1][d];
                    returnArray[x][y-1][d] = 0;
                }
            }
        }
        else if(x == 2 && y == 2)
        {
            for(int d = 0; d < 2; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x-1][y][d];
                    returnArray[x-1][y][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x][y-1][d];
                    returnArray[x][y-1][d] = 0;
                }
            }
        }
        else if(x == 2 && y == 0)
        {
            for(int d = 0; d < 2; d++)
            {
                if(d == 0)
                {
                    returnArray[x][y][d] = returnArray[x-1][y][d];
                    returnArray[x-1][y][d] = 0;
                }
                else if(d == 1)
                {
                    returnArray[x][y][d] = returnArray[x][y+1][d];
                    returnArray[x][y+1][d] = 0;
                }
            }
        }
        return returnArray;
    }

    public static void printNext2Steps(int [][][] initial)
    {
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 2; b++)
            {
                for(int c = 0; c < 3; c++)
                {
                    System.out.print(initial[a][c][b] + " ");
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }

}
