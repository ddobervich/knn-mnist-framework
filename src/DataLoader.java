import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DataLoader {

    private static String normalizeLineBreaks(String s) {
        s= s.replace('\u00A0',' '); // remove non-breaking whitespace characters
        s= s.replace('\u2007',' ');
        s= s.replace('\u202F',' ');
        s= s.replace('\uFEFF',' ');

        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static String readFileAsString(String filepath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner( new File(filepath) );
            String text = scanner.useDelimiter("\\A").next();
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close(); // Put this call in a finally block
        }

        System.err.println("-------------------------------------------------------");
        System.err.println("There was an error reading your data file: " + filepath);
        System.err.println("Check the file path!");
        System.err.println("-------------------------------------------------------");

        return null;
    }

    public static List<DataPoint> loadHeartDiseaseData(String filepath) {
        /* NOTE:  THIS IS ONLY FOR LOADING HEART DISEASE DATA; NOT FOR HAND-WRITTEN DIGIT RECOGNITION */
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
            // TODO: create DataPoint objects here!
        }

        return dataset;
    }

    public static void splitDataIntoTrainingAndTest(List<DataPoint> allData, List<DataPoint> emptyTrainingList, List<DataPoint> emptyTestList, double percentTraining) {
        Collections.shuffle(allData);   // This randomizes the order of allData

        // add percentTraining of the elements in allData to emptyTrainingList.
        // add all the rest of the items to emptyTestList

        // NOTE:  percentTraining is between 0 and 1, NOT 0 to 100%.
        // so, e.g., 0.6 represents 60%.
    }

    public static List<DataPoint> loadMNistData(String filepath) {
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
             /* TODO:
                For each line in the lines array:
                    split line by , to get all coordinates

                    get the correct label for this data point (the first number in the array).
                    create a double array for all of the other pixel values (starting at index 1)

                    Run the DataPoint constructor, giving it the correct label and the double[]

                    Add the DataPoint to your dataset list.
         */
        }

        return dataset;
    }
}