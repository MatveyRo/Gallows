import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);
        File file = new File("wordsm.txt");
        Scanner sc = new Scanner(file); // "CP1251" считывание из файла русской кирилицы
        ArrayList<String> arrayList = new ArrayList<>();

        while (true) {

            Random random = new Random();
            while (sc.hasNextLine()) {
                arrayList.add(sc.nextLine());
            }

            String word = arrayList.get(random.nextInt(0, arrayList.size() - 1)); // рандомный вывод слова из массива
            String smallLetters = word.toLowerCase();
            String[] arrWord = new String[smallLetters.length()];
            System.out.println("[Н]овая Игра или [В]ыход");
            String gameStart = in.nextLine().toLowerCase();

            if (gameStart.contains("н")) {
                System.out.println("Добро пожаловать в игру «Виселица»: ");
                System.out.println("Правила игры: * - это колличество букв, нужно отгадать слово.");
                for (int i = 0; i < arrWord.length; i++) {
                    arrWord[i] = "*";
                    System.out.print(arrWord[i] + " ");
                }
                int index = 0;
                int countoff = 0;
                boolean bWord = true; // проверка все ли буквы угаданны
                StringBuffer bufLetter = new StringBuffer();//буффер для хранения угаданных букв
                while (bWord && countoff < 6) {
                    boolean bLetter = true; // проверка нескольких букв в слове
                    System.out.println("\n" + "Введите букву: ");
                    String letter = in.next().toLowerCase();
                    System.out.println(" ");
                    if (bufLetter.indexOf(letter) != -1) {//проверяем повторный ввод буквы
                        System.out.println("Такую букву уже вводили");
                    } else {
                        //проверяем наличие буквы
                        if ((index = smallLetters.indexOf(letter)) != -1) {
                            System.out.println("Угадали букву");
                            bufLetter.append(letter);
                            arrWord[index] = letter;
                            while (bLetter) {//цикл для нескольких одинаковых букв в слове
                                if ((index = smallLetters.indexOf(letter, index + 1)) != -1) {
                                    arrWord[index] = letter;
                                } else {
                                    bLetter = false;
                                }
                            }
                        } else if (countoff == 5) {
                            System.out.println("Вы проиграли!");
                            System.out.println("Загаданное слово было: " + smallLetters + "\n");
                            break;
                        } else {
                            countoff++;
                            System.out.println("Такой буквы нет, у вас осталось ошибок: " + (6 - countoff));
                            bufLetter.append(letter);
                            // рисуем виселицу
                            if (countoff >= 2) {
                                System.out.print(" ___");
                                System.out.print("\n" + "|/  |" + "\n");
                            }
                            if (countoff >= 3) {
                                System.out.print("|   *" + "\n");
                            }
                            if (countoff >= 4) {
                                System.out.print("|  /||" + "\n");
                            }
                            if (countoff >= 5) {
                                System.out.print("|   | " + "\n");
                            }
                            if (countoff < 6) {
                                for (int i = 0; i < (6 - countoff); i++) {
                                    System.out.println("|");
                                }
                            }
                        }
                    }
                    //снова печатаем зашифрованное слово
                    for (String s : arrWord) {
                        System.out.print(s + " ");
                    }

                    System.out.println("\n" + "Вы использовали буквы: " + bufLetter + " ");

                    int iWord = 0; //проверка все ли буквы угаданы
                    for (int m = 0; m < arrWord.length; m++) {
                        if (arrWord[m].equals("*")) {
                            iWord++;
                        }
                    }
                    if (iWord == 0) {//если не осталось не угаданных букв - завершаем угадывание
                        bWord = false;
                        System.out.println("Вы угадали слово!" + "\n");
                        break;
                    }
                }
            } else if (gameStart.contains("в")) {
                System.out.println("Вы вышли из игры");
                break;
            }
        }
    }
}