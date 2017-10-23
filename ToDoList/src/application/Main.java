package application;
import java.io.*;
import java.util.*;

import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static javafx.application.Application.launch;

import java.awt.Desktop;
import java.awt.Label;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	TableView<MyPlans> table;
	TextField plan;
	DatePicker datePicker;
	ChoiceBox categories;
	private Desktop desktop = Desktop.getDesktop();
	String text="";
    
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		TextArea textarea = new TextArea();
		textarea.setPrefHeight(150);
		
		Text label = new Text("Your Plan : ");
		
		plan = new TextField();
		plan.setPrefWidth(170.0);
		
		Text date = new Text("Day : ");
		Text catg = new Text("Category : ");
		datePicker = new DatePicker();
		
		categories = new ChoiceBox(); 
		categories.getItems().addAll("Shopping", "Submissions", "Tasks", "Entertainment", "Sports","Others"); 
		categories.setPrefWidth(170.0); 
		
		Button add = new Button("Add");
		add.setOnAction(e1 -> addButtonClicked());
		add.setPrefSize(125.0, 50.0);
		
		Button delete = new Button("Delete");
		delete.setOnAction(e2 -> deleteButtonClicked());
		delete.setPrefSize(125.0, 50.0);
		
		Button op = new Button("Saved Plans");
		op.setPrefSize(125.0, 50.0);
		op.setOnAction(
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				
				try {
					textarea.clear();
					File vplan = new File("p.txt");
					Scanner s = new Scanner(vplan);
					while(s.hasNext())
					{
						textarea.appendText(text +"\n" + s.nextLine());
					}
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		}
				);
		
		Button clr = new Button("Clear Saved");
		clr.setPrefSize(125.0, 50.0);
		clr.setOnAction(
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				
				try {
					
					File vplan = new File("p.txt");
					PrintWriter p = new PrintWriter(vplan);
					p.println("");
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
				
		Button modify = new Button("Modify");
		modify.setPrefSize(125.0, 50.0);
		modify.setOnAction(
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				
				try {
					
					File vplan = new File("p.txt");
					PrintWriter p = new PrintWriter(vplan);
					p.println(textarea.getText());
					p.close();
					
					
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
				
		datePicker.setPrefWidth(170.0);
		Text display = new Text("Display : ");
		
		GridPane gridPane = new GridPane();
	    gridPane.setMinSize(100, 70);   
	    gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	    gridPane.setVgap(10); 
	    gridPane.setHgap(20);
	    
	    TableColumn<MyPlans,String> plans = new TableColumn<>("Plans");
        plans.setMinWidth(100);
        plans.setCellValueFactory(new PropertyValueFactory("pl"));
 
        TableColumn<MyPlans,String> cate = new TableColumn<>("Category");
        cate.setMinWidth(100);
        cate.setCellValueFactory(new PropertyValueFactory("ct"));
 
        TableColumn<MyPlans,String> tim = new TableColumn<>("Day");
        tim.setMinWidth(100);
        tim.setCellValueFactory(new PropertyValueFactory("time"));
        
        TableColumn<MyPlans,Boolean> cb = new TableColumn("Status");
        cb.setMinWidth(100);
        cb.setCellValueFactory(new PropertyValueFactory("st"));
        
        table = new TableView<>();
        table.setPrefHeight(300);
        table.getColumns().addAll(plans, cate, tim, cb);
        cb.setCellFactory(column -> new CheckBoxTableCell());
        table.setEditable(true);
        
        Text h = new Text("Start planning...");
        h.setFill(Color.DARKVIOLET);
        
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(10000)); 
        transition.setNode(h); 
        transition.setByX(1200);  
        transition.setCycleCount(50); 
        transition.setAutoReverse(false);
        transition.play(); 
        
        gridPane.add(h,0,2);
        
        plans.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        cate.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        tim.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        cb.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        
        GridPane grid = new GridPane();
        grid.add( add, 2,1);
        grid.add( delete, 3,1);
        grid.add(op, 4, 1);
        grid.add(clr, 5, 1);
        grid.add(modify, 6, 1);
        grid.setMinSize(200, 50);   
        grid.setPadding(new Insets(10, 10, 10, 10)); 
        grid.setVgap(10); 
        grid.setHgap(20);
        
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(label, plan, date, datePicker, catg, categories);
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(gridPane,table, hBox,grid,textarea);
 
	    Scene scene = new Scene(vBox);   
	    
	    add.setStyle("-fx-background-color: darkolivegreen; -fx-text-fill: white;"); 
	    delete.setStyle("-fx-background-color: firebrick; -fx-text-fill: white;"); 
	    op.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); 
	    clr.setStyle("-fx-background-color: black; -fx-text-fill: white;"); 
	       
	    table.setStyle("-fx-font: normal bold 15px 'times'; -fx-background-color: CHOCOLATE; "); 
	    vBox.setStyle("-fx-font: normal bold 15px 'times' ");  
	    grid.setStyle("-fx-background-color: NAVAJOWHITE"); 
	    gridPane.setStyle("-fx-background-color: NAVAJOWHITE;"); 
	    hBox.setStyle("-fx-background-color: BEIGE;"); 
	    label.setStyle("-fx-font: normal bold 14px 'times' "); 
	    date.setStyle("-fx-font: normal bold 14px 'times' ");
	    catg.setStyle("-fx-font: normal bold 14px 'times' "); 
	    h.setStyle("-fx-font: normal bold 25px 'times' ");
	      
	    Glow g = new Glow();
	    g.setLevel(0.2); 
	    categories.setEffect(g);
	    datePicker.setEffect(g);
	    plan.setEffect(g);
	    
	    primaryStage.setTitle("To Do List");  
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show(); 
	}
	
	
	public void addButtonClicked(){
		try{
			MyPlans mp = new MyPlans();
			mp.setPl(plan.getText());
			mp.setCt((String) categories.getValue());
			mp.setTime(datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MMM")));
    	
			Scanner s1 = new Scanner(mp.getPl());
			Scanner s2 = new Scanner(mp.getCt());
			Scanner s3 = new Scanner(mp.getTime());
			FileWriter fw = new FileWriter("p.txt",true);
			PrintWriter p = new PrintWriter(fw);
			p.println(s1.nextLine()+ "|"+ s2.nextLine()+ "|"+s3.nextLine());
			p.close();
		
			table.getItems().add(mp);
			plan.clear();
			
		}catch(Exception e){
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Incomplete FIeld");
			alert.setContentText("One or more fields are blank!");
			alert.showAndWait();
		}
	}
		
	public void deleteButtonClicked(){
		try{
			ObservableList<MyPlans> planSelected, allPlans;
			allPlans = table.getItems();
		    planSelected = table.getSelectionModel().getSelectedItems();
		    planSelected.forEach(allPlans::remove);
		        
		    FileWriter fw = new FileWriter("p.txt",true);
			PrintWriter p = new PrintWriter(fw);
			p.println("1 Entry Removed");
			p.close();
		}catch(Exception e){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("No records");
			alert.setContentText("No plans");
			alert.showAndWait();
		}
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}