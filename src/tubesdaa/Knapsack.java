package tubesdaa;
import java.util.Comparator;
public class Knapsack {
    private int id;
    private int weight;
    private int profit;
    private double density;

    public Knapsack(int weight, int profit,int id) {
        this.id=id;
        this.weight = weight;
        this.profit = profit;
        this.density=(double) this.profit / this.weight;
    }
    public Knapsack(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.density=(double) this.profit / this.weight;

    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getProfit() {
        return profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }
    public double getDensity() {
        return density;
    }
    public void setDensity() {
        this.density = (double) this.profit / this.weight;
    }
    @Override
    public String toString() {
        if (this.density==0) {
            return "Weight  :"+getWeight()+"\n"
                + "Profit  :"+getProfit()+"\n";
        } else{
            return "Weight  :"+getWeight()+"\n"
                + "Profit  :"+getProfit()+"\n"
                + "Density :"+getDensity();
        }
        
    }
    
    public static Comparator<Knapsack> weightComparator = new Comparator<Knapsack>() {
        @Override
        public int compare(Knapsack o1, Knapsack o2) {
            return o1.weight-o2.weight;
        }
    };
    public static Comparator<Knapsack> profitComparator = new Comparator<Knapsack>() {
        @Override
        public int compare(Knapsack o1, Knapsack o2) {
            return o2.profit-o1.profit;
        }
    };
    public static Comparator<Knapsack> densityComparator=new Comparator<Knapsack>() {
        @Override
        public int compare(Knapsack o1, Knapsack o2) {
            return Double.compare(o2.density, o1.density);
        }
    };
    public static Comparator<Knapsack> idComparator = new Comparator<Knapsack>() {
        @Override
        public int compare(Knapsack o1, Knapsack o2) {
            return o1.id-o2.id;
        }
    };
    
    
}

    