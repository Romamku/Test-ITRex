package ITRex;

import java.util.ArrayList;

public class TestOne {
    public static void main(String[] args) {
        String inputOne = "cacao and coffee";
        String inputTwo = "success";

        System.out.println("Result after the first step (input \"success\"): " + removeCFromTheText(inputTwo));
        System.out.println("Result after the second step (input \"ooo\", \"oou\", \"iee\"): " + removeDoubleLetter("ooo") + ", " + removeDoubleLetter("oou") + ", " + removeDoubleLetter("iee"));
        System.out.println("Result after the third step (input \"The\"): " + removeTheLetterAtTheEnd("The"));
        System.out.println("Result after the fourth step (input \"the table\"): " + removeArticles("the table"));

        System.out.println("The result of the entire task (input \"cacao and coffee\", \"success\"):");
        System.out.println(removeTheLetterAtTheEnd(removeDoubleLetter(removeCFromTheText(removeArticles(inputOne)))));
        System.out.println(removeTheLetterAtTheEnd(removeDoubleLetter(removeCFromTheText(removeArticles(inputTwo)))));
    }
    private static String removeCFromTheText(String input) {
        input = input.replace("ce", "se");
        input = input.replace("ci", "si");
        input = input.replace("ck", "k");
        input = input.replace("c", "k");
        return input;
    }

    private static String removeDoubleLetter(String input) {
        input = input.replace("ee", "i");
        input = input.replace("oo", "u");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {

            if (i==0 || input.charAt(i) != input.charAt(i-1)) {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }

    private static String removeTheLetterAtTheEnd(String input) {
        String[] words = input.split(" "); //создал масив из слов с разделитем пробел
        ArrayList<String> resultList = new ArrayList<>();

        for (String word : words) { // перебираем все слова в массиве

            if (word.length() > 1 && word.endsWith("e")) { // выполняем условие
                word = word.substring(0, word.length() - 1); // удаляем "е"
                resultList.add(word);
            }
            else {resultList.add(word);}
        }
        String result = String.join(" ", resultList);
        return result;
    }

    private static String removeArticles(String input) {
        String[] inputWords = input.split( " ");
        ArrayList<String> resultList = new ArrayList<>();

        for (int i = 0; i<inputWords.length; i++) {
            String inputValue = inputWords[i];

            if (!inputValue.equals("the")  && !inputValue.equals("a") && !inputValue.equals("an")) {
                resultList.add(inputValue);
            }
        }
        String result = String.join(" ", resultList);
        return result;
    }
}