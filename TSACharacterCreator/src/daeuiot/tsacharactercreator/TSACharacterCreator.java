/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsacharactercreator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Daeuiot
 */
public class TSACharacterCreator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        build(primaryStage, 1200, 800);
        
        primaryStage.setTitle("Avatar: The Second Age - Character Creator");
        primaryStage.show();
    }
    /**
     * Used to build the primary stage
     * 
     * @param primaryStage The stage with which to build
     * @param windowWidth The width of the window
     * @param windowHeight The height of the window
     */
    private void build(Stage primaryStage, int windowWidth, int windowHeight)
    {       
        VBox tabs = new VBox(); //Used to hold the tabs for the categories ie. Background/Class/Skills/etc.
        tabs.setPrefWidth(100);
        Button btnBackground    = new Button("Background");
        Button btnAttributes    = new Button("Attributes");
        Button btnSkills        = new Button("Skills");
        tabs.getChildren().addAll(btnBackground, btnAttributes, btnSkills);
        tabs.setStyle("-fx-spacing: 10; -fx-alignment: center; -fx-padding: 10; -fx-background-color: pink");
        btnBackground.setMinWidth(tabs.getPrefWidth());
        btnAttributes.setMinWidth(tabs.getPrefWidth());
        btnSkills.setMinWidth(tabs.getPrefWidth());
        tabs.setMaxSize(150, windowHeight);
        
        VBox root = new VBox(tabs);
        
        Scene scene = new Scene(root, windowWidth, windowHeight);
        
        primaryStage.setScene(scene);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
