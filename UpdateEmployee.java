import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class UpdateEmployee {
	private Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	private HBox hbox;
	private ListView<String> lv = null;
	
	public UpdateEmployee() {
	}
	
	public Scene getUpdateEmployee() throws ClassNotFoundException, SQLException {
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
		
		Text text = new Text("Update Employees");
		text.setFill(Color.WHITE);
		text.setStyle("-fx-font-size: 50;");
		
		pane.getChildren().add(text);
		
		vb.getChildren().add(pane);
		
		//CENTER
		VBox center = new VBox();
		center.setMinWidth(screenBounds.getWidth()/3);
		center.setMaxWidth(screenBounds.getWidth()/3);
		center.setSpacing(20);
		center.setAlignment(Pos.CENTER);
		center.setStyle("-fx-background-color: #3e32a8;");
		center.setPadding(new Insets(25, 25, 25, 25));
		
		TextField empId = new TextField();
		empId.setMaxWidth(vb.getMinWidth()/2);
		empId.setMinWidth(vb.getMinWidth()/2);
		Label label1 = new Label("Employee ID: ", empId);
		label1.setContentDisplay(ContentDisplay.BOTTOM);
		label1.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		label1.setTextFill(Color.WHITE);	
		
		ComboBox<String> changes = new ComboBox<String>();
		changes.setMaxWidth(vb.getMinWidth()/2);
		changes.setMinWidth(vb.getMinWidth()/2);
		changes.getItems().addAll("First Name", "Last Name", "Phone No.", "Age", "Salary");
		Label label = new Label("CHANGE DATA: ", changes);
		label.setContentDisplay(ContentDisplay.BOTTOM);
		label.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		label.setTextFill(Color.WHITE);
		
		TextField value = new TextField();
		value.setMaxWidth(vb.getMinWidth()/2);
		value.setMinWidth(vb.getMinWidth()/2);
		Label label2 = new Label("VALUE: ", value);
		label2.setContentDisplay(ContentDisplay.BOTTOM);
		label2.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
		label2.setTextFill(Color.WHITE);
		
		Button update = new Button("UPDATE");
		update.setStyle("-fx-background-color: black; -fx-font-size: 20;");
		update.setTextFill(Color.WHITE);
		update.setMinWidth(vb.getMinWidth()/3);
		
		update.setOnAction(e ->{
			updateDatabase(changes, value);
		});
		
		center.getChildren().addAll(label1, label, label2, update);
		
		vb.getChildren().add(center);
		
		
		//BOTTOM
		StackPane pane2 = new StackPane();
		pane2.setStyle("-fx-background-color: black;");
		pane2.setMinSize(screenBounds.getWidth()/3, 100);
		pane2.setAlignment(Pos.CENTER);
		
		Button back = new Button("BACK");
		back.setStyle("-fx-background-color: #9e2105; -fx-font-size: 20;");
		back.setTextFill(Color.WHITE);
		back.setMinWidth(vb.getMinWidth()/3);
		
		back.setOnAction(e -> {
			Main.loadWelcomeWindow();
		});
		
		pane2.getChildren().add(back);
		
		vb.getChildren().add(pane2);
		
		HBox.setMargin(vb, new Insets(25, 25, 25, 25));
		return vb;
	}
	
	public void updateDatabase(ComboBox<String> data, TextField value) {
		//WRITE IT HERE
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
		empid.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		empid.minWidth(vb.getMaxWidth()/6);
		empid.setFill(Color.WHITE);
		
		Text fname = new Text("First Name");
		fname.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		fname.minWidth(vb.getMaxWidth()/6);
		fname.setFill(Color.WHITE);
		
		Text lname = new Text("Last Name");
		lname.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		lname.minWidth(vb.getMaxWidth()/6);
		lname.setFill(Color.WHITE);
		
		Text phone = new Text("Phone no.");
		phone.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		phone.minWidth(vb.getMaxWidth()/6);
		phone.setFill(Color.WHITE);
		
		Text age = new Text("Age");
		age.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		age.minWidth(vb.getMaxWidth()/6);
		age.setFill(Color.WHITE);
		
		Text salary = new Text("Salary");
		salary.setStyle("-fx-font-family: Arial; -fx-font-size: 15;");
		salary.minWidth(vb.getMaxWidth()/6);
		salary.setFill(Color.WHITE);
		
		attr.getChildren().addAll(empid, fname, lname, phone, age, salary);
		
		vb.getChildren().add(attr);
		
		lv = new ListView<String>();
		lv.setStyle("-fx-font-family: Arial;"); 
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
			
			array [i] = String.format("%17s %17s %17s %17s %17s %17s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

		}
		
		ObservableList<String> list = FXCollections.observableArrayList(array);
		
		db.closeConnection();
		
		return list;
	}

}
