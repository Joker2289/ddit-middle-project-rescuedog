package kr.or.ddit.rms.user.missing_animal_board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.MissingVO;

public class Missing_add_controller implements Initializable{

	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane loadPane;
	@FXML JFXButton missing_add_btn;
	@FXML JFXTextField missing_add_title_tf;
	@FXML JFXTextField missing_add_name_tf;
	@FXML JFXTextField missing_add_gender_tf;
	@FXML JFXTextField missing_add_kind_tf;
	@FXML JFXTextField missing_add_loc_tf;
	@FXML JFXTextField missing_add_content_tf;
	@FXML JFXTextField missing_add_file_tf;
	@FXML JFXTextField missing_add_tel_tf;
	@FXML JFXTextField missing_add_money_tf;
	@FXML JFXButton missing_add_file_btn;
	@FXML JFXButton map_add_file_btn;
	@FXML ImageView missing_add_imgV;
	@FXML Label Hide_Lbl;
	@FXML JFXButton missing_add_back_btn;
	
	public static boolean loc_Img_Check;
	MissingVO vo;
	String fileName;
	int boardNum;
	public static Stage WritePage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		map_add_file_btn.setOnAction(e->{
			modalMap();
		});
		int boardNum = 0;
		try {
			if(Main.ma_u.getSeq()!=null) {
				boardNum = Integer.parseInt(Main.ma_u.getSeq())+1;
			}
			
		} catch (NumberFormatException e3) {
			e3.printStackTrace();
		} catch (RemoteException e3) {
			e3.printStackTrace();
		}
		
		vo = new MissingVO();
		vo.setBoard_num(boardNum+"");

		missing_add_tel_tf.setText(Login_controller.log_c.getCustom_tel());
		
		try {
			ArrayList<MissingVO> list = (ArrayList<MissingVO>) Main.ma_u.getmissingAll();
			boardNum = list.size();
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		missing_add_btn.setOnAction(e -> addMissing());
		missing_add_file_btn.setOnAction(e -> fileAdd());
		missing_add_back_btn.setOnAction(e-> back());
		
		
	}

	private void modalMap() {
		Map_capture_controller.board_num=vo.getBoard_num();
		WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("map_capture.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		WritePage.setTitle("지도 보기");
		WritePage.setScene(scene);
		WritePage.show();
	}

	private void back() {
		ChangePane cp = Main_page_controller.fxmlLoad.getController();
		cp.clearing();
		ChangePane.changePane(getClass().getResource("missingTable.fxml"));
		cp.add();
	}

	private void addMissing() {
		try {
			
			if(missing_add_title_tf.getText().isEmpty() == true) {
				alertError("제목을 입력하세요");
				return;
			}else if(missing_add_name_tf.getText().isEmpty() == true) {
				alertError("이름 입력하세요");
				return;
			}else if(missing_add_gender_tf.getText().isEmpty() == true) {
				alertError("성별 입력하세요");
				return;
			}else if(missing_add_kind_tf.getText().isEmpty() == true) {
				alertError("종류 입력하세요");
				return;
			}else if(missing_add_loc_tf.getText().isEmpty() == true) {
				alertError("실종위치를 입력하세요");
				return;
			}else if(missing_add_content_tf.getText().isEmpty() == true) {
				alertError("특징을 입력하세요");
				return;
			}else if(missing_add_money_tf.getText().isEmpty() == true) {
				alertError("사례금을 입력하세요");
				return;
			}else if(missing_add_file_tf.getText().isEmpty() == true) {
				alertError("파일첨부를 해주세요");
				return;
			}
			
			vo.setTitle(missing_add_title_tf.getText());
			String mix = missing_add_name_tf.getText() + "/" 
			+ missing_add_gender_tf.getText() + "/"
			+ missing_add_kind_tf.getText() + "/"
			+ missing_add_content_tf.getText() +"/"
			+ missing_add_money_tf.getText();
			vo.setContent(mix);
			vo.setMem_id(Login_controller.log_c.getCustom_id());
			vo.setMiss_loc(missing_add_loc_tf.getText());
			vo.setImg(fileName);
			if(loc_Img_Check) {
				vo.setMiss_loc_img(vo.getBoard_num()+",AD"+".jpg");
			}
			int cnt = Main.ma_u.insertmissing(vo);
			if(cnt > 0) {
				alertInfo("등록완료");
				back();
			}else {
				alertError("등록실패");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	private String fileAdd() {
		Stage stage = (Stage) Main.StageHome.getScene().getWindow();
		FileChooser fileChooser =new FileChooser();
		fileChooser.setTitle("Image select");
		
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		
		if(!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		
		File filePath = fileChooser.showOpenDialog(stage);
			BufferedImage getFile;
			try {
				
				getFile = ImageIO.read(filePath);
				Image img = SwingFXUtils.toFXImage(getFile, null);
				missing_add_imgV.setImage(img);
				fileName = filePath.getName();
				int FileLen = fileName.length();
				System.out.println(fileName);	//파일이름
				File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Missing_animal\\"+vo.getBoard_num()+","+Login_controller.log.getMem_id()+","+fileName); //저장파일 이름및 경로 지정
//				File downloadFile = new File("src/image/missing/"+vo.getBoard_num()+","+Login_controller.log.getMem_id()+","+fileName); //저장파일 이름및 경로 지정
				System.out.println(fileName.substring(FileLen-3, FileLen));
				ImageIO.write(getFile, fileName.substring(FileLen-3, FileLen), downloadFile);
				String userFilePath = filePath.getAbsolutePath();
				System.out.println(userFilePath); //절대경로
				missing_add_file_tf.setText(userFilePath);
				return fileName.substring(FileLen-4, FileLen);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}




	public void alertInfo(String temp){
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFO");
		alertInfo.setContentText(temp);
		alertInfo.showAndWait();
	}
	public void alertError(String temp){
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("ERROR");
		alertError.setContentText(temp);
		alertError.showAndWait();
	}

}
