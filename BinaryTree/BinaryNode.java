package BasicImplementations;

public class BinaryNode {

	private Object value;
	private BinaryNode left;
	private BinaryNode right;

	// Default Constructor
	public BinaryNode() {
		this.value = null;
		this.left = null;
		this.right = null;
	}

	// Constructor by value
	public BinaryNode(Object element) {
		this.value = element;
		this.left = null;
		this.right = null;
	}

	// Constructor by value and two tree nodes
	public BinaryNode(Object element, BinaryNode left, BinaryNode right) {
		this.value = element;
		this.left = left;
		this.right = right;
	}

	// getters and setters
	public Object getValue() {
		return this.value;
	}

	public BinaryNode getRight() {
		return this.right;
	}

	public BinaryNode getLeft() {
		return this.left;
	}

	public void setValue(Object newVal) {
		this.value = newVal;
	}

	public void setRight(BinaryNode newRight) {
		this.right = newRight;
	}

	public void setRightByValue(Object value) {
		BinaryNode node = new BinaryNode(value);
		this.setRight(node);
	}

	public void setLeft(BinaryNode newLeft) {
		this.left = newLeft;
	}

	public void setLeftByValue(Object value) {
		BinaryNode node = new BinaryNode(value);
		this.setLeft(node);
	}
}
