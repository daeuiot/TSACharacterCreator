/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeuiot.tsacharactercreator;

import daeuiot.datatypes.*;
import daeuiot.utility.Helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author Daeuiot
 */
public class TSACharacterCreator extends Application {
    Pane contentPage; //This is the Pane that contains all the content that changes when changing tabs
    PlayerCharacter pc; //The currently sellected PC
    
    enum ContentPage {
        CHARACTER, BACKGROUND, ATTRIBUTE, SKILL, TALENT
    }
    
    ContentPage currentContentPage;
    
    //Data
    ObservableList<PlayerCharacter> playerCharacters = FXCollections.observableArrayList();
    ObservableList<CharacterDataType> cultures = FXCollections.observableArrayList();
    ObservableList<CharacterDataType> locations = FXCollections.observableArrayList();
    ObservableList<CharacterDataType> skills = FXCollections.observableArrayList();
    ObservableList<PlayerSkill> characterSkills = FXCollections.observableArrayList();
    
    //Content Pages
    Pane characterContent;
    Pane backgroundContent;
    Pane attributeContent;
    Pane skillContent;
    Pane talentContent;
    
    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();
        //Make an argument class that holes all the parameters, with default values if not read
        
        loadData();
        pc = new PlayerCharacter("char01", skills.toArray(new Skill[skills.size()]));
        characterSkills.addAll(pc.getSkills());
        
        int x = 900; //These are gonna be changable by argument later
        int y = 600; //but It's currently designed for 900x600 anyway
        build(primaryStage, x, y);
        
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
        Button btnTalents       = new Button("Talents");
        tabs.getChildren().addAll(btnCharacters, btnBackgrounds, btnAttributes, btnSkills, btnTalents);
        tabs.setStyle("-fx-spacing: 10; -fx-alignment: center; -fx-padding: 10; -fx-background-color: pink");
        tabs.setPrefWidth(100);
        btnCharacters.setMinWidth(tabs.getPrefWidth());
        btnBackgrounds.setMinWidth(tabs.getPrefWidth());
        btnAttributes.setMinWidth(tabs.getPrefWidth());
        btnSkills.setMinWidth(tabs.getPrefWidth());
        btnTalents.setMinWidth(tabs.getPrefWidth());
        tabs.setMaxSize(150, windowHeight);
        
        buildCharacterContentPage();
        
        buildBackgroundContentPage();
        
        buildAttributeContentPage();
        
        buildSkillContentPage();
        
        buildTalentContentPage();
        
        contentPage = new Pane();
        contentPage.getChildren().setAll(characterContent.getChildren());
        currentContentPage = ContentPage.CHARACTER;
        contentPage.setStyle("-fx-background-color: orange");
        contentPage.setMinSize(750, 540); //Make it dynamic size based on windowWidth/windowHeight
        
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
        btnTalents.setOnAction((e) -> {
            saveContentPageChildren(contentPage.getChildren());
            contentPage.getChildren().setAll(talentContent.getChildren());
            currentContentPage = ContentPage.TALENT;
        });
        
        tabs.relocate(10, 50);
        contentPage.relocate(140, 50);
        Pane root = new Pane(tabs, contentPage);
        Scene scene = new Scene(root, windowWidth, windowHeight);
        
        primaryStage.setScene(scene);
        btnTalents.fire();
    }
    
    private HBox generateTalentHBox()
    {
        Label lbTest = new Label("test");
        HBox hbTalent = new HBox(lbTest);
        hbTalent.setMinSize(145, 145);
        hbTalent.setStyle("-fx-background: white");
        return hbTalent;
    }
    
    private void buildTalentContentPage()
    {
        Label lbTalentName = new Label("Talents");
        lbTalentName.relocate(5, 10);
        
        Label lbTierOne = new Label("Tier One");
        lbTierOne.relocate(55, 30);
        
        Label lbTierTwo = new Label("Tier Two");
        lbTierTwo.relocate(55+149, 30);
        
        Label lbTierThree = new Label("Tier Three");
        lbTierThree.relocate(55+(149*2), 30);
        
        Label lbTierFour = new Label("Tier Four");
        lbTierFour.relocate(55+(149*3), 30);
        
        Label lbTierFive = new Label("Tier Five");
        lbTierFive.relocate(55+(149*4), 30);
        
        ScrollPane spTierOne = new ScrollPane(generateTalentHBox());
        spTierOne.relocate(5+(149*0), 50);
        spTierOne.setMinSize(145, 485);
        spTierOne.setStyle("-fx-background: orange; -fx-border-color: darkorange");
        spTierOne.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spTierOne.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        ScrollPane spTierTwo = new ScrollPane();
        spTierTwo.relocate(5+(149*1), 50);
        spTierTwo.setMinSize(145, 485);
        spTierTwo.setStyle("-fx-background: orange; -fx-border-color: darkorange");
        spTierTwo.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spTierTwo.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        ScrollPane spTierThree = new ScrollPane();
        spTierThree.relocate(5+(149*2), 50);
        spTierThree.setMinSize(145, 485);
        spTierThree.setStyle("-fx-background: orange; -fx-border-color: darkorange");
        spTierThree.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spTierThree.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        ScrollPane spTierFour = new ScrollPane();
        spTierFour.relocate(5+(149*3), 50);
        spTierFour.setMinSize(145, 485);
        spTierFour.setStyle("-fx-background: orange; -fx-border-color: darkorange");
        spTierFour.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spTierFour.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        ScrollPane spTierFive = new ScrollPane();
        spTierFive.relocate(5+(149*4), 50);
        spTierFive.setMinSize(145, 485);
        spTierFive.setStyle("-fx-background: orange; -fx-border-color: darkorange");
        spTierFive.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spTierFive.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        talentContent = new Pane(lbTalentName, lbTierOne, lbTierTwo, lbTierThree, lbTierFour, lbTierFive, spTierOne, spTierTwo, spTierThree, spTierFour, spTierFive);
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
        
        //Setting up the table
        TableView<PlayerSkill> tableView = new TableView<>();
        TableColumn<PlayerSkill, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<PlayerSkill, String> attributeColumn = new TableColumn<>("Attribute");
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("Attribute"));
        TableColumn<PlayerSkill, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        TableColumn<PlayerSkill, String> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        
        TableColumn increaseSkillRank = new TableColumn("");
        increaseSkillRank.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<PlayerSkill, String>, TableCell<PlayerSkill, String>> increaseSkillRankCellFactory = new Callback<TableColumn<PlayerSkill, String>, TableCell<PlayerSkill, String>>() {
            //<editor-fold defaultstate="collapsed" desc="Button">
            @Override
            public TableCell call(final TableColumn<PlayerSkill, String> param) {
                final TableCell<PlayerSkill, String> cell = new TableCell<PlayerSkill, String>() {
                    
                    final Button btn = new Button("+");
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                PlayerSkill skill = getTableView().getItems().get(getIndex());
                                pc.increaseSkill(skill);
                                getTableView().refresh();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
//</editor-fold>
        };
        increaseSkillRank.setCellFactory(increaseSkillRankCellFactory);
        
        TableColumn decreaseSkillRank = new TableColumn("");
        decreaseSkillRank.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<PlayerSkill, String>, TableCell<PlayerSkill, String>> decreaseSkillRankCellFactory = (final TableColumn<PlayerSkill, String> param) -> {
             //<editor-fold defaultstate="collapsed" desc="Button">
            final TableCell<PlayerSkill, String> cell = new TableCell<PlayerSkill, String>() {
                
                final Button btn = new Button("-");
                
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction(event -> {
                            PlayerSkill skill = getTableView().getItems().get(getIndex());
                            pc.decreaseSkill(skill);
                            getTableView().refresh();
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            };
            return cell;
//</editor-fold>
        };
        decreaseSkillRank.setCellFactory(decreaseSkillRankCellFactory);
        
        tableView.getColumns().clear();
        tableView.getColumns().addAll(nameColumn,attributeColumn,typeColumn,rankColumn,increaseSkillRank,decreaseSkillRank);
        tableView.setItems(characterSkills);
        tableView.relocate(5, 30);
        tableView.setMinWidth(590);
        tableView.setMaxHeight(190);
        
        skillContent = new Pane(lbSkillName,tableView);
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
        
        ComboBox<CharacterDataType> cbBackgroundsCulture = new ComboBox<>();
        cbBackgroundsCulture.setItems(cultures);
        cbBackgroundsCulture.setVisibleRowCount(6);
        cbBackgroundsCulture.setPromptText(pc.getBackground()!=null?getCharacterBackgroundCulture(pc.getBackground().getCultureKey()).getName():"Choose a Culture");
        cbBackgroundsCulture.setConverter(new StringConverter<CharacterDataType>() {
            @Override
            public String toString(CharacterDataType object) {
                return object.getName();
            }
            @Override
            public CharacterDataType fromString(String string) {
                return cbBackgroundsCulture.getItems().stream().filter(b ->
                b.getName().equals(string)).findFirst().orElse(null);
            }
        });
        cbBackgroundsCulture.relocate(5, 30);
        cbBackgroundsCulture.setMinWidth(275);
        
        ComboBox<CharacterDataType> cbBackgroundsLocation = new ComboBox<>();
        //cbBackgroundsLocation.setItems(characterBackgrounds);
        cbBackgroundsLocation.setVisibleRowCount(6);
        cbBackgroundsLocation.setPromptText(pc.getBackground()!=null?pc.getBackground().getName():"Choose a Location");
        cbBackgroundsLocation.setConverter(new StringConverter<CharacterDataType>() {
            @Override
            public String toString(CharacterDataType object) {
                return object.getName();
            }
            @Override
            public CharacterDataType fromString(String string) {
                return cbBackgroundsLocation.getItems().stream().filter(b ->
                b.getName().equals(string)).findFirst().orElse(null);
            }
        });
        cbBackgroundsLocation.relocate(300, 30);
        cbBackgroundsLocation.setMinWidth(275);
        
        TextArea tfBackgroundCultureDescription = new TextArea(pc.getBackground()!=null?getCharacterBackgroundCulture(pc.getBackground().getCultureKey()).getDescription():"");
        tfBackgroundCultureDescription.setWrapText(true);
        tfBackgroundCultureDescription.relocate(5, 65);
        tfBackgroundCultureDescription.setMaxWidth(275);
        tfBackgroundCultureDescription.setMinHeight(300);
        TextArea tfBackgroundLocationDescription = new TextArea(pc.getBackground()!=null?pc.getBackground().getDescription():"");
        tfBackgroundLocationDescription.setWrapText(true);
        tfBackgroundLocationDescription.relocate(300, 65);
        tfBackgroundLocationDescription.setMaxWidth(275);
        tfBackgroundLocationDescription.setMinHeight(300);
        
        cbBackgroundsCulture.setOnAction((e) -> {
            tfBackgroundCultureDescription.setText(cbBackgroundsCulture.getValue().getDescription());
            cbBackgroundsLocation.setItems(locations.filtered(location ->
                    ((CharacterBackgroundLocation)location).getCultureKey().equals(cbBackgroundsCulture.getValue().getKey()))
            );
            tfBackgroundLocationDescription.setText("");
        });
        
        cbBackgroundsLocation.setOnAction((e) -> {
            if(cbBackgroundsLocation.getValue() == null)
                return; //This is for when you select a location then change the culture, probably a better way but good enough for now
            tfBackgroundLocationDescription.setText(cbBackgroundsLocation.getValue().getDescription());
            pc.setBackground((CharacterBackgroundLocation)cbBackgroundsLocation.getValue());
        });
        
        backgroundContent = new Pane(lbBackgroundName,cbBackgroundsCulture,cbBackgroundsLocation,tfBackgroundCultureDescription,tfBackgroundLocationDescription);
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
            case TALENT: 
                talentContent.getChildren().setAll(children);
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
        playerCharacters.clear();
        cultures.clear();
        locations.clear();
        skills.clear();
        characterSkills.clear();
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
            System.err.println("LOAD SKILLS - "+e.getMessage());
        }
        
        try
        {
            Scanner fin = new Scanner(new File("Data/cultures.json"));
            String text = "";
            while(fin.hasNextLine())
            {
                text += fin.nextLine() + "\n";
            }
            fin.close();
            cultures.addAll(Helper.getObjectList(text, CharacterBackgroundCulture.class));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("LOAD CULTURES - "+e.getMessage());
        }
        
        
        loadFromFile("locations",locations,CharacterBackgroundLocation.class);
        /*try
        {
            Scanner fin = new Scanner(new File("Data/locations.json"));
            String text = "";
            while(fin.hasNextLine())
            {
                text += fin.nextLine() + "\n";
            }
            fin.close();
            locations.addAll(Helper.getObjectList(text, CharacterBackgroundLocation.class));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("LOAD LOCATIONS - "+e.getMessage());
        }*/
    }
    
    private static <T> void loadFromFile(String fileType, ObservableList<T> list, Class<? extends T> theClass)
    {
        try
        {
            Scanner fin = new Scanner(new File("Data/"+fileType+".json"));
            String text = "";
            while(fin.hasNextLine())
            {
                text += fin.nextLine() + "\n";
            }
            fin.close();
            list.addAll(Helper.getObjectList(text, theClass));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("LOAD "+(fileType.toUpperCase())+" - "+e.getMessage());
        }
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
        }catch(Exception e)
        {
            System.err.println("SAVE CHARACTER - "+e.getMessage());
        }
    }
    
    /**
     * Finds the culture with a given key
     * 
     * @param key Key of culture you want
     * @return The culture with given key or null otherwise
     */
    CharacterBackgroundCulture getCharacterBackgroundCulture(String key)
    {
        for (int i = 0; i < cultures.size(); i++) {
            if(cultures.get(i).getKey().equals(key))
                return (CharacterBackgroundCulture)cultures.get(i);
        }
        return null;
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
