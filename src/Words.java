public class Words {
    private String textNumber;

    public Words(){
        this.textNumber = "sample";
    }

    public static String convert(int inputNumber) {
        final String[] belowTwenty = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одинадцать", "двенадцадь", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        final String[] tens = {" ", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        final String[] hundreds = {"тысяча", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

        Words textOut = new Words();
        if (inputNumber < 20) {
            textOut.textNumber = belowTwenty[inputNumber];
        } else if (inputNumber < 100) {
            int mid = inputNumber / 10;
            int low = inputNumber % 10;
            textOut.textNumber = tens[mid];
            if (low != 0)
                textOut.textNumber = textOut.textNumber + " " + belowTwenty[low];
        } else if (inputNumber < 1000) {
            int high = inputNumber / 100;
            int midLow = inputNumber % 100;
            int mid = inputNumber / 10 % 10;
            int low = inputNumber % 10;
            textOut.textNumber = hundreds[high];
            if (midLow <= 19) {
                textOut.textNumber = textOut.textNumber + " " + belowTwenty[midLow];
            }
            else{
                if (mid != 0)
                    textOut.textNumber = textOut.textNumber + " " + tens[mid];
                if (low != 0)
                    textOut.textNumber = textOut.textNumber + " " + belowTwenty[low];
            }
        }
        return textOut.textNumber;
    }
}
