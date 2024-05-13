import java.util.List;

public class TestClassifier {
    public static void main(String[] args) {
        KNNClassifier classifier;
        String prediction = "";

        classifier = new KNNClassifier(3);
        List<DataPoint> training = DataLoader.loadMNistData("data/mnist_train.csv");
        List<DataPoint> test = DataLoader.loadMNistData("data/mnist_test.csv");
        classifier.trainOnData(training);

        classifier.testOnData(test);
    }
}