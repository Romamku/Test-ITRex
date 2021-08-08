package ITRex;

import java.util.ArrayList;
import java.util.HashMap;

public class ShortestTimeSearch {
    private final double movementTime;
    private final HashMap<String, Cell> cellHashMap;
    private String princeKey;
    private String princessKey;

    public ShortestTimeSearch(ArrayList<String> input, int movementTime){
        this.movementTime = movementTime;
        this.cellHashMap = this.createCellHashMap(input);
    }

    public int findShortestTime() {
        String startKey = this.princeKey;
        String endKey = this.princessKey;

        String [] cellNameArray = cellHashMap.keySet().toArray(new String[cellHashMap.size()]);

        HashMap<String, Integer> cellNameToIndex = new HashMap<String, Integer>();

        for (int i = 0; i < cellHashMap.size(); i++){
            cellNameToIndex.put(cellNameArray[i], i);
        }

        String currentKey = startKey;

        ArrayList<String> path = new ArrayList<>();
        path.add(currentKey);

        Double[] timeArray = new Double[cellNameArray.length];
        timeArray[0] = 0.0;
        for (int i = 1; i < timeArray.length; i++) {
            timeArray[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 0; i < cellNameArray.length; i++){
            Cell cell = cellHashMap.get(currentKey);
            ArrayList<Cell> adjacentCellList = cell.adjacentCellList;

            for (int j = 0; j <adjacentCellList.size(); j++) {
                Cell adjacentCell = cell.adjacentCellList.get(j);
                if(!path.contains(adjacentCell.name)) {
                    int adjacentCellTimeArrayIndex = cellNameToIndex.get(adjacentCell.name);
                    double previousMinTime = timeArray[cellNameToIndex.get(currentKey)];
                    double currentTime = timeArray[adjacentCellTimeArrayIndex];
                    timeArray[adjacentCellTimeArrayIndex] = Math.min(currentTime, previousMinTime+movementTime);
                }
            }

            double minTime = Double.POSITIVE_INFINITY;
            String minTimeCellName = currentKey;
            for (int k = 0; k < timeArray.length; k++) {
                if (!path.contains(cellNameArray[k]) && timeArray[k] < minTime) {
                    minTime = timeArray[k];
                    minTimeCellName = cellNameArray[k];
                }
            }
            currentKey = minTimeCellName;
            path.add(currentKey);
        }

        return timeArray[cellNameToIndex.get(endKey)].intValue();
    }

    private Coordinates createInitialCoordinates(ArrayList<String> input){
        String firstLine = input.get(0);
        Coordinates initialCoordinates = new Coordinates(
                firstLine.charAt(0) - '0',
                firstLine.charAt(1) - '0',
                firstLine.charAt(2) - '0'
        );

        return initialCoordinates;
    }

    private ArrayList<String[]> createBlocks(ArrayList<String> input) {
        ArrayList<String[]> result = new ArrayList<>();
        ArrayList<String> modifiedInput = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (i != 0 && !input.get(i).isEmpty()) {
                modifiedInput.add(input.get(i));
            }
        }

        Coordinates initialCoordinates = this.createInitialCoordinates(input);
        for (int i = 0; i < initialCoordinates.level; i++) {

            String[] levelBlock = new String[initialCoordinates.row];
            for (int j = 0; j < initialCoordinates.row; j++) {
                levelBlock[j] = modifiedInput.get(i * initialCoordinates.row + j);
            }
            result.add(levelBlock);
        }

        return result;
    }

    private HashMap<String, Cell> createCellHashMap(ArrayList<String> input) {
        ArrayList<String[]> blocks = this.createBlocks(input);
        HashMap<String, Cell> cellHashMap = new HashMap<String, Cell>();

        for (int levelIndex = 0; levelIndex < blocks.size(); levelIndex++) {
            String[] level = blocks.get(levelIndex);

            for (int rowIndex = 0; rowIndex < level.length; rowIndex++) {
                String row = level[rowIndex];

                for (int columnIndex = 0; columnIndex < row.length(); columnIndex++) {
                    char value = row.charAt(columnIndex);
                    if (value != 'O') {
                        Coordinates coordinates = new Coordinates(levelIndex, rowIndex, columnIndex);
                        Cell cell = new Cell(coordinates);
                        cellHashMap.put(cell.name, cell);

                        if (value == '1') {
                            princeKey = cell.name;
                        }

                        if (value == '2') {
                            princessKey = cell.name;
                        }
                    }
                }
            }
        }

        for (Cell cell : cellHashMap.values()) {
            cell.fillAdjacentCellList(cellHashMap);
        }

        return  cellHashMap;
    }

    private String getKey(int levelIndex, int rowIndex, int columnIndex) {
        return levelIndex + "_" +  rowIndex + "_" + columnIndex;
    }
}
