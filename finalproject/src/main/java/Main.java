import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.File;
import java.net.URL;

public class Main extends Application {
    //private final Dimension SCREENDIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    //private final Dimension WINDOWDIMENSION = new Dimension(SCREENDIMENSION.width*2/3, SCREENDIMENSION.height*2/3);
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("battle.fxml"));
        primaryStage.setTitle("葫芦兄弟大战妖精");
        primaryStage.setScene(new Scene(root, 1200.0D, 700.0D));
        //primaryStage.setFullScreen(true);
        primaryStage.show();
        //BackGroundMusic.play(null);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {//
            @Override
            public void handle(WindowEvent event) {
                System.out.print("监听到窗口关闭");
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}

