package kr.or.ddit.rms.admin.missing_animal_board;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShowMapAController implements Initializable{
	public static String miss_loc_img;
	@FXML ImageView Map_View;
	@FXML JFXButton Close_Btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Close_Btn.setOnAction(e->{
			Missing_animal_DetailAController.WritePage.close();
		});
//		\\Sem-pc\공유폴더\Rescuedog\Missing_animal\mapImg
		Image img = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Missing_animal\\mapImg\\"+miss_loc_img);
		Map_View.setImage(img);
	}
	
}
