public class Hillclimb
{
    public static int MLL = 12000;
    public static int HEURISTIC = 2;
    public static int optimum = 0;
    public static String [] masterList = new String [MLL];
    public static puzzleNode [] masterNodeList = new puzzleNode [MLL];
    public static int [] masterHeuristic = new int [4];
    public static int gN = 0;

    public static puzzleNode [] open = new puzzleNode[MLL];
    public static puzzleNode [] closed = new puzzleNode[MLL];

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
        //int [][] s = new int [3][3];
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
        for(int a = 0; a < 3; a++)
        {
            for(int b = 0; b < 3; b++)
            {
                System.out.print(grid[a][b] + " ");
            }
            System.out.println();
        }
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

    public static void readTextFile(String name)
    {
        
    }

    public static void main(String[] args) //main function
    {
        System.out.println("Start Of Hill Climbing 8 Puzzle Program!");



        String [] starts = {"123804765","123804765","123804765",
                            "134805726","231708654","231804765",  //string list of start states
                            "123804765","123804765","876105234",
                            "867254301",};

        String [] ends = {"134862705","281043765","281463075",
                            "123804765","123804765","123804765",  //string list of goal states
                            "231804765","567408321","123804765",
                            "123456780",};


        for(int a = 0; a < MLL; a++)
        {      
            masterList[a] = null;
            masterNodeList[a] = null;
        }

        //int [][] puzzleGrid = new int [3][3];
        int [][] goalGrid = new int [3][3];

        //int [] startState1 = new int [9];
        int [] goalState1 = new int [9];

        int c = 9;

        //for(int a = 0; a < 5; a++)
        {
            String state1 = starts[c];
            String goal1 = ends[c];
            puzzleNode newNode = new puzzleNode(state1);

            //startState1 = convertState(state1);
            goalState1 = convertState(goal1);

            //puzzleGrid = convertTo2D(startState1);
            goalGrid = convertTo2D(goalState1);
            
            boolean endWhile = false;
            optimum = 0;
            //puzzleNode recurse = newNode;
            endWhile = hillClimbAlgorithm(newNode, goalGrid);
            System.out.println("Success: " + endWhile);
        }

    }

    public static void HCRecursive(puzzleNode recurse, int[][] goalGrid)
    {
        boolean endWhile = false;
        while(endWhile != true)
        {
            if(optimum == 1000)
            {
                break;
            }
            //recurse = hillClimbAlgorithm(recurse, goalGrid);
            optimum++;
            if(checkIfGoal(convertTo2D(convertState(recurse.getString())), goalGrid))
            {
                break;
            }
        }
        System.out.println("optimum: " + optimum);
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

    public static void addOpen(puzzleNode [] addTo, int number)
    {
       int counter = 0;
       for(int a = 0; a < MLL; a++)
       {
            if(open[a] != null)
            {
                counter++;
            }
       }

       if(counter == 0)
       {
            for(int a = 0; a < number; a++)
            {
                open[a] = addTo[a];
                //counter++;
            }
            return;
       }
       else
       {
            for(int a = number-1; a >= 0; a--)
            {
                System.out.println("path to node: " + addTo[a].getHeuristic());
                add(addTo[a]);
            }
       }

       //for(int a = 0; a < MLL; a++)
       {
            //if(open[a] != null)
            //System.out.println(a + " OPEN LIST: " + open[a].getString());
       }

    }
    
    public static void addClosed(puzzleNode addTo)
    {
        for(int a = 0; a < MLL; a++)
        {
            if(closed[a] == null)
            {
                closed[a] = addTo;
                break;
            }
        }
    }

    public static puzzleNode remove(puzzleNode removeFrom)
    {
        puzzleNode temp = open[0];
        for(int a = 0; a < MLL; a++)
        {
            if(a == MLL-1)
            {
                open[a] = null;
                break;
            }
            open[a] = open[a+1];
        }
        return temp;
    }

    public static void add(puzzleNode addTo)
    {
        //puzzleNode temp = open[0];
        for(int a = MLL-1; a >= 0; a--)
        {
            if(a == 0)
            {
                open[a] = addTo;
                break;
            }
            open[a] = open[a-1];
        }
        //return temp;
    }

    public static puzzleNode removeClose(String removeFrom)
    {
        puzzleNode temp = null;
        for(int a = 0; a < MLL; a++)
        {
            if(closed[a] != null)
            {
                if(removeFrom.compareTo(closed[a].getString()) == 0)
                {
                    temp = closed[a];
                    for(int b = a; b < MLL; b++)
                    {
                        if(b == MLL-1)
                        {
                            closed[b] = null;
                            break;
                        }
                        closed[b] = closed[b+1];
                    }
                }
            }
            else
            {
                break;
            }
        }
        return temp;
    }

    public static boolean inOpen(String contain)
    {
        for(int a = 0; a < MLL; a++)
        {
            if(open[a] != null)
            {
               if(open[a].getString().compareTo(contain) == 0)
                {
                    return true;
                } 
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public static boolean inClosed(String contain)
    {
        for(int a = 0; a < MLL; a++)
        {
            if(closed[a] != null)
            {
               if(closed[a].getString().compareTo(contain) == 0)
                {
                    return true;
                } 
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public static int getIndex(String contain)
    {
        for(int a = 0; a < MLL; a++)
        {
            if(open[a] != null)
            {
                if(open[a].getString() == contain)
                {
                    return a;
                }
            }
        }
        return -1;
    }

    public static puzzleNode [] reorderNodes(puzzleNode [] openList, int number)
    {
       // int count = 0;
        for(int a = 0; a < number; a++)
        {
            if(openList[a] == null)
            {
                //count = a;
                break;
            }
        }
        //System.out.println("OPEN COUNT: " + count);
        puzzleNode temp = null;
        for(int a = 0; a < number; a++)
        {
            for(int b = 0; b < number; b++)
            {
                if(openList[b] != null)
                {
                    temp = openList[b];
                    if(b == number-1)
                    {
                        break;
                    }
                    if(openList[b+1].getHeuristic() < temp.getHeuristic())
                    {
                        openList[b] = openList[b+1];
                        openList[b+1] = temp;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return openList;
    }


    public static boolean hillClimbAlgorithm(puzzleNode initial, int[][] goal) //hill climb algorithm function
    {
        for(int a = 0; a < MLL; a++)
        {
            open[a] = null;
        }

        if(open[0] == null)
        {
            open[0] = initial;
        }

        //int [][] currentGrid = new int [3][3];
        int [][] stepGrid = new int [3][3];
        stepGrid = convertTo2D(convertState(initial.getString()));
        //currentGrid = stepGrid;
        int XZero = findNullCoords(stepGrid)[0];
        int YZero = findNullCoords(stepGrid)[1];
        //gN++;
        boolean state = false;
        puzzleNode temp = null;
        String goalString = revertState(goal);

        while(open[0] != null && state != true)
        {
            if(gN == 78)
            {
                //break;
            }
            temp = remove(open[0]);
            temp.setPath(gN);
            stepGrid = convertTo2D(convertState(temp.getString()));
            XZero = findNullCoords(convertTo2D(convertState(temp.getString())))[0];
            YZero = findNullCoords(convertTo2D(convertState(temp.getString())))[1];
            if(temp.getString().compareTo(goalString) == 0)
            {
                state = true;
            }
            else
            {
                gN++;
                if(XZero == 1 && YZero == 1)
                {
                    int [][][] next4steps = new int [3][3][4];
                    next4steps = generateNext4Steps(stepGrid);
                    System.out.println("Next 4 Steps: ");
                    printNext4Steps(next4steps);
                    //int [] tiles = new int [4];
                    int [] distances = new int [4];
                    //tiles = calculateTilesOutOfPlace(next4steps,goal,4);
                    distances = calculateDistanceOutOfPlace(next4steps,goal,4);
                    puzzleNode [] childrenStates = new puzzleNode[4];

                    for(int a = 0; a < 4; a++)
                    {
                        //childrenStates[a] = new puzzleNode()// + gN;// + tiles[a] + gN;
                    }

                    String compareString = "";
                    for(int b = 0; b < 4; b++)
                    {
                        stepGrid = copyGrid(next4steps, b);
                        compareString = revertState(stepGrid);
                        childrenStates[b] = new puzzleNode(compareString);


                        if(inOpen(compareString) == false && inClosed(compareString) == false)
                        {
                            childrenStates[b].updateHeuristic(distances[b]);
                            //System.out.println("distances: " + distances[b]);
                            masterHeuristic[b] = distances[b];
                            childrenStates[b].setPath(gN);
                        }
                        else if(inOpen(compareString))
                        {
                            childrenStates[b].setPath(gN);
                            childrenStates[b].updateHeuristic(distances[b]);
                            if(childrenStates[b].getPath() < temp.getPath())
                            {
                                if(getIndex(compareString) != -1)
                                {
                                    open[getIndex(compareString)].setPath(temp.getPath());
                                }
                            }
                        }
                        else if(inClosed(compareString))
                        {
                            //childrenStates[b].updateHeuristic(distances[b]);
                            if(childrenStates[b].getPath() < temp.getPath())
                            {
                                if(getIndex(compareString) != -1)
                                {
                                    removeClose(compareString);
                                    //puzzleNode newNode = new puzzleNode(compareString);
                                    //newNode.updateHeuristic(childrenStates[b].getHeuristic());
                                    //newNode.setPath(gN);
                                    //add(newNode);
                                }
                            }
                        }
                    }
                    if(childrenStates[0].getHeuristic() == childrenStates[1].getHeuristic() && childrenStates[1].getHeuristic() == childrenStates[2].getHeuristic() && childrenStates[2].getHeuristic() == childrenStates[3].getHeuristic())
                    {
                        childrenStates[0].addToHeuristic(3);
                        childrenStates[0].addToPath(3);
                        childrenStates[1].addToHeuristic(2);
                        childrenStates[1].addToPath(2);
                        childrenStates[2].addToHeuristic(1);
                        childrenStates[2].addToPath(1);
                        childrenStates[3].addToHeuristic(0);
                        childrenStates[3].addToPath(0);
                        System.out.println("ALL HEURISTICS EQUAL");
                        //break;
                    }

                    addClosed(temp);
                    //System.out.println("childrenStates: " + childrenStates[0].getHeuristic() + " " + childrenStates[1].getHeuristic() + " " + childrenStates[2].getHeuristic() + " " + childrenStates[3].getHeuristic());
                    childrenStates = reorderNodes(childrenStates,4);
                    //System.out.println("childrenStates: " + childrenStates[0].getHeuristic() + " " + childrenStates[1].getHeuristic() + " " + childrenStates[2].getHeuristic() + " " + childrenStates[3].getHeuristic());
                    addOpen(childrenStates,4);
                    //System.out.println(open[0].getHeuristic());

                }
                else if(XZero == 0 && YZero == 1 || XZero == 1 && YZero == 2 || XZero == 2 && YZero == 1 || XZero == 1 && YZero == 0)
                {
                    int [][][] next3steps = new int [3][3][3];
                    next3steps = generateNext3Steps(stepGrid);
                    System.out.println("Next 3 Steps: ");
                    printNext3Steps(next3steps);
                    //int [] tiles = new int [3];
                    int [] distances = new int [3];
                    //tiles = calculateTilesOutOfPlace(next3steps,goal,3);
                    distances = calculateDistanceOutOfPlace(next3steps,goal,3);
                    puzzleNode [] childrenStates = new puzzleNode[3];

                   

                    
                    String compareString = "";
                    for(int b = 0; b < 3; b++)
                    {
                        stepGrid = copyGrid(next3steps, b);
                        compareString = revertState(stepGrid);
                        childrenStates[b] = new puzzleNode(compareString);
                        //childrenStates[b].setPath(gN);

                        if((inOpen(compareString) == false) && (inClosed(compareString) == false))
                        {   
                            System.out.println("here");
                            childrenStates[b].updateHeuristic(distances[b]);
                            System.out.println("distances: " + distances[b]);
                            masterHeuristic[b] = distances[b];
                            childrenStates[b].setPath(gN);
                        }
                        else if(inOpen(compareString))
                        {
                            childrenStates[b].setPath(gN);
                            childrenStates[b].updateHeuristic(distances[b]);
                            if(childrenStates[b].getPath() < temp.getPath())
                            {
                                if(getIndex(compareString) != -1)
                                {
                                    open[getIndex(compareString)].setPath(temp.getPath());
                                }
                            }
                        }
                        else if(inClosed(compareString))
                        {
                            //childrenStates[b].setPath(gN);
                            //childrenStates[b].updateHeuristic(distances[b]);
                            if(childrenStates[b].getPath() < temp.getPath())
                            {
                                if(getIndex(compareString) != -1)
                                {
                                    removeClose(compareString);
                                    //puzzleNode newNode = new puzzleNode(compareString);
                                    //newNode.updateHeuristic(childrenStates[b].getHeuristic());
                                    //newNode.setPath(gN);
                                    //add(newNode);
                                }
                            }
                        }
                    }

                    if(childrenStates[0].getHeuristic() == childrenStates[1].getHeuristic() && childrenStates[1].getHeuristic() == childrenStates[2].getHeuristic())
                    {
                        
                        childrenStates[0].addToHeuristic(2);
                        childrenStates[0].addToPath(2);
                        childrenStates[1].addToHeuristic(1);
                        childrenStates[1].addToPath(1);
                        childrenStates[2].addToHeuristic(0);
                        childrenStates[2].addToPath(0);
                        System.out.println("ALL HEURISTICS EQUAL");
                        //break;
                    }


                    addClosed(temp);
                   // System.out.println("childrenStates: " + childrenStates[0].getHeuristic() + " " + childrenStates[1].getHeuristic() + " " + childrenStates[2].getHeuristic() + " " + childrenStates[3].getHeuristic());
                    childrenStates = reorderNodes(childrenStates,3);
                    //System.out.println("childrenStates: " + childrenStates[0].getHeuristic() + " " + childrenStates[1].getHeuristic() + " " + childrenStates[2].getHeuristic() + " " + childrenStates[3].getHeuristic());
                    addOpen(childrenStates,3);   
                }
                else
                {
                    int [][][] next2steps = new int [3][3][2];
                    next2steps = generateNext2Steps(stepGrid);
                    System.out.println("Next 2 Steps: ");
                    printNext2Steps(next2steps);
                    //int [] tiles = new int [2];
                    int [] distances = new int [2];
                    //tiles = calculateTilesOutOfPlace(next2steps,goal,2);
                    distances = calculateDistanceOutOfPlace(next2steps,goal,2);
                    puzzleNode [] childrenStates = new puzzleNode[2];

                    

                    String compareString = "";
                    for(int b = 0; b < 2; b++)
                    {
                        stepGrid = copyGrid(next2steps, b);
                        compareString = revertState(stepGrid);
                        childrenStates[b] = new puzzleNode(compareString);
                        //System.out.println("Predicate");
                        if((inOpen(compareString) == false) && (inClosed(compareString) == false))
                        {
                            childrenStates[b].updateHeuristic(distances[b]);
                            masterHeuristic[b] = distances[b];
                            childrenStates[b].setPath(gN);
                            System.out.println("Gn: " + gN);
                        }
                        else if(inOpen(compareString))
                        {
                            childrenStates[b].setPath(gN);
                            childrenStates[b].updateHeuristic(distances[b]);
                            if(childrenStates[b].getPath() < temp.getPath())
                            {
                                    System.out.println("Setting path");
                                if(getIndex(compareString) != -1)
                                {
                                    open[getIndex(compareString)].setPath(temp.getPath());
                                }
                            }
                        }
                        else if(inClosed(compareString))
                        {
                           // childrenStates[b].setPath(gN);
                            //childrenStates[b].updateHeuristic(distances[b]);
                            System.out.println("in closed path");
                            if(childrenStates[b].getPath() < temp.getPath())
                            {
                                if(getIndex(compareString) != -1)
                                {
                                    removeClose(compareString);
                                    //puzzleNode newNode = new puzzleNode(compareString);
                                    //newNode.updateHeuristic(childrenStates[b].getHeuristic());
                                    //newNode.setPath(gN);
                                    //add(newNode);
                                }
                            }
                        }
                    }

                    if(childrenStates[0].getHeuristic() == childrenStates[1].getHeuristic())
                    {
                        
                        childrenStates[0].addToHeuristic(1);
                        childrenStates[0].addToPath(1);
                        childrenStates[1].addToHeuristic(0);
                        childrenStates[1].addToPath(0);
                        System.out.println("ALL HEURISTICS EQUAL");
                        //break;
                    }

                    addClosed(temp);
                    childrenStates = reorderNodes(childrenStates,2);
                    addOpen(childrenStates,2);      
                }
            } 
        }
        System.out.println("optimum: " + temp.getPath());
        return state;
        
        // System.out.println("Zero Coordinates: X[" + XZero + "," + YZero + "]Y\n"); 


        // if(checkIfGoal(currentGrid, goal))
        // {
        //     System.out.println("Goal");
        //     String value = revertState(currentGrid);
        //     puzzleNode newNode = new puzzleNode(value);
        //     return newNode;
        // }

        // if(XZero == 1 && YZero == 1)
        // {
        //     //System.out.println("Zero is in middle");
        //     int [][][] next4steps = new int [3][3][4];
        //     next4steps = generateNext4Steps(stepGrid);
        //     System.out.println("Next 4 Steps: ");
        //     printNext4Steps(next4steps);
        //     int [] tiles = new int [4];
        //     int [] distances = new int [4];
        //     tiles = calculateTilesOutOfPlace(next4steps,goal,4);
        //     distances = calculateDistanceOutOfPlace(next4steps,goal,4);

        //     for(int a = 0; a < 4; a++)
        //     {
        //         masterHeuristic[a] = distances[a];// + gN;// + tiles[a] + gN;
        //     }





        //     int high = 10000000;
        //     int index = 0;

        //     //puzzleNode currentNode = new puzzleNode();

        //     String compareString = "";
        //     for(int b = 0; b < 4; b++)
        //     {
        //         stepGrid = copyGrid(next4steps, b);
        //         compareString = revertState(stepGrid);
        //         if(checkIfGoal(stepGrid, goal))
        //         {
        //             puzzleNode newNode = new puzzleNode(compareString);
        //             newNode.updateHeuristic(masterHeuristic[b]);
        //             return newNode;
        //         }
        //         else if(masterHeuristic[b] < initial.getHeuristic())
        //         {
        //             System.out.println("HEURISTIC: " + masterHeuristic[b] + " Current HEURISTIC: " + initial.getHeuristic());
        //             puzzleNode newNode = new puzzleNode(compareString);
        //             newNode.updateHeuristic(masterHeuristic[b]);
        //             return newNode;
        //         }

        //     }



        //     puzzleNode newNode = new puzzleNode(compareString);
        //     newNode.updateHeuristic(masterHeuristic[3]);

        //     return null;

        //     // if(checkIfGoal(stepGrid, goal) == true)
        //     // {
        //     //     System.out.println("Goal State");
        //     // }
        //     // else
        //     // {
        //     //     System.out.println("Not Goal State");
        //     // }
        //     // return newNode;

        // }
        // else if(XZero == 0 && YZero == 1 || XZero == 1 && YZero == 2 || XZero == 2 && YZero == 1 || XZero == 1 && YZero == 0)
        // {
        //     //System.out.println("Zero is edge piece");
        //     int [][][] next3steps = new int [3][3][3];
        //     next3steps = generateNext3Steps(stepGrid);
        //     System.out.println("Next 3 Steps: ");
        //     printNext3Steps(next3steps);
        //     int [] tiles = new int [3];
        //     int [] distances = new int [3];
        //     tiles = calculateTilesOutOfPlace(next3steps,goal,3);
        //     distances = calculateDistanceOutOfPlace(next3steps,goal,3);

        //     for(int a = 0; a < 3; a++)
        //     {
        //         masterHeuristic[a] = distances[a];// + gN;// + tiles[a] + gN;
        //     }


        //     int high = 10000000;
        //     int index = 0;

        //     //puzzleNode currentNode = new puzzleNode();

        //     String compareString = "";
        //     for(int b = 0; b < 3; b++)
        //     {
        //         stepGrid = copyGrid(next3steps, b);
        //         compareString = revertState(stepGrid);
        //         if(checkIfGoal(stepGrid, goal))
        //         {
        //             puzzleNode newNode = new puzzleNode(compareString);
        //             newNode.updateHeuristic(masterHeuristic[b]);
        //             return newNode;
        //         }
        //         else if(masterHeuristic[b] < initial.getHeuristic())
        //         {
        //             System.out.println("HEURISTIC: " + masterHeuristic[b] + " Current HEURISTIC: " + initial.getHeuristic());
        //             puzzleNode newNode = new puzzleNode(compareString);
        //             newNode.updateHeuristic(masterHeuristic[b]);
        //             return newNode;
        //         }

        //     }


        //     puzzleNode newNode = new puzzleNode(compareString);
        //     newNode.updateHeuristic(masterHeuristic[2]);
        //     return null;

        //     // if(checkIfGoal(stepGrid, goal) == true)
        //     // {
        //     //     System.out.println("Goal State");
        //     // }
        //     // else
        //     // {
        //     //     System.out.println("Not Goal State");
        //     // }
        //     // return newNode;
        // }
        // else
        // {
        //     //System.out.println("Zero is in corner");
        //     int [][][] next2steps = new int [3][3][2];
        //     next2steps = generateNext2Steps(stepGrid);
        //     System.out.println("Next 2 Steps: ");
        //     printNext2Steps(next2steps);
        //     int [] tiles = new int [2];
        //     int [] distances = new int [2];
        //     tiles = calculateTilesOutOfPlace(next2steps,goal,2);
        //     distances = calculateDistanceOutOfPlace(next2steps,goal,2);

        //     for(int a = 0; a < 2; a++)
        //     {
        //         masterHeuristic[a] = distances[a];// + gN;// + tiles[a] + gN;
        //     }


        //     int high = 10000000;
        //     int index = 0;

        //     //puzzleNode currentNode = new puzzleNode();

        //     String compareString = "";
        //     for(int b = 0; b < 2; b++)
        //     {
        //         stepGrid = copyGrid(next2steps, b);
        //         compareString = revertState(stepGrid);
        //         if(checkIfGoal(stepGrid, goal))
        //         {
        //             puzzleNode newNode = new puzzleNode(compareString);
        //             newNode.updateHeuristic(masterHeuristic[b]);
        //             return newNode;
        //         }
        //         else if(masterHeuristic[b] < initial.getHeuristic())
        //         {
        //             System.out.println("HEURISTIC: " + masterHeuristic[b] + " Current HEURISTIC: " + initial.getHeuristic());
        //             puzzleNode newNode = new puzzleNode(compareString);
        //             newNode.updateHeuristic(masterHeuristic[b]);
        //             return newNode;
        //         }

        //     }


        //     puzzleNode newNode = new puzzleNode(compareString);
        //     newNode.updateHeuristic(masterHeuristic[1]);
        //     return null;

        //     // if(checkIfGoal(stepGrid, goal) == true)
        //     // {
        //     //     System.out.println("Goal State");
        //     // }
        //     // else
        //     // {
        //     //     System.out.println("Not Goal State");
        //     // }
        //     // return newNode;
        // }

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
        //int count = 4;
        int x = 1;
        int y = 1;
        //int [][] temp = initial;
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
