package code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import given.Util;
import given.iDeque;
import given.iSimpleContainer;

/* * 
 * 
 * ASSIGNMENT 2-Part2
 * STUDENT AUTHOR:  Mertcan Asgun
 *
 * MODIFY 
 * 
 * */

public class Maze {

	// Characters that define the maze
	char O = 'O'; // allowable cells
	char I = 'I'; // walls
	char S = 'S'; // start point of the Maze
	char E = 'E'; // exit cell
	char visited = '*'; // visited cells

	char[][] currentMaze;
	int rows;
	int cols;


	/*
	 * ADD FIELDS IF NEEDED
	 */

	public Maze() {
		/*
		 * 
		 * Implement a constructor if needed
		 * 
		 */
	}

	public static class Coordinate {
		int x;
		int y;

		public Coordinate(int r, int c) {
			x = r;
			y = c;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!Coordinate.class.isAssignableFrom(obj.getClass())) {
				return false;
			}
			final Coordinate other = (Coordinate) obj;
			if (this.x == other.x && this.y == other.y)
				return true;
			else
				return false;
		}

		/*
		 * ADD CODE IF NEEDED
		 * 
		 */
	}

	/*
	 * This method loads a maze given in a file. It is given for you. It reads the
	 * given maze file and load it into a 2-D char array
	 * 
	 * Feel free to modify it if needed.
	 * 
	 */
	public void loadMaze(String fileName) throws IOException {

		// BARIS: ASK THEM TO MODIFY?

		BufferedReader br1 = new BufferedReader(new FileReader(fileName));
		String line1;

		line1 = br1.readLine();
		rows = 0;

		while (line1 != null) { // gets the size of the maze

			line1 = br1.readLine();
			rows++;
		}
		br1.close();

		BufferedReader br2 = new BufferedReader(new FileReader(fileName));
		currentMaze = new char[rows][]; // creates a char array with maze size

		String line2;
		int i = 0;

		while ((line2 = br2.readLine()) != null) // loads maze elements to 2-D char array
		{
			String newStr = line2.replaceAll(", ", "");
			currentMaze[i++] = newStr.toCharArray();
		}

		cols = currentMaze[0].length;

		br2.close();
	}

	// Prints the maze if you want to debug
	//  public String toString() {
	//    int i = 0, j = 0;
	//    StringBuilder sb = new StringBuilder(1000);
	//    sb.append("Maze: " + rows + " x " + cols);
	//
	//    for (; i < rows; i++) {
	//      for (; j < cols - 1; j++) {
	//        sb.append(currentMaze[i][j] + ", ");
	//      }
	//      sb.append(currentMaze[i][j] + System.lineSeparator());
	//    }
	//
	//    return sb.toString();
	//  }

	public String toString() {
		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder(1000);
		sb.append("Maze: " + rows + " x " + cols +System.lineSeparator());

		for (; i < rows; i++) {
			for (; j < cols - 1; j++) {
				sb.append(currentMaze[i][j] + ", ");
			}
			j=0;
			sb.append(currentMaze[i][j] + System.lineSeparator());
		}

		return sb.toString();
	}


	/*
	 * ADD METHODS IF NEEDED
	 */

	/*
	 * Implement the algorithm given in the document in pseudocode form to solve the
	 * maze: i.e. find a path from the start coordinate to the end coordinate.
	 * 
	 * The algorithm takes in a container which changes its behavior, a deque to be
	 * filled from the back (we could have used something from Java but going with
	 * custom made DSes)
	 * 
	 * You are required to fill in the input iDeque with the visited nodes in the
	 * given order in a first-in first-out manner.
	 * 
	 * The neighbors of a coordinate follow the 4-neighborhood rule, i.e., they can
	 * be UP, DOWN, LEFT, RIGHT. Although it does not matter which order you push
	 * the neighbors to the container, we are going to impose an order for the
	 * autograder: 1) Decrease the y coordinate 2) Increase the x coordinate 3)
	 * Increase the y coordinate 4) Decrease the x coordinate
	 * 
	 * It returns true is an exit is fond, false otherwise.
	 * 
	 */
	public boolean solveMaze(iSimpleContainer<Coordinate> sc, iDeque<Coordinate> visitedNodes, String mazeName)
			throws IOException {
		loadMaze(mazeName);

		/* YOUR CODE HERE */

		// TODO Auto-generated method stub
		//Util.NotImplementedYetSoft();
		sc.push(getStartState(currentMaze));
		while(!sc.isEmpty()) {
//			System.out.println("Top is popped:"+sc.peek());
			Coordinate currentState=sc.pop();			
			if(isExit(currentState)) {    
				return true;
			} else if (!isVisited(currentState)) {
				markVisited(currentState,visited);
				visitedNodes.addBehind(currentState);
				
				if(isEmptyLeft(currentState)) {
					sc.push(leftCoor(currentState));

//					System.out.println("current:"+currentState);
//					System.out.println("next coor:"+leftCoor(currentState));
//					System.out.println("isempty:"+isEmptyLeft(currentState));
//					System.out.println("Left is empty and i pushed it");
//					System.out.println("stack:" +sc);
//					System.out.println("visited nodes:"+visitedNodes);
				}
				if(isEmptyDown(currentState)) {
					sc.push(lowerCoor(currentState));

//					System.out.println("current:"+currentState);
//					System.out.println("next coor:"+lowerCoor(currentState));
////					System.out.println("lower coor's char:"+getChar(lowerCoor(currentState)));
//					System.out.println("isempty:"+isEmptyDown(currentState));
//					System.out.println("Down is empty and i pushed it");
//					System.out.println("stack:" +sc);
//					System.out.println("visited nodes:"+visitedNodes);
////					System.out.println("Is Right also empty:"+isEmptyRight(currentState));
////					System.out.println("right coor:"+rightCoor(currentState));
////					System.out.println("right coor's char:"+getChar(rightCoor(currentState)));

				}
				if(isEmptyRight(currentState)) {											
					sc.push(rightCoor(currentState));   

//					System.out.println("current:"+currentState);
//					System.out.println("next coor:"+rightCoor(currentState));
//					System.out.println("isempty:"+isEmptyRight(currentState));
//					System.out.println("Right is empty and i pushed it");
//					System.out.println("stack:" +sc);
//					System.out.println("visited nodes:"+visitedNodes);
				}
				if(isEmptyUp(currentState)) {
					sc.push(upperCoor(currentState));

//					System.out.println("current:"+currentState);
//					System.out.println("next coor:"+upperCoor(currentState));
//					System.out.println("isempty:"+isEmptyUp(currentState));
//					System.out.println("Up is empty and i pushed it");
//					System.out.println("stack:" +sc);
//					System.out.println("visited nodes:"+visitedNodes);				
				}
			}
		}
		return false;
	}

	/*
	 * 
	 * The below functions are given to you as suggestions. You can use them in the
	 * solveMaze function. However, we will not check these explicitly
	 * 
	 */

	// Helper method which marks a coordinate as visited
	private void markVisited(Coordinate c, char val) {
		int x=c.getX();
		int y=c.getY();
		currentMaze[x][y]=val;

	}

	// Helper method which checks if the coordinate has been visited before
	private boolean isVisited(Coordinate c) {
//		return c.equals(visited);
		int x=c.getX();
		int y=c.getY();
		return currentMaze[x][y]==visited;
	}

	// Helper method which checks if the coordinate is within the maze or not
	private boolean isInMaze(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		if(x>=currentMaze.length||y>=currentMaze[1].length||x<0||y<0)
			return false;
		else return true;
	}

	// Helper method which checks if the coordinate is an exit or not


	
	private boolean isExit(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		if(currentMaze[x][y]==E)
			return true;
		else return false;
	}

	// Returns the start state from the maze
	private Coordinate getStartState(char[][] maze) {
		String str=doubleArrayToString(maze);
		int loc=str.indexOf(S);
		int x=loc/maze[0].length;
		int y=loc%maze[0].length;
		return new Coordinate(x,y);
		//return null;
	}

	private Coordinate upperCoor(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		return new Coordinate(x-1,y);
	}

	private Coordinate lowerCoor(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		return new Coordinate(x+1,y);

	}
	private Coordinate leftCoor(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		return new Coordinate(x,y-1);

	}
	private Coordinate rightCoor(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		return new Coordinate(x,y+1);

	}

	private String doubleArrayToString(char[][] arr) {
		String str="";
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr[i].length;j++) {
				str+=arr[i][j];
			}
		}
		return str;
	}




	
	private boolean isEmptyUp(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		if(!isInMaze(new Coordinate(x-1,y))){
			return false;
		}
		if(isExit(new Coordinate(x-1,y))) {
			return true;
		}
		if (currentMaze[x-1][y]==O)
			return true;
		else return false;
	}


	
	private boolean isEmptyDown(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		if(!isInMaze(new Coordinate(x+1,y))){
			return false;
		}
		if(isExit(new Coordinate(x+1,y))) {
			return true;
		}
		if (currentMaze[x+1][y]==O)
			return true;
		else return false;
	}



	private boolean isEmptyLeft(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		if(!isInMaze(new Coordinate(x,y-1))){
			return false;
		}
		if(isExit(new Coordinate(x,y-1))) {
			return true;
		}
		if (currentMaze[x][y-1]==O)
			return true;
		else return false;
	}


	
	
	private boolean isEmptyRight(Coordinate c) {
		int x=c.getX();
		int y=c.getY();
		if(!isInMaze(new Coordinate(x,y+1))){		
			return false;
		}
		if(isExit(new Coordinate(x,y+1))) {
			return true;
		}
		if (currentMaze[x][y+1]==O)
			return true;
		else return false;	
	}
}
