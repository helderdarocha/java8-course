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
public class LabelExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Label label1 = new Label();
        label1.setText("Label1");
        label1.setRotate(270);
        label1.setTranslateY(50);
        label1.setScaleX(1.5);
        
        Label label2 = new Label("Label2 might be wrapped");
        label2.setWrapText(true);
        
        Image image = new Image(this.getClass().getResourceAsStream("JavaFX_Logo.png"));
        Label label3 = new Label("Label3", new ImageView(image));
        label3.setTextFill(Color.RED);
        label3.setGraphicTextGap(50);
        label3.setContentDisplay(ContentDisplay.TOP);
        label3.setFont(Font.font("Calibri", 12));
        label3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clicked!");
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(label1);
        root.setLeft(label2);
        root.setCenter(label3);
        
        Scene scene = new Scene(root);
        
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
