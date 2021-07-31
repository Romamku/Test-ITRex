package ITRex;

import java.util.ArrayList;

public class TestOne {
    public static void main(String[] args) {
        String inputOne = "cacao and coffee";
        String inputTwo = "success";

        String oneAfterStep3 = Step3(Step2(Step1(inputOne)));
        String twoAfterStep3 = Step3(Step2(Step1(inputTwo)));

        System.out.println(Step4(oneAfterStep3, inputOne));
        System.out.println(Step4(twoAfterStep3, inputTwo));
    }
    public static String Step1(String input) {
        input = input.replace("ce", "se");
        input = input.replace("ci", "si");
        input = input.replace("ck", "k");
        input = input.replace("c", "k");
        return input;
    }

    public static String Step2(String input) {
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

    public static String Step3(String input) {
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

    public static String Step4(String input, String originalInput) {
        String[] originalWords = originalInput.split(" "); // создал массив слов из строки
        ArrayList<String> resultList = new ArrayList<>();
        String[] inputWords = input.split( " ");

        for (int i = 0; i<originalWords.length; i++) {
            String inputValue = inputWords[i];
            String originalValue = originalWords[i];

            if (!originalValue.equals("the")  && !originalValue.equals("a") && !originalValue.equals("an")) {
                resultList.add(inputValue);
            }
        }
        String result = String.join(" ", resultList);
        return result;
    }
}