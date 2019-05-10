package kr.or.ddit.rms.admin.goods_mall.delivery_management;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CustomVO;

public class Delivery_management_controller implements Initializable{

	
	@FXML AnchorPane Delivery_Main_Pane;
	
	VBox mainBox;
	
	JFXButton log;
	
	public static List<Buy_LogVO> deliverList;
	public static int list_size = 0;

	@FXML Label Hide_Lbl;
	
	public static String C_email = "";
	public static String C_addr = "";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainBox = new VBox();
		mainBox.setPrefWidth(1036.0);
		
		show_sales_list();
		//Delivery_Main_Pane.getChildren().add(mainBox);
	}
	
	private void show_sales_list() {
		mainBox.getChildren().clear();
		mainBox = new VBox();
		mainBox.setPrefWidth(961.0);
		
		try {
			Buy_LogVO vo = new Buy_LogVO();
			vo.setRefund_check("배송준비중");
			deliverList = Main.gm_a.getSearchBuy_log(vo);
			
			Hide_Lbl.setText("(" + deliverList.size() + "건)");
			
			for (int i = 0; i < deliverList.size(); i++) {
				String buyNum = deliverList.get(i).getBuy_num();
				String prodName = deliverList.get(i).getProd_name();
				String buyDate = deliverList.get(i).getBuy_date();
				String customId = deliverList.get(i).getCustom_id();
				String cardName = deliverList.get(i).getCard_name();
				
				
				HBox H = new HBox();
				H.setPrefWidth(961.0);
				H.setPrefHeight(34.0);
				H.setAccessibleText(i+"");
				
				//상품 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
				Label buyNum_lbl = new Label();
				buyNum_lbl.setText(buyNum);
				buyNum_lbl.setPrefWidth(55.0);
				buyNum_lbl.setPrefHeight(34.0);
				buyNum_lbl.setAlignment(Pos.CENTER);
				buyNum_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(buyNum_lbl);
				
				
				//상품명 라벨에 담기 
				Label prod_name_lbl = new Label();
				prod_name_lbl.setText(prodName);
				prod_name_lbl.setPrefWidth(222.0);
				prod_name_lbl.setPrefHeight(34.0);
				prod_name_lbl.setAlignment(Pos.CENTER);
				prod_name_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(prod_name_lbl);
				
				
				//구매일 라벨에 담기
				Label date_lbl = new Label();
				date_lbl.setText(buyDate.substring(0, 10));
				date_lbl.setPrefWidth(151.0);
				date_lbl.setPrefHeight(34.0);
				date_lbl.setAlignment(Pos.CENTER);
				date_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(date_lbl);
				
				//구매ID 라벨에 담기
				Label custom_id_lbl = new Label();
				custom_id_lbl.setText(customId);
				custom_id_lbl.setPrefWidth(147.0);
				custom_id_lbl.setPrefHeight(34.0);
				custom_id_lbl.setAlignment(Pos.CENTER);
				custom_id_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(custom_id_lbl);
				
				CustomVO cvo = new CustomVO();
				cvo.setCustom_id(customId);
				CustomVO rcvo = Main.gm_a.getSearchCustom(cvo);
				String addr = rcvo.getCustom_addr();
				
				//배송지
				Label addr_lbl = new Label();
				addr_lbl.setText(addr);
				addr_lbl.setPrefWidth(268.0);
				addr_lbl.setPrefHeight(34.0);
				addr_lbl.setAlignment(Pos.CENTER);
				addr_lbl.setFont(Font.font(".System", 15));
				H.getChildren().add(addr_lbl);
				
				
				FontAwesomeIcon icon = new FontAwesomeIcon();
				icon.setIconName("TRUCK");
				icon.setSize("2.5em");

				JFXButton add_bascket_btn = new JFXButton();
				add_bascket_btn.setGraphic(icon);
				add_bascket_btn.setPrefWidth(130);
				add_bascket_btn.setAccessibleText(buyNum);
				H.getChildren().add(add_bascket_btn);
				
				HBox line = new HBox();
				line.setPrefWidth(961.0);
				line.setPrefHeight(2.0);
				line.setStyle("-fx-background-color: #CDCDCD;");
				
				mainBox.getChildren().add(H);
				mainBox.getChildren().add(line);
				
				add_bascket_btn.setOnAction(e->{
					log = (JFXButton) e.getSource();
					
					
					alertTest("'"+customId+"'"+" 회원 물품 배송 완료"
							+ "\n 배송정보 메일 발송 완료!!");
					System.out.println(log.getAccessibleText());
					Buy_LogVO bvo = new Buy_LogVO();
					bvo.setBuy_num(log.getAccessibleText());
					bvo.setRefund_check("배송중");
					
					
					try {
						Main.gm_a.updateRefund_check(bvo);
						
						CustomVO customVO = new CustomVO();
						customVO.setCustom_id(customId);
						CustomVO searchCustom = Main.gm_a.getSearchCustom(customVO);
						C_addr = searchCustom.getCustom_addr();
						C_email = searchCustom.getCustom_email();
						
						send_email(buyNum);
						
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					//새로고침
					show_sales_list();
				});
			}
			Delivery_Main_Pane.getChildren().add(mainBox);
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("결제 오류");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}
	
	public void send_email(String Buy_Num) {
		// Java Mail API
		// 발신자 메일 설정 부분
		String host     		= "smtp.naver.com";
		final String user   	= "pjk2289@naver.com";
		final String password 	= "dntjd72007989@";

		// 수신자 메일 주소
		String to     = C_email;
		
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);
			}
		});
		
		
		// 메세지 설정 부분
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
			//제목작성
			message.setSubject("[공인중견사] 굿즈몰 배송 정보 ");
			
			Buy_LogVO buy_LogVO = new Buy_LogVO();
			buy_LogVO.setBuy_num(Buy_Num);
			List<Buy_LogVO> buy_log = Main.gm_a.getSearchBuy_log(buy_LogVO);
			
			String buy_num = buy_log.get(0).getBuy_num();
			String buy_quan = buy_log.get(0).getBuy_quan();
			String prod_name = buy_log.get(0).getProd_name();
			String card_name = buy_log.get(0).getCard_name();
			String price = buy_log.get(0).getPrice();
			
			
			
			int int_price = Integer.parseInt(buy_log.get(0).getPrice());
			int int_quan = Integer.parseInt(buy_log.get(0).getBuy_quan());
			
			
			int delivery_num1 = (int)(Math.random() * 1000000)+908992;
			int delivery_num2 = (int)(Math.random() * 1000000)+908992;
			
			//내용작성
			message.setText("----------------결제 정보----------------"
					+ "\n\n       - 구매 번호 : "+buy_num
					+"\n       - 구매 품목 : "+prod_name
				    +"\n       - 가격 : " + price
				    +"\n       - 수량 : " + buy_quan
				    +"\n       - 결제 카드사 : " + card_name
				    
				    +"\n       - 총 구매 금액 : " + (int_price * int_quan) + "원"
				    +"\n\n\n----------------배송 정보----------------"
				    
				    +"\n\n    - 배송지 : " + C_addr
				    +"\n\n\n     운송장 번호 [ " + delivery_num1 + delivery_num2  +  " ]"
				    
				    +"\n\n\n정상적으로 배송 처리 되었습니다"
					+ "\n이용해 주셔서 감사합니다");
			
			//메시지 전송!!
			Transport.send(message);
			System.out.println("환불영수증 전송 완료!!");

			
			
			
		 } catch (MessagingException ee) {
			   ee.printStackTrace();
		 } catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
}
