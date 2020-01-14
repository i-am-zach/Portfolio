import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game extends JPanel implements ActionListener, KeyListener
{
    private Snake s;
    private Timer timer;
    private float frameRate = 10;
    private Pos food;
    public static final int width = 600;
    public static final int height = 600;
    public static final int sql = 20;
    private int totalFrames;
    public static final int maxFrameRate = 20;
    public static void main(String[] args){
        JFrame frame = new JFrame("Snake");
        frame.setSize(width+5, height+5);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        
        Game game = new Game();
        frame.add(game);
        frame.addKeyListener(game);
        
        frame.setVisible(true);
    }
    
    public Game(){
        s = new Snake();
        food = new Pos(0,0);
        setUp();
    }
    
    public void paint(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width+5, height+5);
        g.setColor(new Color(51, 51, 51));
        for(Pos t: s.tail){
            g.fillRect(t.x + 1, t.y + 1, sql-2, sql-2);
        }
        g.setColor(new Color(255, 0, 100));
        g.fillRect(food.x, food.y, sql, sql);
        g.setColor(new Color(0,200,0));
        g.drawString("Score: " + s.tail.size(), 0, 14);
    }
    
    public void update(){
        totalFrames++;
        
        s.update();
        ////////////////////////////////////////////////////
        /*Rectangle stage = new Rectangle(0,0, width, height-sql);
        for(Pos t: s.tail){
            Rectangle body = new Rectangle(t.x, t.y, sql, sql);
            if(!body.intersects(stage)){
                reset();
                break;
            }
        }
        */
        if(s.y < 0){
            s.y = height-sql;
        } else if(s.y > height- sql){
            s.y = 0;
        } else if(s.x < 0){
            s.x = width-sql;
        } else if(s.x > width-sql){
            s.x = 0;
        }
        
        if(snakeHit()){
            reset();
        }
    }
    
    public boolean snakeHit(){
        for(int i = 0; i < s.tail.size(); i++){
            for(int j = 0; j < s.tail.size(); j++){
                if(i == j){
                    continue;
                }
                if(s.tail.get(i).x == s.tail.get(j).x && s.tail.get(i).y == s.tail.get(j).y){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void setUp(){
        createFood();
        timer = new Timer(1000/(int)frameRate, this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e){
        update();
        repaint();
    }
    
    public void reset(){
        timer.stop();
        totalFrames = 0;
        frameRate = 10;
        timer.setDelay(1000/10);
        JOptionPane.showMessageDialog(null, "You died\nScore: " + s.tail.size());
        s = new Snake();
        timer.start();
    }
    
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP){
            s.up();
        } else if(keyCode == KeyEvent.VK_DOWN){
            s.down();
        } else if(keyCode == KeyEvent.VK_LEFT){
            s.left();
        } else if(keyCode == KeyEvent.VK_RIGHT){
            s.right();
        }
    }
    
    
    public void keyTyped(KeyEvent e){}
    
    public void keyReleased(KeyEvent e){   
    }
    
    public void createFood(){
        food.x = (int)(Math.random()*(width-20)/20)*20;
        food.y = (int)(Math.random()*(height-20)/20)*20;
        for(Pos t: s.tail){
            if(t.x == food.x && t.y == food.y){
                createFood();
                break;
            }
        }
    }
    
    class Snake{
        protected int x;
        protected int y;
        protected int xSpeed;
        protected int ySpeed;
        protected ArrayList<Pos> tail;
        protected int lastFrameEaten = 0;
        protected boolean up, down, left, right;
        
        public Snake(){
            this.x = 0;
            this.y = 0;
            this.xSpeed = 1;
            this.ySpeed = 0;
            tail = new ArrayList<Pos>();
            tail.add(new Pos(0, 0*20));
            tail.add(new Pos(0, 1*20));
            tail.add(new Pos(0, 2*20));
            tail.add(new Pos(0, 3*20));
            tail.add(new Pos(0, 4*20));
        }
        
        public void buffer(char req){
            if(req == 'u'){
                up = true;
                down = false;
                right = false;
                left = false;
            } else if(req == 'd'){
                up = false;
                down = true;
                right = false;
                left = false;
            } else if(req == 'r'){
                up = false;
                down = false;
                right = true;
                left = false;
            } else if(req == 'l'){
                up = false;
                down = false;
                right = false;
                left = true;
            } else {
                up = false;
                down = false;
                right = false;
                left = false;
            }
        }
        
        public void update(){
            this.x += xSpeed*sql;
            this.y += ySpeed*sql;
            this.eat(food);
            for(int i = tail.size()-1; i >= 1; i--){
                tail.set(i, tail.get(i-1));
            }
            tail.set(0, new Pos(this.x, this.y));
            if(up){
                up();
                up = false;
                down = false;
                right = false;
                left = false;
            } else if(down){
                down();
                up = false;
                down = false;
                right = false;
                left = false;
            } else if(right){
                right();
                up = false;
                down = false;
                right = false;
                left = false;
            } else if(left){
                left();
                up = false;
                down = false;
                right = false;
                left = false;
            }
            //System.out.println("(" + this.x + ", " + this.y + ")");
            //System.out.println(tail.get(0));
            //System.out.println();
        }
        
        public void eat(Pos food){
            Rectangle fRect = new Rectangle(food.x, food.y, sql, sql);
            Rectangle myRect = new Rectangle(this.x, this.y, sql, sql);
            if(fRect.intersects(myRect)){
                createFood();
                tail.add(new Pos(this.x, this.y));
                if(frameRate + 0.5 <= maxFrameRate){
                    frameRate+= 0.5;
                    timer.setDelay(1000/(int)frameRate);
                }
            }
        }
        
        public Rectangle toRect(Pos p){
            return new Rectangle(p.x, p.x, sql, sql);
        }
        
        public void up(){
            if(this.ySpeed != 1 && Math.abs(totalFrames - lastFrameEaten) > 0){
                this.xSpeed = 0;
                this.ySpeed = -1;
                lastFrameEaten = totalFrames;
            } else {
                buffer('u');
            }
        }
        
        public void down(){
            if(this.ySpeed != -1 && Math.abs(totalFrames - lastFrameEaten) > 0){
                this.xSpeed = 0;
                this.ySpeed = 1;
                lastFrameEaten = totalFrames;
            } else{
                buffer('d');
            }
            
        }
        
        public void right(){
            if(this.xSpeed != -1 && Math.abs(totalFrames - lastFrameEaten) > 0){
                this.xSpeed = 1;
                this.ySpeed = 0;
                lastFrameEaten = totalFrames;
            } else {
                buffer('r');
            }
        }
        
        public void left(){
            if(this.xSpeed != 1 && Math.abs(totalFrames - lastFrameEaten) > 0){
                this.xSpeed = -1;
                this.ySpeed = 0;
                lastFrameEaten = totalFrames;
            } else {
                buffer('l');
            }
        }
    }
}
