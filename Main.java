import java.io.*;
import java.util.Scanner;


public class Main {
    private static final int size = 'z' - 'a' + 'Z' - 'A' + 2;
    private int[] cnt = new int[size];
    private String inputName, outputName;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;

    public Main(String inputName, String outputName) {
        this.inputName = inputName;
        this.outputName = outputName;
        numChar();
        writeToFile();
    }

    private void numChar() {
        try {
            inputFile = new FileInputStream(inputName);
            int letter;
            while ((letter = inputFile.read()) != -1) {
                if (letter >= 'a' && letter <= 'z') {
                    cnt[letter - 'a']++;
                }
                else if (letter >= 'A' && letter <= 'Z') {
                    cnt[letter - 'A' + size / 2]++;
                }
            }
            inputFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException ex) {
            System.out.println("Ошибка при чтении файла!");
        }
    }

    private void writeToFile() {
        try {
            outputFile = new FileOutputStream(outputName, false);
            char sym;
            for (int i = 0; i < size; i++) {
                if (cnt[i] != 0) {
                    if (i < 26) {
                        sym = (char) ('a' + i);
                    }
                    else {
                        sym = (char) ('A' + i - size / 2);
                    }
                    String outputLine = sym + " - " + cnt[i] + "\n";
                    outputFile.write(outputLine.getBytes());
                }
            }
            outputFile.close();
        }
        catch (IOException ex) {
            System.out.println("Ошибка при записи в файл!");
        }
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер для выполнения одной из команд: ");
            System.out.println("1 - Подсчитать количество различных символов англ. алфавита");
            System.out.println("2 - Выйти из программы");
            int operation = scanner.nextInt();
            scanner.nextLine();
            switch (operation) {
                case 1:
                    System.out.println("Введите имя входного файла: ");
                    String inputName = scanner.nextLine();
                    System.out.println("Введите имя выходного файла: ");
                    String outputName = scanner.nextLine();
                    new Main(inputName, outputName);
                    break;
                case 2:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Такой команды не существует!");
            }
        }
    }
}
