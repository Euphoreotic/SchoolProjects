package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
 
 */
public class CommonNail extends Nail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6720006897735942665L;

	/**
	 * @param material
	 * @param finish
	 * @param unitPrice
	 * @param numberPerUnit
	 * @param size
	 * @param length
	 * @param gauge
	 * @throws IllegalFastener
	 */
	
	public CommonNail(String size, double length, double gauge, String finish, double unitPrice, int numberPerUnit)
			throws IllegalFastener {
		super("steel", finish, unitPrice, numberPerUnit, size, length, gauge);
		// TODO Auto-generated constructor stub

		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Common Nail, " + super.toString();
	}

}
