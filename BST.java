package bst;

import stackqueue.CL_Queue;

public class BST {

	private class TNode
	{
		public int item;
	    public TNode left;
	    public TNode right;

	    //--------------------------------------------------------------
	    //  Sets up the node
	    //--------------------------------------------------------------
	    public TNode(int newitem)
	    {
	    	item = newitem;
	    	left = null;
	    	right = null;
	    }
	}
	private TNode root;
	CL_Queue travQueue;	
	
	public BST() {
		root = null;	
		travQueue=new CL_Queue();
	}
	
	public BST(TNode cpNode) {
		root = cpNode;	
		travQueue=new CL_Queue();
	}
	
	private int countNodes(TNode curnode) {
		  if (curnode == null)
		    return 0;
		  else 
		    return countNodes(curnode.left) + countNodes(curnode.right) + 1;
	}

	public int getLength()
	{
		return countNodes(root);
	}

	public boolean isEmpty()
	{
		return root == null;
	}
		  
	private TNode insert (TNode curnode, int newitem) {
	  if (curnode==null) {
	    curnode = new TNode(newitem);
	    return curnode;
	  } else if (newitem<curnode.item)
	    curnode.left = insert(curnode.left,newitem);
	  else
	    curnode.right = insert(curnode.right,newitem);
	  return curnode;
	}
	
	public void putItem(int newitem) {
		root = insert(root,newitem);
	}  
		  
	private int findItem(TNode curnode, int gitem) {
	  if (curnode == null)
	    return -1;
	  else if (gitem == curnode.item)
	    return curnode.item;
	  else if (gitem < curnode.item)
	    return findItem(curnode.left,gitem);
	  else
	    return findItem(curnode.right,gitem);
	}

	public int getItem(int gitem) {
	  return findItem(root, gitem);
	}
	
	private TNode deleteNode(TNode delnode, int ditem) {
		  if (delnode.left == null) {
		    return delnode.right;
		  } else if (delnode.right == null) {
		    return delnode.left;
		  } else {
		    TNode predecessor = delnode.left;
		    while (predecessor.right != null) {	
		      predecessor = predecessor.right;
		    }
		    int replaceitem = predecessor.item;
		    delnode.item = replaceitem;
		    delnode.left = delete(delnode.left, replaceitem);
		    return delnode;
		  }     
	}

	private TNode delete(TNode curnode, int ditem) {
	  if (ditem < curnode.item)
	    curnode.left = delete(curnode.left, ditem);
	  else if (ditem > curnode.item)
	    curnode.right = delete(curnode.right, ditem);
	  else
	    curnode = deleteNode(curnode, ditem);
	  return curnode;
	}   

	public void deleteItem(int ditem) {
	  delete(root,ditem);
	}

	public void printNodes(TNode curnode) {
	  if (curnode != null) {
	    printNodes(curnode.left);
	    System.out.print(curnode.item + ", ");
	    printNodes(curnode.right);
	  }
	}

	public void printTree() {
	  if (isEmpty())
	    System.out.println("(Empty Tree)");
	  else {
	    printNodes(root);
	    System.out.println("\b\b   ");
	  }
	}

	private TNode copyNodes(TNode origNode) {	  
	  if (origNode == null)
	    return null;
	  else {
	    TNode copyNode = new TNode(origNode.item);
	    copyNode.left = copyNodes(origNode.left);
	    copyNode.right = copyNodes(origNode.right);
	    return copyNode; 
	  }
	}

	public BST copy() {		
		TNode newroot = copyNodes(root);
		BST cpBSTE = new BST(newroot);
		return cpBSTE;
	}
		
	public boolean travEmpty() {
		return travQueue.isEmpty();	
	}

	private void preNodes(TNode curNode) {    
	  if (curNode != null) {
	    travQueue.enqueue(curNode.item);		    
		preNodes(curNode.left);    
	    preNodes(curNode.right);
	  }
	}

	private void inNodes(TNode curNode) {    
	  if (curNode != null) {
		inNodes(curNode.left);
		travQueue.enqueue(curNode.item);		   
	    inNodes(curNode.right);
	  }
	}
	
	private void postNodes(TNode curNode) {    
	  if (curNode != null) {
		postNodes(curNode.left);
	    postNodes(curNode.right);
		travQueue.enqueue(curNode.item);		   
	  }
	}
	
	//  Part 2: Reverse Pre-order traversal 
	private void revPreNodes(TNode curNode) {
		if (curNode != null) {
			travQueue.enqueue(curNode.item);
			revPreNodes(curNode.right);
			revPreNodes(curNode.left);
		}
	}
	
	public int getNextItem() throws Exception {
		if (travQueue.isEmpty())
		  return Integer.MIN_VALUE;
		else
		  return travQueue.dequeue();
	}

	public void resetTree(String order) {
	  travQueue.makeEmpty();  
	  switch (order) {
	    case "PRE" : preNodes(root);
	    	break;
	    case "IN" : inNodes(root);
    		break;
	    case "POST" : postNodes(root);
    		break;
	    case "RevPre" : revPreNodes(root); // NEW
      }
	}

	//  Part 1: Get height
	
	public int getHeight() {
		return heightHelper(root);
	}

	private int heightHelper(TNode node) {
		if (node == null)
			return -1;
		int leftHeight = heightHelper(node.left);
		int rightHeight = heightHelper(node.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	void sbHelp(int[] orderedArray, int lindex, int rindex) {
		if (rindex - lindex >= 0) {
			int midpoint = (rindex + lindex)/2;
			putItem(orderedArray[midpoint]);
			sbHelp(orderedArray,lindex,midpoint-1);
			sbHelp(orderedArray,midpoint+1,rindex);
		}	
	}
	
	void splitBalance() throws Exception {
		int treeSize = getLength(); 
		int[] orderedArray = new int[treeSize];
		resetTree("IN");
		for (int i = 0; i < treeSize; i++)
			orderedArray[i] = getNextItem();
		root = null;
		sbHelp(orderedArray,0,treeSize-1);	
	}
		
}
