/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.java.fx.comp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author helderdarocha
 */
public class ButtonExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        btn1.setText("Button1");
        btn1.setRotate(270);
        btn1.setTranslateY(50);
        btn1.setScaleX(1.5);
        
        Button btn2 = new Button("Button2");
        btn2.getStyleClass().add("btn2");
        
        Image image = new Image(this.getClass().getResourceAsStream("Java_Logo.png"));
        Button btn3 = new Button("Label3", new ImageView(image));
        btn3.setTextFill(Color.RED);
        btn3.setGraphicTextGap(50);
        btn3.setContentDisplay(ContentDisplay.RIGHT);
        btn3.setFont(Font.font("Calibri", 12));
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked!");
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(btn1);
        root.setLeft(btn2);
        root.setCenter(btn3);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
