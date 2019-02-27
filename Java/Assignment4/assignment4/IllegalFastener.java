package assignment4;

/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
 * This is exception that capture the error
 */
public class IllegalFastener extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619862404795027167L;

	/**
	 * @param message
	 */
	public IllegalFastener(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
