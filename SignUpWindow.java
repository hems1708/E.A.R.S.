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

public class SignUpWindow {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	
	public SignUpWindow() {
		
	}
	
	public Scene getSignUpWindow() {
		StackPane root = new StackPane();
		root.setPadding(new Insets(100, 100, 100, 100));
		root.setStyle("-fx-background-color: grey;");
		
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #3287A8;");
		borderPane.setMinWidth(screenBounds.getWidth()/3);
		borderPane.setMaxWidth(screenBounds.getWidth()/3);
		
		StackPane.setAlignment(borderPane, Pos.CENTER);
		
		borderPane.setTop(getTop());
		borderPane.setCenter(getCenter());
		borderPane.setBottom(getBottom());
		
		root.getChildren().add(borderPane);
		Scene scene = new Scene(root, screenBounds.getWidth()-2, screenBounds.getHeight()-80);
		return scene;
	}
	
	private Label getBottom() {
		Button btn = new Button("LOG IN NOW");
		btn.setStyle("-fx-background-color: #3287A8;");
		btn.setTextFill(Color.WHITE);
		Label label = new Label("Already have an account?", btn);
		label.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		label.setContentDisplay(ContentDisplay.BOTTOM);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadLoginWindow();
			}
		});
		
		BorderPane.setAlignment(label, Pos.TOP_CENTER);
		return label;
	}
	
	private VBox getCenter() {
		VBox fields = new VBox();
		fields.setMinWidth(borderPane.getMinWidth());
		fields.setMaxWidth(borderPane.getMinWidth());
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(20);
		
		HBox names = new HBox();
		names.setMinWidth(fields.getWidth());
		names.setAlignment(Pos.CENTER);
		
		TextField firstName = new TextField();
		Label firstLabel = new Label("First Name: ", firstName);
		firstLabel.setContentDisplay(ContentDisplay.BOTTOM);
		firstLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;"); 
		
		TextField lastName = new TextField();
		Label lastLabel = new Label("Last Name: ", lastName);
		lastLabel.setContentDisplay(ContentDisplay.BOTTOM);
		lastLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		names.getChildren().addAll(firstLabel ,lastLabel);
		names.setSpacing(10);

		TextField userId = new TextField();
		userId.setMinWidth(fields.getMinWidth()/2);
		Label idLabel = new Label("User ID: ", userId);
		idLabel.setContentDisplay(ContentDisplay.BOTTOM);
		idLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		ComboBox<String> designations = new ComboBox<String>();
		designations.getItems().addAll("Employee", "Manager", "Admin");
		designations.setMaxWidth(fields.getMinWidth()/2);
		designations.setMinWidth(fields.getMinWidth()/2);
		Label dsgLabel = new Label("Designation: ", designations);
		dsgLabel.setContentDisplay(ContentDisplay.BOTTOM);
		dsgLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		ComboBox<String> questions = new ComboBox<String>();
		questions.getItems().addAll("What is your city of birth?", ""
				+ "What is your mother's maiden name?", "What is the name of your first pet?");
		questions.setMaxWidth(fields.getMinWidth()/2);
		Label queLabel = new Label("Security Question: ", questions);
		queLabel.setContentDisplay(ContentDisplay.BOTTOM);
		queLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		TextField answer = new TextField();
		answer.setMinWidth(fields.getMinWidth()/2);
		Label ansLabel = new Label("Security Answer: ", answer);
		ansLabel.setContentDisplay(ContentDisplay.BOTTOM);
		ansLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		PasswordField password = new PasswordField();
		password.setMinWidth(fields.getMinWidth()/2);
		Label passLabel = new Label("Password: ", password);
		passLabel.setContentDisplay(ContentDisplay.BOTTOM);
		passLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		
		//SIGN UP button
		Button signUp = new Button("Sign Up");
		signUp.setMaxWidth(fields.getMinWidth()/2);
		signUp.setStyle("-fx-background-color: black; -fx-font-family: Arial; -fx-font-size: 20;");
		signUp.setTextFill(Color.WHITE);
		
		signUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					signUp(firstName, lastName, userId, designations, questions, answer, password);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		fields.getChildren().addAll(names, idLabel, dsgLabel, queLabel, ansLabel, passLabel, signUp);
		BorderPane.setMargin(fields, new Insets(25, 25, 0, 25));
		return fields;
	}
	
	private void signUp(TextField fname, TextField lname, TextField userid,
			ComboBox<String> desg, ComboBox<String> que, 
			TextField ans, PasswordField pass) throws ClassNotFoundException, SQLException {
		
		EARSdatabase db = new EARSdatabase();
		db.updateDatabase("INSERT INTO user_info VALUES("
				+ "\""+userid.getText()+"\", "
				+"\""+fname.getText()+"\", "
				+"\""+lname.getText()+"\", "
				+"\""+desg.getValue()+"\");");
		
		db.updateDatabase("INSERT INTO security VALUES("
				+ "\""+userid.getText()+"\", "
				+"\""+pass.getText()+"\", "
				+"\""+que.getValue()+"\", "
				+"\""+ans.getText()+"\");");
		
		db.closeConnection();
		
	}
	
	private StackPane getTop() {
		
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Sign Up");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-background-color: black; -fx-font-size: 50;");
		
		
		pane.getChildren().add(text);
		
		return pane;
	}
}
