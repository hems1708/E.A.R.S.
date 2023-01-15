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
import java.io.File;

public class WelcomeWindow {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	
	public WelcomeWindow() {
	};
	
	public Scene getWelcomeScreen() {
		
		BorderPane borderPane = new BorderPane();
		
		borderPane.setLeft(getLeft());
		borderPane.setTop(getTop());
		borderPane.setCenter(getCenter());
		
		Scene scene = new Scene(borderPane,screenBounds.getWidth()-2, screenBounds.getHeight()-80);
		return scene;
	}
	
	private GridPane getCenter() {
		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(30);
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setAlignment(Pos.CENTER);
		
		//Employee Section
		
		HBox emp = new HBox();
	
		Image iImage = null;
		try {
			iImage = new Image(new FileInputStream("icons//Identicator_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		Button infoBtn1 = new Button();
		infoBtn1.setGraphic(new ImageView(iImage));
		infoBtn1.setStyle("-fx-background-color: grey;");
		
		Text empSection = new Text("Employee Section");
		empSection.setStyle("-fx-font-family: Arial; -fx-font-size: 20");
		
		emp.setAlignment(Pos.CENTER_LEFT);
		emp.getChildren().addAll(infoBtn1, empSection);
		grid.add(emp, 0, 0);
		
		Button addBtn = new Button("Add Employee");
		Image addImage = null;
		try {
			addImage = new Image(new FileInputStream("icons//Add_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		addBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		addBtn.setTextFill(Color.WHITE);
		addBtn.setGraphic(new ImageView(addImage));
		addBtn.setMinSize(200, 200);
		
		addBtn.setOnAction(e ->{
			try {
				Main.loadAddEmployees();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		grid.add(addBtn, 0, 1);
		
		Button updateBtn = new Button("Update Employee");
		Image updateImage = null;
		try {
			updateImage = new Image(new FileInputStream("icons//Update_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		updateBtn.setOnAction(e ->{
			try {
				Main.loadUpdateEmployees();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		updateBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		updateBtn.setTextFill(Color.WHITE);
		updateBtn.setGraphic(new ImageView(updateImage));
		updateBtn.setMinSize(200, 200);
		grid.add(updateBtn, 1, 1);
		
		Button deleteBtn = new Button("Delete Employee");
		Image deleteImage = null;
		try {
			deleteImage = new Image(new FileInputStream("icons//Delete_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		deleteBtn.setOnAction(e ->{
			try {
				Main.loadDeleteEmployees();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		deleteBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		deleteBtn.setTextFill(Color.WHITE);
		deleteBtn.setGraphic(new ImageView(deleteImage));
		deleteBtn.setMinSize(200, 200);
		grid.add(deleteBtn, 2, 1);
		
		Button reportBtn = new Button("Employee Report");
		Image reportImage = null;
		try {
			reportImage = new Image(new FileInputStream("icons//Report_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		reportBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		reportBtn.setTextFill(Color.WHITE);
		reportBtn.setGraphic(new ImageView(reportImage));
		reportBtn.setMinSize(200, 200);
		grid.add(reportBtn, 3, 1);
		
		
		
		//Application Section
		
		HBox app = new HBox();
		
		Button infoBtn2 = new Button();
		infoBtn2.setGraphic(new ImageView(iImage));
		infoBtn2.setStyle("-fx-background-color: grey;");
		
		Text appSection = new Text("Application Section");
		appSection.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		app.setAlignment(Pos.CENTER_LEFT);
		app.getChildren().addAll(infoBtn2, appSection);
		grid.add(app, 0, 2);
		
		Button listBtn = new Button("Applicant List");
		Image listImage = null;
		try {
			listImage = new Image(new FileInputStream("icons//Salary_Receipt_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		listBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		listBtn.setTextFill(Color.WHITE);
		listBtn.setGraphic(new ImageView(listImage));
		listBtn.setMinSize(200, 200);
		grid.add(listBtn, 0, 3);
		
		Button addAppBtn = new Button("Add Applicant");
		addAppBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		addAppBtn.setTextFill(Color.WHITE);
		addAppBtn.setGraphic(new ImageView(addImage));
		addAppBtn.setMinSize(200, 200);
		grid.add(addAppBtn, 1, 3);
		
		Button deleteAppBtn = new Button("Delete Applicant");
		deleteAppBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		deleteAppBtn.setTextFill(Color.WHITE);
		deleteAppBtn.setGraphic(new ImageView(deleteImage));
		deleteAppBtn.setMinSize(200, 200);
		grid.add(deleteAppBtn, 2, 3);
		
		Button appInfoBtn = new Button("Applicant Information");
		Image infoImage = null;
		try {
			infoImage = new Image(new FileInputStream("icons//Update_Salary_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		appInfoBtn.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		appInfoBtn.setTextFill(Color.WHITE);
		appInfoBtn.setGraphic(new ImageView(infoImage));
		appInfoBtn.setMinSize(200, 200);
		grid.add(appInfoBtn, 3, 3);
		
		grid.setStyle("-fx-background-color: grey");
		
		return grid;
	}
	
	private Text getTop() {		
		//Setting the header
		Text header = new Text("Welcome to Employee Application Review System");
		header.setStyle("-fx-font-family: Arial; -fx-font-size: 100; -fx-font-weight: bold;"); 
		header.setWrappingWidth(screenBounds.getWidth()-2);
		BorderPane.setMargin(header, new Insets(25, 25, 25, 25));
		return header;
	}
	
	private VBox getLeft() {
		//Creating the option panel
		VBox optionPane = new VBox();
		optionPane.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-background-color: #3287A8;");	
		optionPane.setMinWidth(screenBounds.getWidth()/4);
		System.out.println();
		
		Image profileImage = null;
		try {
			profileImage = new Image(new FileInputStream(new File("icons//User_icon.png")));
		}catch(FileNotFoundException e){
			System.out.println(e);
		}
		ImageView imageView = new ImageView(profileImage);
		imageView.setScaleY(2);
		imageView.setScaleX(2);
		
		optionPane.getChildren().add(imageView);
		optionPane.setAlignment(Pos.TOP_CENTER);
		optionPane.setSpacing(25);
		VBox.setMargin(imageView, new Insets(40, 75, 10, 75));
		
		Text name = new Text("Name of faculty".toUpperCase());
		optionPane.getChildren().add(name);
		name.setStyle("-fx-font-size: 20; -fx-fill: white; -fx-font-weight: bold;");
			
		//View profile option
		Button profileButton = new Button("View Profile");
		profileButton.setTextFill(Color.WHITE);
		profileButton.setMinSize(optionPane.getMinWidth()/3, 50);
		profileButton.setStyle("-fx-background-color: black; -fx-border-width: 5; -fx-border-color: white;"); 
		optionPane.getChildren().add(profileButton);
		
		//options
		VBox options = new VBox();
		options.setStyle("-fx-background-color: black;");
		options.setAlignment(Pos.CENTER);
		
		Button dashboardButton = new Button("Dashboard");
		Image dashboardImage = null;
		try {
			dashboardImage = new Image(new FileInputStream("icons//Laptop_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
			
		dashboardButton.setStyle("-fx-background-color: black;");
		dashboardButton.setTextFill(Color.WHITE);
		dashboardButton.setGraphic(new ImageView(dashboardImage));
		VBox.setMargin(dashboardButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(dashboardButton);
			
		Button employeeButton = new Button("Employee");
		Image employeeImage = null;
		try {
			employeeImage = new Image(new FileInputStream("icons//Employee_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		employeeButton.setStyle("-fx-background-color: black;");
		employeeButton.setTextFill(Color.WHITE);
		employeeButton.setGraphic(new ImageView(employeeImage));
		VBox.setMargin(employeeButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(employeeButton);
		
		Button salaryButton = new Button("Salary");
		Image salaryImage = null;
		try {
			salaryImage = new Image(new FileInputStream("icons//Salary_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		salaryButton.setStyle("-fx-background-color: black;");
		salaryButton.setTextFill(Color.WHITE);
		salaryButton.setGraphic(new ImageView(salaryImage));
		VBox.setMargin(salaryButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(salaryButton);
		
		Button leaveButton = new Button("Salary");
		Image leaveImage = null;
		try {
			leaveImage = new Image(new FileInputStream("icons//Leave_Icon.png"));
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		leaveButton.setStyle("-fx-background-color: black;");
		leaveButton.setTextFill(Color.WHITE);
		leaveButton.setGraphic(new ImageView(leaveImage));
		VBox.setMargin(leaveButton, new Insets(10, 0, 10, 0));
		options.getChildren().add(leaveButton);
		
		optionPane.getChildren().add(options);
		
		//logout option
		Button logoutButton = new Button("Log Out");
		logoutButton.setTextFill(Color.WHITE);
		logoutButton.setMinSize(optionPane.getMinWidth()/3, 50);
		logoutButton.setStyle("-fx-background-color: black; -fx-border-width: 5; -fx-border-color: white;"); 
		optionPane.getChildren().add(logoutButton);
		
		VBox.setMargin(logoutButton, new Insets(25, 0, 75, 0));	
		return optionPane;
	};
	
}
