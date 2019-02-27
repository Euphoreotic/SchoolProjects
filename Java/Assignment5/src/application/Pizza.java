package application;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Pizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 149044743347589806L;

	public static final String SIZE_SMALL = "small";
	public static final String SIZE_MEDIUM = "medium";
	public static final String SIZE_LARGE = "large";

	public static final String TOPPING_NONE = "none";
	public static final String TOPPING_SINGLE = "single";
	public static final String TOPPING_DOUBLE = "double";
	public static final String TOPPING_TRIPLE = "triple";

	public String size;
	private String cheese;
	private String mushrooms;
	private String pepperoni;

	private static DecimalFormat twoDForm = new DecimalFormat("#.00");

	
	public Pizza() {
		
		//super();
		size = SIZE_SMALL;
		cheese = TOPPING_SINGLE;
		pepperoni = TOPPING_SINGLE;
		mushrooms = TOPPING_NONE;
		

	}

	public Pizza(String size, String cheese, String mushRooms, String pepperoni) throws IllegalPizza {
		super();

		if (SIZE_SMALL.equalsIgnoreCase(size) || SIZE_MEDIUM.equalsIgnoreCase(size)
				|| SIZE_LARGE.equalsIgnoreCase(size)) {
			this.size = size.toLowerCase();
		} else {
			throw new IllegalPizza("Please choose a correct pizza size(Small, Medium or Large)");
		}

		if (TOPPING_SINGLE.equalsIgnoreCase(cheese) || TOPPING_DOUBLE.equalsIgnoreCase(cheese)
				|| TOPPING_TRIPLE.equalsIgnoreCase(cheese)) {
			this.cheese = cheese.toLowerCase();
		} else {
			throw new IllegalPizza("Please choose either Single, Double or Triple chesse topping");
		}

		if (TOPPING_NONE.equalsIgnoreCase(mushRooms) || TOPPING_SINGLE.equalsIgnoreCase(mushRooms) ||TOPPING_DOUBLE.equalsIgnoreCase(mushRooms)) {
			this.mushrooms = mushRooms.toLowerCase();
		}else {
			throw new IllegalPizza("Please choose either None, Single or Double mushroom topping");
		}

		if (TOPPING_NONE.equalsIgnoreCase(pepperoni) || TOPPING_SINGLE.equalsIgnoreCase(pepperoni) || TOPPING_DOUBLE.equalsIgnoreCase(pepperoni)) {
			this.pepperoni = pepperoni.toLowerCase();
		}else {
			throw new IllegalPizza("Please choose either None, Single or Double pepperoni topping");
		}

		if (!mushRooms.equalsIgnoreCase(TOPPING_NONE) && pepperoni.equalsIgnoreCase(TOPPING_NONE)) {
			throw new IllegalPizza("A Pizza cannot have mushrooms unless it has single or double pepperoni.");
		}

		
		
	}

	public double getCost() {

		double cost = 7.00;

		if (SIZE_MEDIUM.equalsIgnoreCase(this.size)) {
			cost = 9.00;
		}

		if (SIZE_LARGE.equalsIgnoreCase(this.size)) {
			cost = 11.00;
		}

		if (TOPPING_DOUBLE.equalsIgnoreCase(this.cheese)) {
			cost += 1.50;
		}

		if (TOPPING_TRIPLE.equalsIgnoreCase(this.cheese)) {
			cost += 3.00;
		}

		if (TOPPING_SINGLE.equalsIgnoreCase(this.mushrooms)) {
			cost += 1.50;
		}

		if (TOPPING_DOUBLE.equalsIgnoreCase(this.mushrooms)) {
			cost += 3.00;
		}

		if (TOPPING_SINGLE.equalsIgnoreCase(this.pepperoni)) {
			cost += 1.50;
		}

		if (TOPPING_DOUBLE.equalsIgnoreCase(this.pepperoni)) {
			cost += 3.00;
		}

		return cost;

	}

	public String getSize() {
		return size;
	}

	public  String getCheese() {
		return cheese;
	}

	public String getMushrooms() {
		return mushrooms;
	}

	public String getPepperoni() {
		return pepperoni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String mushroomTopping = getMushrooms().toLowerCase();
		String pepperoniTopping = getPepperoni().toLowerCase();

		if (TOPPING_NONE.equalsIgnoreCase(mushroomTopping))
			mushroomTopping = "no";

		if (TOPPING_NONE.equalsIgnoreCase(pepperoniTopping))
			pepperoniTopping = "no";

		return getSize().toLowerCase() + " pizza, " + getCheese().toLowerCase() + " cheese, " + mushroomTopping
				+ " mushrooms, " + pepperoniTopping + " pepperoni. Cost: $" + twoDForm.format(getCost()) + " each.";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Pizza)) {
			return false;
		}
		
		
		
		Pizza another = (Pizza) obj;

		if (this.size.equalsIgnoreCase(another.getSize()) && this.cheese.equalsIgnoreCase(another.getCheese())
				&& this.mushrooms.equalsIgnoreCase(another.getMushrooms())
				&& this.pepperoni.equalsIgnoreCase(another.getPepperoni())) {

			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Pizza clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		try {
			return new Pizza(size, cheese, mushrooms, pepperoni);
		} catch (IllegalPizza e) {

		}
		return null;
	}

}
