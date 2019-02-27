package ds.assignment2;
/* CISC-235 Assignment 2 
 * by Tong Chen, 10189689, 14tc41
 * This class Implement a BinaryTreeVertex in which each object has at least these attributes:
		- value: a value
		- left (or left_child): a pointer to another BinaryTreeVertex object
		- right or right_child): a pointer to another BinaryTreeVertex
*  attribute colour is added for RBTree 
 */
public class BinaryTreeVertex {

	private BinaryTreeVertex left;
	private BinaryTreeVertex right;

	private boolean isALeaf = false;
 
	private int value;

	private char colour;

	private int depth;

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * 
	 */
	public BinaryTreeVertex() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 */
	public BinaryTreeVertex(int x) {
		setValue(x);
		setColour('R');
		
		BinaryTreeVertex lV = new BinaryTreeVertex();
		lV.setColour('B');
		lV.setALeaf(true);

		BinaryTreeVertex rV = new BinaryTreeVertex();
		rV.setColour('B');
		rV.setALeaf(true);

		setLeft(lV);
		setRight(rV);
	}

	/**
	 * @return the left
	 */
	public BinaryTreeVertex getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(BinaryTreeVertex left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BinaryTreeVertex getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(BinaryTreeVertex right) {
		this.right = right;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the color
	 */
	public char getColour() {
		return colour;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColour(char color) {
		this.colour = color;
	}

	/**
	 * @return the isALeaf
	 */
	public boolean isALeaf() {
		return isALeaf;
	}

	/**
	 * @param isALeaf
	 *            the isALeaf to set
	 */
	public void setALeaf(boolean isALeaf) {
		this.isALeaf = isALeaf;
	}
}
