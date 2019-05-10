package kr.or.ddit.rms.user.missing_animal_board;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;

public class ShowMapController implements Initializable{
	public static String miss_loc_img;
	@FXML ImageView Map_View;
	@FXML JFXButton Close_Btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Close_Btn.setOnAction(e->{
			Missing_animal_DetailController.WritePage.close();
		});
//		\\Sem-pc\공유폴더\Rescuedog\Missing_animal\mapImg
		Image img = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\Missing_animal\\mapImg\\"+miss_loc_img);
		Map_View.setImage(img);
	}
	
}
