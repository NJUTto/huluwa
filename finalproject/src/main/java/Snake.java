import javafx.scene.image.Image;


import java.util.Random;

public class Snake extends Creature implements Runnable {

    public Snake(){
        super();
        this.name = "蛇精";
        this.evil = true;
        this.path = this.getClass().getClassLoader().getResource("pics/snake.jpg");
        image = new Image(path.toString());
    }
    public Snake(int x, int y){
        super(x,y);

        this.name = "蛇精";
        this.evil = true;
        this.path = this.getClass().getClassLoader().getResource("pics/snake.jpg");
        image = new Image(path.toString());
    }

    @Override
    public void move(int mx,int my){//游戏中移动方法
        if(battleground.isFromfile()==false){

            //System.out.println("进行移动");
            int sx = this.x;
            int sy = this.y;
            int dx = this.x+mx;
            int dy = this.y+my;
            if(battleground.getGround()[dx][dy].isEmpty()) {//没有生物
                battleground.setCreature(dx,dy,this);
                battleground.getGround()[sx][sy].removeCreture();
                if(battleground.getWriterflag())
                    battleground.getWriter().println(this.name +" ("+sx+","+sy+")MoveTo("+dx+","+dy+")");

            }
            else{//有生物
                String dcre = battleground.getGround()[dx][dy].getCre().getClass().getSimpleName();
                if(dcre.equals("Scorpion")||dcre.equals("Minion")){//友军
                    return;
                }
                else{
                    if(dcre.equals("CalabashBro")){
                        try{
                            Random random = new Random();
                            int a = random.nextInt(5);
                            if(a == 0){//葫芦娃赢
                                synchronized (battleground) {
                                    battleground.getGround()[sx][sy].getCre().setAlive(false);
                                    battleground.getGround()[sx][sy].getCre().LeavaBattle();

                                    battleground.getGround()[sx][sy].setHavedeath(true);
                                    battleground.getGround()[sx][sy].setDeathimage();
                                    /*Death death = new Death();
                                    death.GoBattle(battleground.getGround()[sx][sy]);*/
                                }
                            }
                            else{
                                synchronized (battleground) {
                                    battleground.getGround()[dx][dy].getCre().setAlive(false);
                                    battleground.getGround()[dx][dy].getCre().LeavaBattle();
                                    battleground.setCreature(dx,dy,this);

                                    battleground.getGround()[dx][dy].setHavedeath(true);
                                    battleground.getGround()[dx][dy].setDeathimage();

                                    battleground.getGround()[sx][sy].removeCreture();
                                    if(battleground.getWriterflag())
                                        battleground.getWriter().println(this.name +" ("+sx+","+sy+")MoveTo("+dx+","+dy+")");
                                }
                            }

                        }catch (Exception e){
                            return;
                        }
                    }
                    else if(dcre.equals("Grandpa")){
                        try{
                            Random random = new Random();
                            int a = random.nextInt(11);
                            if(a == 0){//爷爷赢
                                synchronized (battleground) {
                                    battleground.getGround()[sx][sy].getCre().setAlive(false);
                                    battleground.getGround()[sx][sy].getCre().LeavaBattle();//先把死亡的移走再占据

                                    battleground.getGround()[sx][sy].setHavedeath(true);
                                    battleground.getGround()[sx][sy].setDeathimage();
                                }
                            }
                            else{
                                synchronized (battleground) {//不知道有没有必要
                                    battleground.getGround()[dx][dy].getCre().setAlive(false);
                                    battleground.getGround()[dx][dy].getCre().LeavaBattle();//先把死亡的移走再占据
                                    battleground.setCreature(dx,dy,this);
                                    battleground.getGround()[dx][dy].setHavedeath(true);
                                    battleground.getGround()[dx][dy].setDeathimage();
                                    battleground.getGround()[sx][sy].removeCreture();
                                    if(battleground.getWriterflag())
                                        battleground.getWriter().println(this.name +" ("+sx+","+sy+")MoveTo("+dx+","+dy+")");
                                }
                            }

                        }catch (Exception e){
                            return;
                        }
                    }

                }
            }
        }
        else{//从文件读取

        }
    }

    @Override
    public void randomMove(){
        Random random = new Random();
        if(x>5&&x<12&&y>5&&y<12){//在内部
            int a = random.nextInt(4);
            if(a ==0)//上
                move(-1,0);
            else if(a ==1)//下
                move(1,0);
            else if(a == 2)//左
                move(0,-1);
            else//右
                move(0,1);
            return;
        }

        if(y<=5){//在左边
            int a = random.nextInt(12);
            if(a ==0 && y>0)//左
                move(0,-1);
            else if(a ==1 && x>0)//up
                move(-1,0);
            else if(a == 2&&x<17)//d
                move(1,0);
            else//345 向中心移动
                move(0,1);
            return;
        }
        if(y>=12){//在右边
            int a = random.nextInt(12);
            if(a ==0 && y<17)//r
                move(0,1);
            else if(a ==1 && x>0)//up
                move(-1,0);
            else if(a == 2&&x<17)//d
                move(1,0);
            else//345 向中心移动
                move(0,-1);
            return;
        }
        if(x<=5){//在上边
            int a = random.nextInt(12);
            if(a ==0 && x<17)//上
                move(-1,0);
            else if(a ==1 && y>0)//左
                move(0,-1);
            else if(a == 2&&y<17)//右
                move(0,1);
            else//345 向中心移动
                move(1,0);
            return;
        }
        if(x>=12){//在下边
            int a = random.nextInt(12);
            if(a ==0 && x<17)//下
                move(1,0);
            else if(a ==1 && y>0)//左
                move(0,-1);
            else if(a == 2&&y<17)//右
                move(0,1);
            else//345 向中心移动
                move(-1,0);
            return;
        }
    }


    @Override
    public void run() {
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
}
