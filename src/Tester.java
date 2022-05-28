import java.util.List;

public class Tester {
    public static void main(String[] args) {
        KNNModel KNNModel;
        String prediction = "";

        KNNModel = new KNNModel(5);
        List<DataPoint> training = DataLoader.createDataSet("mnist_train.csv");
        List<DataPoint> test = DataLoader.createDataSet("mnist_test.csv");
        KNNModel.addTrainingData(training);

        KNNModel.test(test);
    }
}