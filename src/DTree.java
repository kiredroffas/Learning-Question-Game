import java.io.File;
import java.util.Scanner;

public class DTree  //Initializes the decision tree 
{
	DTreeNode root; //Root of the tree
	String database; //String to save file path
	Scanner filesc;  //Scanner to read file
	int flag = 0;
	
	public DTree()  //Constructor
	{
		database = "/home/erik/Desktop/Erikpa4/CSE 223 PA4 - Question Game/src/20qq.txt"; //Save file path as string
		try 
		{
			File file = new File(database);  //Make a new file object with string path
			filesc = new Scanner(file);      //Pass file to be read from scanner
		} 
		catch (Exception e)  //Print error message if file can't be opened
		{
			System.out.println("File could not be found");
		}
	}
	
	public DTreeNode treeInit(DTreeNode root) //reads file and creates tree accordingly
	{
		String text = filesc.nextLine(); //Read a line from file into text string
		if(text.equals("A:"))  //If the file text is A: (answer)
		{
			root = new DTreeNode(filesc.nextLine(), 1); //make a new tree node, w/ 1 for type flag
			return(root);
		}
		else if(text.equals("Q:"))  //Else is the file text is Q: (question)
		{
			root = new DTreeNode(filesc.nextLine(), 0); //Make a new node, w/ 0 for type flag
			root.left = treeInit(root.left);   //Recursively call treeInit() to make the rest of the tree
			root.right = treeInit(root.right);
		}
		return(root);
	}
}
