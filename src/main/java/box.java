/**
 * Created by geoff on 07/11/2016.
 */


public class box {
    private int x;
    private int y;
    private int value;

    public box() {
        x=0;
        y=0;
        value=0;
    }

    public box(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
