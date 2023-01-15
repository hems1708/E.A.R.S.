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

public class PasswordRecovery {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private BorderPane borderPane = null;
	
	public PasswordRecovery() {
		
	}
	
	public Scene getPasswordRecovery() {
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
	
	private StackPane getTop() {
		
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMinSize(screenBounds.getWidth()/3, 100);
		pane.setAlignment(Pos.CENTER);
		
		Text text = new Text("Recover Password");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-background-color: black; -fx-font-size: 50;");
		
		
		pane.getChildren().add(text);
		
		return pane;
	}
	
	private VBox getCenter() {
		VBox fields = new VBox();
		fields.setMinWidth(borderPane.getMinWidth());
		fields.setMaxWidth(borderPane.getMinWidth());
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(20); 
		
		TextField lastName = new TextField();
		lastName.setMinWidth(fields.getMinWidth()/2);
		Label lastLabel = new Label("Last Name: ", lastName);
		lastLabel.setContentDisplay(ContentDisplay.BOTTOM);
		lastLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");

		TextField userId = new TextField();
		userId.setMinWidth(fields.getMinWidth()/2);
		Label idLabel = new Label("User ID: ", userId);
		idLabel.setContentDisplay(ContentDisplay.BOTTOM);
		idLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
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
		Label passLabel = new Label("New Password: ", password);
		passLabel.setContentDisplay(ContentDisplay.BOTTOM);
		passLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		
		
		//Recovery button
		Button recover = new Button("Recover Password");
		recover.setMaxWidth(fields.getMinWidth()/2);
		recover.setStyle("-fx-background-color: black; -fx-font-family: Arial; -fx-font-size: 20;");
		recover.setTextFill(Color.WHITE);
		
		recover.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					signUp(lastName, userId, questions, answer, password);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		fields.getChildren().addAll(lastLabel, idLabel, queLabel, ansLabel, passLabel, recover);
		BorderPane.setMargin(fields, new Insets(25, 25, 0, 25));
		return fields;
	}
	
	private void signUp(TextField lname, TextField userid,
			ComboBox<String> que, TextField ans, PasswordField pass) throws ClassNotFoundException, SQLException {
		EARSdatabase db = new EARSdatabase();
		ResultSet rs = db.queryDatabase("SELECT lastname "
				+ "FROM user_info "
				+ "WHERE userid = '"+userid.getText()+"';");
		rs.next();
		String lastname = rs.getString(1);
		if(lastname.equals(lname.getText())) {
			rs = db.queryDatabase("SELECT security_question, security_answer "
					+ "FROM security "
					+ "WHERE userid = '"+userid.getText()+"';");
			rs.next();
			String question = rs.getString(1);
			String answer = rs.getString(2);
			if(question.equals(que.getValue()) && answer.equals(ans.getText())) {
				System.out.println("Password recovery successful!");
				db.updateDatabase("UPDATE security "
						+ "SET password = '" + pass.getText()+"' "
						+ "WHERE userid = '"+userid.getText()+"';");
			}
		}
	}
	
	private Button getBottom() {
		Button btn = new Button("BACK");
		btn.setStyle("-fx-background-color: red;");
		btn.setTextFill(Color.WHITE);
		btn.setMinWidth(borderPane.getMinWidth()/2);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.loadLoginWindow();
			}
		});
		
		BorderPane.setMargin(btn, new Insets(0, 25, 75, 25));
		BorderPane.setAlignment(btn, Pos.TOP_CENTER);
		return btn;
	}
}
