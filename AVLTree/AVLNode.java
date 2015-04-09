package BasicImplementations;

public class AVLNode {

	private Object value;
	private AVLNode left;
	private AVLNode right;
	private int leftHeight;
	private int rightHeight;

	// Constructors
	public AVLNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.leftHeight = -1;
		this.rightHeight = -1;
	}

	public AVLNode(Object element) {
		this.value = element;
		this.left = null;
		this.right = null;
		this.leftHeight = -1;
		this.rightHeight = -1;
	}

	// Methods
	public Object getValue() {
		return this.value;
	}

	public void setValue(Object element) {
		this.value = element;
	}

	public AVLNode getLeft() {
		return this.left;
	}

	public void setLeft(AVLNode newNode) {
		this.left = newNode;
		if(newNode != null)
			this.setLeftHeight(newNode.getRightHeight()+1);
		else
			this.setLeftHeight(-1);
		
	}
	
	public void setLeftByValue (Object element){
		AVLNode temp = new AVLNode (element);
		this.setLeft(temp);
	}
	
	public void setRightByValue (Object element){
		AVLNode temp = new AVLNode (element);
		this.setRight(temp);
	}

	public AVLNode getRight() {
		return this.right;
	}

	public void setRight(AVLNode newNode) {
		this.right = newNode;
		if(newNode != null)
			this.setRightHeight(newNode.getRightHeight()+1);
		else
			this.setRightHeight(-1);

	}

	public int getLeftHeight() {
		return this.leftHeight;
	}

	public int getRightHeight() {
		return this.rightHeight;

	}

	public void incLeftHeight() {
		this.leftHeight = this.leftHeight + 1;
	}

	public void incRightHeight() {
		this.rightHeight = this.rightHeight + 1;
	}

	public void decLeftHeight() {
		this.leftHeight = this.leftHeight - 1;
	}

	public void decRightHeight() {
		this.rightHeight = this.rightHeight - 1;
	}
	
	public void setLeftHeight (int value){
		this.leftHeight = value;
	}
	
	public void setRightHeight(int value){
		this.rightHeight = value;
	}

}
