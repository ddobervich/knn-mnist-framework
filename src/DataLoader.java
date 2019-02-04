import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private static String normalizeLineBreaks(String s) {
        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public static String readFileAsString(String filepath) {
        ClassLoader classLoader = DataLoader.class.getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        // Read File Content
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND: " + filepath);
            e.printStackTrace();
        }

        return content;
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
                    i++;
                    if (val.equals("0")) {
                        pixels[r][c] = 255;
                    } else {
                        pixels[r][c] = 0;
                    }
                }
            }

            DImage image = new DImage(28, 28);
            image.setPixels(pixels);

            dataset.add( new DataPoint(label, image) );
        }

        return dataset;
    }
}