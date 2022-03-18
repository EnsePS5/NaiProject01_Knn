public class Main {

    public static void main(String[] args) {

       //List<String> typesInSimulation = dataPrep("tranigVal.csv");
        var data = new PreparedData("tranigVal.csv");

        data.dataPrep();

    }

}
