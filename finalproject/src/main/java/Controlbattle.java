import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controlbattle {

    Battleground battleground;
    Player player;
    Formation formation;

    //private boolean initCanvasFlag = false;//是否被初始化

    private boolean lset = false;//左边阵型是否初始化
    private boolean rset = false;//右边阵型是否初始化
    private boolean begin = false;

    private String leftformation;
    private String rightformation;

    //private PrintWriter out;


    @FXML
    private Canvas mainCanvas;      //主画布
    @FXML
    private Button startButton;     //开始的按钮
    @FXML
    private Button replayButton;
    @FXML
    private Button stopButton;
    @FXML
    private BorderPane mainBorderPane;



    public Controlbattle() {
        battleground = new Battleground(16, 18);
        player = new Player();
        Player.setBattleground(battleground);

        Creature.setBattleground(battleground);
        //initCanvas();
    }

    /*private void initWriter()throws IOException{
        out = new PrintWriter(new BufferedWriter(new FileWriter("record.txt")));
    }*/

    //public PrintWriter getWriter(){return out;}

    //设置画布信息
    private void initCanvas() {
        //System.out.println("initCanvas 快要开始");
        battleground.setGroundcanvas(mainCanvas);
        //System.out.println("initCanvas 结束");
        player.setWindow(mainBorderPane.getScene().getWindow());
    }

    public void startGame(ActionEvent actionEvent){
        ExecutorService pool = Executors.newCachedThreadPool();
        if(!begin) {
            if (lset && rset) {//两边阵型必须都设置好
                begin = true;
                battleground.initWriter();
                battleground.setWriterflag(true);
                battleground.getWriter().println(leftformation+" "+rightformation);
                player.setRun(true);
                player.setEnd(false);
                initCanvas();
                //battleground.showBattle();
                //ExecutorService executor = Executors.newCachedThreadPool();
                pool.execute(player);
                if(battleground.getSnake().isAlive())
                    pool.execute(battleground.getSnake());
                if(battleground.getGrandpa().isAlive())
                    pool.execute(battleground.getGrandpa());

                for (int i = 0; i < 7; i++)
                    if(battleground.getCalabashbro()[i].isAlive())
                        pool.execute(battleground.getCalabashbro()[i]);
                for (int i = 0; i < 8; i++)
                    if(battleground.getMinions()[i].isAlive())
                        pool.execute(battleground.getMinions()[i]);

                //executor.shutdown();
                //System.out.println("关闭线程");
            }
        }
        else{//再次点击开始
            System.out.println("重启游戏");
            pool.shutdownNow();
        }
    }

    public void stopGame(ActionEvent actionEvent){
        begin = false;

        player.setEnd(true);
    }

    public void replayGame(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setTitle("Open Source File");

        File file = fileChooser.showOpenDialog(null);
        if(file.getName().equals("record.txt")){
            String filename = file.getAbsolutePath();
            //System.out.println("eeeee");
            BufferedReader infile = new BufferedReader(new FileReader(filename));
            //ObjectInputStream infile = new ObjectInputStream(new FileInputStream(file));
           //BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

            String s;
            StringBuilder sb= new StringBuilder();

            while((s = infile.readLine())!=null)
                sb.append(s+"\n");
            infile.close();

            String first;//第一条信息
            BufferedReader in = new BufferedReader(new StringReader(sb.toString()));
            first = in.readLine();
            String[] info = first.split(" ");
            leftformation = info[0];
            rightformation = info[1];

            battleground.clearbattle();

            formation = new Formation(leftformation);
            battleground.clearGood();
            formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
            initCanvas();
            battleground.showBattle();
            lset = true;

            formation = new Formation(rightformation);
            battleground.clearEvil();
            formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
            initCanvas();
            battleground.showBattle();
            rset = true;

            battleground.setWriterflag(false);
            if(!begin) {
                if (lset && rset) {//两边阵型必须都设置好
                    begin = true;

                    player.setRun(true);
                    player.setEnd(false);
                    initCanvas();
                    //battleground.showBattle();
                    ExecutorService executor = Executors.newCachedThreadPool();
                    executor.execute(player);
                    if(battleground.getSnake().isAlive())
                        executor.execute(battleground.getSnake());
                    if(battleground.getGrandpa().isAlive())
                        executor.execute(battleground.getGrandpa());

                    for (int i = 0; i < 7; i++)
                        if(battleground.getCalabashbro()[i].isAlive())
                            executor.execute(battleground.getCalabashbro()[i]);
                    for (int i = 0; i < 8; i++)
                        if(battleground.getMinions()[i].isAlive())
                            executor.execute(battleground.getMinions()[i]);

                    executor.shutdown();
                    //System.out.println("关闭线程");
                }
            }
        }
        System.out.println(file);
    }

    public void OnKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.SPACE) {
            this.startGame(new ActionEvent());
        }

    }

    public void leftHY(ActionEvent actionEvent) {
        formation = new Formation("鹤翼");
        leftformation = "鹤翼";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftYX(ActionEvent actionEvent) {
        formation = new Formation("雁行");
        leftformation = "雁行";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftCS(ActionEvent actionEvent) {
        formation = new Formation("长蛇");
        leftformation = "长蛇";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftFM(ActionEvent actionEvent) {
        formation = new Formation("方门");
        leftformation = "方门";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftHE(ActionEvent actionEvent) {
        formation = new Formation("衡轭");
        leftformation = "衡轭";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftYL(ActionEvent actionEvent) {
        formation = new Formation("鱼鳞");
        leftformation = "鱼鳞";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftYY(ActionEvent actionEvent) {
        formation = new Formation("偃月");
        leftformation = "偃月";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void leftFS(ActionEvent actionEvent) {
        formation = new Formation("锋矢");
        leftformation = "锋矢";
        battleground.clearGood();
        formation.initformation(battleground.getGrandpa(), battleground.getCalabashbro(), battleground, "left");
        initCanvas();
        battleground.showBattle();
        lset = true;
    }

    public void rightHY(ActionEvent actionEvent) {
        formation = new Formation("鹤翼");
        rightformation = "鹤翼";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightYX(ActionEvent actionEvent) {
        formation = new Formation("雁行");
        rightformation = "雁行";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightCS(ActionEvent actionEvent) {
        formation = new Formation("长蛇");
        rightformation = "长蛇";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightFM(ActionEvent actionEvent) {
        formation = new Formation("方门");
        rightformation = "方门";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightHE(ActionEvent actionEvent) {
        formation = new Formation("衡轭");
        rightformation = "衡轭";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightYL(ActionEvent actionEvent) {
        formation = new Formation("鱼鳞");
        rightformation = "鱼鳞";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightYY(ActionEvent actionEvent) {
        formation = new Formation("偃月");
        rightformation = "偃月";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }

    public void rightFS(ActionEvent actionEvent) {
        formation = new Formation("锋矢");
        rightformation = "锋矢";
        battleground.clearEvil();
        formation.initformation(battleground.getSnake(), battleground.getMinions(), battleground, "right");
        initCanvas();
        battleground.showBattle();
        rset = true;
    }
}