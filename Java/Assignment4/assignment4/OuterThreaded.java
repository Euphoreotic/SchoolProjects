package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
 */
public abstract class OuterThreaded extends ThreadedFastener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4370462049305420643L;
	private double length;

	public OuterThreaded(String material, String finish, double unitPrice, int numberPerUnit, String diameter,
			double length) throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit, diameter);
		
		if (!isValidLength(length)) {
			throw new IllegalFastener("Wrong length for Screws or Bolts is provided");
		}
		this.length = length;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return length + "\" long, " + super.toString();
	}
	
	private boolean isValidLength(double len) {
		boolean valid = true;
		 
		if (len < 0.5 || len >20) {
			
			return false;
		}
		
		//Lengths for Bolts and Screws: 1/2" to 6" in units of 1/4"
		if (len >= 0.5 && len < 6) {
			if (((len -0.5)*100) % 25 !=0)
				return false;
		}
		
		//6" to 11" in units of 1/2", 
		if (len >= 6 && len < 11) {
			if (((len -6)*10) % 5 !=0)
				return false;
		}
		
		//11 to 20" in units of 1".
		if (len >= 11 && len < 20) {
			if ((len -11) % 1 !=0)
				return false;
		}
		
		return valid;
		
	}

}
