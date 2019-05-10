package kr.or.ddit.rms.admin.goods_mall.goods_management;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.rms.admin.goods_mall.goods_view.A_goods_view_controller;
import kr.or.ddit.rms.main.ChangePane;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;
import kr.or.ddit.rms.vo.ProdVO;

public class Add_goods_controller implements Initializable{

	@FXML JFXButton Add_Prod_Btn;		 	//작성 완료 버튼		
	@FXML JFXButton Find_Img_Btn;			//파일첨부 버튼
	
	@FXML JFXTextField Prod_Name_Txt;		//상품명 텍스트
	@FXML JFXTextField Prod_Price_Txt;		//상품 가격 텍스트
	@FXML JFXTextArea Prod_Info_Txt;		//상품 정보 텍스트
	@FXML JFXTextField Path_Img_Txt;		//첨부 사진 경로 텍스트
	
	@FXML ImageView Find_Img_Iv;			//첨부이미지 미리보기 뷰
	
//	@FXML Label Hide_Label;					//숨김라벨
//	@FXML Label Hide_Label_Name;			//숨김라벨
//	@FXML Label Hide_Label_Price;			//숨김라벨
//	@FXML Label Hide_Label_Info;			//숨김라벨

	
	String fileName;
	String fileFormat;
	String prod_num = ""; //상품번호
	String Admin_id = "";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Path_Img_Txt.setDisable(true);
		System.out.println("로그인 관리자 : " + Login_controller.log_a.getAdmin_id());
		
		
		try {
			String num = Main.gm_a.getProd_num();
			if(num!=null) {
				prod_num = (Integer.parseInt(num)+1)+"";
			}else {
				prod_num = "1";
			}
			System.out.println(prod_num);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		//파일 찾기 버튼
		Find_Img_Btn.setOnAction(e->{
			fileFormat=fileAdd();	
		});
		
		//작성완료 버튼 클릭
		Add_Prod_Btn.setOnAction(e->add_prod());
		
	}
	
	private void add_prod() {
		if(Prod_Name_Txt.getText().isEmpty() == true) {
			alertTest("상품명을 입력하세요");
			return;
		} else if(Prod_Price_Txt.getText().isEmpty() == true) {
			alertTest("상품가격을 입력하세요");
			return;
		} else if(Prod_Info_Txt.getText().isEmpty() == true) {
			alertTest("상품정보를 입력하세요");
			return;
		} 
		else if(Path_Img_Txt.getText().isEmpty() == true) {
			alertTest("상품 이미지를 첨부해주세요");
			return;
		}

		try {
			String num = Main.gm_a.getProd_num();
			ProdVO pvo = new ProdVO();
			
			if(num!=null) {
				prod_num = (Integer.parseInt(num)+1)+"";
			}else {
				prod_num = "1";
			}
			System.out.println(prod_num);
			
			pvo.setProd_num(prod_num);
			pvo.setProd_name(Prod_Name_Txt.getText());
			pvo.setPrice(Prod_Price_Txt.getText());
			pvo.setProd_info(Prod_Info_Txt.getText());
			pvo.setAdmin_id(Login_controller.log_a.getAdmin_id());
			if(fileFormat!=null) {
				pvo.setProd_img("good"+prod_num+fileFormat);
			}else if(fileFormat == null) {
				alertTest("상품추가 실패!!");
				return;
			}
			
			int x = Main.gm_a.insertProd(pvo);
			
			if(x != 0) {
				System.out.println(x+"개 상품 추가");
				alertTest("' "+Prod_Name_Txt.getText()+" '  " + " 상품추가 완료");
				ChangePane cp = Main_page_controller.fxmlLoad.getController();
				cp.clearing();
				ChangePane.changePane(A_goods_view_controller.class.getResource("goods_view.fxml"));
				cp.add();
				
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
				Find_Img_Iv.setImage(img);
				String fileName = filePath.getName();
				int FileLen = fileName.length();
				System.out.println(fileName);	//파일이름
				File downloadFile = new File("\\\\Sem-pc\\공유폴더\\Rescuedog\\goods_img\\"+"good"+
						prod_num+fileName.substring(FileLen-4, FileLen)); //저장파일 이름및 경로 지정
//				File downloadFile = new File("src/image/prod/"+
//						"good"+prod_num+fileName.substring(FileLen-4, FileLen)); //저장파일 이름및 경로 지정
//				System.out.println(prod_num);
//				System.out.println(fileName.substring(FileLen-3, FileLen));
				ImageIO.write(getFile, fileName.substring(FileLen-3, FileLen), downloadFile);
				String userFilePath = filePath.getAbsolutePath();
				System.out.println(userFilePath); //절대경로
				//System.out.println(downloadFile.getName());
//				fileName = downloadFile.getName();
				Path_Img_Txt.setText(userFilePath);
				return fileName.substring(FileLen-4, FileLen);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("상품 추가");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
