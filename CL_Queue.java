package stackqueue;

public class CL_Queue {
	private class CLNode
	{
		public int item;
	    public CLNode next;

	    //--------------------------------------------------------------
	    //  Sets up the node
	    //--------------------------------------------------------------
	    public CLNode(int newitem)
	    {
	    	item = newitem;
	    	next = this;
	    }
	}
	private CLNode qRear;
	
	public CL_Queue() {
		qRear = null;
	}
	
	public boolean isEmpty() {
		return qRear == null;
	}
	
	public void enqueue(int qitem) {
		CLNode newNode = new CLNode(qitem);
		if (qRear == null)
			qRear = newNode;
		else {
			newNode.next = qRear.next;
			qRear.next = newNode;
			qRear = newNode;
		}
	}	
	
	public int dequeue() throws Exception {		
		int dqitem;
		if (isEmpty())
			throw new Exception("Queue is empty -- cannot remove an element.");
		else {
			if (qRear.next == qRear) {
				dqitem = qRear.item;
				qRear = null;				;				
			} 
			else {
				dqitem = qRear.next.item;
				qRear.next = qRear.next.next;
			}
			return dqitem;
		}
	}	
	
	public void makeEmpty() {
		qRear = null;
	}
	
}
