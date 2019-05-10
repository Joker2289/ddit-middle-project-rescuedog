package kr.or.ddit.rms.user.goods_mall.cart_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.user.goods_mall.order.Order_view_controller;
import kr.or.ddit.rms.vo.CartVO;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ProdVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Cart_view_controller implements Initializable {

	@FXML AnchorPane Cart_Main_Pane;	//메인 Pane
	
	@FXML Label Cart_Message_Lbl; 		//경고 메시지 라벨
	
	@FXML JFXButton Cart_Order_Btn;		// 주문/결제 버튼

	@FXML Label Cart_Allprice_Lbl; 		// 총 주문 금액 라벨
	
	@FXML Label Cart_Point_Lbl; 		//포인트 라벨
	
	@FXML Label Cart_Kinds_Lbl;			//수량 라벨
	
	@FXML Label Cart_MyPoint_Lbl;		//잔여포인트 라벨
	
	VBox main_box;
	public static Stage cart_view_dialog;
	
	//상품 정보
	public static JFXButton log_btn;
	public static int all_price = 0; 	//총 구매 금액 저장 될 변수
	public static int list_size = 0;
	public static List<CartVO> cart_list = null;
	public static boolean payFlag=false;


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list_size = 0;
		all_price = 0;
		
		
		
		//메인 박스 생성 / 너비 지정
		main_box = new VBox();
		main_box.setPrefWidth(1036.0);
		
		setVbox();
		
		//메인 판에 장바구니 정보를 담은 메인박스 저장
		
		//총 구매가격, 구매항목개수 라벨값 세팅
//		Cart_Allprice_Lbl.setText(all_price+"");
//		Cart_Kinds_Lbl.setText(list_size+"");
//		Cart_Point_Lbl.setText(all_price/20+"");
		
		
		//주문 결제 버튼 클릭
		Cart_Order_Btn.setOnAction(e->{
			
			gotoOrder(main_box);
			if(payFlag==true) {
				//장바구니 목록 출력하는 메인박스 하위노드 전부삭제
				main_box.getChildren().removeAll(cart_list);
				Cart_Main_Pane.getChildren().remove(main_box);
				
				
				//장바구니 목록 값, 총구매금액 0 으로 셋팅
				Cart_Allprice_Lbl.setText("0");
				Cart_Kinds_Lbl.setText("0");
				Cart_Point_Lbl.setText("0");

				all_price = 0;
				list_size = 0;
				setVbox();
				
				payFlag = false;
			}
			
		});
		
		
	}


	public void setVbox() {
		main_box.getChildren().clear();
	
			//로그기록에서 회원 ID 값 가져오기
			String login_id = Login_controller.log_c.getCustom_id();
			
			
			
			//CartVO에 담아서
			CartVO cvo = new CartVO();
			cvo.setCustom_id(login_id);
			
			all_price = 0;
			list_size = 0;
			
			try {
				cart_list=null;
				
				//회원 ID 값으로 장바구니 검색
				cart_list = Main.gm_u.getSearchCart(cvo);
				list_size = cart_list.size();
				
				
				for(int i = 0; i<cart_list.size(); i++) {
					
					
					//장바구니의 상품번호를 저장
					String prod_num = cart_list.get(i).getProd_num();
					
					//prodVO에 상품번호 셋팅
					ProdVO pvo = new ProdVO();
					pvo.setProd_num(prod_num);
					
					//상품의 정보를 검색 / 저장
					ProdVO prod_info = Main.gm_u.getSearchProd(pvo);
					
					//노드 생성 구역
					//상품1개당 Hbox 생성 / 사이즈 지정
					HBox H = new HBox();
					H.setPrefWidth(1036.0);
					H.setPrefHeight(100.0);
					H.setAccessibleText(i+"");
					
					//상품 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
					Label prod_num_lbl = new Label();
					prod_num_lbl.setText(prod_info.getProd_num());
					prod_num_lbl.setPrefWidth(77.0);
					prod_num_lbl.setPrefHeight(100.0);
					prod_num_lbl.setAlignment(Pos.CENTER);
					prod_num_lbl.setFont(Font.font("HCR Dotum", 18));
					
					//상품 이미지 담기 
//					Image prod_image = new Image("image/prod/"+prod_info.getProd_img());
					Image prod_image = new Image("file:\\\\Sem-pc\\공유폴더\\Rescuedog\\goods_img\\"+prod_info.getProd_img());
					ImageView view = new ImageView();
					view.setImage(prod_image);
					view.setFitWidth(150.0);
					view.setFitHeight(100.0);
					
					//상품명 라벨에 담기 
					Label prod_name_lbl = new Label();
					prod_name_lbl.setText(prod_info.getProd_name());
					prod_name_lbl.setPrefWidth(443.0);
					prod_name_lbl.setPrefHeight(100.0);
					prod_name_lbl.setAlignment(Pos.CENTER);
					prod_name_lbl.setFont(Font.font("HCR Dotum", 18));
					
					//상품 이미지 + 상품명 Hbox에 담기 / 사이즈 지정
					HBox image_name = new HBox();
					image_name.setPrefWidth(587.0);
					image_name.setPrefHeight(100.0);
					image_name.getChildren().add(view);
					image_name.getChildren().add(prod_name_lbl);
					
					
					//수량 라벨에 담기
					Label prod_amount_lbl = new Label();
					prod_amount_lbl.setText(cart_list.get(i).getCart_cnt());
					prod_amount_lbl.setPrefWidth(91.0);
					prod_amount_lbl.setPrefHeight(100.0);
					prod_amount_lbl.setAlignment(Pos.CENTER);
					prod_amount_lbl.setFont(Font.font("HCR Dotum", 18));
					
					
					
					//가격 라벨에 담기
					int amount = Integer.parseInt(cart_list.get(i).getCart_cnt());
					int price = Integer.parseInt(prod_info.getPrice());
					Label prod_price_lbl = new Label();
					prod_price_lbl.setText(amount * price +"");
					prod_price_lbl.setPrefWidth(204.0);
					prod_price_lbl.setPrefHeight(100.0);
					prod_price_lbl.setAlignment(Pos.CENTER);
					prod_price_lbl.setFont(Font.font("HCR Dotum", 18));
					
					//총 주문 금액 저장
					all_price += amount * price;
					
					
					//취소버튼 추가 사이즈 77
					FontAwesomeIcon icon = new FontAwesomeIcon();
					icon.setIconName("CLOSE");
					icon.setSize("2.5em");
					JFXButton cancel_btn = new JFXButton();
					cancel_btn.setPrefWidth(75.0);
					cancel_btn.setPrefHeight(100.0);
					cancel_btn.setGraphic(icon);
					cancel_btn.setAlignment(Pos.CENTER);
					cancel_btn.setAccessibleText(prod_info.getProd_num());
					
					
					//HBox 에 출력할 순서대로 담기
					H.getChildren().add(prod_num_lbl);
					H.getChildren().add(image_name);
					H.getChildren().add(prod_amount_lbl);
					H.getChildren().add(prod_price_lbl);
					H.getChildren().add(cancel_btn);
					
					
					//구분선 생성
					//Line division_line = new Line(7.0, 0.0, 3.0, 4.0);
					//division_line.setStyle("-fx-stroke: #393939");
					//division_line.setStrokeWidth(1);
					//division_line.stroke();
					//division_line.setTranslateY(5);
					
					//라인넣기
					HBox line = new HBox();
					line.setPrefWidth(1036.0);
					line.setPrefHeight(2.0);
					line.setStyle("-fx-background-color: #c2c2c2;");
					
					//마지막 생성된 Hbox를 mainbox에 담기
					main_box.getChildren().add(H);
					main_box.getChildren().add(line);
					//main_box.getChildren().add(division_line);
					
					
					//장바구니 삭제 버튼 클릭
					cancel_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
						log_btn = (JFXButton) e.getSource();
						//구매항목 --
						list_size--;
						//총가격 --
						all_price -= (amount*price); 
						
						try {
							Main.gm_u.deleteCart(log_btn.getAccessibleText());
							alertTest(" ' " + prod_info.getProd_name() + " ' 상품 삭제 완료");
							System.out.println(log_btn.getAccessibleText() + " 번 상품 삭제 완료");
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						
						//H.getChildren().clear(); 			//HBox가 남아서 위치조정이안됌
						main_box.getChildren().remove(H); 	//HBox 삭제
						
						//장바구니 삭제 버튼 누를 때마다 총구매가격, 구매항목 개수 업데이트
						Cart_Allprice_Lbl.setText(all_price +"");
						Cart_Kinds_Lbl.setText(list_size + "");
						Cart_Point_Lbl.setText(all_price/20 +"");
						
				
//						//상품 삭제할때 장바구니 비었습니다 메시지 출력
//						if(main_box.getChildren().isEmpty()) {
//							Cart_Main_Pane.getChildren().clear();
//							
//							Cart_Message_Lbl.setText("장바구니가 비었습니다");
//							VBox v = new VBox();
//							v.getChildren().add(Cart_Message_Lbl);
//							v.setPrefWidth(1036);
//							v.setPrefHeight(329);
//							v.setAlignment(Pos.CENTER);
//							Cart_Main_Pane.getChildren().add(v);
//						}
						
					});
					
				}
				//잔여포인트 가져오기
				CustomVO cvo2 = new CustomVO();
				cvo2.setCustom_id(login_id);
				List<CustomVO> customInfo = Main.gm_u.getSearchCustom(cvo2);
				String myPoint = customInfo.get(0).getCustom_point();
				
				Cart_MyPoint_Lbl.setText(myPoint);
				Cart_Allprice_Lbl.setText(all_price +"");
				Cart_Kinds_Lbl.setText(list_size + "");
				Cart_Point_Lbl.setText(all_price/20 +"");
				
				if(!Cart_Main_Pane.getChildren().isEmpty()) {
					Cart_Main_Pane.getChildren().clear();
				}
				//장바구니가 비었을때
				if(cart_list.size() == 0){
					Cart_Message_Lbl.setText("장바구니가 비었습니다");
					VBox v = new VBox();
					v.getChildren().add(Cart_Message_Lbl);
					v.setPrefWidth(1036);
					v.setPrefHeight(329);
					v.setAlignment(Pos.CENTER);
					Cart_Main_Pane.getChildren().add(v);
				}
				
				//메인 판에 장바구니 정보를 담은 메인박스 저장
				Cart_Main_Pane.getChildren().add(main_box);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	}

	
	private void gotoOrder(VBox main_box) {
		
		
		if(list_size == 0) {
			alertTest("결제할 상품이 없습니다");
			
		} else {
			//새창 띄우기
			cart_view_dialog = new Stage(StageStyle.UTILITY);
			
			cart_view_dialog.initModality(Modality.APPLICATION_MODAL);
			//dialog.initOwner();
			cart_view_dialog.setTitle("주문 / 결제 창");
			
			Parent parent = null;
			try {
				//parent = FXMLLoader.load(order_view_controller.class.getResource("order_view.fxml"));
				
				//order_view_controller cont = (order_view_controller) new FXML(getClass().getResource("order_view.fxml"));
				//fxmlLoad =new FXMLLoader(charset)		
						
				
				FXMLLoader loader = new FXMLLoader(Order_view_controller.class.getResource("order_view.fxml"));
				parent = loader.load();
				Order_view_controller cont = loader.getController();
				cont.setcon(this);
						
			} catch(IOException ee) {
				ee.printStackTrace();
			}
			
			
			//결제창의 결제 버튼
//			JFXButton ok = (JFXButton) parent.lookup("#Order_Pay_Btn");
//			ok.setOnAction(e1->{
//				if(cart_view_dialog.isShowing() == false) {
//					//장바구니 목록 출력하는 메인박스 하위노드 전부삭제
//					main_box.getChildren().clear();
//					//장바구니 목록 값, 총구매금액 0 으로 셋팅
//					Cart_Allprice_Lbl.setText("0");
//					Cart_Kinds_Lbl.setText("( 0 개)");
//				}
//				System.out.println(cart_view_dialog.isShowing());
//			});
			
			Scene scene = new Scene(parent);
			
			cart_view_dialog.setScene(scene);
			cart_view_dialog.setResizable(false);//크기고정
			cart_view_dialog.show();
		}
		
	};
	
	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("결제 오류");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}
	

}
