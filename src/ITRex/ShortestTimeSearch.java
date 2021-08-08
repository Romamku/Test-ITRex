package ITRex;

import java.util.ArrayList;

public class ShortestTimeSearch {
    private final double movementTime;
    private final Coordinates initialCoordinates;

    public ShortestTimeSearch(ArrayList<String> input, int movementTime){
        this.movementTime = movementTime;
        this.initialCoordinates = this.createInitialCoordinates(input);
        ArrayList<String[]> blocks = this.createBlocks(input);
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

        for (int i = 0; i < this.initialCoordinates.level; i++) {

            String[] levelBlock = new String[this.initialCoordinates.row];
            for (int j = 0; j < this.initialCoordinates.row; j++) {
                levelBlock[j] = modifiedInput.get(i * this.initialCoordinates.row + j);
            }
            result.add(levelBlock);
        }
        return result;
    }

}
