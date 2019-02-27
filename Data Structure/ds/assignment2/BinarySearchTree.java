package ds.assignment2;
/* CISC-235 Assignment 2 
 * by Tong Chen, 10189689, 14tc41
 * This class Implement a BinarySearchTree class in which each object has at least this attribute:
 *        - root: a pointer to a BinaryTreeVertex object
 */
import java.util.ArrayList;

public class BinarySearchTree {
	BinaryTreeVertex root;

	/**
	 * @return the root
	 */
	private BinaryTreeVertex getRoot() {
		return root;
	}

	
	/**
	 * @param root
	 */
	private void setRoot(BinaryTreeVertex root) {
		this.root = root;
	}

	// function for inserting a new node
	/**
	 * @param aValue
	 * @return
	 */
	public void insert(int aValue) {
		if (root == null) {
			BinaryTreeVertex v = new BinaryTreeVertex();
			v.setValue(aValue);
			v.setDepth(1);
			setRoot(v);
			return;
		} else {
			insert(aValue, getRoot());
			return;
		}
	}

	// secondary recursive function for node insertion
	/**
	 * @param aValue
	 * @param aTree
	 * @return
	 */
	private void insert(int aValue, BinaryTreeVertex aTree) {

		BinaryTreeVertex current = aTree;
		//insert aValue into right side of current aTree
		if (aValue > current.getValue()) {
			if (current.getRight() == null) {
				BinaryTreeVertex v = new BinaryTreeVertex();
				v.setValue(aValue);
				v.setDepth(current.getDepth() + 1);
				current.setRight(v);
				return;
			} else {
				insert(aValue, current.getRight());
			}

		} else if (aValue < current.getValue()) {
			//insert aValue into left side of current aTree
			if (current.getLeft() == null) {
				BinaryTreeVertex v = new BinaryTreeVertex();
				v.setValue(aValue);
				v.setDepth(current.getDepth() + 1);
				current.setLeft(v);
				return;
			} else {
				insert(aValue, current.getLeft());
			}
		}
		// if aValue == current aTree value, do nothing

		return;
	}

	/**
	 * @param aValue to be searched
	 * @return  a list of all the values visited on the search path
	 */
	public int[] SearchPath(int aValue) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		BinaryTreeVertex current = root;
		while (current != null && current.getValue() != aValue) {
			path.add(current.getValue());
			if (current.getValue() < aValue) {
				//try right side
				if (current.getRight() != null)
					current = current.getRight();
				else
					return new int[0];
			} else {
				//try left side
				if (current.getLeft() != null)
					current = current.getLeft();
				else
					return new int[0];
			}

		}
		path.add(current.getValue());
		
		return path.stream().mapToInt(i -> i).toArray();
	}

	/**
	 * @return depth of the tree
	 */
	public int Total_Depth() {
		if (root == null)
			return 0;
		return getTotalPath(root);
	}

	/**
	 * @param v
	 * @return the depth of from the vertex v
	 */
	private int getTotalPath(BinaryTreeVertex v) {
		int tDepth = 0;

		tDepth = tDepth + v.getDepth();
		if (v.getRight() != null)
			tDepth += getTotalPath(v.getRight());

		if (v.getLeft() != null)
			tDepth += getTotalPath(v.getLeft());

		return tDepth;
	}

	/**
	 * @return max depth of the tree
	 */
	public int Max_Depth() {
		
		//tree is empty
		if (root == null)
			return 0;

		//only one node
		if (root.getLeft() == null && root.getRight() == null)
			return 1;

		//get max depth
		return getMaxDepth(root);
	}

	/**
	 * @param v
	 * @return 
	 */
	private int getMaxDepth(BinaryTreeVertex v) {

		//return current v depth
		if (v.getLeft() == null && v.getRight() == null)
			return v.getDepth();

		//return the depth of left side of v if no right side is empty
		if (v.getRight() == null)
			return getMaxDepth(v.getLeft());

		//return the depth of right side of v if no left side is empty
		if (v.getLeft() == null)
			return getMaxDepth(v.getRight());

		//return the max depth of both side
		int lMax = getMaxDepth(v.getLeft());
		int rMax = getMaxDepth(v.getRight());

		return lMax >= rMax ? lMax : rMax;

	}

	public static void main(String args[]) {
		//for testing purpose
		BinarySearchTree aTree = new BinarySearchTree();

		aTree.insert(6);
		aTree.insert(10);
		aTree.insert(3);
		aTree.insert(8);
		aTree.insert(20);
		aTree.insert(7);
		aTree.insert(22);
		aTree.insert(2); 
		aTree.insert(5);
		aTree.insert(1);
		
		System.out.println("Max depth: " + aTree.Max_Depth());
		System.out.println("Total depth: " + aTree.Total_Depth());

		int[] path = aTree.SearchPath(7);

		for (int i : path) {
			System.out.println(i);
		}

	}
}
