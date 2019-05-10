package kr.or.ddit.rms.mainpage.find_pw;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import aesExam.Aes256;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.MemberVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class Find_pw_controller implements Initializable {
	
	@FXML JFXTextField Findpw_Id_Txt;
	@FXML JFXTextField Findpw_Email_Txt;
	
	@FXML JFXButton Findpw_Findpw_Btn;
	@FXML JFXButton Findpw_Cancel_Btn;
	
	@FXML Label Findpw_result_Lbl;
	
	MemberVO rmvo = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//PW찾기 버튼 클릭
		Findpw_Findpw_Btn.setOnAction(e->{
			
			//빈칸 존재시 경고매시지 출력
			if(Findpw_Id_Txt.getText().isEmpty()) {
				alertError("아이디를 입력 해주세요");
				Findpw_Id_Txt.requestFocus();
				return;
			} else if(Findpw_Email_Txt.getText().isEmpty()) {
				alertError("이메일 주소를 입력 해주세요");
				Findpw_Email_Txt.requestFocus();
				return;
			}
			
			//ID입력하여 회원 인지 관리자 인지 구분
			String mem_author = "";	
			
			MemberVO mvo = new MemberVO();
			mvo.setMem_id(Findpw_Id_Txt.getText());
			
			try {
				rmvo = Main.fps.getSearchMember(mvo);
				
				mem_author = rmvo.getMem_author();
				
				
			} catch (Exception ee) {
				alertError("등록되지 않은 사용자 입니다");
			}
			
			System.out.println(mem_author);
			
			//ID가 회원 일때 
			if(mem_author.equals("1")) {
				CustomVO cvo = new CustomVO();
				cvo.setCustom_id(Findpw_Id_Txt.getText());
				//vo.setCustom_email(Findpw_Email_Txt.getText());
				
				try {
					CustomVO rcvo = Main.fps.getSearchCustom(cvo);
					if(!rcvo.getCustom_email().equals(Findpw_Email_Txt.getText())) {
						alertError("잘못된 이메일 주소 입니다");
						return;
					}
					Login_controller.dialog.close();
					send_email(mvo);
				} catch (Exception ee) {
					
					alertError("등록되지 않은 사용자 입니다");
					return;
				}
				
			}
			
			//ID가 보호기관일때
			if(mem_author.equals("2")) {
				ShelterVO svo  = new ShelterVO();
				svo.setShel_id(Findpw_Id_Txt.getText());
				
				try {
					ShelterVO rsvo = Main.fps.getSearchShelter(svo);
					
					if(!rsvo.getShel_email().equals(Findpw_Email_Txt.getText())) {
						alertError("잘못된 이메일 주소 입니다");
						return;
					}
					Login_controller.dialog.close();
					send_email(mvo);
				} catch (Exception ee) {
					alertError("등록되지 않은 사용자 입니다");
					return;
				}
			}
			
		
			
				 
		});//PW찾기 버튼 끝
		
		
		
		//취소버튼 클릭
		Findpw_Cancel_Btn.setOnAction(e->{
			Login_controller.dialog.close();
		});//취소버튼끝
		
		
	}//Initializable 끝
	
	void send_email(MemberVO mvo) {
		// Java Mail API
		// 발신자 메일 설정 부분
		String host     		= "smtp.naver.com";
		final String user   	= "pjk2289@naver.com";
		final String password 	= "dntjd72007989@";

		// 수신자 메일 주소
		String to     = Findpw_Email_Txt.getText();
		
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);
			}
		});
		
		//임시비밀번호 생성
		// UUID 자체는 Object 타입 이므로 String 타입으로 바꿔 줘야 함.
		String uuid = UUID.randomUUID().toString();
		String[] temp_pw = uuid.split("-");
		
		
		// 메세지 설정 부분
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
			//제목작성
			message.setSubject("[공인중견사] 임시 비밀번호 발송메일 ");
			String key = "RescuedogManagement"; 
			Aes256 aes256;
			String encText = null;
			try {
				aes256 = new Aes256(key);
				encText = aes256.aesEncode(temp_pw[0]);
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				e1.printStackTrace();
			}
			//내용작성
			message.setText("회원 님의 임시 비밀번호는   [ " + temp_pw[0] + " ]      입니다."
					+ "\n로그인 후 비밀번호를 변경해주시기 바랍니다");
			
			//메시지 전송!!
			Transport.send(message);
			System.out.println("임시 비밀번호 전송 완료!!");
			alertTest("등록된 이메일로 임시비밀번호 발송");
			
			//DB 비밀번호 저장
			mvo.setMem_id(rmvo.getMem_id());
			mvo.setMem_author(rmvo.getMem_author());
			mvo.setMem_pw(encText);
			mvo.setMem_report(rmvo.getMem_report());
			System.out.println("임시비밀번호 DB저장 성공");
			
			Main.fps.updateMember(mvo);
			
			
		 } catch (MessagingException ee) {
			   ee.printStackTrace();
		 } catch (RemoteException e2) {
			 
		 }
	}
	
	private void alertError(String message) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("비밀번호 찾기");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("비밀번호 찾기 찾기");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}

}
