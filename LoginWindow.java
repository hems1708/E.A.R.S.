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

public class LoginWindow {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	
	public LoginWindow() {
	}
	
	public Scene getLoginWindow() {
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
		Scene scene = new Scene(root,screenBounds.getWidth()-2, screenBounds.getHeight()-80);
		return scene;
	}
	
	private Label getBottom() {
		Button btn = new Button("SIGN UP NOW");
		btn.setStyle("-fx-background-color: #3287A8;");
		btn.setTextFill(Color.WHITE);
		Label label = new Label("Don't have an account?", btn);
		label.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		label.setContentDisplay(ContentDisplay.BOTTOM);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadSignUpWindow();
			}
		});
		
		BorderPane.setMargin(label, new Insets(0, 25, 50, 25));
		BorderPane.setAlignment(label, Pos.TOP_CENTER);
		return label;
	}
	
	private VBox getCenter() {
		VBox fields = new VBox();
		fields.setMinWidth(borderPane.getMinWidth());
		fields.setMaxWidth(borderPane.getMinWidth());
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(30);
		
		TextField userId = new TextField();
		userId.setMinWidth(fields.getMinWidth()/2);
		Label idLabel = new Label("User ID: ", userId);
		idLabel.setContentDisplay(ContentDisplay.BOTTOM);
		idLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		PasswordField password = new PasswordField();
		password.setMinWidth(fields.getMinWidth()/2);
		Label passLabel = new Label("Password: ", password);
		passLabel.setContentDisplay(ContentDisplay.BOTTOM);
		passLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		Button forgotPassword = new Button("Password?");
		forgotPassword.setTextFill(Color.WHITE);
		forgotPassword.setStyle("-fx-background-color: #3287A8;");
		Label fLabel = new Label("forgot", forgotPassword);
		fLabel.setContentDisplay(ContentDisplay.RIGHT);
		forgotPassword.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadRecoveryWindow();
			}
		});
		
		Button login = new Button("Login");
		login.setMaxWidth(fields.getMinWidth()/2);
		login.setStyle("-fx-background-color: black; -fx-font-family: Arial; -fx-font-size: 20;");
		login.setTextFill(Color.WHITE);
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					login(userId, password);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		fields.getChildren().addAll(idLabel, passLabel, fLabel, login);
		return fields;
	}
	
	public void login(TextField userid, TextField pass) throws ClassNotFoundException, SQLException {
		EARSdatabase db = new EARSdatabase();
		ResultSet rs = db.queryDatabase("SELECT password "
				+ "FROM security "
				+ "WHERE userid = '"+userid.getText()+"';");
		rs.next();
		String password = rs.getString(1);
		if(password.equals(pass.getText())) {
			System.out.println("Login Successful!");
			Main.loadWelcomeWindow();
		}
		db.closeConnection();
	}
	
	private StackPane getTop() {
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Login");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);
		
		return pane;
	}
}
