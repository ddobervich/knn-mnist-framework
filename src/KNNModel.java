import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class KNNModel {
    private ArrayList<DataPoint> trainingData;
    private int n;

    public KNNModel(int n) {
        this.n = n;
        trainingData = new ArrayList<DataPoint>();
    }

    public void addTrainingData(List<DataPoint> points) {
        trainingData.addAll(points);
    }

    public void addTrainingData(DataPoint point) {
        trainingData.add(point);
    }

    public void addTrainingData(String label, DImage img) {
        addTrainingData(new DataPoint(label, img));
    }

    public String classify(short[][] pixels) {
        if (trainingData.size() == 0) return "no training data";

        ArrayList<DataPoint> closestN = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();

        for (DataPoint p : trainingData) {
            double dist = distance(pixels, p.getData().getBWPixelGrid());
            updateList(closestN, distances, p, dist);
        }

        return majorityVoteFrom(closestN);
    }

    private String majorityVoteFrom(ArrayList<DataPoint> closestN) {
        HashMap<String, Integer> votes = new HashMap<String, Integer>();
        for (DataPoint p : closestN) {
            if (votes.containsKey(p.getLabel())) {
                int numvotes = votes.get(p.getLabel());
                votes.put(p.getLabel(), numvotes+1);   // add one to label for vote
            } else {
                votes.put(p.getLabel(), 1);
            }
        }

        String winningLabel = "";
        int maxVotes = 0;
        for (String label : votes.keySet()) {
            if (votes.get(label) > maxVotes) {
                maxVotes = votes.get(label);
                winningLabel = label;
            }
        }

        return winningLabel;
    }

    private void updateList(ArrayList<DataPoint> closestN, ArrayList<Double> distances, DataPoint p, double dist) {
        if (distances.size() == 0) {        // add the first element
            distances.add(dist);
            closestN.add(p);
            return;
        }

        if (distances.size() < n) {
            int index = findIndexToInsert(distances, dist);
            distances.add(index, dist);
            closestN.add(index, p);
            return;
        }

        int index = findIndexToInsert(distances, dist);

        if (index > closestN.size() - 1) return;        // if it's last, don't add it

        distances.add(index, dist);                     // otherwise add it
        closestN.add(index, p);

        closestN.remove(closestN.size() - 1);      // and remove whatever's last
        distances.remove(closestN.size() - 1);

        return;
    }

    private int findIndexToInsert(ArrayList<Double> distances, double dist) {
        int i = distances.size() - 1;
        while (i >= 0 && dist < distances.get(i)) {
            i--;
        }

        return i+1;
    }

    public String classify(DImage img) {
        return classify(img.getBWPixelGrid());
    }

    public double distance(short[][] d1, short[][] d2) {
        double sum = 0;
        for (int r = 0; r < d1.length; r++) {
            for (int c = 0; c < d1[r].length; c++) {
                int delta = d1[r][c] - d2[r][c];
                sum += delta*delta;
            }
        }

        return sum;
    }

    public void test(List<DataPoint> test) {
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
            System.out.println(" % correct: " + ((double)correct.size()/i));
        }

        System.out.println(correct.size() +  " correct out of " + test.size());
        System.out.println(wrong.size() +  " correct out of " + test.size());
        System.out.println("% Error: " + (double)wrong.size()/test.size());
    }
}
