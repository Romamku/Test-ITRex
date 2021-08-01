package ITRex;

import java.io.*;
import java.util.ArrayList;


public class TestTwo {

    public static void main(String[] args) {

        try {
            File file = new File("INPUT.TXT");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            int levelNumber = line.charAt(0) - '0';
            int rowNumber = line.charAt(1) - '0';
            int columnNumber = line.charAt(2) - '0';

            ArrayList<Level> levelList = createLevelList(levelNumber);

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
                    continue; // для пустой строки,после обновления счетчиков, переходим на следующую итерацию цикла
                }

                currentRowNumber++;

                for (int currentColumnNumber = 0; currentColumnNumber<line.length(); currentColumnNumber++) {
                    String name = "H" + currentLevelNumber + "M" +  currentRowNumber + "N" + (currentColumnNumber+1);
                    char value = line.charAt(currentColumnNumber);
                    Cell cell = new Cell(name, value);
                    levelList.get(currentLevelNumber-1).cellList.add(cell);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static ArrayList<Level> createLevelList(int levelNumber) {
        ArrayList<Level> levelList = new ArrayList<>();

        for(int i = 1; i <= levelNumber; i++) {
            levelList.add(new Level("H" + i));
        }

        return levelList;
    }

}

class Cell {
   public String name;
   public char value;

   public Cell (String name, char value) {
       this.name = name;
       this.value = value;
   }
}
    class Level {
        public String name;
        ArrayList<Cell> cellList;

        public Level (String name) {
            this.name = name;
            this.cellList = new ArrayList<Cell>();
        }
    }


