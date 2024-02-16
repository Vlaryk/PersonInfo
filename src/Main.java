import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        while (true) {
            try {
                run();
                break;
            }
            catch (CustomException e) {
                System.out.println(e.getMessage());
                System.out.println("Попробуйте снова");
            }
        }
    }

    public static void run() throws CustomException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите: Фамилию, Имя, Отчество, дату_рождения, номер_телефона, пол");
        String str = scan.nextLine();
        ArrayList<String> array = new ArrayList<>(List.of(str.split(" ")));
        if (array.size() != 6) {
            throw new CustomException("Неверно количество введенных данных");
        }

        if (array.get(0).length() < 2) {
            throw new CustomException("Некорректно введенно имя");
        }

        if (array.get(1).length() < 2) {
            throw new CustomException("Некорректно введенна фамилия");
        }

        if (array.get(2).length() < 2) {
            throw new CustomException("Некорректно введенно отчество");
        }

        ArrayList<String> arrayData = new ArrayList<>(List.of(array.get(3).split("\\.")));
        if (arrayData.size() != 3) {
            throw new CustomException("Некорректно введенна дата рождения");
        }
        for (int i = 0; i < arrayData.size(); i++) {
            if (i < 2) {
                if (arrayData.get(i).length() != 2) {
                    throw new CustomException("Некорректно введенна дата рождения");
                }
            }
            else {
                if (arrayData.get(i).length() != 4) {
                    throw new CustomException("Некорректно введенна дата рождения");
                }
            }
        }

        if (array.get(4).length() != 11) {
            throw new CustomException("Неверная длина номера");
        }
        try {
            double kl = Double.parseDouble(array.get(4));
        }
        catch (NumberFormatException e) {
            throw new CustomException("Неверный формат номера");
        }

        if (!array.get(5).equals("m") && !array.get(5).equals("f")) {
            throw new CustomException("Некорректно введен пол");
        }

        noteFile(array.get(0), str);

    }

    static void noteFile (String name,String str) {
        try (FileWriter fileWriter = new FileWriter(name, true)) {
            fileWriter.write(str + "\n");
        }
        catch (IOException e) {
            System.out.println("Произошла ошибка ввода вывода: ");
            e.printStackTrace();
        }
    }

}