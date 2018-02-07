/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsacharactercreator;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Daeuiot
 */
public class TSACharacterCreator extends Application {
    Pane contentBox; //This is the Pane that contains all the content that changes when changing tabs
    PlayerCharacter pc; //The currently sellected PC
    
    //Data
    ObservableList<CharacterBackground> characterBackgrounds;
    
    @Override
    public void start(Stage primaryStage) {
        pc = new PlayerCharacter(); //Set a default PC
        
        build(primaryStage, 900, 600);
        
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
        loadData();
        
        VBox tabs = new VBox(); //Used to hold the tabs for the categories ie. Background/Class/Skills/etc.
        Button btnCharacters    = new Button("Characters");
        Button btnBackground    = new Button("Background");
        Button btnAttributes    = new Button("Attributes");
        Button btnSkills        = new Button("Skills");
        tabs.getChildren().addAll(btnCharacters, btnBackground, btnAttributes, btnSkills);
        tabs.setStyle("-fx-spacing: 10; -fx-alignment: center; -fx-padding: 10; -fx-background-color: pink");
        tabs.setPrefWidth(100);
        btnCharacters.setMinWidth(tabs.getPrefWidth());
        btnBackground.setMinWidth(tabs.getPrefWidth());
        btnAttributes.setMinWidth(tabs.getPrefWidth());
        btnSkills.setMinWidth(tabs.getPrefWidth());
        tabs.setMaxSize(150, windowHeight);
        
        //---Backgrounds ContentBox
        //Change the background so that the culture textfield is a ComboBox
        //So you pick a culture which filters what background you can pick
        //which also changes from a textfield to a combo box
        //Need to add culture description, not just location description
        Label lbBackgroundName = new Label("Backgrounds");
        lbBackgroundName.relocate(5, 10);
        
        ComboBox<CharacterBackground> cbBackgrounds = new ComboBox<>();
        cbBackgrounds.setItems(characterBackgrounds);
        cbBackgrounds.setVisibleRowCount(6);
        cbBackgrounds.setPromptText("Choose a background");
        //Need to add it so the name displayed is the location not the toString method
        cbBackgrounds.relocate(5, 30);
        
        TextField tfBackgroundCulture = new TextField("");
        tfBackgroundCulture.relocate(5, 70);
        TextField tfBackgroundLocation = new TextField("");
        tfBackgroundLocation.relocate(5, 100);
        TextArea tfBackgroundDescription = new TextArea("");
        tfBackgroundDescription.setWrapText(true);
        tfBackgroundDescription.relocate(5, 130);
        
        cbBackgrounds.setOnAction((e) -> {
            pc.setBackground(cbBackgrounds.getValue());
            //I would prefer to bind the textfields to the backgorund but I just
            //couldn't get that to work so instead I'm just setting them
            tfBackgroundCulture.setText(cbBackgrounds.getValue().getCulture());
            tfBackgroundLocation.setText(cbBackgrounds.getValue().getLocation());
            tfBackgroundDescription.setText(cbBackgrounds.getValue().getDescription());
        });
        
        contentBox = new Pane(lbBackgroundName,cbBackgrounds,tfBackgroundCulture,tfBackgroundLocation,tfBackgroundDescription);
        contentBox.setStyle("-fx-background-color: orange");
        contentBox.setMinSize(600, 400);
        
        tabs.relocate(10, 50);
        contentBox.relocate(200, 50);
        Pane root = new Pane(tabs, contentBox);
        Scene scene = new Scene(root, windowWidth, windowHeight);
        
        primaryStage.setScene(scene);
    }
    
    /**
     * Used to load data from files(probably JSON)
     * Right now it's just hard coded in
     */
    private void loadData()
    {
        characterBackgrounds = FXCollections.observableArrayList();
        characterBackgrounds.clear();
        characterBackgrounds.add(new CharacterBackground(
                "United Republic of Nations",
                "Republic City",
                "Republic City is the capital city of the United Republic of Nations"));
        characterBackgrounds.add(new CharacterBackground(
                "Earth Kingdom",
                "Ba Sing Se",
                "By far the largest city in the world of Avatar, Ba Sing Se is more of a small country than a mere city"));
    }
    
    /**
     * This method gets a PC and loads that data as the current PC
     * 
     * @param pc The Player Character to load
     */
    private void loadCharacter(PlayerCharacter pc)
    {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
