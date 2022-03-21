import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PreparedData {

    private final String trainingValFilePath;

    public List<String> dataTypes;

    public Map<String,Color> colorTypes = new HashMap<>();
    public ArrayList<Point> listOfPoints = new ArrayList<>();

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
                listOfPoints.add(point);

            }
            dataTypes = typeList;

            assignColor();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getPointFromList(ArrayList<Double> values){
        for (Point listOfPoint : listOfPoints) {
            if (listOfPoint.getPointValues().containsAll(values)) {
                for (int j = 0; j < listOfPoint.getPointValues().size(); j++) {
                    System.out.print(listOfPoint.getPointValues().get(j) + " ");
                }
                System.out.println(listOfPoint.getPointType() + " is in simulation!");
                return;
            }
        }
        System.out.println("Record is not in simulation");
    }
    public void getPointFromList(String record){
        List<String> tempS = List.of(record.split(","));
        ArrayList<Double> tempD = new ArrayList<>();
        for (int i = 0; i < tempS.size()-1; i++) {
            tempD.add(Double.parseDouble(tempS.get(i)));
        }
        getPointFromList(tempD);
    }
    private void assignColor(){
        for (String dataType : dataTypes) {
            colorTypes.put(dataType, new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        }
    }
    public void addPointsToGroup(PreparedData otherData, int kArg){//TODO change adding (must find it own type)

        for (int i = 0; i < otherData.listOfPoints.size(); i++) {

            HashMap<Double,Point> minMap = new HashMap<>();
            ArrayList<Double> minVals = new ArrayList<>();
            for (int j = 0; j < this.listOfPoints.size(); j++) {
                minVals.add(otherData.listOfPoints.get(i).distanceCounter(this.listOfPoints.get(j)));
                minMap.put(minVals.get(j),this.listOfPoints.get(j));
            }
            minVals.sort(Collections.reverseOrder());

            HashMap<String,Integer> counterOfTypes = new HashMap<>();
            for (String dataType : this.dataTypes) {
                counterOfTypes.put(dataType, 0);
            }

            for (int j = 0; j < kArg; j++) {
                var temp = minMap.get(minVals.get(j)).getPointType();
                if (counterOfTypes.containsKey(temp)){
                    counterOfTypes.replace(temp,counterOfTypes.get(temp) + 1);
                }
            }
            int maxVal = maxValInMap(counterOfTypes,this.dataTypes);
            double accuracy = ((double) maxVal/kArg);

            System.out.println("Added record: " + otherData.listOfPoints.get(i).getPointValues() + " - " +
                    otherData.listOfPoints.get(i).getPointType() + " to simulation with " + accuracy + " accuracy!");

            this.listOfPoints.add(otherData.listOfPoints.get(i));
        }
    }
    private static int maxValInMap(HashMap<String,Integer> map, List<String> dataTypes){

        int result = 0;

        for (int i = 0; i < map.size(); i++) {
            if (map.get(dataTypes.get(i)) > result){
                result = map.get(dataTypes.get(i));
            }
        }
        return result;
    }
    public void prepSinglePoint(String con){
        List<String> tempS = List.of(con.split(","));
        ArrayList<Double> tempD = new ArrayList<>();
        for (int i = 0; i < tempS.size()-1; i++) {
            tempD.add(Double.parseDouble(tempS.get(i)));
        }
        Point point = new Point(tempD, tempS.get(tempS.size()-1));
        listOfPoints.add(point);
    }
}
