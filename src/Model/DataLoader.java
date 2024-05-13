package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataLoader {

    private static String normalizeLineBreaks(String s) {
        s= s.replace('\u00A0',' '); // remove non-breaking whitespace characters
        s= s.replace('\u2007',' ');
        s= s.replace('\u202F',' ');
        s= s.replace('\uFEFF',' ');

        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static List<String> readFileAsStrings(String filepath) {
        try {
            return Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            System.err.println("-------------------------------------------------------");
            System.err.println("There was an error reading your data file: " + filepath);
            System.err.println("Check the file path!");
            System.err.println("-------------------------------------------------------");
        }

        return null;
    }

    public static void splitDataIntoTrainingAndTest(List<DataPoint> allData, List<DataPoint> emptyTrainingList, List<DataPoint> emptyTestList, double percentTraining) {
        Collections.shuffle(allData);   // This randomizes the order of allData

        // add percentTraining of the elements in allData to emptyTrainingList.
        // add all the rest of the items to emptyTestList

        // NOTE:  percentTraining is between 0 and 1, NOT 0 to 100%.
        // so, e.g., 0.6 represents 60%.
    }

    public static List<DataPoint> loadMNistData(String filepath) {
        return loadMNistData(filepath, -1);
    }

    /***
     * load data from an mnist csv file and return a List of DataPoint objects
     * @param filepath
     * @param num the number of examples to load (will be randomized).  -1 to load all.
     * @return
     */
    public static List<DataPoint> loadMNistData(String filepath, int num) {
        List<String> lines = readFileAsStrings(filepath);
        Collections.shuffle(lines);

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        if (num == -1) {
            num = lines.size();
        } else {
            if (num > lines.size()) {
                System.err.println("warning: specified n of " + num + " is larger than data set size of " + lines.size());
                num = lines.size();
            }
            lines.subList(num, lines.size()).clear();
        }

        for (int i = 0; i < num; i++) {
            String line = lines.remove(0);

            String[] values = line.split(",");
            String label = values[0];

            double[] featureVector = new double[values.length-1];
            for (int j = 0; j < featureVector.length; j++) {
                double grayValue = Double.parseDouble(values[j+1]);
                featureVector[j] = 255 - grayValue;                     // color reverse so background is white
                                                                        // this will let our interactive classifier
                                                                        // run better.
            }

            DataPoint point = new DataPoint(label, featureVector);
            dataset.add(point);
        }

        return dataset;
    }
}