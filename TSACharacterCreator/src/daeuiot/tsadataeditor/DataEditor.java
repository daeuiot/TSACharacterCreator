/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsadataeditor;

import daeuiot.datatypes.CharacterBackground;
import daeuiot.datatypes.Skill;
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
import javafx.scene.control.TextField;
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
    //Data
    ObservableList<Skill> skills = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        loadData();
        build(primaryStage);
        
        primaryStage.setTitle("Avatar: The Second Age - Data Editor");
        primaryStage.show();
    }
    
    void build(Stage primaryStage)
    {
        Label lbSkills = new Label("Skills");
        lbSkills.relocate(5, 5);
        
        Button btnSave = new Button("SAVE");
        btnSave.relocate(50, 5);
        btnSave.setOnAction((e) -> {
            saveData();
        });
        
        Button btnLoad = new Button("LOAD");
        btnLoad.relocate(100, 5);
        btnLoad.setOnAction((e) -> {
            loadData();
        });
        
        Button btnAdd = new Button("ADD");
        btnAdd.relocate(150, 5);
        btnAdd.setOnAction((e) -> {
            //Using a Dialog just to try it out
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
                   return new Skill(tfName.getText(), cbType.getValue().toUpperCase(), cbAttribute.getValue().toUpperCase());
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
        });
        
        Pane root = new Pane(lbSkills, btnSave, btnLoad, btnAdd);
        
        Scene scene = new Scene(root, 600, 800);
        primaryStage.setScene(scene);
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
            fout.print(Helper.getJSONList(skills, Skill.class));
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
