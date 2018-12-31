public class Formation {

    //protected int startx;
    //protected int starty;
    protected String fname;

    public Formation(String name) {
        this.fname = name;
    }

    public void initformation(Creature start,Creature cre[],Battleground battleground,String side){
        int startx = start.getX();
        int starty = start.getY();
        switch(fname){
            case "鹤翼":
                System.out.println("摆出鹤翼阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        if(i < 4)
                            cre[i].GoBattle(battleground.getGround()[startx-4+i][starty-4+i]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx-3+i][starty+3-i]);
                    }
                }
                else if(side =="left"){
                    for(int i = 0; i < cre.length;i++) {
                        if(i < 4)
                            cre[i].GoBattle(battleground.getGround()[startx-3+i][starty+4-i]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx-3+i][starty-2+i]);
                    }
                }
                break;
            case "雁行":
                System.out.println("摆出雁行阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        cre[i].GoBattle(battleground.getGround()[startx-4+i][starty-5+i]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        cre[i].GoBattle(battleground.getGround()[startx-3+i][starty-2+i]);
                    }
                }
                break;
            case "长蛇":
                System.out.println("摆出长蛇阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        cre[i].GoBattle(battleground.getGround()[startx-4+i][starty-2]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        cre[i].GoBattle(battleground.getGround()[startx-3+i][starty+2]);
                    }
                }
                break;
            case "方门":
                System.out.println("摆出方门阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        if(i==0)
                            cre[i].GoBattle(battleground.getGround()[startx][starty-2]);
                        else if(i ==1)
                            cre[i].GoBattle(battleground.getGround()[startx][starty+2]);
                        else if(i < 5)
                            cre[i].GoBattle(battleground.getGround()[startx-1-i%2][starty-3+i]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx+2-i%2][starty-6+i]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        if(i==0)
                            cre[i].GoBattle(battleground.getGround()[startx][starty+2]);
                        else if(i < 4)
                            cre[i].GoBattle(battleground.getGround()[startx-2+i%2][starty-2+i]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx+1+i%2][starty-5+i]);
                    }
                }
                break;
            case "衡轭":
                System.out.println("摆出衡轭阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        cre[i].GoBattle(battleground.getGround()[startx-4+i][starty-2-i%2]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        cre[i].GoBattle(battleground.getGround()[startx-3+i][starty+2+i%2]);
                    }
                }
                break;
            case "鱼鳞":
                System.out.println("摆出鱼鳞阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        if (i < 5)
                            cre[i].GoBattle(battleground.getGround()[startx - 2 + i][starty - 2 - i % 2]);
                        else if (i == 6)
                            cre[i].GoBattle(battleground.getGround()[startx][starty-4]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx + 3][starty - 2]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        if (i < 5)
                            cre[i].GoBattle(battleground.getGround()[startx - 2 + i][starty +2 +i % 2]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx][starty+4]);
                    }
                }
                break;
            case "偃月":
                System.out.println("摆出偃月阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        if (i ==0)
                            cre[i].GoBattle(battleground.getGround()[startx-3][starty-1]);
                        else if (i < 6)
                            cre[i].GoBattle(battleground.getGround()[startx-3+i][starty-3+i%2]);
                        else if(i == 6)
                            cre[i].GoBattle(battleground.getGround()[startx+3][starty-1]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx+4][starty - 1]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        if (i ==0)
                            cre[i].GoBattle(battleground.getGround()[startx-3][starty+1]);
                        else if (i < 6)
                            cre[i].GoBattle(battleground.getGround()[startx-3+i][starty+3-i%2]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx+3][starty+1]);
                    }
                }
                break;
            case "锋矢":
                System.out.println("摆出锋矢阵");
                start.GoBattle(battleground.getGround()[startx][starty]);
                if(side == "right"){
                    for(int i = 0; i < cre.length;i++) {
                        if(i < 6)
                            cre[i].GoBattle(battleground.getGround()[startx][starty-6+i]);
                        else if(i ==6)
                            cre[i].GoBattle(battleground.getGround()[startx+1][starty-5]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx-1][starty-5]);
                    }
                }
                else if(side == "left"){
                    for(int i = 0; i < cre.length;i++) {
                        if(i < 5)
                            cre[i].GoBattle(battleground.getGround()[startx][starty+1+i]);
                        else if(i ==6)
                            cre[i].GoBattle(battleground.getGround()[startx+1][starty+4]);
                        else
                            cre[i].GoBattle(battleground.getGround()[startx-1][starty+4]);
                    }
                }
                break;
        }
    }
}
