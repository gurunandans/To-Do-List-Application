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
		
		//PrintWriter pw = new PrintWriter(new FileWriter("p.txt",true));
		//pw.println("");
		//pw.println("Plan|Category|Day");
		//pw.close();
		
		TextArea ta = new TextArea();
		ta.setPrefHeight(150);
		
		Text label = new Text("Your Plan : ");
		plan = new TextField();
		plan.setPrefWidth(170.0);
		Text date = new Text("Day : ");
		datePicker = new DatePicker();
		Text cat = new Text("Category : ");
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
					ta.clear();
					File vplan = new File("p.txt");
					Scanner s = new Scanner(vplan);
					while(s.hasNext())
					{
						ta.appendText(text +"\n" + s.nextLine());
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
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
			}
				);
				
		Button m = new Button("Modify");
		m.setPrefSize(125.0, 50.0);
		m.setOnAction(
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				
				try {
					
					File vplan = new File("p.txt");
					PrintWriter p = new PrintWriter(vplan);
					p.println(ta.getText());
					p.close();
					
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			}
				);
				//File vplan = new File("p.txt");
		/*op.setOnAction(
				new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				
				File vplan = new File("p.txt");
				if(vplan.exists())
				{
					if(Desktop.isDesktopSupported())
					{
						try{
						Desktop.getDesktop().open(vplan);
						}
						catch(IOException ex)
						{
							
						}
						
						
					}
					else
					{
						System.out.println("Not Supported");
					}
				}
				else
				{
					System.out.println("File not exists");
				}
				
			}
			
			}
		);*/
		
		datePicker.setPrefWidth(170.0);
		Text display = new Text("Display : ");
		
		/*ToggleGroup show = new ToggleGroup(); 
		RadioButton all = new RadioButton("All"); 
		RadioButton shopping = new RadioButton("Shopping"); 
		RadioButton submissions = new RadioButton("Submissions");
		RadioButton tasks = new RadioButton("Tasks");
		RadioButton entertainment = new RadioButton("Entertainment");
		RadioButton sports = new RadioButton("Sports");
	    all.setToggleGroup(show);   
	    shopping.setToggleGroup(show); 
	    submissions.setToggleGroup(show);   
	    tasks.setToggleGroup(show); 
	    entertainment.setToggleGroup(show);
	    sports.setToggleGroup(show);*/
		
		
		
		GridPane gridPane = new GridPane();
	    gridPane.setMinSize(100, 70);   
	    gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	    gridPane.setVgap(10); 
	    gridPane.setHgap(20);
	  /*  gridPane.setAlignment(Pos.TOP_LEFT);  
	    gridPane.add(label, 0, 0); 
	    gridPane.add(plan, 1, 0); 
	    gridPane.add(date, 2, 0);  
	    gridPane.add(datePicker, 3, 0);
	    gridPane.add(cat, 4, 0); 
	    gridPane.add(categories, 5, 0);
	    gridPane.add(add, 1, 2);
	    gridPane.add(delete, 2, 2);
	    /*gridPane.add(display, 0, 5);
	    gridPane.add(all, 1, 5);
	    gridPane.add(shopping, 1, 6);
	    gridPane.add(submissions, 1, 7);
	    gridPane.add(tasks, 1, 8);
	    gridPane.add(entertainment, 1, 9);
	    gridPane.add(sports, 1, 10);*/
         
        //gridPane.add(table, 1, 10);*/

	    
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
        //table.setItems(getProduct());
        table.getColumns().addAll(plans, cate, tim, cb);
        cb.setCellFactory(column -> new CheckBoxTableCell());
        table.setEditable(true);
        //ImageView img_view = new ImageView();
        //Image img = new Image("/home/gurunandan/pc.jpeg");
        //img_view.setImage(img);
        Text h = new Text("Start planning...");
        h.setFill(Color.DARKVIOLET);
        
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(10000)); 
        translateTransition.setNode(h); 
        translateTransition.setByX(1200);  
        translateTransition.setCycleCount(50); 
        translateTransition.setAutoReverse(false);
        translateTransition.play(); 
        gridPane.add(h, 0, 2);
        
        
        
        plans.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        cate.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        tim.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        cb.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        GridPane grid = new GridPane();
        grid.add( add, 2,1);
        grid.add( delete, 3,1);
        grid.add(op, 4, 1);
        grid.add(clr, 5, 1);
        grid.add(m, 6, 1);
        grid.setMinSize(200, 50);   
        grid.setPadding(new Insets(10, 10, 10, 10)); 
        grid.setVgap(10); 
        grid.setHgap(20);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(label, plan, date, datePicker, cat, categories);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(gridPane,table, hBox,grid,ta);
 
	    Scene scene = new Scene(vBox);   
	    
	    
	    
	    ////////////////////////////////////////////
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
	      cat.setStyle("-fx-font: normal bold 14px 'times' "); 
	      h.setStyle("-fx-font: normal bold 25px 'times' ");
	      
	      
	      
	      Glow g = new Glow();
	      g.setLevel(0.2); 
	     // plan.setEffect(g);
	       
	      
	      //Applying bloom effect to text 
	      categories.setEffect(g);
	      datePicker.setEffect(g);
	      plan.setEffect(g);
	      //
	    ///////////////////////////////////////////////  
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
        //datePicker.setValue(null);
        //categories.setValue(null);
		}catch(Exception e)
		{
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
			}catch(Exception e)
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("No records");
				alert.setContentText("No plans");

				alert.showAndWait();
			}
    	
    }
	
	//public ObservableList<MyPlans> getProduct(){
      //  ObservableList<MyPlans> products = FXCollections.observableArrayList();
        //products.add(new MyPlans("Laptop","hdvm","25-02-1996"));
        //return products;
//}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
}
