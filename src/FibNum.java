public class FibNum implements Cloneable {
    private int current;
    private int byCount;

    public FibNum()
    {
        this.current = 1;
        this.byCount = 1;
    }

    public int getCurrent() {
        return current;
    }

    public int getByCount() {
        return byCount;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    private String verbose(){
        return String.format("%d число Фибоначчи: %d", this.byCount, this.current);
    }

    public static String[] reciveCurrent(FibNum doCount, int range) {
        String[] str = new String[50];
        int last = 1, preLast = 0;
        for(doCount.byCount = 1; doCount.byCount < range + 1; doCount.byCount++)
        {
            str[doCount.byCount - 1] = doCount.verbose();
            doCount.current = (last + preLast);
            preLast = last;
            last = doCount.current;
        }
        return str;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
