package assignment4;
/* CISC-124 Assignment 4 
 * by Tong Chen, 10189689, 14tc41
 * This assignment demonstrate the OOP three characteristics 
 *   -- Inheritance
 *   -- Polymorphism
 *   -- Encapsulation
 *   
 * This is the base generic abstract class for building the Fastener hierarchy
 */
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public abstract class Fastener implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8968180887252937719L;

	//general info applied to all fasteners
	private String material;
	private String finish;
	private double unitPrice;
	private int numberPerUnit;

	private static final List<String> materials = Arrays.asList("brass", "stainless steel", "steel");

	/**
	 * @param material
	 * @param finish
	 * @param unitPrice
	 * @param numberPerUnit
	 */
	public Fastener(String material, String finish, double unitPrice, int numberPerUnit) throws IllegalFastener {
		super();
		// check material and finish

		if (material == null || !materials.contains(material.toLowerCase())) {
			throw new IllegalFastener("Wrong material for Screw is provided");
		}

		this.material = material;
		this.finish = finish;
		this.unitPrice = unitPrice;
		this.numberPerUnit = numberPerUnit;
	}

	public double getOrderCost(int numOfUnits) {
		return unitPrice * numOfUnits;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return material + ", with a " + finish + " finish. " + numberPerUnit + " in a unit, $" + unitPrice
				+ " per unit";
	}

}
