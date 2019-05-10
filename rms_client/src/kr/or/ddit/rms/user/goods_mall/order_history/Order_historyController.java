package kr.or.ddit.rms.user.goods_mall.order_history;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ProdVO;

public class Order_historyController implements Initializable{
@FXML AnchorPane Order_Main_Pane;
	
	
	VBox mainBox;
	
	public static JFXButton log_btn; // 콤보박스 버튼사용을 위한 전역 변수	
	
	public static int all_price = 0; 	//총 구매 금액 저장 될 변수
	public static int list_size = 0;
	public static List<Buy_LogVO> buyList;
	
	public static JFXButton log = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list_size = 0;
		all_price = 0;
		
		mainBox = new VBox();
		mainBox.setPrefWidth(1036.0);
		Buy_LogVO vo = new Buy_LogVO();
		
		setVbox();
		Order_Main_Pane.getChildren().add(mainBox);
		
	}
	
	private void setVbox() {
		mainBox.getChildren().clear();
		try {
			Buy_LogVO vo = new Buy_LogVO();
			vo.setCustom_id(Login_controller.log_c.getCustom_id()); 
			buyList = Main.gm_u.getSearchBuy_log(vo);
			list_size = buyList.size();
			for (int i = 0; i < buyList.size(); i++) {
				ProdVO pvo = new ProdVO();
				pvo.setProd_num(buyList.get(i).getProd_num());
				ProdVO imgTemp = Main.gm_u.getSearchProd(pvo);
				
				String buyNum = buyList.get(i).getBuy_num();
				String prodName = imgTemp.getProd_name();
				String imgName = imgTemp.getProd_img();
				String quan = buyList.get(i).getBuy_quan();
				String price = buyList.get(i).getPrice();
				String tempBuyDate = buyList.get(i).getBuy_date().substring(0, 10);
				String buyDate = tempBuyDate.replace("-","/");
				String state = buyList.get(i).getRefund_check();
				
				HBox H = new HBox();
				H.setPrefWidth(1036.0);
				H.setPrefHeight(50.0);
				H.setAccessibleText(i+"");
				
				//상품 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
				Label buyNum_lbl = new Label();
				buyNum_lbl.setText(buyNum);
				buyNum_lbl.setPrefWidth(77.0);
				buyNum_lbl.setPrefHeight(50.0);
				buyNum_lbl.setAlignment(Pos.CENTER);
				buyNum_lbl.setFont(Font.font(".HCR Dotum", 15));
				
				//상품 이미지 담기 
				Image prod_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\goods_img\\"+imgName);
				ImageView view = new ImageView();
				view.setImage(prod_image);
				view.setFitWidth(100.0);
				view.setFitHeight(50.0);
				
				//상품명 라벨에 담기 
				Label prod_name_lbl = new Label();
				prod_name_lbl.setText(prodName);
				prod_name_lbl.setPrefWidth(249.0);
				prod_name_lbl.setPrefHeight(50.0);
				prod_name_lbl.setAlignment(Pos.CENTER);
				prod_name_lbl.setFont(Font.font(".HCR Dotum", 15));
				
				HBox imgH = new HBox();
				imgH.setPrefWidth(349.0);
				imgH.setPrefHeight(50.0);
				imgH.setAccessibleText(i+"");
				imgH.getChildren().add(view);
				imgH.getChildren().add(prod_name_lbl);
				
				//수량 라벨에 담기
				Label prod_amount_lbl = new Label();
				prod_amount_lbl.setText(quan);
				prod_amount_lbl.setPrefWidth(71.0);
				prod_amount_lbl.setPrefHeight(50.0);
				prod_amount_lbl.setAlignment(Pos.CENTER);
				prod_amount_lbl.setFont(Font.font(".HCR Dotum", 15));
				
				
				//가격 라벨에 담기
				Label price_lbl = new Label();
				price_lbl.setText(price);
				price_lbl.setPrefWidth(153.0);
				price_lbl.setPrefHeight(50.0);
				price_lbl.setAlignment(Pos.CENTER);
				price_lbl.setFont(Font.font(".HCR Dotum", 15));
				
				//구매일 라벨에 담기
				Label date_lbl = new Label();
				date_lbl.setText(buyDate);
				date_lbl.setPrefWidth(162.0);
				date_lbl.setPrefHeight(50.0);
				date_lbl.setAlignment(Pos.CENTER);
				date_lbl.setFont(Font.font(".HCR Dotum", 15));
				
				//상태 라벨에 담기
				Label state_lbl = new Label();
				state_lbl.setText(state);
				state_lbl.setPrefWidth(146.0);
				state_lbl.setPrefHeight(50.0);
				state_lbl.setAlignment(Pos.CENTER);
				state_lbl.setFont(Font.font(".HCR Dotum", 15));
				
				
				//환불버튼 추가 사이즈 77
				FontAwesomeIcon icon = new FontAwesomeIcon();
				icon.setIconName("CLOSE");
				icon.setSize("2em");
				JFXButton refund_btn = new JFXButton();
				refund_btn.setPrefWidth(79.0);
				refund_btn.setPrefHeight(50.0);
				refund_btn.setGraphic(icon);
				refund_btn.setAlignment(Pos.CENTER);
				refund_btn.setAccessibleText(buyNum+"/"+prodName+"/"+quan+"/"+price);
				
				//HBox 에 출력할 순서대로 담기
				H.getChildren().add(buyNum_lbl);
				H.getChildren().add(imgH);
				H.getChildren().add(prod_amount_lbl);
				H.getChildren().add(price_lbl);
				H.getChildren().add(date_lbl);
				H.getChildren().add(state_lbl);
				
				if(!state.equals("환불완료")) {
					H.getChildren().add(refund_btn);
				}
				
				
				//라인넣기
				HBox line = new HBox();
				line.setPrefWidth(1036.0);
				line.setPrefHeight(2.0);
				line.setStyle("-fx-background-color: #dadada;");
				
				// VBox 한줄 추가
				mainBox.getChildren().add(H);
				mainBox.getChildren().add(line);
				
				refund_btn.setOnAction(e->{
					
					int int_price = Integer.parseInt(price);
					int int_quan = Integer.parseInt(quan);
					
					log = (JFXButton) e.getSource();
					checkRefund(buyDate,buyNum);
					
					send_email(log);
					
					alertTest(" 환불 완료!!\n - 환불 품목 : " + prodName + "\n - 가격 : " + price + "\n - 수량 : " + quan + "\n - 차감 포인트 : - " + (int_price*int_quan)/20 + "\n\n 이용해 주셔서 감사합니디");
					
					Order_Main_Pane.getChildren().remove(mainBox);
					setVbox();
					Order_Main_Pane.getChildren().add(mainBox);
					
					
				});
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("장바구니 추가 완료");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}
	
	
	private void checkRefund(String buyDate, String buyNum) {
		boolean check = alertConfirm("환불하시겠습니까?");
		if(check) {
			System.out.println("ha");
		int checkNum = -1;
		try{ // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
	        // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
	        Date FirstDate = format.parse(buyDate);
	        Date SecondDate = new Date();
	        
	        // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
	        // 연산결과 -950400000. long type 으로 return 된다.
	        long calDate =  SecondDate.getTime()- FirstDate.getTime(); 
	        
	        // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
	        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        calDateDays = Math.abs(calDateDays);
	        checkNum = (int) calDateDays;
	        if(checkNum<0) {
	        	alertError("날짜가 이상합니다!");
	        }else if(checkNum>=0&&checkNum<=7) {
	        	Buy_LogVO rvo = new Buy_LogVO();
	        	rvo.setBuy_num(buyNum);
	        	try {
	        		List<Buy_LogVO> list = Main.gm_u.getSearchBuy_log(rvo);
	        		rvo = list.get(0);
	        		rvo.setRefund_check("환불완료");
					Main.gm_u.updateBuy_log(rvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }else {
	        	alertError("환불기간이 지났습니다..");
	        }
	        
        }
        catch(ParseException e)
        {
        	e.printStackTrace();
            // 예외 처리
        }
			
			
	}
		
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
	
	public void send_email(JFXButton log) {
		// Java Mail API
		// 발신자 메일 설정 부분
		String host     		= "smtp.naver.com";
		final String user   	= "pjk2289@naver.com";
		final String password 	= "dntjd72007989@";

		// 수신자 메일 주소
		String to     = Login_controller.log_c.getCustom_email();
		
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
			message.setSubject("[공인중견사] 굿즈몰 환불영수증 ");
			String[] info = log.getAccessibleText().split("/");
			int quan = Integer.parseInt(info[3]);
			int price = Integer.parseInt(info[2]);
			
			
			//내용작성
			message.setText("구매 번호 : "+info[0]
					+"\n환불 품목 : " + info[1]
				    +"\n가격 : " + info[3]
				    +"\n수량 : " + info[2]
				    +"\n차감 포인트 : -" + (quan * price)/20 +"원"
				    +"\n총 구매 금액 : " + (quan * price) + "원"
				    +"\n\n\n정상적으로 환불 처리 되었습니다"
					+ "\n이용해 주셔서 감사합니다");
			
			//메시지 전송!!
			Transport.send(message);
			System.out.println("환불영수증 전송 완료!!");

			
			
			
		 } catch (MessagingException ee) {
			   ee.printStackTrace();
		 } 
	}
}


