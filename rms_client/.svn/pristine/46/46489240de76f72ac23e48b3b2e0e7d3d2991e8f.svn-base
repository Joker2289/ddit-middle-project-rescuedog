package kr.or.ddit.rms.admin.sharing_info_board;

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
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.Board_classVO;
import kr.or.ddit.rms.vo.Board_detailVO;

public class Share_addController implements Initializable{
	public static String bc_num;
	public static String board_num;
	public static String fileName;
	@FXML JFXTextField share_newtitle_tf;
	@FXML HTMLEditor share_content_ta;
	@FXML JFXTextField share_newfile_tf;
	@FXML JFXButton share_filefind_btn;
	@FXML JFXButton share_cancel_btn;
	@FXML JFXButton share_add_btn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(board_num==null) {
			board_num="1";
		}
		share_filefind_btn.setOnAction(e->fileAdd());
		share_newfile_tf.setDisable(true);
		
		share_add_btn.setOnAction(e->addCheck(share_newtitle_tf, share_content_ta)); //등록
		share_cancel_btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("share_table.fxml"));
				cp.add();
			}
		});
	}
	
	private void addCheck(JFXTextField share_newtitle_tf, HTMLEditor share_content_ta) {
		if(share_newtitle_tf.getText().isEmpty()) { // title 비어있을때
			alertInfo("title을 입력하세요.");
		}else if(share_content_ta.getHtmlText().isEmpty()) { // 내용이 비어있을때
			alertInfo("내용을 입력하세요.");
		}else {
			Board_detailVO vo =new Board_detailVO();
			vo.setCustom_id(Login_controller.log.getMem_id());
			vo.setBc_num(Share_addController.bc_num);
			vo.setBoard_img(Share_addController.fileName);
			Board_classVO tempBn = new Board_classVO();
			tempBn.setBc_num(Share_addController.bc_num);
			try {
				List<Board_classVO> tempBns = (List<Board_classVO>) Main.bcs.getSearchBoardClass(tempBn);
				vo.setBoard_num(tempBns.get(0).getBc_idx());
				int idx =Integer.parseInt(tempBns.get(0).getBc_idx())+1;
				tempBn=tempBns.get(0);
				tempBn.setBc_idx(idx+"");
				Main.bcs.updateBoardClass(tempBn);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			vo.setContent(share_content_ta.getHtmlText().replaceAll("<[^>]*>",""));
			vo.setTitle(share_newtitle_tf.getText());
			vo.setCom_idx("1");
			try {
				Main.sib_a.insertBoard_detail(vo); 
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(getClass().getResource("share_table.fxml"));
				cp.add();
			} catch (RemoteException e) {
				e.printStackTrace();
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
				File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Sharing_info\\"+bc_num+","+board_num+fileName);
				ImageIO.write(getFile, fileName.substring(FileLen-3, FileLen), downloadFile);
				String userFilePath = filePath.getAbsolutePath();
				System.out.println(userFilePath);
				share_newfile_tf.setText(userFilePath);
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
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	
}
