package org.openjfx.ristinolla;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception{
    	Ristinolla ristinolla = new Ristinolla();
    	
    	Font f = Font.font("Monospaced", 30);
    	
    	Label situation = new Label("Vuoro: "+ristinolla.vuoro());
    	situation.setFont(f);
    	
    	BorderPane board = new BorderPane();
    	board.setTop(situation);
    	
        GridPane ruudukko = new GridPane();
        ruudukko.setHgap(20);
        ruudukko.setVgap(20);
        ruudukko.setPadding(new Insets(10, 10, 10, 10));
    	
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button nappi = new Button(ristinolla.status(x, y));
                nappi.setFont(f);
 
                ruudukko.add(nappi, x, y);
 
                int rx = x;
                int ry = y;
 
                nappi.setOnAction((event) -> {
                    ristinolla.aseta(rx, ry);
                    nappi.setText(ristinolla.status(rx, ry));
                    situation.setText("Vuoro: " + ristinolla.vuoro());
 
                    if (ristinolla.loppu()) {
                        situation.setText("Loppu!");
                    }
                });
            }
        }
 
        board.setCenter(ruudukko);
        
        Scene scene = new Scene(board);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}