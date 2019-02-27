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

public abstract class Screw extends OuterThreaded {

	private static final long serialVersionUID = -4896763529743999541L;

	private static final List<String> screwSteelFinish = Arrays.asList("black phosphate", "acq 1000 hour", "ubricated");

	private static final List<String> heads = Arrays.asList("bugle", "flat", "oval", "pan", "round");
	private static final List<String> drives = Arrays.asList("6-Lobe", "philips", "slotted", "square");

	private String head;
	private String drive;

	public Screw(String material, String finish, double unitPrice, int numberPerUnit, String diameter, double length,
			String head, String drive) throws IllegalFastener {
		super(material, finish, unitPrice, numberPerUnit, diameter, length);
		if ("steel".equalsIgnoreCase(material.toLowerCase())) {
			if (finish == null || (!stealFinishes.contains(finish.toLowerCase())
					&& !screwSteelFinish.contains(finish.toLowerCase()))) {
				throw new IllegalFastener("Wrong Finish for Steel Screw is provided");
			}
		} 

		if (head == null || !heads.contains(head.toLowerCase()))
			throw new IllegalFastener("Wrong head for Screws is provided!");

		if (drive == null || !drives.contains(drive.toLowerCase()))
			throw new IllegalFastener("Wrong head for Screws is provided!");

		this.head = head;
		this.drive = drive;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return head + " head, " + drive + " drvice, " + super.toString();
	}

}
