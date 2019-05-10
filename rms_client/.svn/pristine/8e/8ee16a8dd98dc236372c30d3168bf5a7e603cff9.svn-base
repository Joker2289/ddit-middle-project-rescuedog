package kr.or.ddit.rms.user.locating_animal_hospitals;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapController implements Initializable{

	@FXML WebView hospital_wv;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		WebEngine webEngine = hospital_wv.getEngine();
		webEngine.load("http://192.168.206.18:8080/MAP_TEST/search_place_map.html");
		
		
		
	}

}
