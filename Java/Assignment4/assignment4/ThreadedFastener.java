package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
 */
import java.util.Arrays;
import java.util.List;

public abstract class ThreadedFastener extends Fastener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8222351280400704301L;
	
	static final List<String> stealFinishes = Arrays.asList("chrome", "hot dipped galvanized", "plain",
			"yellow zinc", "zinc");
	private static final List<String> otherFinishes = Arrays.asList("plain");

	private static final List<String> diameters = Arrays.asList("#8-13", "#8-15", "#8-32", "#10-13", "#10-24", "#10-32",
			"1/4-20", "5/16-18", "3/8-16", "7/16-14", "1/2-13", "5/8-11", "3/4-10");

	private String diameter;

	/**
	 * @param material
	 * @param finish
	 * @param unitPrice
	 * @param numberPerUnit
	 * @param diameter
	 * @throws IllegalFastener
	 */
	public ThreadedFastener(String material, String finish, double unitPrice, int numberPerUnit, String diameter)
			throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit);
		
		if (diameter == null || !diameters.contains(diameter))
			throw new IllegalFastener("Wrong Thread/Diameter for Fastener is provided");
		
		if (!"steel".equalsIgnoreCase(material.toLowerCase())) {
			if (finish == null || !otherFinishes.contains(finish.toLowerCase()))
				throw new IllegalFastener("Wrong finish for Brass or Stainless Steel threaded fastener is provided");
		}
		
		this.diameter = diameter;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return diameter + " thread, " + super.toString();
	}

}
