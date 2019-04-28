import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Java program that implements greedy discrete and continuous algorithms for 
 * the knapsack problem.
 * 
 * @author Crystal Pendergrass 
 * @version Project 6, 2 December 2014
 */
public class GreedyKnapsack {

	private int numberOfItems;
	private LinkedList<KnapsackItem> listOfItems;

	static double capacity;
	static Scanner in = new Scanner( System.in );
	static int numOfItems;
	static double itemWeight;
	static double itemValue;

	class KnapsackItem{
		private double weight;
		private double value;
		private double ratio;
		private double takeWeight;

		public KnapsackItem(double weight, double value){
			this.weight = weight;
			this.value = value;
			this.ratio = value / weight;
			this.takeWeight = 0;
		}

		public String toString(){
			return String.format("%-9.2f%-10.2f%-15.2f%.2f", 
					weight,value,ratio,takeWeight);
		}
	}

	public GreedyKnapsack(int number){
		numberOfItems = number;
		listOfItems = new LinkedList<>();
	}

	public GreedyKnapsack(){
		listOfItems = new LinkedList<>();
	}

	public void addItem(double weight, double value){
		if(listOfItems.isEmpty())
			listOfItems.add(new KnapsackItem(weight, value));
		else{
			listOfItems.add(new KnapsackItem(weight, value));
			knapsackOrder();
		}
	}

	// orders the items, item values, and weights in descending order 
	// of the value/weight ratios
	public void knapsackOrder(){
		ListIterator<KnapsackItem> iter =  listOfItems.listIterator();
		KnapsackItem currentItem = listOfItems.getLast();
		KnapsackItem compareItem;

		while(iter.hasNext()){
			compareItem = iter.next();
			if(currentItem.ratio > compareItem.ratio){
				int insertIndex = listOfItems.indexOf(compareItem);
				listOfItems.add(insertIndex, currentItem);
				listOfItems.removeLast();
				break;
			}
		}
	}

	// prints out in a tablular format (see p. 454) the items, values, 
	// weights, value/weight, and take weight values
	public void printKnapsack(){
		System.out.println("----------------------------------------------------");
		System.out.printf("%-7s%-9s%-8s%-15s%s%n", 
				"item", "weight", "value", "value\\weight", "take weight");
		System.out.println("----------------------------------------------------");
		for(int i=1; i<=listOfItems.size(); i++){
			System.out.println(" " + i + "\t" + listOfItems.get(i-1));
		}
		System.out.println("----------------------------------------------------");
	}

	public void resetKnapsack(){
		for(KnapsackItem ki : listOfItems)
			ki.takeWeight = 0;
	}

	// implements the discrete greedy knapsack algorithm
	public void greedyKnapsackD(){
		double currentCapacity = 0;
		for(KnapsackItem ki : listOfItems){
			if((currentCapacity + ki.weight) <= capacity){
				currentCapacity += ki.weight;
				ki.takeWeight = 1;
			}
		}
	}

	// implements the continuous greedy knapsack algorithm
	public void greedyKnapsackC(){
		double currentCapacity = 0;
		for(KnapsackItem ki : listOfItems){
			if((currentCapacity + ki.weight) <= capacity){
				currentCapacity += ki.weight;
				ki.takeWeight = 1;
			}
			else{
				ki.takeWeight = (capacity - currentCapacity) /  ki.weight;
				currentCapacity += ki.weight * ki.takeWeight;
			}
		}	
	}

	public static void generalInput(){
		do {
			try {
				System.out.print("Input total number of items that can be "
						+ "stolen: ");
				numOfItems = in.nextInt();

				System.out.print("Input capacity of knapsack: ");
				capacity = in.nextDouble();

				if(numOfItems < 1) 
					System.out.printf("numOfItems = %d is NOT a valid entry."
							+ "[numOfItems > 1]%n", numOfItems);
				if(capacity < 0) 
					System.out.printf("capacity = %f is NOT a valid entry."
							+ "[capacity > 0]%n", capacity);

			} catch ( InputMismatchException ex ){ 
				in.nextLine();
				System.out.println("Invalid input.");
				generalInput();
			}in.nextLine(); 
		} while (numOfItems < 1 || capacity < 0);
		System.out.println();
	}

	public static void itemInput(int i){
		do {
			try {
				System.out.printf("Input weight and value of Item %d "
						+ "[separated by space]: ", i);
				itemWeight = in.nextDouble();
				itemValue = in.nextDouble();

				if(itemWeight < 0) 
					System.out.printf("itemWeight = %f is NOT a valid entry."
							+ "[itemWeight > 0]%n", itemWeight);
				if(itemValue < 0) 
					System.out.printf("itemValue = %f is NOT a valid entry."
							+ "[itemValue > 0]%n", itemValue);

			} catch ( InputMismatchException ex ){ 
				in.nextLine();
				System.out.println("Invalid input.");
				itemInput(i);
			}
		} while (itemWeight < 0 || itemValue < 0);
	}

	public static void main(String[] args) {
		//GreedyKnapsack gk = new GreedyKnapsack();

		generalInput();

		GreedyKnapsack gk = new GreedyKnapsack(numOfItems);

		for(int i = 1; i<=gk.numberOfItems; i++){
			itemInput(i);

			gk.addItem(itemWeight, itemValue);
		}
		System.out.println();

		/*		gk.addItem(7, 42);
		gk.addItem(3, 12);
		gk.addItem(4, 40);
		gk.addItem(5, 25);
		capacity = 10;*/
		in.close();

		System.out.println("ITEMS AVAILABLE FOR STEALING");
		gk.printKnapsack();

		gk.greedyKnapsackD();
		System.out.println("\nITEMS STOLEN DISCRETELY");
		gk.printKnapsack();

		gk.resetKnapsack();

		gk.greedyKnapsackC();
		System.out.println("\nITEMS STOLEN CONTINUOUSLY");
		gk.printKnapsack();
	}
        
        