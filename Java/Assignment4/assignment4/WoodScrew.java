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

public class WoodScrew extends Screw {
	
	private static final long serialVersionUID = -2289933053247435441L;

	private static final List<String> points = Arrays.asList("double Cut", "sharp", "type 17");

	private String point;

	/**
	 * @param material
	 * @param finish
	 * @param unitPrice
	 * @param numberPerUnit
	 * @param diameter
	 * @param length
	 * @throws IllegalFastener
	 */
	// length, thread, material, finish, head, drive, point, price/unit, unit size
	public WoodScrew(double length, String diameter, String material, String finish, String head, String drive,
			String point, double unitPrice, int numberPerUnit) throws IllegalFastener {
		
		super(material, finish, unitPrice, numberPerUnit, diameter, length, head, drive);
		
		
		if (point == null || !points.contains(point.toLowerCase()))
			throw new IllegalFastener("Wrong Point for Wood Screw is provided");

		this.point = point;
	}

	/**
	 * 
	 */
	

	

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Wood Screw, " + point + " point, " + super.toString();
	}

}
