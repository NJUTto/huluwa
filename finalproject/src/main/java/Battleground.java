import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Battleground {
    private int row;
    private int col;
    private Position ground[][];

    private static Canvas groundcanvas;
    private static GraphicsContext gc;//2D画布
    private static double height;//画布高度
    private static double width;
    private static double possize;//每个Position的宽度
    //生物们
    private CalabashBro[] calabashbro = new CalabashBro[7];
    private Grandpa grandpa;
    private Snake snake;
    private Scorpion scorpion;
    private Creature[] minions = new Creature[8];//包括蝎子精

    private PrintWriter writer;
    private boolean writerflag;

    private boolean fromfile;
    private boolean start;

    public Battleground(int row, int col){
        this.row = row;
        this.col = col;
        ground = new Position[row][col];
        for(int i = 0; i < row;i++) {
            for(int j = 0; j < col;j++) {
                ground[i][j] = new Position(i, j);
            }
        }
        //初始化生物
        snake = new Snake(7,14);
        grandpa = new Grandpa(7,2);
        //七个葫芦娃
        for(int i = 0; i < calabashbro.length; i++){
            CalabashBro temp = new CalabashBro(CalaName.values()[i]);
            calabashbro[i] = temp;
        }
        //七个喽啰一个蝎子精
        scorpion = new Scorpion();
        minions[0] = scorpion;
        for(int i = 1; i <8;i++){
            Creature temp = new Minion();
            minions[i] = temp;
        }
        fromfile = false;

    }

    public boolean getWriterflag(){return writerflag;}

    public void setWriterflag(boolean flag){writerflag = flag;}

    public PrintWriter getWriter(){return writer;}

    public void initWriter() {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter("record.txt")));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public boolean judgeGood(){
        boolean flag = true;
        if(grandpa.isAlive())
            flag = false;
        for(int i = 0;i<calabashbro.length;i++) {
            if (calabashbro[i].isAlive())
                flag = false;
        }
        return  flag;
    }

    public boolean judgeEvil(){
        boolean flag = true;
        if(snake.isAlive())
            flag = false;
        for(int i = 0;i<minions.length;i++){
            if(minions[i].isAlive())
                flag = false;
        }
        return flag;
    }

    public Grandpa getGrandpa(){return grandpa;}

    public Snake getSnake(){return snake;}

    public CalabashBro[] getCalabashbro(){return calabashbro;}

    public Creature[] getMinions(){return minions;}

    public Position[][] getGround(){return ground;}

    public boolean isFromfile(){return fromfile;}


    //设置画布参数
    public void setGroundcanvas(Canvas canvas){
        this.groundcanvas = canvas;

        this.height = groundcanvas.getHeight();
        this.width = groundcanvas.getWidth();
        this.possize = height/row;
        assert (possize != width/col);

        gc = groundcanvas.getGraphicsContext2D();
    }

    public int getRow(){return row;}

    public int getCol(){return col;}

    public double getHeight(){return height;}

    public double getWidth(){return width;}

    public synchronized void setCreature(int x, int y,Creature cre){
        cre.GoBattle(ground[x][y]);
    }
    //没有调用这个枷锁方法！！！

    private void divideBattle(){
        gc.setFill(Color.rgb(255,255,255,0.5));
        gc.fillRect(0,0,width,height);

        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        //绘制格子界限
        for(int i=1;i<18;i++){
            gc.strokeLine(i*possize,0,i*possize,height);
        }
        for(int i=1;i<16;i++){
            gc.strokeLine(0,i*possize,width,i*possize);
        }

    }

    public synchronized void showBattle(){
        //System.out.println("进行界面刷新");
        gc.clearRect(0,0,width,height);//刷新时先清空
        divideBattle();//清理后重新打印边界

        for(int i = 0; i < row;i++)
        {
            for(int j = 0; j < col; j++){
                if(ground[i][j].isHavedeath())
                    gc.drawImage(ground[i][j].getDeathimage(),j*possize,i*possize,possize,possize);
                //Creature temp = ground[i][j].getCre();
                if(!ground[i][j].isEmpty()){
                    gc.drawImage(ground[i][j].getCre().getImage(),j*possize,i*possize,possize,possize);
                }
            }
        }

    }

    public void clearbattle(){
        for(int i=0; i < row;i++ )
            for(int j = 0; j < col;j++)
                if(!ground[i][j].isEmpty()){
                    ground[i][j].getCre().LeavaBattle();
                }
    }

    public void clearGood(){
        for(int i=0; i < row;i++ )
            for(int j = 0; j < col;j++)
                if(!ground[i][j].isEmpty()&& !ground[i][j].getCre().isEvil()){
                    ground[i][j].getCre().LeavaBattle();
                }
    }

    public void clearEvil(){
        for(int i=0; i < row;i++ )
            for(int j = 0; j < col;j++)
                if(!ground[i][j].isEmpty()&& ground[i][j].getCre().isEvil()){
                    ground[i][j].getCre().LeavaBattle();
                }
    }

}
