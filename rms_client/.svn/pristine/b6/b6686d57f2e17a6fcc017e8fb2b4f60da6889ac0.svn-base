package kr.or.ddit.rms.mainpage.sign_up.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDialog implements Serializable {
	
	public void ShowAlert(String _strTile, String _strHeaderText, String _message, AlertType _AlertType) {
		Alert alert = new Alert(_AlertType);
		alert.setTitle(_strTile);
		alert.setHeaderText(_strHeaderText);
		alert.setContentText(_message);
		alert.showAndWait();
	}
}
