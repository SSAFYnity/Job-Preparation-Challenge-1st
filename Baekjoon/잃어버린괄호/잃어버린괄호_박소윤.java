import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] strNumbers = input.split("[\\+,\\-]");
        int[] numbers = new int[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[i] = Integer.parseInt(strNumbers[i]);
        }

        String strOperator = input.replaceAll("[^\\+,\\-]", "");
        char[] operators = new char[strOperator.length()];
        for (int i = 0; i < strOperator.length(); i++) {
            operators[i] = strOperator.charAt(i);
        }

        int result = numbers[0];
        boolean existsInBracket = false;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == '+') {
                if (existsInBracket) {
                    result -= numbers[i + 1];
                } else {
                    result += numbers[i + 1];
                }
            }
            if (operators[i] == '-') {
                result -= numbers[i + 1];
                existsInBracket = true;
            }
        }
        System.out.println(result);
    }
}
