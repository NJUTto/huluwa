
import javafx.scene.image.Image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.io.PrintWriter;

enum CalaName {红娃,橙娃,黄娃,绿娃,青娃,蓝娃,紫娃}

public class Creature implements Runnable{

    //public static PrintWriter out;
    //= new PrintWriter(new BufferedWriter(new FileWriter("record.txt"));
    protected String name;
    protected int x, y;//战场中坐标
    protected Image image;
    protected boolean alive;
    //protected  int rank;
    protected Position pos;
    protected boolean evil;
    public static Battleground battleground;//共享同一个战场

    protected int sleeptime = 800;

    protected URL path;

    //private double hp;
    //private double atk;
    //private boolean alive;//是否存活
    public Creature(){
        x=y=-1;
        pos = null;
        alive = true;

    }
    public Creature(int x, int y) {
       this.x = x;
       this.y = y;
       alive = true;
    }
    public Image getImage(){
        return image;
    }

    public void setImage(Image img){
        this.image = img;
    }

    public boolean isEvil(){return evil;}

    public boolean isAlive(){return alive;}

    public void setAlive(boolean alive){this.alive = alive;}

    synchronized public static void setBattleground(Battleground battleground){Creature.battleground = battleground;}

    public int getX(){ return x; }

    public int getY(){return y;}

    public void setX(int x){this.x = x;}

    public void setY(int y){this.y = y;}

    public void move(int x, int y){//游戏中移动方法
    }

    public void randomMove(){}

    public String getName() {
        return name;
    }

    public Position getPos(){
        return pos;
    }

    public void LeavaBattle(){
        pos.removeCreture();
        this.pos = null;
    }

    public void GoBattle(Position p){
        p.setCreture(this);
        this.pos = p;
    }

    @Override
    public void run(){
        while(alive){
            synchronized (battleground) {
                randomMove();
            }

            try{
                Thread.sleep(sleeptime);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /*public void GoBattle(Space space) {
        //if(space.isEmpty()) {
        space.setCreature(this);
            //}
     }

        public void LeaveBattle(Space space) {
            if(! space.isEmpty()) {
                space.removeCreature();
            }
        }

    }*/


}
