package kr.or.ddit.rms.shelter.protected_animal_board;

import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.sign_up.common.AlertDialog;
import kr.or.ddit.rms.shelter.protected_animal_board.IProtectedBoardShelterService;
import kr.or.ddit.rms.vo.Board_classVO;
import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Protected_boardVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class Protected_Add implements Initializable {
	public static String bc_num;
	public static int board_num;
	public static String fileName;
	@FXML
	JFXTextField Title_Fb;
	@FXML
	JFXTextField Name_Fb;
	@FXML
	JFXTextField Sex_Fb;
	@FXML
	JFXTextField Find_Fb;
	@FXML
	JFXTextField Kind_Fb;
	@FXML
	JFXTextField Path_Fb;
	@FXML
	JFXTextField Characteristic_Fb;
	@FXML
	JFXTextField Tel_Fb;
	@FXML
	JFXButton Pr_Findfile;
	@FXML
	JFXButton Register_Btn;
	@FXML
	VBox Vb_imgbox;
	@FXML
	ImageView Pr_ImgView;
	@FXML
	AnchorPane TopPane;
	@FXML
	AnchorPane CenterPane;
	@FXML
	Label Hide_Lbl_Title;
	@FXML
	Label Hide_Lbl_name;
	@FXML
	Label Hide_Lbl_Find;
	@FXML
	Label Hide_Lbl_Ch;
	@FXML
	AnchorPane loadPane;
	boolean rule_check;
	static String arrayVO[];
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	@FXML AnchorPane Rg_pane;
	public AlertDialog alertDlg = new AlertDialog();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ShelterVO svo =new ShelterVO();
		svo.setShel_id(Login_controller.log_s.getShel_id());
		try {
			ShelterVO stemp=Main.fis.getSearchShelter(svo);
			Tel_Fb.setText(stemp.getShel_tel());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		Register_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Calendar cal = Calendar.getInstance();
				Protected_boardVO vo = new Protected_boardVO();
					if (Title_Fb.getText().isEmpty() == true) {
						errMsg("경고","제목을 입력하세요");
						return;
					}  else if (Find_Fb.getText().isEmpty() == true) {
						errMsg("경고","찾은위치를 입력하세요");
						return;
					} else if (Characteristic_Fb.getText().isEmpty() == true) {
						errMsg("경고","특징을 입력하세요");
						return;
					}else if (Path_Fb.getText().isEmpty() == true) {
						errMsg("경고","사진을 등록하세요");
						return;
					}
					Pattern chkTel = Pattern.compile("^[0-9_]{10,11}"); 
					Matcher Telcheck = chkTel.matcher(Tel_Fb.getText());
					String tel =Tel_Fb.getText();
					if (!Telcheck.matches()) {
						Tel_Fb.requestFocus();
						errMsg("경 고 ", "숫자만 입력하세요");
						return;
					}

					vo.setBoard_num(vo.getBoard_num());
					vo.setImg(fileName);
					vo.setMem_id(Login_controller.log_s.getShel_id());
					vo.setTitle(Title_Fb.getText());
//					vo.setName(Name_Fb.getText());
					vo.setGender(Sex_Fb.getText());
					vo.setFind_loc(Find_Fb.getText());
					vo.setKind(Kind_Fb.getText());
					vo.setCharacteristic(Characteristic_Fb.getText());
					vo.setUpd_date(format.format(cal.getTime()));
					vo.setTel(tel);
				
					 
					try {

						Main.pbss.insertBoard(vo);
						infoMsg("등록완료", "등록되었습니다.");
					} catch (RemoteException e) {
						System.out.println("fail");
					}
					Rg_pane.getChildren().clear();
					try {
						loadPane=FXMLLoader.load(IProtectedBoardShelterService.class.getResource("protectedBoard_View.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Rg_pane.getChildren().add(loadPane);
					
					
				} 
				
			
		});

		Pr_Findfile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fileAdd();
			}
		});

	}

	private void fileAdd() {
		Stage stage = (Stage) Main.StageHome.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Image select");

		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		
		
		





		if (!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}

		File filePath = fileChooser.showOpenDialog(stage);
		BufferedImage getFile;
		try {
			getFile = ImageIO.read(filePath);
			Image img = SwingFXUtils.toFXImage(getFile, null);
			Pr_ImgView.setImage(img);
			fileName = filePath.getName();
			int FileLen = fileName.length();
			File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Protected_Board\\"+fileName);
//			File downloadFile = new File("C:\\Users\\shrtm\\OneDrive\\바탕 화면\\testpath"+fileName);
//			File downloadFile = new File("src\\kr\\or\\ddit\\rms\\user\\protected_animal_board\\protected_img"+"1"+ "2"+ fileName);
			ImageIO.write(getFile, fileName.substring(FileLen - 3, FileLen), downloadFile);
			String userFilePath = filePath.getAbsolutePath();
			System.out.println(userFilePath);
			Path_Fb.setText(userFilePath);
			
		} catch (IOException e) {
		}
	}

	public void errMsg(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

	// 확인메세지창
	public void infoMsg(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
