import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The Java program which address the problem of detecting the
 * most and least popular vertices in an undirected graph G.
 * @author benkun.chen
 *
 */
public class myClass {
	public static void main(String[] args){
		Scanner object = new Scanner(System.in);	// create a scanner for user input
		System.out.println("Please enter vertices(positive integer bigger than 0):");
		int input = object.nextInt();	// the user input(number of vertices)
		System.out.println("Please enter the path of CSV text file:");
		Scanner object1 = new Scanner(System.in);	// create a scanner for user input
		String csvFile = object1.nextLine();	// the user input(directory)
		object.close();		// close the scanner
		object1.close();	// close the scanner
		String[] File = csvFile.split("/");	// use comma as separator
		String outFile = "";
		//Calculate the path for output file
		for(int i = 0; i < File.length - 1; i++){
			outFile = outFile + File[i] + "/";
		}
		//Create a 2 dimensional List for storing the adjacency list
		List<List<Integer>> vertice = new ArrayList<List<Integer>>();
		vertice = read(csvFile, input);		// call read function for a returned 2-D list
		System.out.println("\n");
		MPV(vertice);		// call MPV function to get the most popular vertices print out
		System.out.println("\n");
		LPV(vertice);		// call MPV function to get the least popular vertices print out
		//Create a 2 dimensional array for store adjacency matrix
		String[][] table = matrix(csvFile, vertice, input);		// call matrix function and get a 2D array returned
		writeList(vertice, outFile);		// call writeList function
		writeMatrix(table, outFile);		// call writeMatrix function to write matrix into the file
	}
	/**
	 * Part1:
	 * 	Precondition:
	 * 		1. user has input a valid directory for their text file rather than it is going to be caught by the exception.
	 * 		2. user has input a valid vertices which is a positive integer bigger than 0.
	 *
	 * 	Postcondition:
	 * 		1. This method will take the 2 user inputs which are directory of the text file and numbers of vertices. The method
	 * 	starts with initializing a 2 dimensional list for storing the adjacency list, and then, the method reads the text file by
	 * 	using bufferReader line by line and separate the integers between the comma. Then it saves the number into the correct index
	 * 	of the 2 dimensional list and print it out on console. In the end, it returns the 2 dimensional array.
	 * @param csvFile
	 * @param vertices
	 * @return adjacency
	 */
	public static List<List<Integer>> read(String csvFile, int vertices){
		//Create a 2 dimensional List for storing the adjacency list
		List<List<Integer>> adjacency = new ArrayList<List<Integer>>();
		//Create a for loop to initialize the lists
		for(int i = 0; i < vertices; i++){
			adjacency.add(new LinkedList<Integer>());	// initialized a new list
			adjacency.get(i).add(i);	// add indexes "0, 1, 2, 3..."
		}
		BufferedReader br = null;	// initialized bufferedReader
		String csvSplit = ",";		// comma for separating two integers
		String line = "";
		try{
			br = new BufferedReader(new FileReader(csvFile));	// read the file
			System.out.println("\nEdges(as read from a CSV text file):");
			//Create a while loop to go over lines in the file as long as the line does equal to null
			while((line = br.readLine()) != null){
				String[] numbers = line.split(csvSplit);	// use comma as separator
				if(Integer.parseInt(numbers[0]) < vertices && Integer.parseInt(numbers[1]) < vertices){
					List<Integer> a = adjacency.get(Integer.parseInt(numbers[0]));	// locate the index for adding number
					a.add(Integer.parseInt(numbers[1]));	// add number in to the adjacency list
					List<Integer> b = adjacency.get(Integer.parseInt(numbers[1]));	// locate the index for adding number
					b.add(Integer.parseInt(numbers[0]));	// add number in to the adjacency list
					System.out.println(numbers[0] + "," + numbers[1]);		// print out the edges as read from a CSV text file
				}else{
					break;		// if the number in the line is bigger than or equal to the vertices that user inputed, break
				}
			}
		}catch(FileNotFoundException e){		// catch FileNotFoundException
			System.out.println("File not found");		// print out error message
			System.exit(0);
		}catch(IOException e){		// catch IOException
			System.out.println("IOException");		// print out error message
			System.exit(0);
		}finally{
			if(br != null){		// if the file is empty, close the file
				try{
					br.close();		// close the bufferReader
				}catch(IOException e){		// catch IOException
					System.out.println("IOException");		// print out error message
					System.exit(0);
				}
			}
		}
		return adjacency;		// return the 2 dimensional list
	}

	/**
	 * Part2:
	 * 	Precondition:
	 * 		1. 2 dimensional List is a valid 2 dimensional List.
	 *
	 * 	Postcondition:
	 * 		1. This method will take the 2 dimensional list and calculates the most popular vertices. The method will return nothing.
	 * @param vertices
	 */
	public static void MPV(List<List<Integer>> vertices){
		int largest = vertices.get(0).size();		// create a temporary variable to store the largest number
		//Create a for loop to find the number of neighbors for most popular vertices
		for(int i = 0; i < vertices.size(); i++){
			//If the size of the vertices at index i is bigger than the temporary variable, we change the temporary variable to it
			if(vertices.get(i).size() > largest){
				largest = vertices.get(i).size();
			}
		}
		//Print out the most popular vertices
		System.out.println("Number of neighbors for MPV: " + (largest - 1) + "\n");
		//Create a for loop to find the most popular vertices' neighbors
		System.out.println("MPV,Neighbors");
		for(int i = 0; i < vertices.size(); i++){
			if(vertices.get(i).size() == largest){
				for(int j = 0; j < vertices.get(i).size(); j++){
					//If we find the vertices in the list which has the same length, that is the most popular vertices neighbors
					if(j == vertices.get(i).size() - 1){
						System.out.print(vertices.get(i).get(j));		// print out the most popular vertices neighbors
					}else{
						System.out.print(vertices.get(i).get(j) + ",");		// print out the most popular vertices neighbors
					}
				}
				System.out.println();
			}
		}
	}


	/**
	 * Part3:
	 * 	Precondition:
	 * 		1. 2 dimensional List is a valid 2 dimensional List.
	 *
	 * 	Postcondition:
	 * 		1. This method will take the 2 dimensional list and calculates the least popular vertices. The method will return nothing.
	 * @param vertices
	 */
	public static void LPV(List<List<Integer>> vertices){
		int smallest = vertices.get(0).size();		// create a temporary variable to store the smallest number
		// Find the number of neighbors for least popular vertices
		for(int i = 0; i < vertices.size(); i++){
			if(vertices.get(i).size() < smallest){
				smallest = vertices.get(i).size();
			}
		}
		System.out.println("Number of neighbors for LPV: " + (smallest - 1) + "\n");
		// Find the least popular vertices' neighbors
		System.out.println("LPV,Neighbors");
		//If the size of the vertices at index i is smaller than the temporary variable, we change the temporary variable to it
		for(int i = 0; i < vertices.size(); i++){
			if(vertices.get(i).size() == smallest){
				for(int j = 0; j < vertices.get(i).size(); j++){
					//If we find the vertices in the list which has the same length, that is the most popular vertices neighbors
					if(j == vertices.get(i).size() - 1){
						System.out.print(vertices.get(i).get(j));		// print out the most popular vertices neighbors
					}else{
						System.out.print(vertices.get(i).get(j) + ",");		// print out the most popular vertices neighbors
					}
				}
				System.out.println();
			}
		}
	}

	/**
	 * Part4:
	 * 	Precondition:
	 * 		1. 2 dimensional array "table" is a valid array
	 *
	 * 	Postcondition:
	 * 		1. This method will take the 2 dimensional array which been created in the matrix method and print the adjacency matrix
	 *  in a file called "AdjacencyList.CSV". The method returns nothing.
	 * @param vertices
	 */
	public static void writeList(List<List<Integer>> vertices, String outFile){
		PrintWriter wr = null;
		try{
			wr = new PrintWriter(outFile + "AdjacencyList.CSV", "UTF-8");		// create file
			//Create a nested loop to write adjacency list in the file
			for(int i = 0; i < vertices.size(); i++){
				for(int j = 0; j < vertices.get(i).size(); j++){
					wr.print(vertices.get(i).get(j));
					if(j != vertices.get(i).size() - 1){
						wr.print(',');
					}
				}
				wr.println();
			}
			wr.close();		// close the writer
		}catch (IOException e){		// catch IOException
			System.out.println("IOException");		// print out error message
			System.exit(0);
		}
	}

	/**
	 * Part5:
	 * 	Precondition:
	 * 		1. user has input a valid directory for their text file rather than it is going to be caught by the exception.
	 * 		2. 2 dimensional List is a valid list
	 * 		3. user has input a valid vertices which is a positive integer bigger than 0.
	 *
	 * Postcondition:
	 * 		1. This method will take the 2 user inputs and 2 dimensional array which are directory of the text file and numbers of
	 * 	vertices and 2-D List. The method starts with initializing a 2 dimensional string array for storing the adjacency matrix, and
	 *  then, the method reads the text file by using bufferReader line by line and separate the integers between the comma. It writes
	 *  1 into the index where the two number table[i][j] is, then the rest part of the 2-D array would be 0. In the end, it returns the
	 *  2 dimensional string array.
	 * @param csvFile
	 * @param vertices
	 * @param input
	 * @return table
	 */
	public static String[][] matrix(String csvFile, List<List<Integer>> vertices, int input){
		int rowAndColum = vertices.size() + 1;		//calculates the rows and columns
		//Create a 2 dimensional array for adjacency matrix
		String[][] table = new String[rowAndColum][rowAndColum];
		table[0][0] = "x";		// the top left hand side of the matrix is "x" as it says from assignment
		//Create a for loop to initialize the index for row and column from 0 to the length of user input minus 1
		for(int i = 0; i < table.length - 1; i++){
			table[0][i + 1] = Integer.toString(i);
			table[i + 1][0] = Integer.toString(i);
		}
		BufferedReader br = null;	// initialized bufferedReader
		String csvSplit = ",";		// use comma in order to separate the integers in the file
		String line = "";
		try{
			br = new BufferedReader(new FileReader(csvFile));	// initialize the bufferReader
			//Create a while loop to go over lines in the file as long as the line does equal to null
			while((line = br.readLine()) != null){
				String[] numbers = line.split(csvSplit);	// use comma as separator
				if(Integer.parseInt(numbers[0]) < input && Integer.parseInt(numbers[1]) < input){
					table[Integer.parseInt(numbers[0]) + 1][Integer.parseInt(numbers[1]) + 1] = "1";
					table[Integer.parseInt(numbers[1]) + 1][Integer.parseInt(numbers[0]) + 1] = "1";
				}else{
					break;		// if the number in the line is bigger than or equal to the vertices that user inputed, break
				}
			}
		}catch(FileNotFoundException e){		// catch FileNotFoundException
			System.out.println("File not found");		// print out error message
			System.exit(0);
		}catch(IOException e){		// catch IOException
			System.out.println("IOException");		// print out error message
			System.exit(0);
		}finally{
			if(br != null){		// if the file is empty, close the file
				try{
					br.close();		// close buffer reader
				}catch(IOException e){		// catch IOException
					System.out.println("IOException");		// print out error message
					System.exit(0);
				}
			}
		}
		//After we have matrix with 1s, now we add zeros in the rest part of the matrix
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j ++){
				//If the index of table[i][j] is null, that means it is 0
				if(table[i][j] == null){
					table[i][j] = "0";
				}
			}
		}
		return table;		// return the 2 dimensional array
	}

	/**
	 * Part6:
	 * 	Precondition:
	 * 		1. 2 dimensional array "table" is a valid array
	 *
	 * Postcondition:
	 * 		1. This method will take the 2 dimensional array which been created in the matrix
	 * 	method and print the adjacency matrix in a file called "AdjacencyMatrix.CSV". The method
	 * 	returns nothing.
	 * @param table
	 */
	public static void writeMatrix(String[][] table, String outFile){
		PrintWriter wr = null;
		try{
			wr = new PrintWriter(outFile + "AdjacencyMatrix.CSV", "UTF-8");		// create a file
			//Create a nested loop to write adjacency matrix in the file
			for(int i = 0; i < table.length; i++){
				for(int j = 0; j < table[i].length; j ++){
					if(j == table[i].length - 1){
						wr.print(table[i][j]);
					}else{
						wr.print(table[i][j] + ",");
					}
				}
				wr.println();
			}
			wr.close();		// close the writer
		}catch (IOException e){		// catch IOException
			System.out.println("IOException");		// print out error message
			System.exit(0);
		}
	}
}
