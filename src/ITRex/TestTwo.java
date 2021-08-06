package ITRex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class TestTwo {

    public static void main(String[] args) {

        try {
            // create reader
            File file = new File("INPUT.TXT");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();

            Coordinates initialCoordinates = new Coordinates(
                    line.charAt(0) - '0',
                    line.charAt(1) - '0',
                    line.charAt(2) - '0'
            );

            HashMap<String, Cell> cellHashMap = new HashMap<String, Cell>();

            int currentLevelNumber = 0;
            int currentRowNumber = 1;

            while (line != null) {
                line = reader.readLine();

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

                    if(value == '1') {

                    }

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

            HashMap<String, Integer> cellNameToIndex = getCellNameToIndexTo(cellHashMap);

            ArrayList<String> path = new ArrayList<>();

            String currentKey = "1_1_1";


            Double [] timeArray = new Double[cellNameToIndex.size()];

            for (int i = 0; i < cellNameToIndex.size(); i++){

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

    private static HashMap<String, Integer> getCellNameToIndexTo(HashMap<String, Cell> cellHashMap) {
        HashMap<String, Integer> cellNameToIndex = new HashMap<String, Integer>();
        String [] keyArray = cellHashMap.keySet().toArray(new String[cellHashMap.size()]);

        for (int i = 0; i < cellHashMap.size(); i++){
            cellNameToIndex.put(keyArray[i], i);
        }

        return cellNameToIndex;
    }

}



