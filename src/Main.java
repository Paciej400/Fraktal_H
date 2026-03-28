import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    static AnchorPane root = new AnchorPane();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, 800, 800);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Rectangle tlo = new Rectangle(0, 0, 800, 800);
        tlo.setFill(Color.LIGHTGRAY);
        root.getChildren().add(tlo);
        Text text = new Text(300, 40, "Wpisz liczbę pętli:");
        TextField textField = new TextField();
        textField.setTranslateY(25);
        textField.setTranslateX(410);
        Text error = new Text(430, 70, "");
        error.setFill(Color.RED);
        root.getChildren().addAll(text, textField);
        scene.setOnKeyPressed( event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    Rectangle czyszczenie = new Rectangle(100, 100, 600, 600);
                    czyszczenie.setFill(Color.LIGHTGRAY);
                    root.getChildren().add(czyszczenie);
                    int loop = Integer.parseInt(textField.getText());
                    if (loop > 6) {
                        loop = 6;
                    }
                    error.setText("");
                    drawH(400, 400, 200, loop);
                } catch (NumberFormatException e) {
                    error.setText("Podaj liczbę całkowitą!!!");
                    root.getChildren().add(error);
                }
            }
        });

    }
    public static void drawH(int x, int y, int size, int loops) {
        if (loops < 1) {
            return;
        }
        Line line = new Line(x-size/2, y, x+size/2, y);
        Line vertical = new Line(x - size/2, y - size, x - size / 2, y + size);
        Line vertical2 = new Line(x+size/2, y-size, x+size/2, y+size);
        root.getChildren().addAll(line, vertical2, vertical);
        if (loops > 1) {
            loops--;
            drawH(x - size/2, y - size, size/3, loops);
            drawH(x - size / 2, y + size, size/3, loops);
            drawH(x+size/2, y-size, size/3, loops);
            drawH(x+size/2, y+size, size/3, loops);
        }
    }
}