package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   

 */
public class CarriageBolt extends Bolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159989651982426445L;

	public CarriageBolt(double length, String diameter, String material, String finish, double unitPrice,
			int numberPerUnit) throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit, diameter, length);
		// TODO validation
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Carriage Bolt " + super.toString();
	}
}
