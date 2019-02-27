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

public abstract class Nail extends Fastener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7499360822366103100L;
	private static final List<String> nailFinishes = Arrays.asList("bright", "hot dipped galvanized");
	private static final List<String> sizes = Arrays.asList("6d", "8d", "10d", "12d", "16d", "60d");
		
	private static final List<Double> nailLengths = Arrays.asList(new Double(2), new Double(2.5), new Double(3),
			new Double(3.25), new Double(3.5), new Double(6));
	private static final List<Double> gauges = Arrays.asList(new Double(2), new Double(8), new Double(9),
			new Double(10.25), new Double(11.5));
	
	private String size;
	private double length;
	private double gauge;

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
	public Nail(String material, String finish, double unitPrice, int numberPerUnit, String size, double length,
			double gauge) throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit);

		if (finish == null || !nailFinishes.contains(finish.toLowerCase())) {
			throw new IllegalFastener("Wrong Finish for Nails is provided");
		}

		if (size == null || !sizes.contains(size.toLowerCase()))
			throw new IllegalFastener("Wrong Size for Nails is provided");

		if (!nailLengths.contains(new Double(length)))
			throw new IllegalFastener("Wrong Length for Nails is provided");

		if (!gauges.contains(new Double(gauge)))
			throw new IllegalFastener("Wrong Gauge for Nails is provided");

		this.size = size;
		this.length = length;
		this.gauge = gauge;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return size + " size, " + length + " long, " + gauge + "  gauge, " + super.toString();
	}

}
