import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Main extends Application{
	
	Button nextButton,backButton, nextButton2;
	int currentScene = 0;
	int currentPress = 0;
	Scene[] scenes = new Scene[4];
	Stage window;
	Scene emailScene,passwordScene;
	String userEmail, userPassword, senderName, recipientEmail, companyName, sponsorReason, emailIntro, emailSubject;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// start
		window = primaryStage;
		window.setTitle("Spartan Email Sender");
		
		// SCENE 1
			// Nodes
			Label prompt1 = new Label("Please input your @spartanrobotics email:");
			prompt1.setTextFill(Color.WHITESMOKE);
			Label prompt2 = new Label("Please input your @spartanrobotics password:");
			prompt2.setTextFill(Color.WHITESMOKE);
			Label prompt3 = new Label("Please input your name (FirstName LastName):");
			prompt3.setTextFill(Color.WHITESMOKE);
			
			nextButton = new Button();
			nextButton.setText("Next");
			backButton = new Button();
			backButton.setText("Back");
			// Input
			TextField field1 = new TextField();
			TextField field2 = new TextField();
			TextField field3 = new TextField();
			

			// back button, reset to last prompts
			backButton.setOnAction(e -> {
				if(currentPress == 1) {
					
					field1.clear();
					field2.clear();
					field3.clear();
					
					prompt1.setText("Please input your @spartanrobotics email:");
					prompt2.setText("Please input your @spartanrobotics password:");
					prompt3.setText("Please input your name (FirstName LastName)");
				}
				else if (currentPress == 2) {
					field1.clear();
					field2.clear();
					field3.clear();
					
					prompt1.setText("Please input the recipient's email:");
					prompt2.setText("Please input the company's name:");
					prompt3.setText("This company should sponsor us because... (do not start with a capital)");
					
					nextButton.setText("Next");
				}
				else if(currentPress == 0) currentPress++;
				
				currentPress--;
			});
	
			// JavaFX layout
				// VBox
				VBox startLayoutV = new VBox(20);
				startLayoutV.getChildren().addAll(prompt1, field1,prompt2,field2,prompt3,field3);
				// HBox
				HBox startLayoutH = new HBox(250);
				startLayoutH.getChildren().addAll(backButton,nextButton);
				// Container
				BorderPane container = new BorderPane();
				container.setTop(startLayoutV);
				container.setBottom(startLayoutH);	
				BackgroundFill spartanGreen = new BackgroundFill(Color.DARKSLATEGREY, null, null); // colour fill
				
				container.setBackground(new Background(spartanGreen));
			
			// On nextButton press, advance to next field
			nextButton.setOnAction(e -> {
				if(currentPress == 0) {
					userEmail = field1.getText();
					userPassword = field2.getText();
					senderName = field3.getText();
					
					field1.clear();
					field2.clear();
					field3.clear();
					
					prompt1.setText("Please input the recipient's email:");
					prompt2.setText("Please input the company's name:");
					prompt3.setText("This company should sponsor us because... (do not start with a capital)");
				}
				else if(currentPress == 1) {
					
					recipientEmail = field1.getText();
					companyName = field2.getText();
					sponsorReason = field3.getText();
					
					field1.clear();
					field2.clear();
					field3.clear();
					
					prompt1.setText("Enter the introduction to your email (e.g: To whom it may concern at ASDASDASD Ltd,)");
					prompt2.setText("Enter the header subject for your email:");
					startLayoutV.getChildren().removeAll(prompt3,field3);
					nextButton.setText("Send Email");
					
				}
				else if(currentPress ==2) {
					emailIntro = field1.getText();
					emailSubject = field2.getText();
					MessageSender emailSender = new MessageSender(userEmail,userPassword,senderName, recipientEmail, companyName, sponsorReason, emailIntro, emailSubject);
					emailSender.sendEmail();
					window.close();
		
						
					
					
				}
				currentPress++;
			});
				
				
			
		// start scene
		Scene startScene = new Scene(container, 600, 300);
		window.setScene(startScene);
		window.show();
	}
	private void firstPrompt() {
		
	}
	private void secondPrompt() {
		
	}
	private void thirdPrompt() {
		
	}
	// currently unused; will be used in case of expansion
	private void nextScene() {
		currentScene++;
		window.setScene(scenes[currentScene]);
	}
	private void lastScene() {
		if(currentScene != 0) {
		currentScene--;
		window.setScene(scenes[currentScene]);
	
		}
	}

}
