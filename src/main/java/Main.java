import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please insert trainingFile path:");

        Scanner in = new Scanner(System.in);
        //String filePath = in.nextLine();
        String filePath = "tranigVal.csv";

        var data = new PreparedData(filePath);

        data.dataPrep();

        new PreparedSimulation(data);

        System.out.println(filePath + " loaded successfully! Please insert testFilePath:");
        //String testFilePath = in.nextLine();
        String testFilePath = "testVal.csv";

        System.out.println(testFilePath + " loaded successfully! Please insert 'k' parameter:");
        int kParam = in.nextInt();

        var newData = new PreparedData(testFilePath);

        newData.dataPrep();

        data.addPointToGroup(newData, kParam);
        //new PreparedSimulation(newData);
        System.out.println("Simulation ended successfully! Program shutdown!");

        /*ArrayList<Double> test1 = new ArrayList<>();
        test1.add(6.7);
        test1.add(3.1);
        test1.add(5.6);
        test1.add(2.4);*/

        //data.getPointFromList(test1);
    }

}
