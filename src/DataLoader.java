import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private static String normalizeLineBreaks(String s) {
        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static String readFileAsString(String filePath) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }

            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath);
        }

        return null;
    }

    public static List<DataPoint> createDataSet(String filepath) {
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");

        // create storage for data
        ArrayList<DataPoint> dataset = new ArrayList<>();

        for (int a = 0; a < lines.length; a++) {
            String line = lines[a];
            String[] lineData = line.split(",");

            String label = lineData[0];
            short[][] pixels = new short[28][28];

            int i = 1;  // start from index 1 in 1d array
            for (int r = 0; r < pixels.length; r++) {
                for (int c = 0; c < pixels[r].length; c++) {
                    String val = lineData[i];
                    if (val.equals("0")) {
                        pixels[r][c] = 255;
                    } else {
                        pixels[r][c] = 0;
                    }
                    i++;
                }
            }

            DImage image = new DImage(28, 28);
            image.setPixels(pixels);

            dataset.add( new DataPoint(label, image) );
        }

        return dataset;
    }
}