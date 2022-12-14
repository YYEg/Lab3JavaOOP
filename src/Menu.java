import java.io.*;
import java.util.Scanner;

public class Menu {
    public static FibNum fibNum; //ссылка на обьекта класса FibNum
    public static void start(){
        mainMenu();
    }
    public static int readChoice(){        //Функция для чтения, обрабатывающая ошибки ввода
        Scanner in = new Scanner(System.in);
        int readed = -1;
        try {
             readed = Integer.parseInt(in.nextLine());
        }
        catch(NumberFormatException ex){
            System.out.println("Вводите только значения пунктов меню!");
            readed = readChoice();
        }
        return readed;
    }
    private static void mainMenu(){        //Главное меню
        int choice = -1;
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }

    private static void fibNumToFile(String[] text, String filename){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))){
            for(int i = 0; i < text.length; i++){
                dos.writeUTF(text[i]);
                dos.writeUTF("\n");
            System.out.println("Сохранение успешно в файле" + filename);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }
    private static int fibNumReader(String filepath) {

        int readed = 0;
        String str = "";
        try (FileInputStream dis = new FileInputStream(filepath)) {
            int i;
            char cc;
            readed = 0;
            while ((i = dis.read()) != -1) {

                cc = ((char) i);
                str += String.valueOf(cc);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");

        }
        readed = Integer.parseInt(String.valueOf(str));
        return readed;
    }

    private static void fibNumInMenu(){
        int choice = -1;
        do {
            System.out.println("Ввод ограничения вывода");
            System.out.println("1 - Ввести с консоли");
            System.out.println("2 - Ввести из файла");
            System.out.println("0 - Назад");
            choice = readChoice();

            switch (choice){
                case 1: fibNum = new FibNum();
                    System.out.println("ведите желаемый номер чесла Фибоначчи:");
                    int numberIn = readChoice();
                    String[] text = FibNum.reciveCurrent(fibNum, numberIn);
                    fibNumOutMenu(text, numberIn);
                    break;
                case 2:
                    int readedNumberIn = fibNumReader("C:\\Users\\supir\\IdeaProjects\\Lab3JavaOOP\\lin.dat");
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
    private static void fibNumOutMenu(String[] text, int numberIn){
        int choice = -1;
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
                    fibNumToFile(text, "fibNum.txt");
                    System.out.println("Данные записаны в файл");
                    break;
                case 0: break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }while(choice != 0);
    }
}

