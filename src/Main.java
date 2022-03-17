import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> typesInSimulation = dataPrep("tranigVal.csv");

    }

    // Reads and prepare data to use. Extracts unique types of groups
    // returns List<String> type
    private static List<String> dataPrep(String filePath){

        File file = new File(filePath);
        ArrayList<String> typeList = new ArrayList<>(3);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bufferedReader.readLine()) != null){

                List<String> temp = List.of(line.split(","));
                if (!(typeList.contains(temp.get((temp.size()-1))))){
                    typeList.add(temp.get((temp.size()-1)));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeList;
    }
}
