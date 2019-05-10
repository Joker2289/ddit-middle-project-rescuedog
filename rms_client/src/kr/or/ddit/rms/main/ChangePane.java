package kr.or.ddit.rms.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rms.user.free_board.Free_boardTbUController;

public class ChangePane implements Initializable{
	public static String selFxml;
	@FXML AnchorPane mainPane;
	public static AnchorPane loadPane;
	public static FXMLLoader loader;
	public static Class fxmlClass;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choosePane(fxmlClass,selFxml);
		
	}
	private void choosePane(Class fxmlClass2, String selFxml) {
		mainPane.getChildren().clear();
		changePane(fxmlClass2.getResource(selFxml));
		mainPane.getChildren().add(loadPane);
		
	}
	public static void changePane(URL url) {
		try {
			if(loadPane!=null) {
				loadPane.getChildren().clear();
			}
			loader = new FXMLLoader(url);
			loadPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void clearing() {
		this.mainPane.getChildren().remove(loadPane);
	}
	public void add() {
		this.mainPane.getChildren().add(loadPane);
	}
}
