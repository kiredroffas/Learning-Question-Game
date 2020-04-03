/*  
	Erik Safford
	Learning Question Game 
	JUNE 2018

	Sample database .txt file:
	------------------------------------------------
	Q:
	Is it an animal
	A:
	Pig
	A:
	Rock
	------------------------------------------------
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class QGame //Main class, it plays the game, checks input,
{   			   //and adjusts tree/prints to file after each game  
	
	Scanner sc;  //Scanners used to read in input from user
	Scanner sc1;
	String input; //String used to save input from user
	PrintWriter outputStream; //PrintWriter used to write into file
	DTree tree;  //DTree class variable, returns root after reading file
	DTreeNode root;  //Root of tree
	
	public QGame()  //Constructor
	{
		tree = new DTree(); //Make new DTree object
		root = tree.treeInit(root);  //Initialize tree and return root
	}
	public static void main(String[] args) //Main
	{
		QGame play = new QGame(); //Make new QGame object
		System.out.println("Welcome to the Question Game!");  //Print welcome message
		System.out.println("");
		
		while(true)  //Infinite loop
		{
			System.out.println("Think of an object, and I will try to guess it!");
			System.out.println("");
			play.playGame(play.root); //Play the game
			play.printInit();  //Initialize new print writer
			play.printFile(play.root); //Print to file
			play.outputStream.close(); //Close printWriter so can use later
			break;
		}
	}
//---------------------METHODS----------------------------------
	
	public void playGame(DTreeNode root)
	{
		sc = new Scanner(System.in);  //Scanners for reading input from user
		sc1 = new Scanner(System.in);
		
		if(root.typeFlag == 0)  //If question
		{
			System.out.println(root.data + "? (y/n)"); //print the question
			input = sc.next();   //Read input
			if(input.equals("y") || input.equals("Y"))  //If user input is y (yes)
			{
				playGame(root.left);  //Traverse to left node 
			}
			else if(input.equals("n") || input.equals("N")) //Else if user input is n (no)
			{
				playGame(root.right); //Traverse to right node
			}
			else  //Else if user types the wrong input
			{
				System.out.println("Invalid input, type 'y' for yes or 'n' for no."); 
				playGame(root);
			}
		}
		else if(root.typeFlag == 1)  //If answer
		{
			System.out.println("Is it a " + root.data + "? (y/n)"); //guess the answer
			input = sc.next();  //Read in user input
			if(input.equals("y") || input.equals("Y"))  //If yes, computer wins 
			{ 
				System.out.println("Yes I win!");
				return;
			}
			else if(input.equals("n") || input.equals("N")) //If no, answer is wrong
			{ 
				insertNew(root); //Get new answer/question and adjust tree
			}
			else //Else if invalid input
			{
				System.out.println("Invalid input, type 'y' for yes or 'n' for no."); //error check
				playGame(root);
			}
		}
		return;
	}
	
	public void insertNew(DTreeNode root) //Insert new Q/A into node and adjust tree
	{
		System.out.println("Dang it you stumped me! What were you thinking of?");
		root.left = new DTreeNode(sc1.nextLine(), 1); //make new answer the new yes (left) node
		System.out.println("");
		System.out.println("Ahhh I see, how about you help me get better at guessing?");
		System.out.println("Please type in a question where the answer 'yes' means " + root.left.data + ",");
		System.out.println("and the answer 'no' means " + root.data + ".");
		DTreeNode temp = new DTreeNode(root.data, root.typeFlag); //save root into temp
		root.data = sc1.nextLine();  //make new question root data
		root.typeFlag = 0;  //set type flag to 0 (question)
		root.right = temp; //make root.right equal the original root
		System.out.println("Thanks! I just got a little smarter...");
		return; 
	}

	public void printInit() //Initializes new printWriter to overwrite file
	{
		try 
		{
			outputStream = new PrintWriter(tree.database);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error, could not open printWriter.");
		}
	}
	
	public void printFile(DTreeNode root)  //prints tree (potentially different) to file
	{
		if (root == null) { //return once root is null
			return;
		}
			if(root.typeFlag == 0) 
			{ //if Question
				outputStream.println("Q:");   
				outputStream.flush();  //flush to update file
			}
			else if(root.typeFlag == 1) 
			{
				outputStream.println("A:"); 
				outputStream.flush();   //flush
			}
			outputStream.println(root.data); //print data to next line
			outputStream.flush();  
			printFile(root.left); //traverse through yes recursively
			printFile(root.right); //traverse through no recursively
			return;
	}
}
