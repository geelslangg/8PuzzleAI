public class Main {

    public static void main(String[] args) //main function
    {

        long startTime = System.currentTimeMillis(); //start time
        BreadthFirst newBFS = new BreadthFirst(); //creating object of class BreadthFirst
            
        System.out.println("Start Of Breadth First Search 8 Puzzle Program!\n");


        String [] ownStarts = {"231804765","714683520","123804765",
                            "134805726","231708654","231804765",  //string list of start states
                            "123804765","123804765","876105234",
                            "867254301",};
        
        String [] ownEnds = {"123804765","123456780","123456780",
                            "123456780","123456780","123456780",  //string list of goal states
                            "123456780","123456780","123456780",
                            "123456780",};


        String [] starts = {"123804765","123804765","123804765",
                            "134805726","231708654","231804765",  //string list of start states
                            "123804765","123804765","876105234",
                            "867254301",};

        String [] ends = {"134862705","281043765","281463075",
                            "123804765","123804765","123804765",  //string list of goal states
                            "231804765","567408321","123804765",
                            "123456780",};


        //array to store 10 specific known integer optimums
        int [] knownOptimums = {5,9,12,6,14,16,16,30,28,31};
        
                            
        int [][] goalGrid = new int [3][3];
        int [] goalState1 = new int [9];
        int [] optimums = new int [starts.length];
        int [] iterationsPerState = new int [starts.length];
        String state1 = starts[0];
        String goal1 = ends[0];

        int c = 5;

        for(int a = 0; a < 10; a++)
        {
            state1 = starts[a];
            goal1 = ends[a];
            puzzleNode newNode = new puzzleNode(state1);

            goalState1 = newBFS.convertState(goal1);
            goalGrid = newBFS.convertTo2D(goalState1);
            
            boolean endWhile = false;

            System.out.print("Start State: ");
            newBFS.printPuzzle(newBFS.convertTo2D(newBFS.convertState(state1)));
            System.out.print("Goal State: ");
            newBFS.printPuzzle(goalGrid);

            newBFS.listOfStates.clear();
            newBFS.tempList.clear();

            //generateNextFourSteps("123405678");

            endWhile = newBFS.breadthFirstAlgorithm(newNode, goalGrid);
            System.out.println("Success: " + endWhile);
            System.out.println("Absolute Optimum: " + newBFS.optimum + "");
            System.out.println("Iterations: " + newBFS.iterations);
            System.out.println("Max Positions Queued: " + newBFS.maxPositionsStored + "\n\n");
            optimums[a] = newBFS.optimum;
            iterationsPerState[a] = newBFS.iterations;
            newBFS.iterations = 0;
            newBFS.maxPositionsStored = 0;
        }

        //function that displays the optimums variable neatly
        System.out.println("|Instance|\t|Start State|\t|Goal State|\t|Known Optimum|\t|Absolute Optimum|\t|Iterations|");
        for(int a = 0; a < 10; a++)
        {
            System.out.println((a+1) + "\t\t" + starts[a] + "\t" + ends[a] + "\t" + knownOptimums[a] + "\t\t" + optimums[a] + "\t\t\t" + iterationsPerState[a]);
        }
        long endTime = System.currentTimeMillis(); //end time
        long totalTime = endTime - startTime; //total time 
        System.out.println("\nTotal Execution Time: " + totalTime + " milliseconds");
    
}
}
