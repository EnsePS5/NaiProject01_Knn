import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

       //List<String> typesInSimulation = dataPrep("tranigVal.csv");
        var data = new PreparedData("tranigVal.csv");

        data.dataPrep();

        /*ArrayList<Double> test1 = new ArrayList<>();
        test1.add(6.7);
        test1.add(3.1);
        test1.add(5.6);
        test1.add(2.4);*/

        //data.getPointFromList(test1);
    }

}
