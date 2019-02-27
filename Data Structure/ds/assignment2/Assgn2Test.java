package ds.assignment2;
/* CISC-235 Assignment 2 
 * by Tong Chen, 10189689, 14tc41
 * This class implements the testcase if this assignment, and it provides 
 *   -- the flexibility that number of permutations can be easisy ajusted by set values if array of NUM_OF_PERMUATIONS
 *   -- Friendly layout of the testing result
 *         
 */
import java.text.DecimalFormat;
import java.util.Random;

public class Assgn2Test {
	private static final int[] NUM_OF_PERMUATIONS = { 10, 50, 100, 1000, 2000, 4000, 8000, 16000 };
	private static final int TRAILS = 500;

	private static Random r = new Random();
	private static DecimalFormat df2 = new DecimalFormat(".##");

	public static void main(String[] args) {

		for (int pt : NUM_OF_PERMUATIONS) {
			double bstTotalDepth = 0;
			double rbtTotalDepth = 0;
			double bstMaxDepth = 0;
			double rbtMaxDepth = 0;

			double rtLT05 = 0;
			double rtLT075 = 0;
			double rtLT125 = 0;
			double rtLT15 = 0;
			double rtGT15 = 0;

			double mtLT05 = 0;
			double mtLT075 = 0;
			double mtLT125 = 0;
			double mtLT15 = 0;
			double mtGT15 = 0;
			System.out.println(TRAILS + " trails for " + pt + " permutation tree starts...");
			for (int eachTrail = 0; eachTrail < TRAILS; eachTrail++) {
				int[] permutation = genPermutation(pt);

				BinarySearchTree bst = genBST(permutation);
				RedBlackTree rbt = genRBT(permutation);

				bstTotalDepth = bst.Total_Depth();
				rbtTotalDepth = rbt.Total_Depth();

				double rt = bstTotalDepth / rbtTotalDepth;
				rt = Double.valueOf(df2.format(rt));

				if (rt < 0.5) {
					rtLT05++;
				} else if (rt < 0.75) {
					rtLT075++;
				} else if (rt <= 1.25) {
					rtLT125++;
				} else if (rt <= 1.5) {
					rtLT15++;
				} else {
					rtGT15++;
				}

				bstMaxDepth = bst.Max_Depth();
				rbtMaxDepth = rbt.Max_Depth();

				// System.out.println("bmp: " + bstMaxDepth + " | rmp: " + rbtMaxDepth);
				double mt = bstMaxDepth / rbtMaxDepth;
				mt = Double.valueOf(df2.format(mt));

				if (mt < 0.5) {
					mtLT05++;
				} else if (mt < 0.75) {
					mtLT075++;
				} else if (mt <= 1.25) {
					mtLT125++;
				} else if (rt <= 1.5) {
					mtLT15++;
				} else {
					mtGT15++;
				}

			}

			System.out.println("| RT < 0.5 | 0.5 <= RT < 0.75 | 0.75 <= RT <= 1.25 | 1.25 < RT <= 1.5 | RT > 1.5 |");
			System.out.println("|" + String.format("%10s", Double.valueOf(df2.format(rtLT05 / (TRAILS / 100))) + "% ")
					+ "|" + String.format("%18s", Double.valueOf(df2.format(rtLT075 / (TRAILS / 100))) + "% ") + "|"
					+ String.format("%20s", Double.valueOf(df2.format(rtLT125 / (TRAILS / 100))) + "% ") + "|"
					+ String.format("%18s", Double.valueOf(df2.format(rtLT15 / (TRAILS / 100))) + "% ") + "|"
					+ String.format("%11s", Double.valueOf(df2.format(rtGT15 / (TRAILS / 100))) + "% |"));

			System.out.println("----------------------------------------------------------------------------------");

			System.out.println("| MT < 0.5 | 0.5 <= MT < 0.75 | 0.75 <= MT <= 1.25 | 1.25 < MT <= 1.5 | MT > 1.5 |");
			System.out.println("|" + String.format("%10s", Double.valueOf(df2.format(mtLT05 / (TRAILS / 100))) + "% ")
					+ "|" + String.format("%18s", Double.valueOf(df2.format(mtLT075 / (TRAILS / 100))) + "% ") + "|"
					+ String.format("%20s", Double.valueOf(df2.format(mtLT125 / (TRAILS / 100))) + "% ") + "|"
					+ String.format("%18s", Double.valueOf(df2.format(mtLT15 / (TRAILS / 100))) + "% ") + "|"
					+ String.format("%11s", Double.valueOf(df2.format(mtGT15 / (TRAILS / 100))) + "% |"));

			System.out.println("\n");

		}

	}

	/**
	 * @param pt
	 * @return
	 */
	private static int[] genPermutation(int pt) {
		int[] permuation = new int[pt];

		for (int i = 0; i < pt; i++)
			permuation[i] = getRandomNumberInRange(1, pt);

		return permuation;
	}

	/**
	 * @param permutation
	 * @return
	 */
	private static BinarySearchTree genBST(int[] permutation) {
		BinarySearchTree bst = new BinarySearchTree();

		for (int i = 0; i < permutation.length; i++) {
			bst.insert(permutation[i]);
		}

		return bst;
	}

	/**
	 * @param permutation
	 * @return
	 */
	private static RedBlackTree genRBT(int[] permutation) {
		RedBlackTree rbt = new RedBlackTree();

		for (int i = 0; i < permutation.length; i++) {
			rbt.insert(permutation[i]);
		}

		return rbt;
	}

	/**
	 * @param min
	 * @param max
	 * @return
	 */
	private static int getRandomNumberInRange(int min, int max) {
		return r.nextInt((max - min) + 1) + min;
	}
}
