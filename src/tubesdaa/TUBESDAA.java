/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesdaa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author geryn
 */
public class TUBESDAA {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Knapsack> knapsack=new ArrayList<Knapsack>();
    public static void main(String[] args) throws IOException {
        inputData(knapsack);
        Scanner reader=new Scanner(System.in);
        System.out.print("\n Masukkan kapasitas :");
        int kapasitas=reader.nextInt();
        int num=0;
        do {
            mainmenu();
            num=reader.nextInt();
            pilihan(num,kapasitas);
            
        }while (num!=0);
        
        
        
    }
    public static void inputData(ArrayList<Knapsack> k) {
        Scanner reader=new Scanner(System.in);
        System.out.print("Masukkan Banyak data :");
        int jum= reader.nextInt();
        for (int i=0;i<jum;i++) {
            System.out.println("data ke "+(i+1)+" :");
            System.out.print("Weight :");
            int weight=reader.nextInt();
            System.out.print("Profit :");
            int profit=reader.nextInt();
            knapsack.add(new Knapsack(weight,profit,i));
        }
    }
    
    public static Knapsack greedy(int num,int k) throws IOException {
        Knapsack data;
        if (num==1) {
//            knapsack=sort("Weight",knapsack); Bubble Sort
            Collections.sort(knapsack,Knapsack.weightComparator); //Sort by Weight
        }else if (num==2) {
//            knapsack=sort("Profit",knapsack); Bubble Sort
            Collections.sort(knapsack,Knapsack.profitComparator); //Sort by Profit
        } else if (num==3) {
//            knapsack=sort("Density",knapsack); Bubble Sort
            Collections.sort(knapsack,Knapsack.densityComparator);//Sort by Density
        }
        int kTemp=0;
        int pTemp=0;
        int i=0;
        while (kTemp<=k && i<knapsack.size()) {
            if (kTemp+knapsack.get(i).getWeight()<=k) {
                kTemp=kTemp+knapsack.get(i).getWeight();
                pTemp=pTemp+knapsack.get(i).getProfit();
            }
            i++;
        }
        
        data=new Knapsack(kTemp,pTemp);
        return data;
        
        
    }
    public static void mainmenu(){
        System.out.println("\n>> Menu Greedy:");
        System.out.println("   1. Greedy by Weight");
        System.out.println("   2. Greedy by Profit");
        System.out.println("   3. Greedy by Density");
        System.out.println("   4. solution");
        System.out.print(">> Masukkan pilihan anda :");
    }
    public static void pilihan(int num,int k) throws IOException {
        Knapsack[] Data=new Knapsack[3];
        switch(num) {
            case 1:
                System.out.println("\n");
                Data[0]=greedy(num,k);
                System.out.println("NO  ||  Profit  ||  Weight  ||  Density  ||");
                for (int i=0;i<knapsack.size();i++) {
                    System.out.println((i+1)+"        "+knapsack.get(i).getProfit()+"          "+knapsack.get(i).getWeight()+"             "+knapsack.get(i).getDensity());
                    System.out.println("");
                }
                System.out.println("Greedy By Weight :");
                System.out.println(Data[0]);
                System.in.read();
                break;
            case 2:
                System.out.println("\n");
                Data[1]=greedy(num,k);
                System.out.println("NO  ||  Profit  ||  Weight  ||  Density  ||");
                for (int i=0;i<knapsack.size();i++) {
                    System.out.println((i+1)+"        "+knapsack.get(i).getProfit()+"          "+knapsack.get(i).getWeight()+"             "+knapsack.get(i).getDensity());
                    System.out.println("");
                }
                System.out.println("Greedy By Profit :");
                System.out.println(Data[1]);
                System.in.read();
                break;
            case 3:
                System.out.println("\n");
                Data[2]=greedy(num,k);
                System.out.println("NO  ||  Profit  ||  Weight  ||  Density  ||");
                for (int i=0;i<knapsack.size();i++) {
                    System.out.println((i+1)+"        "+knapsack.get(i).getProfit()+"          "+knapsack.get(i).getWeight()+"             "+knapsack.get(i).getDensity());
                    System.out.println("");
                }
                System.out.println("Greedy By Density :");
                System.out.println(Data[2]);
                System.in.read();
                break;
            case 4:
                Data[0]=greedy(1,k);
                Data[1]=greedy(2,k);
                Data[2]=greedy(3,k);
                Knapsack Max=max(Data[0],Data[1],Data[2]);
                Collections.sort(knapsack,Knapsack.idComparator);
                System.out.println("NO  ||  Profit  ||  Weight  ||  Density  ||");
                for (int i=0;i<knapsack.size();i++) {
                    System.out.println((i+1)+"        "+knapsack.get(i).getProfit()+"          "+knapsack.get(i).getWeight()+"             "+knapsack.get(i).getDensity());
                    System.out.println("");
                }
                System.out.print("Solusi Maksimal yang didapat dari algoritma greedy adalah \n"
                        + "weight = "+Max.getWeight()+""
                                + "\nProfit = "+Max.getProfit());
                System.in.read();
                break;
            case 0:
                System.out.println("Tekan enter untuk exit");
                System.in.read();
                break;
            default:
                System.out.println("inputan salah");
        }
    }
    public static Knapsack max(Knapsack a,Knapsack b,Knapsack c) {
        if (a.getProfit()>b.getProfit() && a.getProfit()>c.getProfit()) {
            return a;
        } else if (b.getProfit()>a.getProfit() && b.getProfit()>c.getProfit()) {
            return b;
        } else {
            return c;
        }
    }
    public static ArrayList<Knapsack> sort(String str,ArrayList<Knapsack> k){
        Knapsack temp;
        Knapsack[] test= new Knapsack[k.size()];
        for (int i=0;i<k.size();i++) {
            test[i]=k.get(i);
        }
        
        if (str=="Weight") {
            for (int i=0;i<k.size()-1;i++) {
                for (int j=1;j<k.size()-1;j++){
                    if (test[j-1].getWeight()>test[j].getWeight()){
                        temp=test[j-1];
                        test[j-1]=test[j];
                        test[j]=temp;
                    }
                }
            }
        } else if (str=="Profit")  {
            for (int i=0;i<k.size()-1;i++) {
                for (int j=1;j<k.size()-1;j++){
                    if (test[j-1].getProfit()<test[j].getProfit()){
                        temp=test[j-1];
                        test[j-1]=test[j];
                        test[j]=temp;
                    }
                }
            }
        } else if (str=="Density") {
            for (int i=0;i<k.size()-1;i++) {
                for (int j=1;j<k.size()-1;j++){
                    if (test[j-1].getDensity()<test[j].getDensity()){
                        temp=test[j-1];
                        test[j-1]=test[j];
                        test[j]=temp;
                    }
                }
            }
        }
        ArrayList<Knapsack> T=new ArrayList<Knapsack>();
        for (int i=0;i<test.length;i++) {
            T.add(test[i]);
        }
        return T  ;
        }

    
}
