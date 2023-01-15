import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
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
import javafx.scene.image.*;

public class Main extends Application{
	
	private static Stage ps = null;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage ps) throws ClassNotFoundException, SQLException {
		this.ps = ps;
		
		LoginWindow login = new LoginWindow();
		Scene scene = login.getLoginWindow();
		ps.setScene(scene);
		//loadEmployeesReport();
		ps.show();
	}
	
	public static void loadSignUpWindow() {
		SignUpWindow window = new SignUpWindow();
		ps.setScene(window.getSignUpWindow());
	}
	
	public static void loadLoginWindow() {
		LoginWindow window = new LoginWindow();
		ps.setScene(window.getLoginWindow());
	}
	
	public static void loadWelcomeWindow() {
		WelcomeWindow window = new WelcomeWindow();
		ps.setScene(window.getWelcomeScreen());
	}
	
	public static void loadRecoveryWindow() {
		PasswordRecovery window = new PasswordRecovery();
		ps.setScene(window.getPasswordRecovery());
	}
	
	public static void loadAddEmployees() throws ClassNotFoundException, SQLException {
		AddEmployees window = new AddEmployees();
		ps.setScene(window.getAddEmployee());
	}
	
	public static void loadUpdateEmployees() throws ClassNotFoundException, SQLException {
		UpdateEmployee window = new UpdateEmployee();
		ps.setScene(window.getUpdateEmployee());
	}
	
	public static void loadDeleteEmployees() throws ClassNotFoundException, SQLException {
		DeleteEmployee window = new DeleteEmployee();
		ps.setScene(window.getDeleteEmployee());
	}
	
	public static void loadEmployeesReport() throws ClassNotFoundException, SQLException {
		EmployeeReport window = new EmployeeReport();
		ps.setScene(window.getEmployeeReport());
	}
}
