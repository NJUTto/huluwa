import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Window;
import java.net.URL;

public class Player implements Runnable {

    private static Battleground battleground;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Window window;
    private URL path;

    private boolean run = true;
    //private boolean initplayer;
    private boolean end = false;

    public Player(){
        //initplayer = true;
        path = this.getClass().getClassLoader().getResource("music/calabash.mp3");
        media = new Media(path.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        System.out.println("播放音乐");
        mediaPlayer.setVolume(0.4);
        mediaPlayer.play();

    }

    public void setEnd(boolean flag){end = flag;}

    public void setRun(boolean flag){run = flag;}

    //public void getinitPlayer(boolean flag){initplayer = flag;}

    public void setWindow(Window window){this.window = window;}//没有flag

    public static void setBattleground(Battleground battleground){Player.battleground = battleground;}

    private void replay(){

    }
    private void play(){
        while(!end){
            synchronized (battleground){
                battleground.showBattle();

            }
            /*if(battleground.judgeEvil()|| battleground.judgeGood())
            {
                run = false;
                end = true;
            }*/

            try{
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run(){
        while(run){
            play();
            //等待外界的回放信号
            try{
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
