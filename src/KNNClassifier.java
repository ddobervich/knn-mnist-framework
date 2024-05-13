import java.util.ArrayList;
import java.util.List;

public class KNNClassifier {
    private ArrayList<DataPoint> trainingData;
    private int k;

    public KNNClassifier(int k) {
        this.k = k;
        trainingData = new ArrayList<DataPoint>();
    }

    public void trainOnData(List<DataPoint> points) {
        trainingData.addAll(points);
    }

    public void trainOnData(DataPoint point) {
        trainingData.add(point);
    }

    public String classify(double[] featureVector) {
        if (trainingData.size() == 0) return "no training data";

        // TODO: write a k-nearest-neighbor classifier.  Return its prediction of "0" to "9"

        return "no prediction";  // replace this line
    }

    public static double distance(double[] d1, double[] d2) {
        // TODO:  Use the n-dimensional Euclidean distance formula to find the distance between d1 and d2

        return -1;
    }

    public void testOnData(List<DataPoint> test) {
        ArrayList<DataPoint> correct = new ArrayList<>();
        ArrayList<DataPoint> wrong = new ArrayList<>();

        int i = 0;
        for (DataPoint p : test) {
            String predict = classify(p.getData());
            System.out.print("#" + i + " REAL:\t" + p.getLabel() + " predicted:\t" + predict);
            if (predict.equals(p.getLabel())) {
                correct.add(p);
                System.out.print(" Correct ");
            } else {
                wrong.add(p);
                System.out.print(" WRONG ");
            }

            i++;
            System.out.println(" % correct: " + ((double) correct.size() / i));
        }

        System.out.println(correct.size() + " correct out of " + test.size());
        System.out.println(wrong.size() + " wrong out of " + test.size());
        System.out.println("% Error: " + (double) wrong.size() / test.size());
    }
}