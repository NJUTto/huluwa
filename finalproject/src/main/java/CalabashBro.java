import javafx.scene.image.Image;
import java.util.Random;

public class CalabashBro extends Creature implements Runnable {

    private int rank;

    String[] calapics = {"pics/1.jpg","pics/2.jpg","pics/3.jpg","pics/3.jpg","pics/4.jpg","pics/5.jpg","pics/6.jpg","pics/7.jpg"};


    public CalabashBro(CalaName name){
        super();
        this.name = name.toString();
        this.rank = name.ordinal()+1;
        this.evil = false;
        //this.rank = rank;
        //this.battleground = battleground;

        this.path = this.getClass().getClassLoader().getResource(calapics[rank -1]);
        image = new Image(path.toString());
    }

    public CalabashBro(int x, int y, CalaName name){
        super(x,y);

        this.name = name.toString();
        this.rank = name.ordinal();
        this.evil = false;
        //this.rank = rank;
        //this.battleground = battleground;

        this.path = this.getClass().getClassLoader().getResource(calapics[rank -1]);
        image = new Image(path.toString());
    }



    public int getRank(){
        return rank;
    }

    public void setRank(int rank){this.rank = rank;}

    @Override
    public void move(int mx, int my){//游戏中移动方法
        //System.out.println("进行随机移动");
        if(battleground.isFromfile()==false){
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
                if(dcre.equals("Grandpa")||dcre.equals("CalabashBro")){//友军
                    return;
                }
                else{
                    if(dcre.equals("Snake")){
                        try{
                            Random random = new Random();
                            int a = random.nextInt(5);
                            if(a == 0){//葫芦娃赢
                                synchronized (battleground) {
                                    battleground.getGround()[dx][dy].getCre().setAlive(false);//蛇精死亡
                                    battleground.getGround()[dx][dy].getCre().LeavaBattle();//先把死亡的移走再占据
                                    battleground.setCreature(dx,dy,this);
                                    battleground.getGround()[sx][sy].removeCreture();

                                    battleground.getGround()[dx][dy].setHavedeath(true);
                                    battleground.getGround()[dx][dy].setDeathimage();

                                    if(battleground.getWriterflag())
                                        battleground.getWriter().println(this.name +" ("+sx+","+sy+")MoveTo("+dx+","+dy+")");
                                }
                            }
                            else{
                                synchronized (battleground) {
                                    battleground.getGround()[sx][sy].getCre().setAlive(false);//葫芦娃死亡
                                    battleground.getGround()[sx][sy].getCre().LeavaBattle();
                                    battleground.getGround()[sx][sy].setHavedeath(true);
                                    battleground.getGround()[sx][sy].setDeathimage();
                                }
                            }

                        }catch (Exception e){
                            return;
                        }
                    }
                    else if(dcre.equals("Scorpion")){
                        try{
                            Random random = new Random();
                            int a = random.nextInt(2);
                            if(a == 0){//葫芦娃赢
                                synchronized (battleground) {
                                    battleground.getGround()[dx][dy].getCre().setAlive(false);//蝎子死亡
                                    battleground.getGround()[dx][dy].getCre().LeavaBattle();//先把死亡的移走再占据
                                    battleground.setCreature(dx,dy,this);
                                    battleground.getGround()[dx][dy].setHavedeath(true);
                                    battleground.getGround()[dx][dy].setDeathimage();
                                    battleground.getGround()[sx][sy].removeCreture();
                                    if(battleground.getWriterflag())
                                        battleground.getWriter().println(this.name +" ("+sx+","+sy+")MoveTo("+dx+","+dy+")");
                                }
                            }
                            else{
                                synchronized (battleground) {
                                    battleground.getGround()[sx][sy].getCre().setAlive(false);//葫芦娃死亡
                                    battleground.getGround()[sx][sy].getCre().LeavaBattle();//先把死亡的移走再占据
                                    battleground.getGround()[sx][sy].setHavedeath(true);
                                    battleground.getGround()[sx][sy].setDeathimage();
                                }
                            }

                        }catch (Exception e){
                            return;
                        }
                    }
                    else if(dcre.equals("Minion")){
                        try{
                            Random random = new Random();
                            int a = random.nextInt(3);
                            if(a < 2){//葫芦娃赢
                                synchronized (battleground) {
                                    battleground.getGround()[dx][dy].getCre().setAlive(false);//小喽啰死亡
                                    battleground.getGround()[dx][dy].getCre().LeavaBattle();//先把死亡的移走再占据
                                    battleground.setCreature(dx,dy,this);
                                    battleground.getGround()[dx][dy].setHavedeath(true);
                                    battleground.getGround()[dx][dy].setDeathimage();
                                    battleground.getGround()[sx][sy].removeCreture();
                                    if(battleground.getWriterflag())
                                        battleground.getWriter().println(this.name +" ("+sx+","+sy+")MoveTo("+dx+","+dy+")");
                                }
                            }
                            else{
                                synchronized (battleground) {//不知道有没有必要
                                    battleground.getGround()[sx][sy].getCre().setAlive(false);//精死亡
                                    battleground.getGround()[sx][sy].getCre().LeavaBattle();//先把死亡的移走再占据
                                    battleground.getGround()[sx][sy].setHavedeath(true);
                                    battleground.getGround()[sx][sy].setDeathimage();
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
        /*while(!Thread.interrupted()){
            if(alive == false && battleground.isFromfile() == false)
                break;

            randomMove();

            try{
                Thread.sleep(sleeptime);
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/

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


    /*public void messbro() //打乱葫芦娃顺序
    {
        int rank1,rank2;
        Random random = new Random();
        for(int i = 0; i < 14;i++) {//14次保证充分交换
            rank1 = random.nextInt(7);//控制范围
            rank2 = random.nextInt(7);
            if(rank1 != rank2) {//生成的两个随机数不一样，交换顺序
                Calabash temp = bro[rank1];
                bro[rank1] = bro[rank2];
                bro[rank2] = temp;
            }
        }

    }

    //冒泡排序
    public void CalabashBubble() {
        System.out.println("葫芦兄弟整理阵型进行排序");
        for(int i = 0; i < bro.length-1; i++) {
            for(int j = 0; j < bro.length-1; j++) {
                if(bro[j].getrank() > bro[j+1].getrank()) {
                    Calabash temp = bro[j];
                    bro[j] = bro[j+1];
                    bro[j+1] = temp;
                }
            }
        }

    }*/
}
