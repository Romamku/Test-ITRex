package ITRex;

import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
    public String name;
    private final Coordinates coordinates;
    private final ArrayList<Cell> adjacentCellList;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.adjacentCellList = new ArrayList<Cell>();
        this.name = createName(coordinates.level, coordinates.row, coordinates.column);
    }

    public void fillAdjacentCellList(HashMap<String, Cell> cellHashMap) {
        String[] adjacentCellNames = this.getAdjacentCellNames();

        for (int i = 0; i < adjacentCellNames.length; i++) {
            String adjacentCellName = adjacentCellNames[i];

            if (cellHashMap.containsKey(adjacentCellName)) {
                Cell cell = cellHashMap.get(adjacentCellName);
                adjacentCellList.add(cell);
            }
        }
    }

    private String[] getAdjacentCellNames() {
        int level = coordinates.level;
        int row = coordinates.row;
        int column = coordinates.column;

        String[] adjacentCellNames = new String[6];

        adjacentCellNames[0] = createName(level, row - 1, column);
        adjacentCellNames[1] = createName(level, row + 1, column);
        adjacentCellNames[2] = createName(level, row, column + 1);
        adjacentCellNames[3] = createName(level, row, column - 1);
        adjacentCellNames[4] = createName(level - 1, row, column);
        adjacentCellNames[5] = createName(level + 1, row, column);

        return adjacentCellNames;
    }

    private String createName(int level, int row, int column) {

        return  level + "_" +  row + "_" + column;
    }
}
