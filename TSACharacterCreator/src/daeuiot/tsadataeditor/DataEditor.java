/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsadataeditor;

import daeuiot.datatypes.*;
import daeuiot.utility.Helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Daeuiot
 */
public class DataEditor extends Application {
    enum ContentPage {
        BACKGROUND, ATTRIBUTE, SKILL
    }
    
    ContentPage currentContentPage;
    
    //Data
    ObservableList<CharacterDataType> skills = FXCollections.observableArrayList();
    ObservableList<CharacterDataType> attributes = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        loadData();
        build(primaryStage, 600, 800);
        
        primaryStage.setTitle("Avatar: The Second Age - Data Editor");
        primaryStage.show();
    }
    
    void build(Stage primaryStage, int windowWidth, int windowHeight)
    {
        //Set up tabs on the side - used for switching content pages
        VBox tabs = new VBox(); //Used to hold the tabs for the categories ie. Background/Class/Skills/etc.
        Button btnBackgrounds   = new Button("Backgrounds");
        Button btnAttributes    = new Button("Attributes");
        Button btnSkills        = new Button("Skills");
        tabs.getChildren().addAll(btnBackgrounds, btnAttributes, btnSkills);
        tabs.setStyle("-fx-spacing: 10; -fx-alignment: center; -fx-padding: 10; -fx-background-color: pink");
        tabs.setPrefWidth(100);
        btnBackgrounds.setMinWidth(tabs.getPrefWidth());
        btnAttributes.setMinWidth(tabs.getPrefWidth());
        btnSkills.setMinWidth(tabs.getPrefWidth());
        tabs.setMaxSize(150, windowHeight);
        
        Label lbTabName = new Label("Skills");
        lbTabName.relocate(5, 5);
        
        Button btnSave = new Button("SAVE");
        btnSave.relocate(5, 25);
        btnSave.setOnAction((e) -> {
            saveData();
        });
        Button btnLoad = new Button("LOAD");
        btnLoad.relocate(55, 25);
        btnLoad.setOnAction((e) -> {
            loadData();
        });
        Button btnAdd = new Button("ADD");
        btnAdd.relocate(105, 25);
        btnAdd.setOnAction((e) -> {
            skillAdd();
        });
        
        //Setting up the table
        TableView<CharacterDataType> tableView = new TableView<>();
        TableColumn<CharacterDataType, String> keyColumn = new TableColumn<>("Key");
        TableColumn<CharacterDataType, String> nameColumn = new TableColumn<>("Name");
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("Key"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableView.getColumns().clear();
        tableView.getColumns().addAll(keyColumn, nameColumn);
        tableView.setItems(skills);
        keyColumn.setMinWidth(100);
        nameColumn.setMinWidth(tableView.getWidth()+150);
        tableView.relocate(5, 55);
        
        Pane contentPage = new Pane(lbTabName, btnSave, btnLoad, btnAdd,tableView);
        currentContentPage = ContentPage.SKILL;
        contentPage.setStyle("-fx-background-color: orange");
        contentPage.setMinSize(windowWidth-145, windowHeight-100);
        
        //Switching currentData
        /*btnBackgrounds.setOnAction((e) -> {
            saveContentPageChildren(contentPage.getChildren());
            contentPage.getChildren().setAll(backgroundContent.getChildren());
            currentContentPage = ContentPage.BACKGROUND;
        });*/
        btnAttributes.setOnAction((e) -> {
            btnAdd.setOnAction((ev) -> {
                attributeAdd();
            });
            lbTabName.setText("Attributes");
            tableView.setItems(attributes);
        });
        btnSkills.setOnAction((e) -> {
            btnAdd.setOnAction((ev) -> {
                skillAdd();
            });
            lbTabName.setText("Skills");
            tableView.setItems(skills);
        });
        
        tabs.relocate(5, 50);
        contentPage.relocate(135, 50);
        Pane root = new Pane(tabs, contentPage);
        
        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);
    }
    
    private void skillAdd()
    {
        //Dialog might not be the best option, just an option
        Dialog<Skill> dialog = new Dialog<>();
        dialog.setTitle("Add Skill");
        dialog.setHeaderText(null);
        dialog.setContentText(null);
        dialog.setGraphic(null);

        TextField tfName = new TextField();
        tfName.setPromptText("Name");

        ComboBox<String> cbType = new ComboBox<>();
        cbType.getItems().addAll("General", "Combat", "Knowledge");
        cbType.setPromptText("Choose a Type");
        cbType.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> cbAttribute = new ComboBox<>();
        cbAttribute.getItems().addAll("Body", "Agility", "Charisma", "Guile", "Intellect", "Chi", "Any");
        cbAttribute.setPromptText("Choose an Attribute");
        cbAttribute.setMaxWidth(Double.MAX_VALUE);

        VBox vbox = new VBox(tfName, cbType, cbAttribute);
        dialog.getDialogPane().setContent(vbox);

        ButtonType btnAddType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAddType, ButtonType.CANCEL);

        Node btnAdd2 = dialog.getDialogPane().lookupButton(btnAddType);
        btnAdd2.setDisable(true);

        tfName.textProperty().addListener((e2) -> {
            btnAdd2.setDisable(tfName.getText().isEmpty() || cbType.getValue() == null || cbAttribute.getValue() == null);
        });                
        cbType.valueProperty().addListener((e2) -> {
            btnAdd2.setDisable(tfName.getText().isEmpty() || cbType.getValue() == null || cbAttribute.getValue() == null);
        });    
        cbAttribute.valueProperty().addListener((e2) -> {
            btnAdd2.setDisable(tfName.getText().isEmpty() || cbType.getValue() == null || cbAttribute.getValue() == null);
        });    

        Platform.runLater(() -> tfName.requestFocus());

        dialog.setResultConverter(dialogButton ->
        {
           if(dialogButton == btnAddType)
           {
               return new Skill(tfName.getText(), cbType.getValue().toUpperCase(), cbAttribute.getValue().toUpperCase(), "DESCRIPTION TO BE ADDED");
           }
           return null;
        });

        Optional<Skill> result = dialog.showAndWait();
        result.ifPresent(skill ->
        {
            if(skill != null)
            {
                skills.add(skill);
            }
        });
    }
    
    private void attributeAdd()
    {
        //Dialog might not be the best option, just an option
        Dialog<Skill> dialog = new Dialog<>();
        dialog.setTitle("Add Attribute");
        dialog.setHeaderText(null);
        dialog.setContentText(null);
        dialog.setGraphic(null);

        //Need to change this to reflect Attribute
        TextField tfName = new TextField();
        tfName.setPromptText("Name");

        ComboBox<String> cbType = new ComboBox<>();
        cbType.getItems().addAll("General", "Combat", "Knowledge");
        cbType.setPromptText("Choose a Type");
        cbType.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> cbAttribute = new ComboBox<>();
        cbAttribute.getItems().addAll("Body", "Agility", "Charisma", "Guile", "Intellect", "Chi", "Any");
        cbAttribute.setPromptText("Choose an Attribute");
        cbAttribute.setMaxWidth(Double.MAX_VALUE);

        VBox vbox = new VBox(tfName, cbType, cbAttribute);
        dialog.getDialogPane().setContent(vbox);

        ButtonType btnAddType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnAddType, ButtonType.CANCEL);

        Node btnAdd2 = dialog.getDialogPane().lookupButton(btnAddType);
        btnAdd2.setDisable(true);

        tfName.textProperty().addListener((e2) -> {
            btnAdd2.setDisable(tfName.getText().isEmpty() || cbType.getValue() == null || cbAttribute.getValue() == null);
        });                
        cbType.valueProperty().addListener((e2) -> {
            btnAdd2.setDisable(tfName.getText().isEmpty() || cbType.getValue() == null || cbAttribute.getValue() == null);
        });    
        cbAttribute.valueProperty().addListener((e2) -> {
            btnAdd2.setDisable(tfName.getText().isEmpty() || cbType.getValue() == null || cbAttribute.getValue() == null);
        });    

        Platform.runLater(() -> tfName.requestFocus());

        dialog.setResultConverter(dialogButton ->
        {
           if(dialogButton == btnAddType)
           {
               return new Skill(tfName.getText(), cbType.getValue().toUpperCase(), cbAttribute.getValue().toUpperCase(), "DESCRIPTION TO BE ADDED");
           }
           return null;
        });

        Optional<Skill> result = dialog.showAndWait();
        result.ifPresent(skill ->
        {
            if(skill != null)
            {
                //skills.add(skill);
            }
        });
    }
    
    void loadData()
    {
        try
        {
            Scanner fin = new Scanner(new File("Data/skills.json"));
            String text = "";
            while(fin.hasNextLine())
            {
                text += fin.nextLine() + "\n";
            }
            fin.close();
            skills.clear();
            skills.addAll(Helper.getObjectList(text, Skill.class));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("LOAD DATA - "+e.getMessage());
        }
    }
    
    void saveData()
    {
        try
        {
            PrintWriter fout = new PrintWriter(new File("Data/skills.json"));
            fout.print(Helper.getJSONList(skills, CharacterDataType.class));
            fout.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("SAVE DATA - "+e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}