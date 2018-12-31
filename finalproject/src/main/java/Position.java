import javafx.scene.image.Image;
import java.net.URL;

public class Position {
    private int x;
    private int y;
    private boolean empty;
    private Creature creature;

    private URL path = this.getClass().getClassLoader().getResource("pics/death.jpg");//可考虑设为stactic

    private boolean havedeath;
    private Image deathimage;

    public Position(){
        //super()
        x=y=-1;
        empty = true;
        creature = null;
    }
    public  Position(int x, int y){
        //super();
        this.x = x;
        this.y = y;
        empty = true;
        creature = null;
    }

    public boolean isHavedeath(){
        return havedeath;
    }

    public void setHavedeath(boolean death){
        havedeath = death;
    }

    public Image getDeathimage(){
        return deathimage;
    }

    public void setDeathimage(){
        //this.path = this.getClass().getClassLoader().getResource("pics/death.jpg");
        deathimage = new Image(path.toString());
    }

    public Creature getCre(){
        if(!empty)
            return creature;
        else
            return null;
    }

    public void setCreture(Creature cre){
        //if(empty) {//如果该位置为空
            creature = cre;
            creature.setX(x);
            creature.setY(y);
            empty = false;
        //}
        //else
            //System.out.println("该位置已被占据");
    }

    public void removeCreture(){
        //if(!empty) {
            //creature.setx(-1);
            //creature.sety(-1);
            creature = null;
            empty = true;
        //}
        //else
            //System.out.println("该位置已空");
    }

    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public void setx(int x){
        this.x = x;
    }
    public void sety(int y){
        this.y = y;
    }

    public boolean isEmpty(){
        return empty;
    }
}
