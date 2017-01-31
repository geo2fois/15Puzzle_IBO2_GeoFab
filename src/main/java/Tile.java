/**
 * Created by geoff on 31/01/2017.
 */
public class Tile {

    private int x;
    private int y;
    private int value;

    public Tile(int a, int b, int v) {
        this.x = a;
        this.y = b;
        this.value = v;
    }

//    public Tile getTile(Tile t) {
//        return (t);
//    }

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

    public Tile getTopTile(Tile t){
        if((t.getX())<=0){
            return null;
        }
        else{
            return (new Tile((x+1), y, value));
        }
    }

    public Tile getBottomTile(Tile t){
        if((t.getX())>=4){
            return null;
        }
        else{
            return (new Tile((x-1), y, value));
        }
    }

    public Tile getLeftTile(Tile t){
        if((t.getY())<=0){
            return null;
        }
        else{
            return (new Tile(x, (y-1), value));
        }
    }

    public Tile getRightTile(Tile t){
        if((t.getY())>=4){
            return null;
        }
        else{
            return (new Tile(x, (y+1), value));
        }
    }

    public Boolean isMovable (Tile t){
        if (this.getTopTile(t) != null && this.getTopTile(t).getValue() == 0) { return true; }  // Si la Tile au dessus existe ET est égale à 0
        else if (this.getBottomTile(t) != null && this.getBottomTile(t).getValue() == 0) { return true; }
        else if (this.getLeftTile(t) != null && this.getLeftTile(t).getValue() == 0) { return true; }
        else if (this.getRightTile(t) != null && this.getRightTile(t).getValue() == 0) { return true; }
        else { return false; }
    }
}
