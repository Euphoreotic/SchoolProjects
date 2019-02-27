package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
  */
public class WingNut extends InnerThreaded {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6934100690437996178L;

	/**
	 * @param material
	 * @param finish
	 * @param unitPrice
	 * @param numberPerUnit
	 * @param diameter
	 * @throws IllegalFastener
	 */
	
	public WingNut(String diameter, String material, String finish, double unitPrice, int numberPerUnit)
			throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit, diameter);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Wing Nut " + super.toString();
	}

}
