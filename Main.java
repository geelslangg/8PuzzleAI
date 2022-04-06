public class Main {

    public static void main(String[] args) //main function
    {

        long startTime = System.currentTimeMillis(); //start time
        BreadthFirst newBFS = new BreadthFirst(); //creating object of class BreadthFirst
        Hillclimb newHill = new Hillclimb(); //creating object of class Hillclimb
        aAlgorithm newAstar = new aAlgorithm(); //creating object of class Astar
            
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
        long [] times = new long[10];

        int c = 5;

        for(int a = 0; a < 10; a++)
        {
            long S = System.currentTimeMillis();

            state1 = starts[a];
            goal1 = ends[a];
            puzzleNode newNode = new puzzleNode(state1);

            goalState1 = BreadthFirst.convertState(goal1);
            goalGrid = BreadthFirst.convertTo2D(goalState1);
            
            boolean endWhile = false;

            System.out.print("Start State: ");
            BreadthFirst.printPuzzle(BreadthFirst.convertTo2D(BreadthFirst.convertState(state1)));
            System.out.print("Goal State: ");
            BreadthFirst.printPuzzle(goalGrid);

            BreadthFirst.listOfStates.clear();
            BreadthFirst.tempList.clear();

            //generateNextFourSteps("123405678");

            //endWhile = newBFS.breadthFirstAlgorithm(newNode, goalGrid);
            System.out.println("Success: " + endWhile);
            System.out.println("Absolute Optimum: " + BreadthFirst.optimum + "");
            System.out.println("Iterations: " + BreadthFirst.iterations);
            System.out.println("Max Positions Queued: " + BreadthFirst.maxPositionsStored + "\n\n");
            long F = System.currentTimeMillis();
            times[a] = F - S;
            optimums[a] = BreadthFirst.optimum;
            iterationsPerState[a] = BreadthFirst.iterations;
            BreadthFirst.iterations = 0;
            BreadthFirst.maxPositionsStored = 0;
        }

        //function that displays the optimums variable neatly
        System.out.println("|Instance|\t|Start State|\t|Goal State|\t|Known Optimum|\t|Absolute Optimum|\t|Iterations|\t|Time is ms|");
        for(int a = 0; a < 10; a++)
        {
            System.out.println((a+1) + "\t\t" + starts[a] + "\t" + ends[a] + "\t" + knownOptimums[a] + "\t\t" + optimums[a] + "\t\t\t" + iterationsPerState[a] + "\t\t" + times[a]);
        }
        long endTime = System.currentTimeMillis(); //end time
        long totalTime = endTime - startTime; //total time 
        System.out.println("\nTotal Execution Time: " + totalTime + " milliseconds");

        times = new long[10];


        int n = 0;
        for(int a = 0; a < 10; a++)
        {
            long perInstance = System.currentTimeMillis();
            state1 = starts[a];
            goal1 = ends[a];
            puzzleNode newNode = new puzzleNode(state1);

            goalState1 = Hillclimb.convertState(goal1);
            goalGrid = Hillclimb.convertTo2D(goalState1);
            
            boolean endWhile = false;

            System.out.print("Start State: ");
            Hillclimb.printPuzzle(Hillclimb.convertTo2D(Hillclimb.convertState(state1)));
            System.out.print("Goal State: ");
            Hillclimb.printPuzzle(goalGrid);
            //newHill.openList.clear();

            endWhile = Hillclimb.hillClimbAlgorithm(newNode, goalGrid);
            System.out.println("Success: " + endWhile);
            System.out.println("Absolute Optimum: " + newHill.gN + "");
            System.out.println("Iterations: " + Hillclimb.iterations);
            System.out.println("Open List Size: " + Hillclimb.openListSize + "\n\n");
            long perInstanceFinished = System.currentTimeMillis();
            long finishTime = perInstanceFinished - perInstance;
            times[a] = finishTime;
            optimums[a] = newHill.gN;
            iterationsPerState[a] = Hillclimb.iterations;
            Hillclimb.iterations = 0;
            Hillclimb.openListSize = 0;
            
        }

        System.out.println("|Instance|\t|Start State|\t|Goal State|\t|Known Optimum|\t|Absolute Optimum|\t|Iterations|\t|Time in ms|");
        for(int a = 0; a < 10; a++)
        {
            System.out.println((a+1) + "\t\t" + starts[a] + "\t" + ends[a] + "\t" + knownOptimums[a] + "\t\t" + optimums[a] + "\t\t\t" + iterationsPerState[a] + "\t\t" + times[a]);
        }
        endTime = System.currentTimeMillis(); //end time
        totalTime = endTime - startTime; //total time 
        System.out.println("\nTotal Execution Time: " + totalTime + " milliseconds");




        times = new long[10];


        int A = 0;
        for(int a = 0; a < 10; a++)
        {
            long perInstance = System.currentTimeMillis();
            state1 = starts[a];
            goal1 = ends[a];
            puzzleNode newNode = new puzzleNode(state1);

            goalState1 = aAlgorithm.convertState(goal1);
            goalGrid = aAlgorithm.convertTo2D(goalState1);
            
            boolean endWhile = false;

            System.out.print("Start State: ");
            aAlgorithm.printPuzzle(aAlgorithm.convertTo2D(aAlgorithm.convertState(state1)));
            System.out.print("Goal State: ");
            aAlgorithm.printPuzzle(goalGrid);
            //newHill.openList.clear();

            endWhile = aAlgorithm.aAlgorithmSearch(newNode, goalGrid);
            System.out.println("Success: " + endWhile);
            System.out.println("Absolute Optimum: " + aAlgorithm.optimum + "");
            System.out.println("Iterations: " + aAlgorithm.iterations);
            long perInstanceFinished = System.currentTimeMillis();
            long finishTime = perInstanceFinished - perInstance;
            times[a] = finishTime;
            optimums[a] = aAlgorithm.optimum;
            iterationsPerState[a] = aAlgorithm.iterations;
            //aAlgorithm.iterations = 0;
        }

        System.out.println("|Instance|\t|Start State|\t|Goal State|\t|Known Optimum|\t|Absolute Optimum|\t|Iterations|\t|Time in ms|");
        for(int a = 0; a < 10; a++)
        {
            System.out.println((a+1) + "\t\t" + starts[a] + "\t" + ends[a] + "\t" + knownOptimums[a] + "\t\t" + optimums[a] + "\t\t\t" + iterationsPerState[a] + "\t\t" + times[a]);
        }
        endTime = System.currentTimeMillis(); //end time
        totalTime = endTime - startTime; //total time 
        System.out.println("\nTotal Execution Time: " + totalTime + " milliseconds");
    
}
}
