import java.util.List;

public class TestClassifier {
    public static void main(String[] args) {
        Classifier classifier;
        String prediction = "";

        classifier = new Classifier(3);
        List<DataPoint> training = DataLoader.loadMNistData("data/mnist_train.csv");
        List<DataPoint> test = DataLoader.loadMNistData("data/mnist_test.csv");
        classifier.addTrainingData(training);

        classifier.test(test);
    }
}