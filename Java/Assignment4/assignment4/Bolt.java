package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
 */
public abstract class Bolt extends OuterThreaded {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3916767126306897884L;

	public Bolt(String material, String finish, double unitPrice, int numberPerUnit, String diameter, double length)
			throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit, diameter, length);
		
		if ("steel".equalsIgnoreCase(material.toLowerCase())) {
			if (finish == null || (!stealFinishes.contains(finish.toLowerCase()))) {
				throw new IllegalFastener("Wrong Finish for Steel Bolt is provided");
			}
		} 
	}

	

}
