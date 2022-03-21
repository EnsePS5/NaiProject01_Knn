import java.awt.*;
import java.io.*;
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
    public void dataPrep() throws IOException {

        ArrayList<String> typeList = new ArrayList<>(3);

        BufferedReader bufferedReader = fileOpener(trainingValFilePath);

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
    public void assigningPointsToTypes(int kParam,PreparedData superData) throws IOException {
        BufferedReader bufferedReader = fileOpener(trainingValFilePath);

        String line;
        while ((line = bufferedReader.readLine()) != null){
            List<String>temp = List.of(line.split(","));
            assigningPointsToTypes(temp,kParam,superData);
        }
    }
    public void assigningPointsToTypes(List<String> pointVal,int kParam,PreparedData superData){

        ArrayList<Double> values = new ArrayList<>();

        for (String s : pointVal) {
            values.add(Double.parseDouble(s));
        }

        ArrayList<Double> minValues = new ArrayList<>();
        HashMap <Double,Integer> mapOfPoints = new HashMap<>();

        for (int i = 0; i < superData.listOfPoints.size(); i++) {

            double temp = superData.listOfPoints.get(i).distanceCounter(values);

            minValues.add(temp);
            mapOfPoints.put(temp,i);
        }

        Collections.sort(minValues);
        int[] mostTypesCounter = new int[superData.dataTypes.size()];

        for (int i = 0; i < kParam; i++) {

            String tempType = superData.listOfPoints.get(mapOfPoints.get(minValues.get(i))).getPointType();

            for (int j = 0; j < superData.dataTypes.size(); j++) {
                if (tempType.equals(superData.dataTypes.get(j))){
                    mostTypesCounter[j]++;
                }
            }
        }

        int maxValIndex = maxValIndex(mostTypesCounter);

        Point point = new Point(values,superData.dataTypes.get(maxValIndex));
        superData.listOfPoints.add(point);

        double accuracy = (double)mostTypesCounter[maxValIndex]/kParam;

        System.out.print("Record ");
        for (Double value : values) {
            System.out.print(value + " ");
        }
        System.out.println(superData.dataTypes.get(maxValIndex) + " has been added successfully with " + accuracy + " accuracy!");
    }
    public static BufferedReader fileOpener(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        return new BufferedReader(new FileReader(file));
    }
    private int maxValIndex(int [] vals){

        int result = 0;

        for (int i = 1; i < vals.length; i++) {
            if (vals[i] > vals[result]){
                result = i;
            }
        }

        return result;
    }
}
