package kr.or.ddit.rms.user.missing_animal_board;


import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;

public class MakeADController implements Initializable{

	@FXML AnchorPane mainPane;
	@FXML GridPane adPane;
	@FXML JFXTextField makeADcontent_ta;
	@FXML JFXTextField makeADLoc_tf;
	@FXML JFXTextField makeADPhone_tf;
	@FXML JFXTextField makeADdog_tf;
	@FXML JFXTextField makeADgender_tf;
	@FXML ImageView adBgImg_iv;
	@FXML ImageView adAddImg_iv;
	@FXML JFXButton adImg_btn;
	@FXML JFXButton makeADok_btn;
	@FXML JFXButton makeADcancel_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeADok_btn.setOnAction(e->adCheck());
		makeADcancel_btn.setOnAction(e->gotoAdList());
		adImg_btn.setOnAction(e->{
			addImage();
		});
		adBgImg_iv.toBack();
		adAddImg_iv.toBack();
		adImg_btn.setText("사진 등록");
	}
	private void addImage() {
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
			javafx.scene.image.Image img = SwingFXUtils.toFXImage(getFile, null);
			adAddImg_iv.setImage(img);
			adImg_btn.setText(" ");
		} catch (IOException e) {
			alertInfo("이미지가 아닙니다.");
		}
		
	}
	private void gotoAdList() {
		Main_page_controller.WritePage.close();
	}
	private void adCheck() {
		if(makeADdog_tf.getText().isEmpty()) {
			alertError("이름을 입력하세요");
		}else if(makeADgender_tf.getText().isEmpty()) {
			alertError("견종을 입력하세요");
		}else if(makeADLoc_tf.getText().isEmpty()) {
			alertError("실종 위치를 입력하세요");
		}else if(makeADcontent_ta.getText().isEmpty()) {
			alertError("를 입력하세요");
		}else if(makeADPhone_tf.getText().isEmpty()){
			alertError("연락처를 입력하세요");
		}else {
			capture();
			alertInfo("전단제 제작 성공");
			Main_page_controller.WritePage.close();
		}
	}
	
	private void capture() {
			try {
				Robot robot = new Robot();
				Rectangle r = new Rectangle(550,100, 570, 720);
				BufferedImage bi = robot.createScreenCapture(r);
				javafx.scene.image.Image i = SwingFXUtils.toFXImage(bi, null);
				
				ImageIO.write(bi,"jpg",new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\Advertisement\\"+Login_controller.log.getMem_id()+",AD"+".jpg"));
				makeAd();
			} catch (AWTException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private void makeAd() {
		Document doc = new Document(PageSize.A4, 20,20,0,0);  // 페이지 사이즈와 좌우상하 여백

		Stage stage = (Stage) Main.StageHome.getScene().getWindow();
		FileChooser fileChooser =new FileChooser();
		fileChooser.setTitle("저장 위치 설정");
		
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		if(!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		File filePath = fileChooser.showSaveDialog(stage);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(filePath.getAbsolutePath()+".pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doc.open();

		try {
			doc.add(new Paragraph(" "));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Image jpg=null;
		try {
			
			jpg = Image.getInstance("\\\\Sem-pc\\공유폴더\\Rescuedog\\Advertisement\\"+Login_controller.log.getMem_id()+",AD"+".jpg");
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			doc.add(jpg);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			doc.add(new Paragraph(" "));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		String resPath = "font/malgun.ttf";

		BaseFont bf = null;
		try {
			bf = BaseFont.createFont(resPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Font cellTitleFont = new Font(bf,10,Font.BOLD);

		Font cellNormalFont = new Font(bf,10, Font.NORMAL);

		        doc.close();

	}
	
	public boolean alertConfirm(String ment) {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		
		alertConfirm.setTitle("확인 창");
		alertConfirm.setContentText(ment);
		
		//Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult==ButtonType.OK) {
			return true;
		}else if(confirmResult==ButtonType.CANCEL) {
			return false;
		}
		return false;
	}
	
	public void alertError(String temp){
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}
	
	public void alertInfo(String temp){
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("Information");
		alertErorr.setContentText(temp);
		alertErorr.showAndWait();
	}

}
