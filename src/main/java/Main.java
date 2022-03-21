import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please insert trainingFile path:");

        Scanner in = new Scanner(System.in);
        //String filePath = in.nextLine();
        String filePath = "tranigVal.csv";

        var data = new PreparedData(filePath);
        data.dataPrep();

        var simData = new PreparedSimulation(data);

        System.out.println(filePath + " loaded successfully! Please insert testFilePath:");
        //String testFilePath = in.nextLine();
        String testFilePath = "testVal.csv";

        System.out.println(testFilePath + " loaded successfully! Please insert 'k' parameter:");
        int kParam = in.nextInt();

        var newData = new PreparedData(testFilePath);

        newData.dataPrep();
        data.addPointsToGroup(newData, kParam);

        simData.updateSimulation();

        while (true){
            String con = in.nextLine();
            switch (con) {
                case "EXIT" -> System.exit(0);
                case "FIND" -> {
                    System.out.println("Insert record:");
                    data.getPointFromList(in.nextLine());
                }
                case "ADD" -> {

                    System.out.println("Insert record:");

                    String record = in.nextLine();

                    var tempData = new PreparedData(record);
                    tempData.prepSinglePoint(record);

                    System.out.println("Please insert 'k' parameter:");

                    data.addPointsToGroup(tempData, in.nextInt());
                    simData.updateSimulation();
                }
            }
            System.out.println("""
                    Action ended successfully!
                    Insert 'EXIT' to shutdown the program or click 'X' on window!
                    Insert 'FIND' to find a specific record!
                    Insert 'ADD' to add a new record""");
        }
    }

}
