package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Group rootGroup = new Group();
        Scene testScene = new Scene(rootGroup);
        primaryStage.setScene(testScene);
        Canvas canvas = new Canvas(512, 512);
        rootGroup.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ArrayList<String> input = new ArrayList<>();

        testScene.setOnKeyPressed(e-> {
            String code = e.getCode().toString();
            if (!input.contains(code)) input.add(code);
        });

        testScene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });

        //Image clippy = new Image("file:clippytransparent.png");

        AnimatedImage agents = new AnimatedImage();
        Image[] agentArray = new Image[4];
        agentArray[0] = new Image("file:clippytransparent.png");
        agentArray[1] = new Image("file:merlintransparent.png");
        agentArray[2] = new Image("file:linkstransparent.png");
        agentArray[3] = new Image("file:rovertransparent.png");
        agents.frames = agentArray;
        agents.duration = 0.100;

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                gc.drawImage(agents.getFrame(t), x, y );

                if(!input.isEmpty()) {
                    switch(input.get(0)) {
                        case "W":
                            gc.getCanvas().setTranslateY(gc.getCanvas().getTranslateY()-2);
                            break;
                        case "A":
                            gc.getCanvas().setTranslateX(gc.getCanvas().getTranslateX()-2);
                            break;
                        case "S":
                            gc.getCanvas().setTranslateY(gc.getCanvas().getTranslateY()+2);
                            break;
                        case "D":
                            gc.getCanvas().setTranslateX(gc.getCanvas().getTranslateX()+2);
                            break;
                    }
                }
            }
        }.start();
        primaryStage.show();
    }
}
