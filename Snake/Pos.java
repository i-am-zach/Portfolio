public class Pos{
    protected int x;
    protected int y;
    
    public Pos(int _x, int _y){
        this.x = _x;
        this.y = _y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String toString(){
        return "( " + x + ", " + y + ")";
    }
}