import java.util.List;

public class Tester {
    public static void main(String[] args) {
        KNNModel KNNModel;
        String prediction = "";

        KNNModel = new KNNModel(1);
        List<DataPoint> training = DataLoader.createDataSet("data/mnist_train.csv");
        List<DataPoint> test = DataLoader.createDataSet("data/mnist_test.csv");
        KNNModel.addTrainingData(training);

        KNNModel.test(test);
    }
}