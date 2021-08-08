package ITRex;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class TestTwo {

    public static void main(String[] args) {

        try {
            ArrayList<String> input = readFromFile("INPUT.TXT");
            ShortestTimeSearch shortestTimeSearch = new ShortestTimeSearch(input, 5);
            System.out.println(shortestTimeSearch.findShortestTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> readFromFile(String fileName) throws IOException {

        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }
}



