package kr.or.ddit.rms.admin.goods_mall.sales_management;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.vo.Buy_LogVO;
import kr.or.ddit.rms.vo.CardVO;
import kr.or.ddit.rms.vo.ProdVO;

public class Sales_management_controller implements Initializable {

	@FXML
	Tab All_Tab;
	@FXML
	AnchorPane Sales_All_Pane; // 전체 상품 출력판

	@FXML
	AnchorPane Date_Pane; // 기간별 출력판
	@FXML
	JFXButton Date_Search_Btn; // 날짜 검색 버튼
	@FXML
	DatePicker Start_Date; // 시작 날짜
	@FXML
	DatePicker End_Date; // 종료 날짜

	@FXML
	JFXButton Sales_Chart_Btn; // 차트 버튼
	@FXML
	Label Sales_Price_Lbl; // 총 매출 라벨
	@FXML
	Label Hide_Label; // 숨김 라벨

	@FXML
	JFXComboBox Prod_Comb; // 상품 콤보박스
	@FXML
	JFXButton Prod_Search_Btn; // 상품 검색버튼
	@FXML
	AnchorPane Prod_Pane; // 상품별 검색 판

	@FXML
	JFXComboBox Card_Comb; // 카드 콥보박스
	@FXML
	JFXButton Card_Search_Btn; // 카드 검색 박스
	@FXML
	AnchorPane Card_Pane; // 카드별 출력판

	public static String preDate = "";
	public static String postDate = "";

	VBox mainBox;
	public static List<Buy_LogVO> salesList;
	public static List<Buy_LogVO> showList;
	public static int all_price = 0; // 총 판매 금액 저장 될 변수
	public static int list_size = 0;

	public static int flag = 0;
	String selBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selBtn="0";
		mainBox = new VBox();
		mainBox.setPrefWidth(989.0);

		// 초기 세팅
		List<Buy_LogVO> buy_logAll;
		try {

			buy_logAll = Main.gm_a.getBuy_logAll();
			show_sales_list(buy_logAll);

			Hide_Label.setText("전체 매출 / ");
			showList=buy_logAll;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 상품 콤보 세팅
		List<ProdVO> prodAll;
		List<CardVO> cardAll;
		try {
			// 상품 콤보 세팅
			prodAll = Main.gm_a.getProdAll();
			for (int i = 0; i < prodAll.size(); i++) {
				Prod_Comb.getItems().add(prodAll.get(i).getProd_name());
			}
			// 카드 콤보 세팅
			cardAll = Main.gm_a.getCardAll();
			for (int i = 0; i < cardAll.size(); i++) {
				Card_Comb.getItems().add(cardAll.get(i).getCard_name());
			}
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// 기간별 검색
		Date_Search_Btn.setOnAction(e -> {
			System.out.println(selBtn);
			if (Start_Date.getValue() == null) {
				alertTest("시작날짜를 선택하세요 ");
				return;
			} else if (End_Date.getValue() == null) {
				alertTest("종료날짜를 선택하세요 ");
				return;
			}

			flag = 1;
		});

		dateSearch();

		// 상품별 검색
		Prod_Search_Btn.setOnAction(e -> {
			this.selBtn="2";
			if (Prod_Comb.getValue() == null) {
				alertTest("상품을 선택 해주세여");
				return;
			}

			flag = 2;
			Buy_LogVO vo = new Buy_LogVO();
			vo.setProd_name(Prod_Comb.getValue().toString());
			try {
				List<Buy_LogVO> ProdList = Main.gm_a.getSearchBuy_log(vo);
				showList=Main.gm_a.getBuy_logAll();
				show_sales_list(ProdList);
				Hide_Label.setText(Prod_Comb.getValue().toString() + " 상품 / ");
				alertTest(Prod_Comb.getValue().toString() + " 상품 \n - 총 매출액 : " + all_price);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});

		// card별 검색
		Card_Search_Btn.setOnAction(e -> {
			this.selBtn="3";
			if (Card_Comb.getValue() == null) {
				alertTest("카드사를 선택 해주세요");
				return;
			}

			flag = 3;
			Buy_LogVO vo = new Buy_LogVO();
			vo.setCard_name(Card_Comb.getValue().toString());
			try {
				List<Buy_LogVO> CardList = Main.gm_a.getSearchBuy_log(vo);
				show_sales_list(CardList);
				Hide_Label.setText(Card_Comb.getValue().toString() + "사 / ");
				showList=Main.gm_a.getBuy_logAll();
				alertTest(Card_Comb.getValue().toString() + "\n - 총 매출액 : " + all_price);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});

		All_Tab.setOnSelectionChanged(e -> {
			try {
				flag = 0;
				List<Buy_LogVO> buy_logAll2 = Main.gm_a.getBuy_logAll();
				show_sales_list(buy_logAll2);
				Hide_Label.setText("전체 매출 / ");
				showList=buy_logAll2;
				selBtn="0";
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		Sales_Chart_Btn.setOnAction(e->{
			chartSet();
		});

	}

	private void chartSet() {
		Stage WritePage = new Stage(StageStyle.UTILITY);
		WritePage.initModality(Modality.APPLICATION_MODAL);

		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("chart.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AnchorPane chartMainPane = (AnchorPane) parent.lookup("#chartMainPane");
		switch(selBtn) {
		case "0":
			allLineSet(chartMainPane);
			break;
		case "1":
			selLineSet(chartMainPane);
			break;
		case "2":
			prodBarSet(chartMainPane);
			break;
		case "3":
			cardBarSet(chartMainPane);
			break;
		}
		if(selBtn.equals("0")) {
		}else if(selBtn.equals("2")||selBtn.equals("3")) {
		}
		Scene scene = new Scene(parent);
		WritePage.setTitle("Chart");
		WritePage.setScene(scene);
		WritePage.show();
		
	}

	private void prodBarSet(AnchorPane chartMainPane) {
		NumberAxis yAxis = new  NumberAxis();
		CategoryAxis xAxis = new CategoryAxis();
		BarChart<String, ?> barChart = new BarChart<>(xAxis, yAxis);
		XYChart.Series series = new XYChart.Series<>();
		ArrayList<String> prodList = prodSet();
		HashMap<String, Integer> cashMap = new HashMap<>();
		for (int i = 0; i < prodList.size(); i++) {
			cashMap.put(prodList.get(i), 0);
		}
		for (int i = 0; i < showList.size(); i++) {
			for (int j = 0; j < prodList.size(); j++) {
				if(prodList.get(j).equals(showList.get(i).getProd_name())){
					int addMoney = Integer.parseInt(showList.get(i).getPrice());
					cashMap.put(prodList.get(j), cashMap.get(prodList.get(j))+addMoney);
					break;
				}
			}
		}
		for (int i = 0; i < prodList.size(); i++) {
			series.getData().add(new XYChart.Data(prodList.get(i),cashMap.get(prodList.get(i))));
		}
		barChart.getData().add(series);
		chartMainPane.getChildren().add(barChart);
	}
	private void cardBarSet(AnchorPane chartMainPane) {
		NumberAxis yAxis = new  NumberAxis();
		CategoryAxis xAxis = new CategoryAxis();
		BarChart<String, ?> barChart = new BarChart<>(xAxis, yAxis);
		XYChart.Series series = new XYChart.Series<>();
		ArrayList<String> cardList = cardSet();
		HashMap<String, Integer> cashMap = new HashMap<>();
		for (int i = 0; i < cardList.size(); i++) {
			cashMap.put(cardList.get(i), 0);
		}
		for (int i = 0; i < showList.size(); i++) {
			for (int j = 0; j < cardList.size(); j++) {
				if(cardList.get(j).equals(showList.get(i).getCard_name())){
					int addMoney = Integer.parseInt(showList.get(i).getPrice());
					cashMap.put(cardList.get(j), cashMap.get(cardList.get(j))+addMoney);
					break;
				}
			}
		}
		for (int i = 0; i < cardList.size(); i++) {
			series.getData().add(new XYChart.Data(cardList.get(i),cashMap.get(cardList.get(i))));
		}
		barChart.getData().add(series);
		chartMainPane.getChildren().add(barChart);
	}

	private ArrayList<String> prodSet() {
		ArrayList<String> prodList = new ArrayList<>();
		for (int i = 0; i < showList.size(); i++) {
			String str = showList.get(i).getProd_name();
			if(prodList.isEmpty()) {
				prodList.add(str);
			}else if(!prodList.isEmpty()){
				boolean monthCheck=false;
				for (int j = 0; j < prodList.size(); j++) {
					if(prodList.get(j).equals(str)) {
						monthCheck = true;
						break;
					}
				}
				if(!monthCheck) {
					prodList.add(str);
				}
			}
		}
		return prodList;
	}
	private ArrayList<String> cardSet() {
		ArrayList<String> cardList = new ArrayList<>();
		for (int i = 0; i < showList.size(); i++) {
			String str = showList.get(i).getCard_name();
			if(cardList.isEmpty()) {
				cardList.add(str);
			}else if(!cardList.isEmpty()){
				boolean monthCheck=false;
				for (int j = 0; j < cardList.size(); j++) {
					if(cardList.get(j).equals(str)) {
						monthCheck = true;
						break;
					}
				}
				if(!monthCheck) {
					cardList.add(str);
				}
			}
		}
		return cardList;
	}

	private void selLineSet(AnchorPane chartMainPane) {
		System.out.println("기간별");
		ArrayList<String> dateList = dateSet();
		HashMap<String, Integer> cashMap = new HashMap<>();
		for (int i = 0; i < dateList.size(); i++) {
			cashMap.put(dateList.get(i), 0);
		}
		NumberAxis yAxis = new  NumberAxis();
		CategoryAxis xAxis = new CategoryAxis();
		LineChart<?, ?> lineChart = new LineChart<String, Number>(xAxis,yAxis);
		XYChart.Series series = new XYChart.Series<>();
		for (int i = 0; i < showList.size(); i++) {
			for (int j = 0; j < dateList.size(); j++) {
				if(dateList.get(j).equals(showList.get(i).getBuy_date().substring(0, 10))){
					int addMoney = Integer.parseInt(showList.get(i).getPrice());
					cashMap.put(dateList.get(j), cashMap.get(dateList.get(j))+addMoney);
					break;
				}
			}
		}
		for (int i = 0; i < dateList.size(); i++) {
			series.getData().add(new XYChart.Data(dateList.get(i),cashMap.get(dateList.get(i))));
		}
		lineChart.getData().addAll(series);
		chartMainPane.getChildren().add(lineChart);
	}
	private void allLineSet(AnchorPane chartMainPane) {
		System.out.println(selBtn);
			System.out.println("전체");
			ArrayList<String> monthList = monthSet();
			HashMap<String, Integer> cashMap = new HashMap<>();
			for (int i = 0; i < monthList.size(); i++) {
				cashMap.put(monthList.get(i), 0);
			}
			NumberAxis yAxis = new  NumberAxis();
			CategoryAxis xAxis = new CategoryAxis();
			LineChart<?, ?> lineChart = new LineChart<String, Number>(xAxis,yAxis);
			XYChart.Series series = new XYChart.Series<>();
			for (int i = 0; i < showList.size(); i++) {
				for (int j = 0; j < monthList.size(); j++) {
					if(monthList.get(j).equals(showList.get(i).getBuy_date().substring(0, 7))){
						int addMoney = Integer.parseInt(showList.get(i).getPrice());
						cashMap.put(monthList.get(j), cashMap.get(monthList.get(j))+addMoney);
						break;
					}
				}
			}
			for (int i = 0; i < monthList.size(); i++) {
				series.getData().add(new XYChart.Data(monthList.get(i),cashMap.get(monthList.get(i))));
			}
			lineChart.getData().addAll(series);
			chartMainPane.getChildren().add(lineChart);
		
	}

	private ArrayList<String> monthSet() {
		ArrayList<String> monthList = new ArrayList<>();
		for (int i = 0; i < showList.size(); i++) {
			String str =showList.get(i).getBuy_date().substring(0, 7);
			if(monthList.isEmpty()) {
				monthList.add(str);
			}else if(!monthList.isEmpty()){
				boolean monthCheck=false;
				for (int j = 0; j < monthList.size(); j++) {
					if(monthList.get(j).equals(str)) {
						monthCheck = true;
						break;
					}
				}
				if(!monthCheck) {
					monthList.add(str);
				}
			}
		}
		return monthList;
	}

	private ArrayList<String> dateSet(){
		ArrayList<String> dateList = new ArrayList<>();
		for (int i = 0; i < showList.size(); i++) {
			String str =showList.get(i).getBuy_date().substring(0, 10);
			if(dateList.isEmpty()) {
				dateList.add(str);
			}else if(!dateList.isEmpty()) {
				boolean dateCheck=false;
				for (int j = 0; j < dateList.size(); j++) {
					if(dateList.get(j).equals(str)) {
						dateCheck=true;
						break;
					}
				}
				if(!dateCheck) {
					dateList.add(str);
				}
			}
		}
		return dateList;
		
	}
	private void show_sales_list(List<Buy_LogVO> list) throws RemoteException {
		mainBox.getChildren().clear();
		mainBox = new VBox();
		mainBox.setPrefWidth(989.0);
		all_price = 0;
		Sales_Price_Lbl.setText(all_price + "");

		for (int i = 0; i < list.size(); i++) {

			String buyNum = list.get(i).getBuy_num();
			String prodName = list.get(i).getProd_name();
			String price = list.get(i).getPrice();
			String quan = list.get(i).getBuy_quan();
			String buyDate = list.get(i).getBuy_date();
			String customId = list.get(i).getCustom_id();
			String cardName = list.get(i).getCard_name();
			String state = list.get(i).getRefund_check();

			int int_price = Integer.parseInt(price);
			int int_quan = Integer.parseInt(quan);

			if (state.equals("환불완료")) {
				continue;
			} else if (!state.equals("환불완료")) {
				all_price += int_price * int_quan;
				Sales_Price_Lbl.setText(all_price + "");
			}

			HBox H = new HBox();
			H.setPrefWidth(989.0);
			H.setPrefHeight(20.0);
			H.setAccessibleText(i + "");

			// 상품 번호 담을 라벨 생성 / 사이즈 지정 / 넘버값 넣기
			Label buyNum_lbl = new Label();
			buyNum_lbl.setText(buyNum);
			buyNum_lbl.setPrefWidth(55.0);
			buyNum_lbl.setPrefHeight(20.0);
			buyNum_lbl.setAlignment(Pos.CENTER);
			buyNum_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(buyNum_lbl);

			// 상품명 라벨에 담기
			Label prod_name_lbl = new Label();
			prod_name_lbl.setText(prodName);
			prod_name_lbl.setPrefWidth(222.0);
			prod_name_lbl.setPrefHeight(20.0);
			prod_name_lbl.setAlignment(Pos.CENTER);
			prod_name_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(prod_name_lbl);

			// 거래금액 라벨에 담기
			Label prod_amount_lbl = new Label();
			prod_amount_lbl.setText(int_price * int_quan + "");
			prod_amount_lbl.setPrefWidth(151.0);
			prod_amount_lbl.setPrefHeight(20.0);
			prod_amount_lbl.setAlignment(Pos.CENTER);
			prod_amount_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(prod_amount_lbl);

			// 구매일 라벨에 담기
			Label date_lbl = new Label();
			date_lbl.setText(buyDate.substring(0, 10));
			date_lbl.setPrefWidth(181.0);
			date_lbl.setPrefHeight(20.0);
			date_lbl.setAlignment(Pos.CENTER);
			date_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(date_lbl);

			// 구매ID 라벨에 담기
			Label custom_id_lbl = new Label();
			custom_id_lbl.setText(customId);
			custom_id_lbl.setPrefWidth(117.0);
			custom_id_lbl.setPrefHeight(20.0);
			custom_id_lbl.setAlignment(Pos.CENTER);
			custom_id_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(custom_id_lbl);

			// 카드사 담기
			Label card_name_lbl = new Label();
			card_name_lbl.setText(cardName);
			card_name_lbl.setPrefWidth(129.0);
			card_name_lbl.setPrefHeight(20.0);
			card_name_lbl.setAlignment(Pos.CENTER);
			card_name_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(card_name_lbl);

			// 상태 라벨에 담기
			Label state_lbl = new Label();
			state_lbl.setText(state);
			state_lbl.setPrefWidth(120.0);
			state_lbl.setPrefHeight(20.0);
			state_lbl.setAlignment(Pos.CENTER);
			state_lbl.setFont(Font.font(".Apple SD Gothic NeoI", 15));
			H.getChildren().add(state_lbl);

			HBox line = new HBox();
			line.setPrefWidth(989.0);
			line.setPrefHeight(2.0);
			line.setStyle("-fx-background-color: #c3c3c3;");
			mainBox.getChildren().add(H);
			mainBox.getChildren().add(line);

		}

		if (flag == 0) {
			if (!Sales_All_Pane.getChildren().isEmpty()) {
				Sales_All_Pane.getChildren().clear();
			}

			Sales_All_Pane.getChildren().add(mainBox);
		}

		if (flag == 1) {
			if (!Date_Pane.getChildren().isEmpty()) {
				Date_Pane.getChildren().clear();
			}

			Date_Pane.getChildren().add(mainBox);
		}

		if (flag == 2) {
			if (!Prod_Pane.getChildren().isEmpty()) {
				Prod_Pane.getChildren().clear();
			}

			Prod_Pane.getChildren().add(mainBox);
		}

		if (flag == 3) {
			if (!Card_Pane.getChildren().isEmpty()) {
				Card_Pane.getChildren().clear();
			}

			Card_Pane.getChildren().add(mainBox);
		}

	}

	// 날짜검색
	private void dateSearch() {

		Buy_LogVO bvo = new Buy_LogVO();
		Start_Date.setOnAction(event -> {

			if (Start_Date.getValue() != null) {
				LocalDate date = Start_Date.getValue();
				preDate = date.toString().replaceAll("-", "/");
				preDate = preDate.substring(2, preDate.length());
				bvo.setPre_date(preDate);
				System.out.println(Start_Date.getValue());
				System.out.println(preDate);
			}
			End_Date.setOnAction(event1 -> {
				if (End_Date.getValue() != null) {
					LocalDate date1 = End_Date.getValue();
					postDate = date1.toString().replaceAll("-", "/");
					postDate = postDate.substring(2, postDate.length());
					bvo.setPost_date(postDate);
					System.out.println(End_Date.getValue());
					System.out.println(postDate);
				}
				Date_Search_Btn.setOnAction(e -> {
					flag = 1;
					System.out.println(flag);
					try {
						List<Buy_LogVO> SellList = Main.gm_a.getBetweenSell(bvo);
						show_sales_list(SellList);
						this.selBtn="1";
						Hide_Label.setText(preDate + " ~ " + postDate + " / ");
						alertTest(preDate + " ~ " + postDate + "\n총 매출액 : " + all_price);
						showList=SellList;
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				});
			});
		});
	}

	private void alertTest(String message) {
		Alert alertErorr = new Alert(AlertType.INFORMATION);
		alertErorr.setTitle("결제 오류");
		alertErorr.setContentText(message);
		alertErorr.showAndWait();
	}
}
