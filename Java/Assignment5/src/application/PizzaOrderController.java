package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static application.Pizza.*;//SIZE_SMALL;
//import static application.Pizza.SIZE_MEDIUM;
//import static application.Pizza.SIZE_LARGE;
//import static application.Pizza.TOPPING_NONE;
//import static application.Pizza.TOPPING_SINGLE;
//import static application.Pizza.TOPPING_DOUBLE;
//import static application.Pizza.TOPPING_TRIPLE;

public class PizzaOrderController {
	private Pizza aPizza;
	private LineItem anOrder;
	private static ArrayList<LineItem> allOrders = new ArrayList<>();

	private static final List<String> quantities = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
			"29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
			"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
			"65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82",
			"83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99",
			"100");

	private static DecimalFormat twoDForm = new DecimalFormat("#.00");
	
	private Image image = new Image("application/pizza.gif");
	
	@FXML
	private TextArea resultText;

	@FXML
	private TextField unitCost;

	@FXML
	private Button addOrder;

	@FXML
	private TextField totalCost;

	@FXML
	private ComboBox<String> pSize;

	@FXML
	private ComboBox<String> pCheese;

	@FXML
	private ComboBox<String> pMushrooms;

	@FXML
	private ComboBox<String> pPepperoni;

	@FXML
	private ComboBox<String> pQuantity;

	@FXML
	void addOrder(ActionEvent event) {
		allOrders.add(anOrder);
		allOrders.sort(null);
		String allOrderLines = "";
		int line = 1;
		double totCost = 0.0;
		for (LineItem order : allOrders) {
			totCost += order.getCost();
			allOrderLines += line++ + "\t" + order + "\n";
		}
		resultText.setText(allOrderLines + "Cost for all above orders: " + twoDForm.format(totCost));
	}

	@FXML
	void configPizza(ActionEvent event) {
		try {
			aPizza = new Pizza(pSize.getSelectionModel().getSelectedItem(),
					pCheese.getSelectionModel().getSelectedItem(), pMushrooms.getSelectionModel().getSelectedItem(),
					pPepperoni.getSelectionModel().getSelectedItem());

		} catch (IllegalPizza e) {
			Alert alert = new Alert(AlertType.ERROR, null, ButtonType.OK);
			alert.setTitle("Pizza Selection Error Alert");
			alert.setHeaderText(e.getMessage());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(image);
			alert.showAndWait();
			aPizza = null;
			anOrder = null;
			unitCost.setText("");
			totalCost.setText("");
			addOrder.setDisable(true);
			return;
		}

		int nums = 0;
		try {
			nums = Integer.valueOf(pQuantity.getSelectionModel().getSelectedItem());
			anOrder = new LineItem(nums, aPizza);

		} catch (IllegalPizza e) {
			Alert alert = new Alert(AlertType.ERROR, null, ButtonType.OK);
			alert.setTitle("Pizza Selection Error Alert");
			alert.setHeaderText(e.getMessage());
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(image);
			alert.showAndWait();
			aPizza = null;
			anOrder = null;
			unitCost.setText("");
			totalCost.setText("");
			addOrder.setDisable(true);
			return;
		}
		
		unitCost.setText(aPizza.getCost() + "");
		totalCost.setText(twoDForm.format(anOrder.getCost()) + "");

		if (nums >= 10 && event.getTarget().equals(pQuantity)) {
			Alert msg = new Alert(AlertType.INFORMATION, null, ButtonType.OK);
			msg.setTitle("Discount for Purchasing More Pizzas");
			msg.setHeaderText("10% discount for 10 to 20 pizzas inclusively\n15% discount for over 20 pizzas");
			Stage stage = (Stage) msg.getDialogPane().getScene().getWindow();
			stage.getIcons().add(image);
			msg.showAndWait();
		}

		
		addOrder.setDisable(false);

	}

	@FXML
	void initialize() {
		
		assert addOrder != null : "fx:id=\"addOrder\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert pCheese != null : "fx:id=\"pCheese\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert pMushrooms != null : "fx:id=\"pMushrooms\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert pPepperoni != null : "fx:id=\"pPepperoni\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert pSize != null : "fx:id=\"pSize\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert pQuantity != null : "fx:id=\"pQuantity\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert resultText != null : "fx:id=\"resultText\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert totalCost != null : "fx:id=\"totalCost\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		assert unitCost != null : "fx:id=\"unitCost\" was not injected: check your FXML file 'PizzaOrder.fxml'.";
		
		
		try {
			aPizza = new Pizza(SIZE_SMALL, TOPPING_SINGLE, TOPPING_NONE, TOPPING_SINGLE);
			anOrder = new LineItem(1, aPizza);
		} catch (IllegalPizza e) {
			System.out.println(e.getMessage());
		}
		
		

		// System.out.println("controller initializing...");
		pSize.getItems().addAll(SIZE_SMALL, SIZE_MEDIUM, SIZE_LARGE);
		pSize.getSelectionModel().selectFirst();

		pCheese.getItems().addAll(TOPPING_SINGLE, TOPPING_DOUBLE, TOPPING_TRIPLE);
		pCheese.getSelectionModel().selectFirst();

		pPepperoni.getItems().addAll(TOPPING_NONE, TOPPING_SINGLE, TOPPING_DOUBLE);
		pPepperoni.getSelectionModel().select(1);

		pMushrooms.getItems().addAll(TOPPING_NONE, TOPPING_SINGLE, TOPPING_DOUBLE);
		pMushrooms.getSelectionModel().selectFirst();

		pQuantity.getItems().addAll(quantities);
		pQuantity.getSelectionModel().selectFirst();

		unitCost.setText("$8.50");
		totalCost.setText("$8.50");

		

	}
}
