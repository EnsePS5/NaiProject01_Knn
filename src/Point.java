import java.util.ArrayList;

public record Point(ArrayList<Double> values, String type) {

    public void addPointToGroup() {


    }

    public double distanceCounter(Point otherPoint) {

        double sumVar = 0.0;

        for (int i = 0; i < this.values.size(); i++) {
            sumVar += Math.pow(this.values.get(i) - otherPoint.values.get(i), 2);
        }
        return sumVar;
    }
}
