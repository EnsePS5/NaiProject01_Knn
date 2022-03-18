import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreparedData {

    private String trainingValFilePath;

    List<String> dataTypes;

    public PreparedData(String trainingVal){
        this.trainingValFilePath = trainingVal;
    }


    // Reads and prepare data to use. Extracts unique types of groups
    // returns List<String> type
    public void dataPrep(){

        File file = new File(trainingValFilePath);
        ArrayList<String> typeList = new ArrayList<>(3);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = bufferedReader.readLine()) != null){

                List<String> temp = List.of(line.split(","));
                if (!(typeList.contains(temp.get((temp.size()-1))))){
                    typeList.add(temp.get((temp.size()-1)));
                }

                ArrayList<Double> tempContainerForValues = new ArrayList<>(temp.size()-1);

                for (int i = 0; i < temp.size()-1; i++) {
                    tempContainerForValues.add(Double.parseDouble(temp.get(i)));
                    //System.out.print(tempContainerForValues.get(i) + " ");
                }
                //System.out.println(temp.get(temp.size()-1));
                Point point = new Point(tempContainerForValues, temp.get(temp.size()-1));

            }
            dataTypes = typeList;

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
