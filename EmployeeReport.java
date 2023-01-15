import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.awt.Panel;
import java.io.File;
import java.sql.*;

public class EmployeeReport {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	private ListView<String> lv = null;
	
	public EmployeeReport() {
		
	}
	
	public Scene getEmployeeReport() throws ClassNotFoundException, SQLException {
		StackPane root = new StackPane();
		root.setPadding(new Insets(100, 100, 100, 100));
		root.setStyle("-fx-background-color: grey;");
		
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #3287A8;");
		borderPane.setMinWidth(screenBounds.getWidth()*2/3);
		borderPane.setMaxWidth(screenBounds.getWidth()*2/3);
		
		StackPane.setAlignment(borderPane, Pos.CENTER);
		
		borderPane.setTop(getTop());
		borderPane.setCenter(getCenter());
		
		root.getChildren().add(borderPane);
		Scene scene = new Scene(root,screenBounds.getWidth()-2, screenBounds.getHeight()-80);
		return scene;
	}
	
	private StackPane getTop() {
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Employees Report");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);
		
		return pane;
	}
	
	public VBox getCenter() throws ClassNotFoundException, SQLException {
		VBox vb = new VBox();
		vb.setMinWidth(screenBounds.getWidth()*2/3);
		vb.setMaxWidth(screenBounds.getWidth()*2/3);
		
		//TOP
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()*2/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Current List");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);

		vb.getChildren().add(pane);
		
		//CENTER
		HBox attr = new HBox();
		attr.setAlignment(Pos.CENTER);
		attr.setSpacing(50);
		attr.setPadding(new Insets(25, 25, 25, 25));
		attr.setStyle("-fx-background-color:  #3e32a8;");
		
		Text empid = new Text("Employee ID");
		empid.setStyle("-fx-font-family: Arial; -fx-font-size: 30;");
		empid.minWidth(vb.getMaxWidth()/6);
		empid.setFill(Color.WHITE);
		
		Text fname = new Text("First Name");
		fname.setStyle("-fx-font-family: Arial; -fx-font-size: 30;");
		fname.minWidth(vb.getMaxWidth()/6);
		fname.setFill(Color.WHITE);
		
		Text lname = new Text("Last Name");
		lname.setStyle("-fx-font-family: Arial; -fx-font-size: 30;");
		lname.minWidth(vb.getMaxWidth()/6);
		lname.setFill(Color.WHITE);
		
		Text phone = new Text("Phone no.");
		phone.setStyle("-fx-font-family: Arial; -fx-font-size: 30;");
		phone.minWidth(vb.getMaxWidth()/6);
		phone.setFill(Color.WHITE);
		
		Text age = new Text("Age");
		age.setStyle("-fx-font-family: Arial; -fx-font-size: 30;");
		age.minWidth(vb.getMaxWidth()/6);
		age.setFill(Color.WHITE);
		
		Text salary = new Text("Salary");
		salary.setStyle("-fx-font-family: Arial; -fx-font-size: 30;");
		salary.minWidth(vb.getMaxWidth()/6);
		salary.setFill(Color.WHITE);
		
		attr.getChildren().addAll(empid, fname, lname, phone, age, salary);
		
		vb.getChildren().add(attr);
		
		lv = new ListView<String>();
		lv.setStyle("-fx-font-family: Arial; -fx-font-size: 20;"); 
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
		
		
		rs = db.queryDatabase("SELECT * "
				+ "FROM employees; ");
		
		for(int i = 0; i<array.length; i++) {
			rs.next();
			
			array [i] = String.format("%28s %28s %28s %28s %28s %28s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

		}
		
		ObservableList<String> list = FXCollections.observableArrayList(array);
		
		db.closeConnection();
		
		return list;
	}

}
