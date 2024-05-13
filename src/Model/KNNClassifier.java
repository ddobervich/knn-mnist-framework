package Model;

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

        ArrayList<DataPoint> closestN = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();

        double closestDist = Double.MAX_VALUE;
        DataPoint closestPoint = null;

        for (DataPoint p : trainingData) {
            double dist = distance(featureVector, p.getData());
            if (dist < closestDist) {
                dist = closestDist;
                closestPoint = p;
            }
        }

        return closestPoint.getLabel();
    }

    public static double distance(double[] d1, double[] d2) {
        double dist = 0;
        for (int i = 0; i < d1.length; i++) {
            double diff = d1[i] - d2[i];
            dist += diff*diff;
        }
        return Math.sqrt(dist);
    }

    public void testOnData(List<DataPoint> test) {
        testOnData(test, 1);
    }

    public void testOnData(List<DataPoint> test, int outputFreq) {
        ArrayList<DataPoint> correct = new ArrayList<>();
        ArrayList<DataPoint> wrong = new ArrayList<>();

        if (outputFreq < 1) outputFreq = test.size();

        int i = 0;
        for (DataPoint p : test) {
            String predict = classify(p.getData());
            if (i % outputFreq == 0)
                System.out.print("#" + i + " REAL:\t" + p.getLabel() + " predicted:\t" + predict);
            if (predict.equals(p.getLabel())) {
                correct.add(p);
                if (i % outputFreq == 0) System.out.print(" Correct ");
            } else {
                wrong.add(p);
                if (i % outputFreq == 0) System.out.print(" WRONG ");
            }

            i++;
            if (i % outputFreq == 0) System.out.println(" % correct: " + ((double) correct.size() / i));
        }

        System.out.println(correct.size() + " correct out of " + test.size());
        System.out.println(wrong.size() + " wrong out of " + test.size());
        System.out.println("% Error: " + (double) wrong.size() / test.size());
    }
}