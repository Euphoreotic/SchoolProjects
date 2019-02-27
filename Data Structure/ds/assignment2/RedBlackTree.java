package ds.assignment2;
/* CISC-235 Assignment 2 
 * by Tong Chen, 10189689, 14tc41
 * This class Implement a Red-Black tree:
 *        - Additional attribute color to store the vertex's colour 
 */
import java.util.ArrayList;

public class RedBlackTree {
	BinaryTreeVertex root;

	/**
	 * @return the root
	 */
	private BinaryTreeVertex getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	private void setRoot(BinaryTreeVertex root) {
		this.root = root;
	}

	/**
	 * @param aValue
	 * @return
	 */
	public void insert(int aValue) {
		// function for inserting a new node and marked as black
		if (getRoot() == null) {
			BinaryTreeVertex newT = new BinaryTreeVertex(aValue);
			newT.setDepth(1);
			newT.getLeft().setDepth(newT.getDepth() + 1);
			newT.getRight().setDepth(newT.getDepth() + 1);
			newT.setColour('B');
			// aTree = new RBTree();
			setRoot(newT);
			return ;
		}

		setRoot(recInsert(getRoot(), aValue));
		getRoot().setColour('B');
		return ;

	}

	/**
	 * @param v
	 * @param x
	 * @return
	 */
	private BinaryTreeVertex recInsert(BinaryTreeVertex v, int x) {

		if (v.isALeaf()) {
			BinaryTreeVertex newV = new BinaryTreeVertex(x);
			newV.setDepth(v.getDepth());
			newV.getLeft().setDepth(newV.getDepth() + 1);
			newV.getRight().setDepth(newV.getDepth() + 1);
			return newV;

		} else {
			// insert into left side
			if (v.getValue() > x) {
				v.setLeft(recInsert(v.getLeft(), x));
				if (v.getLeft().getColour() == 'B') {
					// there is no problem here, officer
					return v;
				} else {
					// v's left child is Red, so there may be a
					// problem. Check the grandchildren - we know they
					// exist because a Red vertex must have two children
					if (v.getLeft().getLeft().getColour() == 'R' || v.getLeft().getRight().getColour() == 'R') {
						// PROBLEM!
						// Now we identify the problem case
						if (v.getLeft().getLeft().getColour() == 'R') {
							return fixLeftLeft(v);
						} else {
							return fixLeftRight(v);
							// Note: we handle Case 1 inside these ¡°fix¡±
							// methods.
						}
					} else {
						// no problem after all
						return v;
					}
				}
			} else if (v.getValue() < x) {
				// insert into right side
				v.setRight(recInsert(v.getRight(), x));
				// now check for problems
				// we check to see if v needs to play the grandparent role
				// and fix a Red-Red problem between its child and
				// grandchild
				// Since we recursed down to the right from v, we only
				// need to look at its right subtree
				if (v.getRight().getColour() == 'B') {
					// It¡¯s all good. Nothing to see here folks.
					return v;
				} else {
					// v's right child is Red, so there may be a
					// problem. Check the grandchildren - we know they
					// exist because a Red vertex must have two children
					if (v.getRight().getLeft().getColour() == 'R' || v.getRight().getRight().getColour() == 'R') {
						// OH NO!
						// Now we identify the problem case
						if (v.getRight().getLeft().getColour() == 'R') {
							return fixRightLeft(v);
						} else {
							return fixRightRight(v);
							// Note: we handle Case 1 inside these ¡°fix¡±
							// methods.
						}
					} else {
						// false alarm
						return v;
					}
				}
			} else
				return v;
		}
		// return null;

	}

	/**
	 * @param gp
	 * @return
	 */
	private BinaryTreeVertex fixLeftLeft(BinaryTreeVertex gp) {

		// GP's left child is Red, and that child's left child is
		// also Red
		BinaryTreeVertex p = gp.getLeft();
		BinaryTreeVertex s = gp.getRight();
		if (s.getColour() == 'R') {
			// Case 1 applies: no rotation needed
			// Just recolour and return
			p.setColour('B');
			s.setColour('B');
			gp.setColour('R');
			return gp;
		} else {
			// S.colour == Black, so we need to do a single rotation
			// fix the pointers
			p.setDepth(gp.getDepth());
			gp.setLeft(p.getRight());
			p.setRight(gp);
			// fix the colours
			p.setColour('B');
			gp.setColour('R');

			resetPath(p);
			// return the new root of this subtree
			return p;
		}
	}

	/**
	 * @param gp
	 * @return
	 */
	private BinaryTreeVertex fixLeftRight(BinaryTreeVertex gp) {

		// GP's left child is Red, and that child's right child is
		// Red
		BinaryTreeVertex p = gp.getLeft();
		BinaryTreeVertex s = gp.getRight();

		if (s.getColour() == 'R') {
			// Case 1 applies: no rotation neede
			// Just recolour and return
			p.setColour('B');
			s.setColour('B');
			gp.setColour('R');
			return gp;
		} else {
			// S.colour == Black, so we need to do a double rotation
			// fix the pointers
			BinaryTreeVertex c = p.getRight();
			p.setRight(c.getLeft());
			gp.setLeft(c.getRight());
			c.setLeft(p);
			c.setRight(gp);
			c.setDepth(gp.getDepth());
			// fix the colours
			c.setColour('B');
			gp.setColour('R');
			resetPath(c);
			// return the new root of this subtree
			return c;
		}
	}

	/**
	 * @param gp
	 * @return
	 */
	private BinaryTreeVertex fixRightRight(BinaryTreeVertex gp) {
		BinaryTreeVertex p = gp.getRight();
		BinaryTreeVertex s = gp.getLeft();
		if (s.getColour() == 'R') {
			// Case 1 applies: no rotation needed
			// Just recolour and return
			p.setColour('B');
			s.setColour('B');
			gp.setColour('R');
			return gp;
		} else {
			// S.colour == Black, so we need to do a single rotation
			// fix the pointers
			p.setDepth(gp.getDepth());
			gp.setRight(p.getLeft());
			p.setLeft(gp);
			// fix the colours
			p.setColour('B');
			gp.setColour('R');

			resetPath(p);
			// return the new root of this subtree
			return p;
		}
	}

	/**
	 * @param gp
	 * @return
	 */
	private BinaryTreeVertex fixRightLeft(BinaryTreeVertex gp) {

		BinaryTreeVertex p = gp.getRight();
		BinaryTreeVertex s = gp.getLeft();

		if (s.getColour() == 'R') {
			// Case 1 applies: no rotation neede
			// Just recolour and return
			p.setColour('B');
			s.setColour('B');
			gp.setColour('R');
			return gp;
		} else {
			// S.colour == Black, so we need to do a double rotation
			// fix the pointers
			BinaryTreeVertex c = p.getLeft();
			p.setLeft(c.getRight());
			gp.setRight(c.getLeft());
			c.setRight(p);
			c.setLeft(gp);
			c.setDepth(gp.getDepth());
			// fix the colours
			c.setColour('B');
			gp.setColour('R');
			resetPath(c);
			// return the new root of this subtree
			return c;
		}
	}

	/**
	 * @param v
	 */
	private void resetPath(BinaryTreeVertex v) {
		if (v == null)
			return;

		v.getLeft().setDepth(v.getDepth() + 1);
		v.getRight().setDepth(v.getDepth() + 1);
		if (!v.getLeft().isALeaf())
			resetPath(v.getLeft());
		if (!v.getRight().isALeaf())
			resetPath(v.getRight());
	}

	/**
	 * @param aValue
	 * @return
	 */
	public int[] SearchPath(int aValue) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		BinaryTreeVertex current = root;
		System.out.println("Searching Path: ");
		while (current != null && current.getValue() != aValue) {
			path.add(current.getValue());
			System.out.println(current.getValue() + " color: " + current.getColour());
			if (current.getValue() < aValue) {
				if (!current.getRight().isALeaf())
					current = current.getRight();
				else
					return new int[0];
			} else {
				if (!current.getLeft().isALeaf())
					current = current.getLeft();
				else
					return new int[0];
			}

		}
		path.add(current.getValue());
		System.out.println(current.getValue() + " color: " + current.getColour());
		return path.stream().mapToInt(i -> i).toArray();
	}

	/**
	 * @return
	 */
	public int Total_Depth() {
		if (root == null)
			return 0;
		return getTotalPath(root);
	}

	/**
	 * @param v
	 * @return
	 */
	private int getTotalPath(BinaryTreeVertex v) {
		int tDepth = 0;

		tDepth = tDepth + v.getDepth();
		if (!v.getRight().isALeaf())
			tDepth += getTotalPath(v.getRight());

		if (!v.getLeft().isALeaf())
			tDepth += getTotalPath(v.getLeft());

		return tDepth;
	}

	/**
	 * @return
	 */
	public int Max_Depth() {
		if (root == null)
			return 0;

		return getMaxDepth(root);
	}

	/**
	 * @param v
	 * @return
	 */
	private int getMaxDepth(BinaryTreeVertex v) {

		if (v.getLeft().isALeaf() && v.getRight().isALeaf())
			return v.getDepth();

		if (v.getRight().isALeaf())
			return getMaxDepth(v.getLeft());

		if (v.getLeft().isALeaf())
			return getMaxDepth(v.getRight());

		int lMax = getMaxDepth(v.getLeft());
		int rMax = getMaxDepth(v.getRight());

		return lMax >= rMax ? lMax : rMax;

	}

	public static void main(String args[]) {
		//testing purpose
		RedBlackTree aTree = new RedBlackTree();

		aTree.insert(6);
		aTree.insert(10);
		aTree.insert(8);
		aTree.insert(20);
		aTree.insert(3);
		aTree.insert(38);
		aTree.insert(40);
		aTree.insert(40);
		aTree.insert(40);
		aTree.insert(42);
		aTree.insert(36);
		aTree.insert(44);
		aTree.insert(34);
		aTree.insert(50);

		System.out.println("Max depth: " + aTree.Max_Depth());
		System.out.println("Total depth: " + aTree.Total_Depth());

		int[] path = aTree.SearchPath(34);

		for (int i : path) {
			System.out.println(i);
		}

	}
}
