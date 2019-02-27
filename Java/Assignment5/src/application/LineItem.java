package application;

import java.io.Serializable;
import java.text.DecimalFormat;

public class LineItem implements Serializable, Comparable<LineItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844537493176901073L;

	private int number;

	private  Pizza pizza;

	private static DecimalFormat twoDForm = new DecimalFormat("#.00");
	
	

	/**
	 * @param number
	 * @param pizzaOrdered
	 */
	public LineItem(int number, Pizza pizzaOrdered) throws IllegalPizza {
		super();

		if (number < 1 || number > 100) {
			throw new IllegalPizza("Invalid order number! Order number must be between 1 and 100 inclusive");
		}

		if (pizzaOrdered == null) {
			throw new IllegalPizza("Pizza must be provided to order");
		}

		this.number = number;
		this.pizza = pizzaOrdered;

	}

	/**
	 * @param pizza
	 */
	public LineItem(Pizza pizzaOrdered) throws IllegalPizza {
		super();

		if (pizzaOrdered == null) {
			throw new IllegalPizza("Pizza must be provided to order");
		}

		this.number = 1;
		this.pizza = pizzaOrdered;

	}

	public double getCost() {
		double totalCost = getPizza().getCost() * getNumber();

		if (getNumber() > 20)
			totalCost = totalCost * 0.85;

		if (getNumber() >= 10 && getNumber() <= 20)
			totalCost = totalCost * 0.9;

		return totalCost;
	}

	/**
	 * @return the pizzaOrdered
	 */
	public Pizza getPizza() {
		return pizza;
	}

	/**
	 * @return the lineNum
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) throws IllegalPizza {
		if (number < 1 || number > 100) {
			throw new IllegalPizza("Invalid order number! Order number must be between 1 and 100 inclusive");
		}
		this.number = number;
	}

	@Override
	public int compareTo(LineItem o) {
		
		int result = 0;
		
		if (Math.abs(getCost() - o.getCost()) <1)
			result = 0;

		if (getCost() > o.getCost())
			result = -1;

		if (getCost() < o.getCost())
			result = 1;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return String.format("%2s", getNumber()) + " " + getPizza().getSize() + " pizza, " + getPizza().getCheese()
				+ " cheese, " + getPizza().getMushrooms() + " mushrooms, " + getPizza().getPepperoni()
				+ " pepperoni. Cost: $" + twoDForm.format(getPizza().getCost()) + " each.";
	}

}
