/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsacharactercreator;

import daeuiot.datatypes.PlayerCharacter;
import daeuiot.datatypes.CharacterBackground;
import daeuiot.utility.Helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
    Pane contentPage; //This is the Pane that contains all the content that changes when changing tabs
    PlayerCharacter pc; //The currently sellected PC
    
    enum ContentPage {
        CHARACTER, BACKGROUND, ATTRIBUTE, SKILL
    }
    
    ContentPage currentContentPage;
    
    //Data
    ObservableList<PlayerCharacter> playerCharacters;
    ObservableList<CharacterBackground> characterBackgrounds;
    
    //Content Pages
    Pane characterContent;
    Pane backgroundContent;
    Pane attributeContent;
    Pane skillContent;
    
    @Override
    public void start(Stage primaryStage) {
        pc = new PlayerCharacter(); //Set a default PC
        loadData();
        
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
        //Set up tabs on the side - used for switching content pages
        VBox tabs = new VBox(); //Used to hold the tabs for the categories ie. Background/Class/Skills/etc.
        Button btnCharacters    = new Button("Characters");
        Button btnBackgrounds   = new Button("Backgrounds");
        Button btnAttributes    = new Button("Attributes");
        Button btnSkills        = new Button("Skills");
        tabs.getChildren().addAll(btnCharacters, btnBackgrounds, btnAttributes, btnSkills);
        tabs.setStyle("-fx-spacing: 10; -fx-alignment: center; -fx-padding: 10; -fx-background-color: pink");
        tabs.setPrefWidth(100);
        btnCharacters.setMinWidth(tabs.getPrefWidth());
        btnBackgrounds.setMinWidth(tabs.getPrefWidth());
        btnAttributes.setMinWidth(tabs.getPrefWidth());
        btnSkills.setMinWidth(tabs.getPrefWidth());
        tabs.setMaxSize(150, windowHeight);
        
        buildCharacterContentPage();
        
        buildBackgroundContentPage();
        
        buildAttributeContentPage();
        
        buildSkillContentPage();
        
        contentPage = new Pane();
        contentPage.getChildren().setAll(characterContent.getChildren());
        currentContentPage = ContentPage.CHARACTER;
        contentPage.setStyle("-fx-background-color: orange");
        contentPage.setMinSize(600, 400); //Make it dynamic size based on windowWidth/windowHeight
        
        //Switching Content Pages
        btnCharacters.setOnAction((e) -> {
            saveContentPageChildren(contentPage.getChildren());
            contentPage.getChildren().setAll(characterContent.getChildren());
            currentContentPage = ContentPage.CHARACTER;
        });
        btnBackgrounds.setOnAction((e) -> {
            saveContentPageChildren(contentPage.getChildren());
            contentPage.getChildren().setAll(backgroundContent.getChildren());
            currentContentPage = ContentPage.BACKGROUND;
        });
        btnAttributes.setOnAction((e) -> {
            saveContentPageChildren(contentPage.getChildren());
            contentPage.getChildren().setAll(attributeContent.getChildren());
            currentContentPage = ContentPage.ATTRIBUTE;
        });
        btnSkills.setOnAction((e) -> {
            saveContentPageChildren(contentPage.getChildren());
            contentPage.getChildren().setAll(skillContent.getChildren());
            currentContentPage = ContentPage.SKILL;
        });
        
        tabs.relocate(10, 50);
        contentPage.relocate(200, 50);
        Pane root = new Pane(tabs, contentPage);
        Scene scene = new Scene(root, windowWidth, windowHeight);
        
        primaryStage.setScene(scene);
    }
    
    private void buildAttributeContentPage()
    {
        Label lbAttributeName = new Label("Attributes");
        lbAttributeName.relocate(5, 10);
        
        attributeContent = new Pane(lbAttributeName);
    }
    
    private void buildSkillContentPage()
    {
        Label lbSkillName = new Label("Skills");
        lbSkillName.relocate(5, 10);
        
        skillContent = new Pane(lbSkillName);
    }
    
    private void buildCharacterContentPage()
    {
        Label lbCharacterName = new Label("Characters");
        lbCharacterName.relocate(5, 10);
        
        Button btnSave = new Button("SAVE");
        btnSave.relocate(100, 10);
        btnSave.setOnAction((e) -> {
            saveCharacter();
        });
        
        characterContent = new Pane(lbCharacterName, btnSave);
    }
    
    private void buildBackgroundContentPage()
    {
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
        
        TextField tfBackgroundCulture = new TextField(pc.getBackground().getCulture());
        tfBackgroundCulture.relocate(5, 70);
        TextField tfBackgroundLocation = new TextField(pc.getBackground().getLocation());
        tfBackgroundLocation.relocate(5, 100);
        TextArea tfBackgroundDescription = new TextArea(pc.getBackground().getDescription());
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
        
        backgroundContent = new Pane(lbBackgroundName,cbBackgrounds,tfBackgroundCulture,tfBackgroundLocation,tfBackgroundDescription);
    }
    
    private void saveContentPageChildren(ObservableList<Node> children)
    {
        switch(currentContentPage)
        {
            case ATTRIBUTE: 
                attributeContent.getChildren().setAll(children);
                break;
            case BACKGROUND: 
                backgroundContent.getChildren().setAll(children);
                break;
            case CHARACTER: 
                characterContent.getChildren().setAll(children);
                break;
            case SKILL: 
                skillContent.getChildren().setAll(children);
                break;
            default:
                System.err.println("SaveContentPageChildren - no case for "+currentContentPage.toString());
        }
    }
    
    /**
     * Used to load data from files(probably JSON)
     * Right now it's just hard coded in
     */
    private void loadData()
    {
        playerCharacters = FXCollections.observableArrayList();
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
     * Saves the current playerChacter to file
     */
    private void saveCharacter()
    {
        String outText = Helper.getJSON(pc);
        try
        {
            File saveFolder = new File("Characters");
            if(saveFolder.exists() == false)
            {
                saveFolder.mkdir();
            }
            PrintWriter fout = new PrintWriter(new File("Characters/"+pc.getFileName()+".json"));
            fout.println(outText);
            fout.close();
        }catch(FileNotFoundException e)
        {
            System.err.println("SAVE CHARACTER - "+e.getMessage());
        }
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
