import java.util.ArrayList;
import java.util.List;

public class TestHeartDiseaseClassifier {
    public static void main(String[] args) {
        KNNClassifier classifier;
        String prediction = "";

        classifier = new KNNClassifier(5);
        List<DataPoint> allData = DataLoader.loadHeartDiseaseData("data/heart.csv");
        List<DataPoint> training = new ArrayList<>();
        List<DataPoint> test = new ArrayList<>();
        DataLoader.splitDataIntoTrainingAndTest(allData, training, test, .6);

        classifier.trainOnData(training);

        classifier.testOnData(test);
    }
}