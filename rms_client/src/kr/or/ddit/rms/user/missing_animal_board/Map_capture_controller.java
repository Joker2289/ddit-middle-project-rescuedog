package kr.or.ddit.rms.user.missing_animal_board;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;

public class Map_capture_controller implements Initializable{
	public static String board_num;
	@FXML WebView map_view;
	@FXML JFXButton Close_Btn;
	@FXML JFXButton Cqpture_Btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WebEngine webEngine = map_view.getEngine();
		webEngine.load("http://192.168.206.18:8080/MAP_TEST/search_place_map.html");
		
		Close_Btn.setOnAction(e->{
			Missing_add_controller.WritePage.close();
		});
		
		Cqpture_Btn.setOnAction(e->{
			capture();
		});
		
		
		map_view.addEventFilter(MouseEvent.MOUSE_MOVED, e->{
//			FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), PAW);
//	        fadeTransition.setFromValue(0.5);
//	        fadeTransition.setToValue(0.0);
//	        fadeTransition.setCycleCount(Animation.INDEFINITE);
//	        fadeTransition.play();
	        e.getX();
	        e.getY();
			
	        System.out.println(e.getX());
	        System.out.println(e.getY());
//	        if(PAW.is);{
//	        	fadeTransition.stop();
//	        }
	        
//	        double yx = myNode.getLocalToSceneTransform().getMyx();
//	        double yy = myNode.getLocalToSceneTransform().getMyy();
//	        double angle = Math.atan2(yx, yy);
//	        angle = Math.toDegrees(angle);
//	        angle = angle < 0 ? angle + 360 : angle;
	        
	      
		});
		
	}
	
	private void capture() {
		try {
			Robot robot = new Robot();
			Rectangle r = new Rectangle(638, 267, 406, 371);
			BufferedImage bi = robot.createScreenCapture(r);
			javafx.scene.image.Image i = SwingFXUtils.toFXImage(bi, null);
			
			ImageIO.write(bi,"jpg",new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Missing_animal\\mapImg\\"+board_num+",AD"+".jpg"));
			Missing_add_controller.loc_Img_Check=true;
			Missing_add_controller.WritePage.close();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
