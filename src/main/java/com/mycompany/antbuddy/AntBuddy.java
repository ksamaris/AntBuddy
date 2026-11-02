/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.antbuddy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author kevin
 */
public class AntBuddy extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("AntBuddy");
        stage.setScene(new Scene(new StackPane(title), 900, 600));
        stage.setTitle("AntBuddy");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}