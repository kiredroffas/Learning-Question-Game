
public class DTreeNode 
{
	String data;  //String to hold node data
	DTreeNode left; //YES (left node)
	DTreeNode right;  //NO  (right node)
	int typeFlag;  //0 = question, 1 = answer

	public DTreeNode(String input,int type)  //Constructor
	{
		data = input;    //Initializes string and flag
		typeFlag = type;
		left = null;      //Set left and right nodes to null
		right = null;
	}	
}
