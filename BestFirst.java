import java.util.*;
public class BestFirst
{

    public static HashMap<String, Integer> map = new HashMap<String, Integer>();
    public static int gN = 0;
    public static int OLS = 10000;
    public static int optimum = 0; 
    public static int iterations = 0;
    public static puzzleNode [] open = new puzzleNode[OLS];
    public static puzzleNode [] closed = new puzzleNode[OLS];
    public static puzzleNode [] tempList = new puzzleNode[OLS];
    


    public static boolean BestFirstSearch(puzzleNode initial, int [][] goal)
    {   
        int [][] stepGrid = new int [3][3];
        boolean state = false;
        stepGrid = convertTo2D(convertState(initial.getString()));
        int XZero = findNullCoords(stepGrid)[0];
        int YZero = findNullCoords(stepGrid)[1];
        gN= 0;
        optimum = 0;
        iterations = 0;

        clearLists();
        addToOpen(initial);

        while(isEmpty(open) == false && state != true)
        {
            System.out.println("Starting");
            puzzleNode currentState = removeFirst();
            stepGrid = convertTo2D(convertState(currentState.getString()));
            XZero = findNullCoords(stepGrid)[0];
            YZero = findNullCoords(stepGrid)[1];
            gN++;
            optimum++;
            clearTemp();
            
            if(compare(stepGrid, goal))
            {
                state = true;
            }
            else
            {
                if(XZero == 1 && YZero == 1)
                {
                    addToTemp(generateChild(currentState, "up"));
                    addToTemp(generateChild(currentState, "down"));
                    addToTemp(generateChild(currentState, "left"));
                    addToTemp(generateChild(currentState, "right"));
                    for(int a = 0; a < 4; a++)
                    {
                        if(!isInOpen(tempList[a]) && !isInClosed(tempList[a]))
                        {
                            tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                            addToOpen(tempList[a]);
                        }
                        else if(isInOpen(tempList[a]))
                        {
                            int index = findIndex(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                open[index].setPath(tempList[a].getPath());
                            }
                        }
                        else if(isInClosed(currentState))
                        {
                            int index = findIndexClosed(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                removeFromClosed(currentState);
                                tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                                addToOpen(tempList[a]);
                            }
                        }
                    }
                }
                else if(XZero == 1 && YZero == 0)
                {
                    //addToClosed(currentState);
                    addToTemp(generateChild(currentState, "down"));
                    addToTemp(generateChild(currentState, "right"));
                    addToTemp(generateChild(currentState, "up"));
                    for(int a = 0; a < 3; a++)
                    {
                        if(!isInOpen(tempList[a]) && !isInClosed(tempList[a]))
                        {
                            tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                            addToOpen(tempList[a]);
                        }
                        else if(isInOpen(tempList[a]))
                        {
                            int index = findIndex(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                open[index].setPath(tempList[a].getPath());
                            }
                        }
                        else if(isInClosed(currentState))
                        {
                            int index = findIndexClosed(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                removeFromClosed(currentState);
                                tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                                addToOpen(tempList[a]);
                            }
                        }
                    }
                }
                else if(XZero == 1 && YZero == 2)
                {
                    //addToClosed(currentState);
                    addToTemp(generateChild(currentState, "up"));
                    addToTemp(generateChild(currentState, "down"));
                    addToTemp(generateChild(currentState, "left"));
                    for(int a = 0; a < 3; a++)
                    {
                        if(!isInOpen(tempList[a]) && !isInClosed(tempList[a]))
                        {
                            tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                            addToOpen(tempList[a]);
                        }
                        else if(isInOpen(tempList[a]))
                        {
                            int index = findIndex(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                open[index].setPath(tempList[a].getPath());
                            }
                        }
                        else if(isInClosed(currentState))
                        {
                            int index = findIndexClosed(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                removeFromClosed(currentState);
                                tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                                addToOpen(tempList[a]);
                            }
                        }
                    }
                }
                else if(XZero == 0 && YZero == 1)
                {
                    //addToClosed(currentState);
                    addToTemp(generateChild(currentState, "left"));
                    addToTemp(generateChild(currentState, "down"));
                    addToTemp(generateChild(currentState, "right"));
                    for(int a = 0; a < 3; a++)
                    {
                        if(!isInOpen(tempList[a]) && !isInClosed(tempList[a]))
                        {
                            tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                            addToOpen(tempList[a]);
                        }
                        else if(isInOpen(tempList[a]))
                        {
                            int index = findIndex(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                open[index].setPath(tempList[a].getPath());
                            }
                        }
                        else if(isInClosed(currentState))
                        {
                            int index = findIndexClosed(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                removeFromClosed(currentState);
                                tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                                addToOpen(tempList[a]);
                            }
                        }
                    }
                }
                else if(XZero == 2 && YZero == 1)
                {
                    //addToClosed(currentState);
                    addToTemp(generateChild(currentState, "up"));
                    addToTemp(generateChild(currentState, "right"));
                    addToTemp(generateChild(currentState, "left"));
                    for(int a = 0; a < 3; a++)
                    {
                        if(!isInOpen(tempList[a]) && !isInClosed(tempList[a]))
                        {
                            tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                            addToOpen(tempList[a]);
                        }
                        else if(isInOpen(tempList[a]))
                        {
                            int index = findIndex(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                open[index].setPath(tempList[a].getGN());
                            }
                        }
                        else if(isInClosed(currentState))
                        {
                            int index = findIndexClosed(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                removeFromClosed(currentState);
                                tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                                addToOpen(tempList[a]);
                            }
                        }
                    }
                }
                else if(XZero == 0 && YZero == 0)
                {
                    //addToClosed(currentState);
                    addToTemp(generateChild(currentState, "down"));
                    addToTemp(generateChild(currentState, "right"));
                    for(int a = 0; a < 2; a++)
                    {
                        if(!isInOpen(tempList[a]) && !isInClosed(tempList[a]))
                        {
                            tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                            addToOpen(tempList[a]);
                        }
                        else if(isInOpen(tempList[a]))
                        {
                            int index = findIndex(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                open[index].setPath(tempList[a].getGN());
                            }
                        }
                        else if(isInClosed(currentState))
                        {
                            int index = findIndexClosed(tempList[a]);
                            if(tempList[a].getGN() < open[index].getGN())
                            {
                                removeFromClosed(currentState);
                                tempList[a].updateHeuristic(calculateHeuristic(tempList[a], goal));
                                addToOpen(tempList[a]);
                            }
                        }
                    }
                }
            }
            addToClosed(currentState);
            orderOpen();
        }
        return state; 
    }

    //function to order to nodes in open from lowest to highest heuristic
    public static void orderOpen()
    {
        int temp = 0;
        for(int a = 0; a < OLS; a++)
        {
            for(int b = 0; b < OLS; b++)
            {
                if(open[b] != null && open[a] != null)
                {
                    if(open[a].getHeuristic() < open[b].getHeuristic())
                    {
                        temp = open[a].getHeuristic();
                        open[a].setHeuristic(open[b].getHeuristic());
                        open[b].setHeuristic(temp);
                    }
                }
            }
        }
    }

    //function to find the index of a node in the open list
    public static int findIndex(puzzleNode node)
    {
        int index = 0;
        for(int a = 0; a < open.length; a++)
        {
            if(open[a] != null)
            {
                if(open[a].getString().equals(node.getString()))
                {
                    index = a;
                }
            }
        }
        return index;
    }

    ///function to find the index of a node in the closed list
    public static int findIndexClosed(puzzleNode node)
    {
        int index = 0;
        for(int a = 0; a < closed.length; a++)
        {
            if(closed[a] != null)
            {
                if(closed[a].getString().equals(node.getString()))
                {
                    index = a;
                }
            }
        }
        return index;
    }

    //function to remove a node from closed
    public static void removeFromClosed(puzzleNode node)
    {
        for(int a = 0; a < closed.length; a++)
        {
            if(closed[a] != null)
            {
                if(closed[a].getString().equals(node.getString()))
                {
                    closed[a] = null;
                }
            }
        }
    }

    //function to clear templist
    public static void clearTemp()
    {
        for(int a = 0; a < OLS; a++)
        {
            tempList[a] = null;
        }
    }

    //function that calculates the heuristic value of a given state
    public static int calculateHeuristic(puzzleNode state, int [][] goal)
    {
        int heuristic = 0;
        int [][] stepGrid = new int [3][3];
        stepGrid = convertTo2D(convertState(state.getString()));
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(stepGrid[i][j] != goal[i][j])
                {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    //function that checks if a node is in the closed list
    public static boolean isInClosed(puzzleNode node)
    {
        boolean state = false;
        for(int a = 0; a < closed.length; a++)
        {
            if(closed[a] != null)
            {
                if(closed[a].getString().equals(node.getString()))
                {
                    state = true;
                }
            }
        }
        return state;
    }

    //function that checks if a node is in the open list
    public static boolean isInOpen(puzzleNode node)
    {
        for(int a = 0; a < OLS; a++)
        {
            if(open[a] == null)
            {
                break;
            }
            else if(open[a].getString() == (node.getString()))
            {
                return true;
            }
        }
        return false;
    }

    //function that adds a node to the temp list
    public static void addToTemp(puzzleNode node)
    {
        tempList[iterations] = node;
        iterations++;
    }


    //function that generate child nodes for the current node
    public static puzzleNode generateChild(puzzleNode current, String direction)
    {
        int [][] stepGrid = new int [3][3];
        stepGrid = convertTo2D(convertState(current.getString()));
        int XZero = findNullCoords(stepGrid)[0];
        int YZero = findNullCoords(stepGrid)[1];
        puzzleNode newNode = new puzzleNode(current.getString());

        if(direction == "up")
        {
            int temp = stepGrid[XZero][YZero];
            stepGrid[XZero][YZero] = stepGrid[XZero-1][YZero];
            stepGrid[XZero-1][YZero] = temp;
            newNode.setPath(gN+1);
            System.out.println("Nullere: " + XZero + " " + YZero);
            //newNode.addToPath(1);
            //newNode.updateHeuristic(calculateHeuristic(stepGrid, convertToString(stepGrid)));
            //newNode.addToHeuristic(1);
            newNode.setString(convertToString(stepGrid));
        }
        else if(direction == "down")
        {
            int temp = stepGrid[XZero][YZero];
            stepGrid[XZero][YZero] = stepGrid[XZero+1][YZero];
            stepGrid[XZero+1][YZero] = temp;
            newNode.setPath(gN+1);
            //newNode.addToPath(1);
            //newNode.updateHeuristic(calculateHeuristic(stepGrid, convertToString(stepGrid)));
           // newNode.addToHeuristic(1);
            newNode.setString(convertToString(stepGrid));
        }
        else if(direction == "left")
        {
            int temp = stepGrid[XZero][YZero];
            stepGrid[XZero][YZero] = stepGrid[XZero][YZero-1];
            stepGrid[XZero][YZero-1] = temp;
            newNode.setPath(gN+1);
            //newNode.addToPath(1);
            //newNode.updateHeuristic(calculateHeuristic(stepGrid, convertToString(stepGrid)));
            //newNode.addToHeuristic(1);
            newNode.setString(convertToString(stepGrid));
        }
        else if(direction == "right")
        {
            int temp = stepGrid[XZero][YZero];
            stepGrid[XZero][YZero] = stepGrid[XZero][YZero+1];
            stepGrid[XZero][YZero+1] = temp;
            newNode.setPath(gN+1);
           // newNode.addToPath(1);
           //newNode.updateHeuristic(calculateHeuristic(stepGrid, convertToString(stepGrid)));
           // newNode.addToHeuristic(1);
            newNode.setString(convertToString(stepGrid));
        }
        return newNode;
    }

    //function that converts a 2d array to a string
    public static String convertToString(int [][] grid)
    {
        String gridString = "";
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                gridString += grid[i][j];
            }
        }
        return gridString;
    }

    //function that adds a node to the closed list
    public static void addToClosed(puzzleNode node)
    {
        closed[iterations] = node;
        iterations++;
    }

    //function that compares the current state to the goal state
    public static boolean compare(int [][] current, int [][] goal)
    {
        boolean state = false;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(current[i][j] != goal[i][j])
                {
                    state = false;
                    break;
                }
                else
                {
                    state = true;
                }
            }
        }
        return state;
    }


    //function that removes and returns the first element in the open list
    public static puzzleNode removeFirst()
    {
        puzzleNode temp = open[0];
        for(int i = 0; i < open.length - 1; i++)
        {
            open[i] = open[i+1];
        }
        open[open.length - 1] = null;
        return temp;
    }

    //function to check if open list is empty
    public static boolean isEmpty(puzzleNode [] list)
    {
        if(list[0] == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //function that clears both open and closed lists
    public static void clearLists()
    {
        for(int i = 0; i < OLS; i++)
        {
            open[i] = null;
            closed[i] = null;
        }
    }

    //function that adds a node to the open list
    public static void addToOpen(puzzleNode node)
    {
        int i = 0;
        while(i < OLS)
        {
            if(open[i] == null)
            {
                open[i] = node;
                break;
            }
            i++;
        }
    }

    public static int [][] convertTo2D(int [] grid)
    {
        int [][] grid2D = new int [3][3];
        int c = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                grid2D[i][j] = grid[c];
                c++;
            }
        }
        return grid2D;
    }


    public static int [] convertState(String state)
    {
        int [] stateArray = new int [9];
        for(int i = 0; i < state.length(); i++)
        {
            stateArray[i] = Integer.parseInt(state.substring(i, i+1));
        }
        return stateArray;
    }
    
    
    
    //function that finds the coordinates of the null tile
    public static int [] findNullCoords(int [][] grid)
    {
        int [] coords = new int [2];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(grid[i][j] == 0)
                {
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }
        return coords;
    }

}
