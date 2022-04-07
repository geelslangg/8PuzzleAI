public class aAlgorithm
{
    public static int MLL = 10000;
    public static int HEURISTIC = 2;
    public static int optimum = 0;
    public static String [] masterList = new String [MLL];
    public static puzzleNode [] masterNodeList = new puzzleNode [MLL];
    public static int [] masterHeuristic = new int [4];
    public static int gN = 0;
    public static int iterations = 0;

    public static int [] convertState(String state)
    {
        int [] s = new int [9];
        for(int a = 0; a < 9; a++)
        {
            char temp = state.charAt(a);
            s[a] = Integer.parseInt(String.valueOf(temp));
            //System.out.print(startStates[a] + " ");
        }
        return s;
    } //converts from a state string to and integer state array

    public static String revertState(int [][] state)
    {
        int [][] s = new int [3][3];
        String stringState = "";
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                char temp = Character.forDigit(state[a][b], 10);
                //System.out.println(temp);
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

    public static int[] calculateTilesOutOfPlace(int[][][] initial, int[][] goal, int number)
    {
        int outOfPlace = 0;
        int [] line = new int [number];
        for(int a = 0; a < number; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                for(int c = 0; c < 3; c++)
                {
                    if(initial[b][c][a] != goal[b][c])
                    {
                        if(initial[b][c][a] == 0)
                        {

                        }
                        else
                        {
                            outOfPlace++;
                        }
                    }
                }
            }
            line[a] = outOfPlace;
            outOfPlace = 0;
        }
        return line;
    } //calculate the amount of tiles out of place

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
                                    int d = 0;
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

    public static void readTextFile(String name)
    {
        
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

    public static void printMasterList()
    {
        for(int a = 0; a < MLL; a++)
        {
            if(masterList[a] != null)
            System.out.println(masterList[a] + " ");
        }
    }

    public static void printMasterNodeList()
    {
        for(int a = 0; a < MLL; a++)
        {
            //if(masterNodeList[a] != null)
            //System.out.println(masterNodeList[a].getString() + " " + a);
        }
    }
    

    public static boolean aAlgorithmSearch(puzzleNode initial, int[][] goal) //hill climb algorithm function
    {
        int [][] stepGrid = new int [3][3];
        boolean state = false;
        stepGrid = convertTo2D(convertState(initial.getString()));
        int XZero = findNullCoords(stepGrid)[0];
        int YZero = findNullCoords(stepGrid)[1];
        gN= 0;
        optimum = 0;
        iterations = 0;

        while(state != true)
        {
            if(optimum == 90000)
            {
                state = false;
                break;
            }
            optimum++;
            XZero = findNullCoords(stepGrid)[0];
            YZero = findNullCoords(stepGrid)[1];
            gN++;

        if(XZero == 1 && YZero == 1)
        {
            int [][][] next4steps = new int [3][3][4];
            next4steps = generateNext4Steps(stepGrid); 
            int [] distances = new int [4];
            distances = calculateDistanceOutOfPlace(next4steps,goal,4);

            for(int a = 0; a < 4; a++)
            {
                masterHeuristic[a] = distances[a] + gN;// + tiles[a] + gN;
            }


            int high = 10000000;
            int index = 0;

            String compareString = "";
            for(int b = 0; b < 4; b++)
            {
                stepGrid = copyGrid(next4steps, b);
                compareString = revertState(stepGrid);
                iterations++;

                for(int a = 0; a < MLL; a++)
                {
                    if(masterNodeList[a] != null)
                    {
                        if(masterNodeList[a].getString().compareTo(compareString) == 0)
                        {
                            masterNodeList[a].updateHeuristic(HEURISTIC);
                            masterHeuristic[b] += masterNodeList[a].getHeuristic();
                            //System.out.println("Already In List");
                            break;
                        }
                    }
                    else
                    {
                        //masterList[a] = compareString;
                        break;
                    }
                }
            }

            for(int a = 0; a < 4; a++)
            {
               // System.out.println(a + " h(n)=" + masterHeuristic[a] + " ");
                if(masterHeuristic[a] < high)
                {
                    high = masterHeuristic[a];
                    index = a;
                }
                    //System.out.println("Distance: " + high + " Index: " + index);
            }
            //System.out.println("\nSmallest Heuristic Index: " + index);
            stepGrid = copyGrid(next4steps, index);
            //optimum++;
            compareString = revertState(stepGrid);
            puzzleNode newNode = new puzzleNode(compareString);
            //newNode.updateHeuristic(HEURISTIC);


            for(int a = 0; a < MLL; a++)
            {
                if(masterNodeList[a] == null)
                {   
                    masterNodeList[a] = newNode;
                    break;
                }
                else if(masterNodeList[a].getString().compareTo(compareString) == 0)
                {
                    break;
                }
            }

            //printMasterNodeList();

            if(checkIfGoal(stepGrid, goal) == true)
            {
                //System.out.println("Goal State");
                state = true;
                return true;
            }
            else
            {
                //System.out.println("Not Goal State");
            }
            //return state;

        }
        else if(XZero == 0 && YZero == 1 || XZero == 1 && YZero == 2 || XZero == 2 && YZero == 1 || XZero == 1 && YZero == 0)
        {
            //System.out.println("Zero is edge piece");
            int [][][] next3steps = new int [3][3][3];
            //System.out.println("Next 3 Steps:");
            next3steps = generateNext3Steps(stepGrid);
            //printNext3Steps(next3steps);

            //int [] tiles = new int [3];
            int [] distances = new int [3];
            //tiles = calculateTilesOutOfPlace(next3steps,goal,3);
            distances = calculateDistanceOutOfPlace(next3steps,goal,3);

            for(int a = 0; a < 3; a++)
            {
                masterHeuristic[a] = distances[a] + gN;// + tiles[a] + gN;
            }
            
             int high = 10000000;
            int index = 0;

            String compareString = "";
            for(int b = 0; b < 3; b++)
            {
                stepGrid = copyGrid(next3steps, b);
                compareString = revertState(stepGrid);
                iterations++;

                for(int a = 0; a < MLL; a++)
                {
                    if(masterNodeList[a] != null)
                    {
                        if(masterNodeList[a].getString().compareTo(compareString) == 0)
                        {
                            //masterNodeList[a].updateHeuristic(HEURISTIC);
                            masterHeuristic[b] += masterNodeList[a].getHeuristic();
                            //System.out.println("Already In List");
                            break;
                        }
                    }
                    else
                    {
                        //masterList[a] = compareString;
                        break;
                    }
                }
            }

            for(int a = 0; a < 3; a++)
            {
                //System.out.println(a + " h(n)=" + masterHeuristic[a] + " ");
                if(masterHeuristic[a] < high)
                {
                    high = masterHeuristic[a];
                    index = a;
                }
                    //System.out.println("Distance: " + high + " Index: " + index);
            }
            //System.out.println("\nSmallest Heuristic Index: " + index);
            //optimum++;
            stepGrid = copyGrid(next3steps, index);
            compareString = revertState(stepGrid);
            puzzleNode newNode = new puzzleNode(compareString);
            //newNode.updateHeuristic(HEURISTIC);


            for(int a = 0; a < MLL; a++)
            {
                if(masterNodeList[a] == null)
                {   
                    masterNodeList[a] = newNode;
                    break;
                }
                else if(masterNodeList[a].getString().compareTo(compareString) == 0)
                {
                    break;
                }
            }


            // for(int a = 0; a < MLL; a++)
            //     {
            //         if(masterList[a] != null)
            //         {
            //             if(compareString == masterList[a])
            //             {
            //                 break;
            //             }
            //         }
            //         else
            //         {
            //             masterList[a] = compareString;
            //             break;
            //         }
            //     }
            

            //System.out.println("\nNext Step: ");
            //printPuzzle(stepGrid);

            printMasterNodeList();

            if(checkIfGoal(stepGrid, goal) == true)
            {
               // System.out.println("Goal State");
                state = true;
                return true;
            }
            else
            {
                //System.out.println("Not Goal State");
            }
            //return state;
        }
        else
        {
            //System.out.println("Zero is in corner");
            int [][][] next2steps = new int [3][3][2];
            //System.out.println("Next 2 Steps:");
            next2steps = generateNext2Steps(stepGrid);
            //printNext2Steps(next2steps);

            //int [] tiles = new int [2];
            int [] distances = new int [2];
           // tiles = calculateTilesOutOfPlace(next2steps,goal,2);
            distances = calculateDistanceOutOfPlace(next2steps,goal,2);

            for(int a = 0; a < 2; a++)
            {
                masterHeuristic[a] = distances[a] + gN;// + tiles[a] + gN;
            }
            
             int high = 10000000;
            int index = 0;

            String compareString = "";
            for(int b = 0; b < 2; b++)
            {
                stepGrid = copyGrid(next2steps, b);
                compareString = revertState(stepGrid);
                iterations++;

                for(int a = 0; a < MLL; a++)
                {
                    if(masterNodeList[a] != null)
                    {
                        if(masterNodeList[a].getString().compareTo(compareString) == 0)
                        {
                            masterNodeList[a].updateHeuristic(HEURISTIC);
                            masterHeuristic[b] += masterNodeList[a].getHeuristic();
                            //System.out.println("Already In List");
                            break;
                        }
                    }
                    else
                    {
                        //masterList[a] = compareString;
                        break;
                    }
                }
            }

            for(int a = 0; a < 2; a++)
            {
                //System.out.println(a + " h(n)=" + masterHeuristic[a] + " ");
                if(masterHeuristic[a] < high)
                {
                    high = masterHeuristic[a];
                    index = a;
                }
                    //System.out.println("Distance: " + high + " Index: " + index);
            }
            //System.out.println("\nSmallest Heuristic Index: " + index);
            //optimum++;
            stepGrid = copyGrid(next2steps, index);
            compareString = revertState(stepGrid);
            puzzleNode newNode = new puzzleNode(compareString);
            //newNode.updateHeuristic(HEURISTIC);

            for(int a = 0; a < MLL; a++)
            {
                if(masterNodeList[a] == null)
                {   
                    masterNodeList[a] = newNode;
                    break;
                }
                else if(masterNodeList[a].getString().compareTo(compareString) == 0)
                {  
                    break;
                }
            }


            // for(int a = 0; a < MLL; a++)
            //     {
            //         if(masterList[a] != null)
            //         {
            //             if(compareString == masterList[a])
            //             {
            //                 break;
            //             }
            //         }
            //         else
            //         {
            //             masterList[a] = compareString;
            //             break;
            //         }
            //     }
            

            //System.out.println("\nNext Step: ");
            //printPuzzle(stepGrid);

            

            if(checkIfGoal(stepGrid, goal) == true)
            {
                //System.out.println("Goal State");
                state = true;
                return true;
            }
            else
            {
                //System.out.println("Not Goal State");
            }
            //return state;
        }
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
        int count = 4;
        int x = 1;
        int y = 1;
        int [][] temp = initial;
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
        int count = 3;
        int x = findNullCoords(initial)[0];
        int y = findNullCoords(initial)[1];
        int [][] temp = initial;
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
        int count = 2;
        int x = findNullCoords(initial)[0];
        int y = findNullCoords(initial)[1];
        int [][] temp = initial;
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
