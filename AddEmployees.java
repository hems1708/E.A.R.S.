import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.scene.shape.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Panel;
import java.io.File;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class AddEmployees {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private HBox hbox;
	private ListView<String> lv = null;
	
	public AddEmployees() {
		
	}
	
	public Scene getAddEmployee() throws ClassNotFoundException, SQLException {
		StackPane root = new StackPane();
		root.setPadding(new Insets(100, 100, 100, 100));
		root.setStyle("-fx-background-color: grey;");
		
		hbox = new HBox();
		hbox.setStyle("-fx-background-color: #3287A8;");
		hbox.setMinWidth(screenBounds.getWidth()*0.8);
		hbox.setMaxWidth(screenBounds.getWidth()*0.8);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		
		StackPane.setAlignment(hbox, Pos.CENTER);
		
		hbox.getChildren().addAll(getLeft(), getRight());
		
		root.getChildren().add(hbox);
		Scene scene = new Scene(root, screenBounds.getWidth()-2, screenBounds.getHeight()-80);
		return scene;
	}
	
	public VBox getLeft() {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth()/3);
		vb.setMaxWidth(screenBounds.getWidth()/3);
		vb.setAlignment(Pos.TOP_CENTER);
		
		//TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Add Employees");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);
		
		vb.getChildren().add(pane);
		
		//CENTER
		VBox fields = new VBox();
		fields.setMinWidth(screenBounds.getWidth()/3);
		fields.setMaxWidth(screenBounds.getWidth()/3);
		fields.setSpacing(20);
		fields.setAlignment(Pos.CENTER);
		fields.setStyle("-fx-background-color: #3e32a8;");
		fields.setPadding(new Insets(25, 25, 25, 25));
		
		HBox names = new HBox();
		names.setSpacing(20);
		names.setAlignment(Pos.CENTER);
		
		TextField firstName = new TextField();
		Label firstlabel = new Label("First name: ", firstName);
		firstlabel.setContentDisplay(ContentDisplay.BOTTOM);
		firstlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		firstlabel.setTextFill(Color.WHITE);
		
		TextField lastName = new TextField();
		Label lastlabel = new Label("Last name: ", lastName);
		lastlabel.setContentDisplay(ContentDisplay.BOTTOM);
		lastlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		lastlabel.setTextFill(Color.WHITE);
		
		names.getChildren().addAll(firstlabel, lastlabel);
		
		fields.getChildren().add(names);
		
		TextField empId = new TextField();
		Label idlabel = new Label("Employee ID: ", empId);
		idlabel.setContentDisplay(ContentDisplay.BOTTOM);
		idlabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		idlabel.setTextFill(Color.WHITE);
		
		fields.getChildren().add(idlabel);
		
		TextField phone = new TextField();
		Label phonelabel = new Label("Phone no.: ", phone);
		phonelabel.setContentDisplay(ContentDisplay.BOTTOM);
		phonelabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		phonelabel.setTextFill(Color.WHITE);
		
		fields.getChildren().add(phonelabel);
		
		TextField age = new TextField();
		Label agelabel = new Label("Age: ", age);
		agelabel.setContentDisplay(ContentDisplay.BOTTOM);
		agelabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		agelabel.setTextFill(Color.WHITE);
		
		fields.getChildren().add(agelabel);
		
		TextField salary = new TextField();
		Label salarylabel = new Label("Salary/hour: ", salary);
		salarylabel.setContentDisplay(ContentDisplay.BOTTOM);
		salarylabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		salarylabel.setTextFill(Color.WHITE);
		
		fields.getChildren().add(salarylabel);
		
		Button addEmp = new Button("ADD EMPLOYEE");
		addEmp.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		addEmp.setTextFill(Color.WHITE);
		addEmp.setMinWidth(fields.getMinWidth()/3);
		
		addEmp.setOnAction(e ->{
			EARSdatabase db = null;
			
			try {
				db = new EARSdatabase();
				db.updateDatabase("INSERT INTO employees VALUES("
					+ "'"+empId.getText()+"', "
					+ "'" +firstName.getText()+"', "
					+ "'" + lastName.getText()+"', "
					+ phone.getText()+", "
					+ age.getText()+", "
					+ salary.getText() +");");
				db.closeConnection();
				lv.setItems(getList());
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
		});
		
		fields.getChildren().add(addEmp);
		
		vb.getChildren().add(fields);
		
		
		//BOTTOM
		StackPane pane2 = new StackPane();
		pane2.setStyle("-fx-background-color: black;");
		pane2.setMinSize(screenBounds.getWidth()/3, 100);
		pane2.setAlignment(Pos.CENTER);
		
		Button back = new Button("BACK");
		back.setStyle("-fx-background-color: #9e2105; -fx-font-size: 20;");
		back.setTextFill(Color.WHITE);
		back.setMinWidth(fields.getMinWidth()/3);
		
		back.setOnAction(e -> {
			Main.loadWelcomeWindow();
		});
		
		pane2.getChildren().add(back);
		
		vb.getChildren().add(pane2);
		
		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		return vb;
	}
	
	public VBox getRight() throws ClassNotFoundException, SQLException {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth()/3);
		vb.setMaxWidth(screenBounds.getWidth()/3);
		
		//TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Current List");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);

		vb.getChildren().add(pane);
		
		//CENTER
		HBox attr = new HBox();
		attr.setAlignment(Pos.CENTER_LEFT);
		attr.setSpacing(40);
		attr.setPadding(new Insets(25, 25, 25, 25));
		attr.setStyle("-fx-background-color:  #3e32a8;");
		
		Text empid = new Text("Employee ID");
		empid.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		empid.minWidth(vb.getMaxWidth()/3);
		empid.setFill(Color.WHITE);
		
		Text fname = new Text("First Name");
		fname.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		fname.minWidth(vb.getMaxWidth()/3);
		fname.setFill(Color.WHITE);
		
		Text lname = new Text("Last Name");
		lname.setStyle("-fx-font-family: Arial; -fx-font-size: 25;");
		lname.minWidth(vb.getMaxWidth()/3);
		lname.setFill(Color.WHITE);
		
		attr.getChildren().addAll(empid, fname, lname);
		
		vb.getChildren().add(attr);
		
		lv = new ListView<String>();
		lv.setStyle("-fx-font-family: Arial; -fx-font-size: 25;"); 
		lv.setItems(getList());
	
		vb.getChildren().add(lv);
		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		return vb;
	}
	
	public ObservableList<String> getList() throws ClassNotFoundException, SQLException{
		EARSdatabase db = new EARSdatabase();
		
		ResultSet rs = db.queryDatabase("SELECT COUNT(*) FROM employees;");
		rs.next();
		int num = Integer.parseInt(rs.getString(1));
		
		String[] array = new String[num];
		
		
		rs = db.queryDatabase("SELECT employee_id, first_name, last_name "
				+ "FROM employees; ");
		
		for(int i = 0; i<array.length; i++) {
			rs.next();
			array[i] = rs.getString(1) + "                  " + rs.getString(2) + "                  " + rs.getString(3);
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(array);
		
		db.closeConnection();
		
		return list;
	}
}
