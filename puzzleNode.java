public class puzzleNode
{
	int [][] nodeState = new int [3][3];
	String stateString = "";
 	int heuristic = 100000000;
 	int pathValue = 100000000;
	 int gN = 0;

	puzzleNode(String state)
	{
		stateString = state;
	}

	public String getString()
	{
		return stateString;
	}

	public void setString(String state)
	{
		stateString = state;
	}

	public int getGN()
	{
		return gN;
	}

	public void updateHeuristic(int update)
	{
		heuristic = update;
	}

	public void addToHeuristic(int add)
	{
		heuristic += add;
	}
	public void addToPath(int add)
	{
		pathValue += add;
	}

	public void setPath(int path)
	{
		pathValue = path;
	}

	public int getPath()
	{
		return pathValue;
	}

	public int getHeuristic()
	{
		return heuristic;
	}

	public void setHeuristic(int heuristic)
	{
		this.heuristic = heuristic;
	}
 	
}
