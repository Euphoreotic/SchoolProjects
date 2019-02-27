package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
  */
public abstract class InnerThreaded extends ThreadedFastener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224966169138332738L;

	/**
	 * @param material
	 * @param finish
	 * @param unitPrice
	 * @param numberPerUnit
	 * @param diameter
	 * @throws IllegalFastener
	 */
	public InnerThreaded(String material, String finish, double unitPrice, int numberPerUnit, String diameter)
			throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit, diameter);
		
		if ("steel".equalsIgnoreCase(material.toLowerCase())) {
			if (finish == null ||!stealFinishes.contains(finish.toLowerCase())) {
				throw new IllegalFastener("Wrong Finish for Steel inner threaded fastener is provided");
			}
		} 
	}
	
}
