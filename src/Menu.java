import java.io.*;
import java.util.Scanner;

public class Menu {
    public static FibNum fibNum; //ссылка на обьекта класса FibNum
    public static Array limit; //ссылка на обьект класса Array
    //Вызов меню
    public static void start(){
        mainMenu();
    }
    //Использование сканнера с исключениями
    //Функция для чтения, обрабатывающая ошибки ввода
    public static int readChoice(){
        Scanner in = new Scanner(System.in);
        int readed;
        try {
             readed = Integer.parseInt(in.nextLine());
        }
        catch(NumberFormatException ex){
            System.out.println("Вводите только значения пунктов меню!");
            readed = readChoice();
        }
        return readed;
    }
    //Главное меню
    private static void mainMenu(){        //Главное меню
        int choice;
        do {
            System.out.println("Главное меню");
            System.out.println("1 - Числа Фибоначчи");
            System.out.println("2 - массив");
            System.out.println("3 - среднее масива");
            System.out.println("4 - конвертация");
            System.out.println("0 - Выход");
            choice = readChoice();

            switch (choice){
                case 1: fibNumInMenu();
                    break;
                case 2:
                    ArrayLimit();
                    break;
                case 3:
                    AvarageArrayInMenu();
                    break;
                case 4:
                    convertInMenu();
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //вывод в файл
    private static void ToFile(String[] text, String filename){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))){
            for (String s : text) {
                dos.writeUTF(s);
                dos.writeUTF("\n");
            }
            System.out.println("Сохранение успешно в файле " + filename);
        }
        catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }
    //Чтение из файла числа
    private static int NumReader(String filepath) {
        int readed;
        StringBuilder str = new StringBuilder();
        try (FileInputStream dis = new FileInputStream(filepath)) {
            int i;
            char cc;
            while ((i = dis.read()) != -1) {
                cc = ((char) i);
                str.append(cc);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
        readed = Integer.parseInt(str.toString());
        return readed;
    }
    //Чтение массива из файл
    private static String[] ArrReader(String filepath) {
        StringBuilder str = new StringBuilder();
        try (FileInputStream dis = new FileInputStream(filepath)) {
            int i;
            char cc;
            while ((i = dis.read()) != -1) {

                cc = ((char) i);
                str.append(cc);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");

        }
        return str.toString().split(" ");
    }
    //Ввести необходимый номер нужного числа Фибоначчи
    private static void fibNumInMenu(){
        int choice;
        do {
            System.out.println("Ввод ограничения вывода");
            System.out.println("1 - Ввести с консоли");
            System.out.println("2 - Ввести из файла");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1: fibNum = new FibNum();
                    System.out.println("ведите желаемый номер числа Фибоначчи:");
                    int numberIn = readChoice();
                    String[] text = FibNum.reciveCurrent(fibNum, numberIn);
                    fibNumOutMenu(text, numberIn);
                    break;
                case 2:
                    int readedNumberIn = NumReader("fibIn.dat");
                    fibNum = new FibNum();
                    String[] str = FibNum.reciveCurrent(fibNum, readedNumberIn);
                    fibNumOutMenu(str, readedNumberIn);
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //вывести число Фибоначчи
    private static void fibNumOutMenu(String[] text, int numberIn){
        int choice;
        do {
            System.out.println("Способ вывода");
            System.out.println("1 - Вывести в консоль");
            System.out.println("2 - Сохранить в файл");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    for (int i = 0; i < numberIn; i++) {
                        System.out.println(text[i]);
                    }
                    break;
                case 2:
                    ToFile(text, "fibNum.txt");
                    System.out.println("Данные записаны в файл");
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Ввести ограничение вывода массива
    private static void ArrayLimit(){
        int choice;
        do {
            System.out.println("Ввод ограничения вывода");
            System.out.println("1 - Ввести с консоли");
            System.out.println("2 - Ввести из файла");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    System.out.println("ведите ограничение вывода");
                    int numberIn = readChoice();
                    ArrayInMenu(numberIn);
                    break;
                case 2:
                    int readedNumberIn = NumReader("arrLimit.dat");
                    ArrayInMenu(readedNumberIn);
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Заполнить массив
    private static void ArrayInMenu(int numberIn){
        int choice;
        do {
            System.out.println("Ввод ограничения вывода");
            System.out.println("1 - заполнить случайными числами");
            System.out.println("2 - заполнить из файла");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    System.out.println("Введите размерность массива:");
                    int lenAr = readChoice();
                    limit = new Array(lenAr);
                    limit.FillRandom();
                    String[] arr = Array.PrintArray(limit, numberIn);
                    ArrayOutMenu(arr, numberIn);
                    break;
                case 2:
                    String[] readedArrayIn = ArrReader("ArrayIn.dat");
                    int [] newArr = new int[readedArrayIn.length];
                    for(int i = 0; i < readedArrayIn.length; i++){
                        newArr[i] = Integer.parseInt(readedArrayIn[i]);
                    }
                    Array outArray = new Array(readedArrayIn.length);
                    outArray.setNumbersArr(newArr);
                    readedArrayIn = Array.PrintArray(outArray, numberIn);
                    ArrayOutMenu(readedArrayIn, numberIn);

                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Вывести массив
    private static void ArrayOutMenu(String[] text, int limit){
        int choice;
        do {
            System.out.println("Способ вывода");
            System.out.println("1 - Вывести в консоль");
            System.out.println("2 - Сохранить в файл");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    for (int i = 0; i < limit; i++) {
                        System.out.println(text[i]);
                    }
                    break;
                case 2:
                    ToFile(text, "ArrayOut.dat");
                    System.out.println("Данные записаны в файл");
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Заполнить массив для среднего значения
    private static void AvarageArrayInMenu(){
        int choice;
        do {
            System.out.println("Заполнить массив");
            System.out.println("1 - заполнить случайными числами");
            System.out.println("2 - заполнить из файла");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    System.out.println("Введите размерность массива:");
                    int lenAr = readChoice();
                    limit = new Array(lenAr);
                    limit.FillRandom();
                    int avarage = Array.reciveAvarage(limit);
                    String[] avarageArr = {String.format("%d", avarage)};
                    AvarageOutMenu(avarageArr);
                    break;
                case 2:
                    String[] readedArrayIn = ArrReader("avarageIn.dat");
                    int [] newArr = new int[readedArrayIn.length];
                    for(int i = 0; i < readedArrayIn.length; i++){
                        newArr[i] = Integer.parseInt(readedArrayIn[i]);
                    }
                    Array outArray = new Array(readedArrayIn.length);
                    outArray.setNumbersArr(newArr);
                    int avarageNum = Array.reciveAvarage(outArray);
                    String[] avarageValueArr = {String.format("%d", avarageNum)};
                    AvarageOutMenu(avarageValueArr);
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Вывести среднее значение
    private static void AvarageOutMenu(String[] text){
        int choice;
        do {
            System.out.println("Способ вывода среднего значения");
            System.out.println("1 - Вывести в консоль");
            System.out.println("2 - Сохранить в файл");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    System.out.println(text[0]);
                    break;
                case 2:
                    ToFile(text, "avarageOut.dat");
                    System.out.println("Данные записаны в файл");
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Ввести число, которое необходимо конвертировать
    private static void convertInMenu(){
        int choice;
        do {
            System.out.println("Ввести число для конвертации");
            System.out.println("1 - Ввести с консоли");
            System.out.println("2 - Ввести из файла");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    System.out.println("Какое число вы желаете конвертировать?");
                    int numberIn = readChoice();
                    String converted = Words.convert(numberIn);
                    String[] convertedArr = {String.format("%s", converted)};
                    convertOutMenu(convertedArr);
                    break;
                case 2:
                    int wordNumber = NumReader("wordsIn.dat");
                    String word = Words.convert(wordNumber);
                    String[] convertedFileArr = {String.format("%s", word)};
                    convertOutMenu(convertedFileArr);
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
    //Вывести среднее значение
    private static void convertOutMenu(String[] text){
        int choice;
        do {
            System.out.println("Способ вывода среднего значения");
            System.out.println("1 - Вывести в консоль");
            System.out.println("2 - Сохранить в файл");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1:
                    System.out.println(text[0]);
                    break;
                case 2:
                    ToFile(text, "wordsOut.dat");
                    System.out.println("Данные записаны в файл");
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
}