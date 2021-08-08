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

            String line = "";

            double movementTime = 5;

            HashMap<String, Cell> cellHashMap = new HashMap<String, Cell>();

            int currentLevelNumber = 0;
            int currentRowNumber = 1;

            while (line != null) {
                line = "";

                if (line == null) {
                    break; // после считывания последней строки выходим из цикла
                }

                if (line.isEmpty()) {
                    currentLevelNumber++;
                    currentRowNumber = 0;
                    continue; // для пустой строки, после обновления счетчиков, переходим на следующую итерацию цикла
                }

                currentRowNumber++;

                for (int currentColumnNumber = 0; currentColumnNumber < line.length(); currentColumnNumber++) {
                    char value = line.charAt(currentColumnNumber);

                    if (value != 'O') {
                        Coordinates currentCoordinates = new Coordinates(
                                currentLevelNumber,
                                currentRowNumber,
                                currentColumnNumber + 1
                        );
                        addCellsToHashMap(currentCoordinates, cellHashMap);
                    }
                }
            }

            for (Cell cell : cellHashMap.values()) {
                cell.fillAdjacentCellList(cellHashMap);
            }

            HashMap<String, Integer> cellNameToIndex = getCellNameToTimeArrayIndex(cellHashMap);

            String currentKey = "1_1_1";

            ArrayList<String> path = new ArrayList<>();
            path.add(currentKey);

            Double[] timeArray = new Double[cellNameToIndex.size()];
            timeArray[0] = 0.0;
            for (int i = 1; i < timeArray.length; i++) {
                timeArray[i] = Double.POSITIVE_INFINITY;
            }

            for (int i = 0; i < cellNameToIndex.size(); i++){
                Cell cell = cellHashMap.get(currentKey);
                ArrayList<Cell> adjacentCellList = cell.adjacentCellList;

                for (int j = 0; j <adjacentCellList.size(); j++) {
                    Cell adjacentCell = cell.adjacentCellList.get(j);
                    int adjacentCellTimeArrayIndex = cellNameToIndex.get(adjacentCell.name);
                    timeArray[adjacentCellTimeArrayIndex] = movementTime;

                    System.out.println();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addCellsToHashMap(Coordinates currentCoordinates, HashMap<String, Cell> cellHashMap) {
        Cell cell = new Cell(currentCoordinates);
        cellHashMap.put(cell.name, cell);
    }

    //написать о том зачем нам индекс -> к таймэрэю

    private static HashMap<String, Integer> getCellNameToTimeArrayIndex(HashMap<String, Cell> cellHashMap) {
        HashMap<String, Integer> cellNameToIndex = new HashMap<String, Integer>();
        String [] keyArray = cellHashMap.keySet().toArray(new String[cellHashMap.size()]);

        for (int i = 0; i < cellHashMap.size(); i++){
            cellNameToIndex.put(keyArray[i], i);
        }

        return cellNameToIndex;
    }

    private static String findNextKey (Double[] timeArray, HashMap<String, Integer> cellNameToTimeArrayIndex, ArrayList<String> path){
        double minValue = Double.POSITIVE_INFINITY;
        String minKey;

        for(int i = 0; i < timeArray.length; i++) {
            minValue = timeArray[i];



        }

        return "";
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



