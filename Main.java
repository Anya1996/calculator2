package calcRA;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите выражение. Все знаки должны быть разделены пробелом");
        System.out.println("Калькулятор принимает арабские и римские числа от 1 до 10 (I-X)");
        try (Scanner sc = new Scanner(System.in)) {
            String result = new Calculator().calculate(sc.nextLine().trim().split(" "));
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Неверные значения");
        }
    }
}
class Convert {
    private final static TreeMap<Integer, String> arabic = new TreeMap<Integer, String>();

    static {
        arabic.put(100, "C");
        arabic.put(90, "XC");
        arabic.put(50, "L");
        arabic.put(40, "XL");
        arabic.put(10, "X");
        arabic.put(9, "IX");
        arabic.put(5, "V");
        arabic.put(4, "IV");
        arabic.put(1, "I");
    }

    private final static TreeMap<String, Integer> roman = new TreeMap<String, Integer>();

    static {
        roman.put("I", 1);
        roman.put("II", 2);
        roman.put("III", 3);
        roman.put("IV", 4);
        roman.put("V", 5);
        roman.put("VI", 6);
        roman.put("VII", 7);
        roman.put("VIII", 8);
        roman.put("IX", 9);
        roman.put("X", 10);
    }

    public String toRome(int number) {
        int n = arabic.floorKey(number);
        return number == n ? arabic.get(number) : arabic.get(n) + toRome(number - n);
    }

    public int toArab(String number) {
        return roman.get(number);
    }
}
class Calculator {
    private final Convert converter;
    public Calculator() {
        this.converter = new Convert();
    }
    public String calculate(String [] args) {
        if (args.length!=3) throw new IllegalArgumentException();
        try {
            int result = calculate(Integer.parseInt(args[0]), Integer.parseInt(args[2]), args[1]);
            return String.valueOf(result);

            //римские или арабские

        } catch (NumberFormatException e) {
            int result = calculate (converter.toArab(args[0]), converter.toArab(args[2]), args[1]);
            return converter.toRome(result);
        }
    }
    //работа с оператором и проверка на величину цифр
    private int calculate(int firstNum, int secondNum, String op) {
        if (firstNum < 1 || firstNum > 10 || secondNum < 1 || secondNum > 10) throw new IllegalArgumentException();
        switch (op) {
            case "+": return firstNum + secondNum;
            case "/": return firstNum / secondNum;
            case "*": return firstNum * secondNum;
            case "-": return firstNum - secondNum;
            default: throw new IllegalArgumentException();
        }
    }
}




