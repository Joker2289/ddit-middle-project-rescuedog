package kr.or.ddit.rms.user.free_board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.Board_classVO;
import kr.or.ddit.rms.vo.Board_detailVO;

public class Free_board_addUController implements Initializable{
	public static String bc_num;
	public static String board_num;
	public static String fileName;
	@FXML JFXTextField freeboard_newtitle_tf;
	@FXML HTMLEditor freeboard_content_ta;
	@FXML JFXTextField freeboard_newfile_tf;
	@FXML JFXButton freeboard_filefind_btn;
	@FXML JFXButton freeboard_cancel_btn;
	@FXML JFXButton freeboard_add_btn;
	@FXML AnchorPane servicePane;
	@FXML AnchorPane tempPane;
	@FXML AnchorPane addPane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(board_num==null) {
			board_num="1";
		}
		freeboard_filefind_btn.setOnAction(e->fileAdd());
		freeboard_newfile_tf.setDisable(true);
		freeboard_add_btn.setOnAction(e->addCheck(freeboard_newtitle_tf, freeboard_content_ta)); //등록
		freeboard_cancel_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("free_board_table.fxml"));
				cp.add();
			}
		});
	}
	private void addCheck(JFXTextField freeboard_newtitle_tf, HTMLEditor freeboard_content_ta) {
		if(freeboard_newtitle_tf.getText().isEmpty()) { // title 비어있을때
			alertInfo("title을 입력하세요.");
		}else if(freeboard_content_ta.getHtmlText().isEmpty()) { // 내용이 비어있을때
			alertInfo("내용을 입력하세요.");
		}else {
			Board_detailVO vo =new Board_detailVO();
			vo.setCustom_id(Login_controller.log_c.getCustom_id());
			vo.setBc_num(Free_board_addUController.bc_num);
			vo.setBoard_img(Free_board_addUController.fileName);
			Board_classVO tempBn = new Board_classVO();
			tempBn.setBc_num(Free_board_addUController.bc_num);
			try {
				List<Board_classVO> tempBns = (List<Board_classVO>) Main.bcs.getSearchBoardClass(tempBn);
				vo.setBoard_num(tempBns.get(0).getBc_idx());
				System.out.println(tempBns.get(0).getBc_name());
				int idx =Integer.parseInt(tempBns.get(0).getBc_idx())+1;
				tempBn=tempBns.get(0);
				tempBn.setBc_idx(idx+"");
				Main.bcs.updateBoardClass(tempBn);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			vo.setContent(freeboard_content_ta.getHtmlText().replaceAll("<[^>]*>",""));
			vo.setTitle(freeboard_newtitle_tf.getText());
			vo.setCom_idx("1");
			try {
				Main.fb_u.insertBoard_detail(vo); 
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("free_board_table.fxml"));
				cp.add();
			} catch (RemoteException e) {
				alertInfo("등록 성공");
			}
		}
	}
	

	private void fileAdd() {
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
				fileName = filePath.getName();
				int FileLen = fileName.length();
				System.out.println(fileName);
				File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Freeboard\\"+bc_num+","+board_num+fileName);
				ImageIO.write(getFile, fileName.substring(FileLen-3, FileLen), downloadFile);
				String userFilePath = filePath.getAbsolutePath();
				System.out.println(userFilePath);
				freeboard_newfile_tf.setText(userFilePath);
				
			} catch (IOException e) {
				alertInfo("이미지가 아닙니다.");
			}
	}



	public void alertTest(){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText("ERROR 창 입니다");
		alertErorr.showAndWait();
	}
	public void alertInfo(String temp){
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	
}
