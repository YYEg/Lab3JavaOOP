import java.util.Random;

public class Array {
    private int[] numbersArr;
    private final int rangeOfArray;

    public Array(int rangeOfArray) {
        this.rangeOfArray = rangeOfArray;
        this.numbersArr = new int[rangeOfArray];
    }

    public void setNumbersArr(int[] numbersArr) {
        this.numbersArr = numbersArr;
    }

    public void FillRandom() {
        Random randomNum = new Random();
        for (int i = 0; i < this.rangeOfArray; i++) {
            this.numbersArr[i] = randomNum.nextInt(100);
        }
    }

    public static int reciveAvarage(Array workArray) {
        int arrSum = 0, count = 0;
        for (int i = 0; i < workArray.rangeOfArray; i++) {
            arrSum += workArray.numbersArr[i];
            count++;
        }
        return arrSum / count;
    }

    public static String[] PrintArray(Array workArray, int limit) {
        String[] arr = new String[limit];
        for (int i = 0; i < limit; i++) {
            if (workArray.numbersArr[i] % 2 == 0)
                arr[i] = String.format("%d!!!", workArray.numbersArr[i]);
            else
                arr[i] = String.format("%d", workArray.numbersArr[i]);
        }
        return arr;
    }
}
