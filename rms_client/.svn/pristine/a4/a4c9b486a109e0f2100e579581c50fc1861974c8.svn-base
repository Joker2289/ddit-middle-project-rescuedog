package kr.or.ddit.rms.main;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.rms.admin.adopt_board.IAdoptBoardAdminService;
import kr.or.ddit.rms.admin.donation_board.ISponRecruitAService;
import kr.or.ddit.rms.admin.free_board.Free_boardService;
import kr.or.ddit.rms.admin.goods_mall.IGoodsMallAService;
import kr.or.ddit.rms.admin.missing_animal_board.IMissing_animalAService;
import kr.or.ddit.rms.admin.protected_animal_board.IProtectedBoardAdminService;
import kr.or.ddit.rms.admin.review_board.Review_boardService;
import kr.or.ddit.rms.admin.service_center.notice.INoticeAdminService;
import kr.or.ddit.rms.admin.sharing_info_board.Sharing_info_boardService;
import kr.or.ddit.rms.admin.volunteer_board.IVolunteerBoardAdmin_Service;
import kr.or.ddit.rms.mainpage.find_id.IFindIdService;
import kr.or.ddit.rms.mainpage.find_pw.IFindPwService;
import kr.or.ddit.rms.mainpage.login.ILoginService;
import kr.or.ddit.rms.mainpage.sign_up.ISignupService;
import kr.or.ddit.rms.member.MemberService;
import kr.or.ddit.rms.member.board_class.BoardClassService;
import kr.or.ddit.rms.member.comments.CommentsService;
import kr.or.ddit.rms.member.report.BlacklistService;
import kr.or.ddit.rms.question.QuestionService;
import kr.or.ddit.rms.shelter.adopt_board.IAdoptBoardShelService;
import kr.or.ddit.rms.shelter.donation_board.IDonationBoardSService;
import kr.or.ddit.rms.shelter.donation_board.recruit.ISponRecruitSService;
import kr.or.ddit.rms.shelter.free_board.Free_boardSService;
import kr.or.ddit.rms.shelter.missing_animal_board.IMissing_animalSService;
import kr.or.ddit.rms.shelter.mypage.break_away.IBreak_AwaySService;
import kr.or.ddit.rms.shelter.mypage.updateshelter.IUpdateShelterService;
import kr.or.ddit.rms.shelter.protected_animal_board.IProtectedBoardShelterService;
import kr.or.ddit.rms.shelter.review_board.Review_boardSService;
import kr.or.ddit.rms.shelter.service_center.notice.INoticeShelService;
import kr.or.ddit.rms.shelter.sharing_info_board.Sharing_info_boardSService;
//import kr.or.ddit.rms.shelter.adopt_board.IAdoptBoardShelService;
//import kr.or.ddit.rms.shelter.adopt_board.IAdoptBoardShelService;
import kr.or.ddit.rms.shelter.volunteer_board.IVolunteerBoardShelter_Service;
import kr.or.ddit.rms.shelter.volunteer_log.IVolunteerLogShelter_Serivce;
import kr.or.ddit.rms.user.adopt_board.IAdoptBoardUserService;
import kr.or.ddit.rms.user.donation_board.IDonationBoardService;
import kr.or.ddit.rms.user.free_board.Free_boardUService;
import kr.or.ddit.rms.user.goods_mall.IGoodsMallUService;
import kr.or.ddit.rms.user.missing_animal_board.IMissing_animalService;
import kr.or.ddit.rms.user.mypage.activeList.IActiveListService;
import kr.or.ddit.rms.user.mypage.break_away.IBreak_AwayService;
import kr.or.ddit.rms.user.mypage.buylist.IBuyListService;
import kr.or.ddit.rms.user.mypage.note.INoteService;
import kr.or.ddit.rms.user.mypage.updatecustom.IUpdateCustomService;
import kr.or.ddit.rms.user.mypage.volunteer_log.IVolunteerLogMpService;
import kr.or.ddit.rms.user.protected_animal_board.IProtectedBoardUserService;
import kr.or.ddit.rms.user.review_board.Review_boardUService;
import kr.or.ddit.rms.user.service_center.notice.INoticeUserService;
import kr.or.ddit.rms.user.sharing_info_board.Sharing_info_boardUService;
import kr.or.ddit.rms.user.volunteer_board.IVolunteerBoardUser_Service;
import kr.or.ddit.rms.user.volunteer_log.IVolunteerLogUser_Service;

public class Main extends Application {
	
	
	public static Stage StageHome;
	
	//레지스트리
	public static Registry reg;
	
	public static MemberService mems;
	
	// 게시판 조회수 인덱스를 위한 클래스
	public static BoardClassService bcs;
	
	//회원가입
	public static ISignupService ss;
	
	//로그인
	public static ILoginService li;
	
	//아이디찾기
	public static IFindIdService fis;
	
	//PW 찾기
	public static IFindPwService fps;
	
	//보호동물 게시판
    //public static IProtectedBoardService pbs = null;
    
    //쪽지함
    public static INoteService ns = null;
	
	//자유게시판_관리자
	public static Free_boardService fb_a = null;
	
	//자유게시판_보호기관
	public static Free_boardSService fb_s = null;
	
	//자유게시판_유저
	public static Free_boardUService fb_u = null;	
	
	//지식공유게시판 유저
	public static Sharing_info_boardUService sib_u = null;
	
	//지식공유게시판 보호기관
	public static Sharing_info_boardSService sib_s = null;
	
	//지식공유게시판 관리자
	public static Sharing_info_boardService sib_a = null;
	
	//후기게시판 유저
	public static Review_boardUService rb_u = null;
	
	//후기게시판 보호기관
	public static Review_boardSService rb_s =null;
	
	//후기게시판 관리
	public static Review_boardService rb_a = null;
	
	//실종동물 게시판_유저
	public static IMissing_animalService ma_u = null;

	//실종동물 게시판_보호기관
	public static IMissing_animalSService ma_s = null;
	
	//실종동물 게시판_관리자
	public static IMissing_animalAService ma_m = null;
	
	//후원모집 게시판 _유저
	public static IDonationBoardService db_u = null;
	public static IDonationBoardSService db_s = null;
	
	//굿즈몰
	public static IGoodsMallUService gm_u = null;
	public static IGoodsMallAService gm_a = null;
	
    //마이페이지_user
	public static IUpdateCustomService cus = null;
	public static IBreak_AwayService ba = null;
	public static IBuyListService buy = null;
	public static IActiveListService al = null;

	//마이페이지_shelter
	public static IUpdateShelterService up_s = null; 
	public static IBreak_AwaySService ba_s = null;
	
	//자원봉사
	public static IVolunteerBoardUser_Service vbus = null;
	public static IVolunteerBoardShelter_Service vbss = null;
	public static IVolunteerBoardAdmin_Service vbas = null;
	//봉사내역
	public static IVolunteerLogUser_Service vlus = null;
	public static IVolunteerLogShelter_Serivce vlss = null;
	
	//보호동물
	public static IProtectedBoardUserService pbus = null;
	public static IProtectedBoardShelterService pbss = null;
	public static IProtectedBoardAdminService pbas = null;
	
	//입양게시판
	public static IAdoptBoardUserService ab_u = null;
	public static IAdoptBoardShelService ab_s = null;
	public static IAdoptBoardAdminService ab_a = null;
	
	//공지사항
	public static INoticeUserService n_u = null;
	public static INoticeAdminService n_a = null;
	public static INoticeShelService n_s = null;
	
	public static ISponRecruitSService sr_s = null;
	public static ISponRecruitAService sr_a = null;
	
	// 신고하기
	public static BlacklistService bs = null;

	// 댓글
	public static CommentsService cs =null;
	
	public static IVolunteerLogMpService vlmp = null;
	
	public static QuestionService qs = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageHome = primaryStage;

		FXMLLoader loader =
				new FXMLLoader(getClass().getResource("login_page.fxml"));
			Parent root = loader.load();
			
			
			Scene scene = new Scene(root);
			StageHome.setTitle("RMS 메인 화면");
			StageHome.setScene(scene);
			StageHome.show();
			
			//root.requestFocus();

			//컨트롤러 접근 테스트
			//BoardControl cont = loade.getController(); //controller 접근
			
			//lookup 테스트 -> 자손 페이지로넘어 갈 수 있음
			//root.lookup("TableView").requestFocus();	//fxml접근
			//root.lookup("#search_fd").requestFocus();	//fxml접근
			//StageHome.getScene().getRoot().lookup("#search_fd").requestFocus();
			//BoardMain.StageHome.getScene().getRoot().lookup("VBox").requestFocus();
			
			System.out.println("화면 출력 성공");
	}
	
	
	public static void main(String[] args) {
		
		
		try {
			
			reg = LocateRegistry.getRegistry("localhost", 7823);
			
			//look up
			bcs = (BoardClassService) reg.lookup("bcs");
			mems = (MemberService) reg.lookup("mems");
			li = (ILoginService) reg.lookup("li");
			ss = (ISignupService) reg.lookup("ss");
			ns = (INoteService) reg.lookup("note");
			
			//마이페이지
			cus = (IUpdateCustomService) reg.lookup("custom"); 
			ba = (IBreak_AwayService) reg.lookup("ba");
			buy = (IBuyListService) reg.lookup("buy");
			al = (IActiveListService) reg.lookup("al");
			
			
			//마이페이지_shelter
			up_s = (IUpdateShelterService) reg.lookup("up_s");
			ba_s = (IBreak_AwaySService) reg.lookup("ba_s");
			
			fb_a = (Free_boardService) reg.lookup("fb_a");
			fb_s = (Free_boardSService) reg.lookup("fb_s");
			fb_u = (Free_boardUService) reg.lookup("fb_u");
			sib_u = (Sharing_info_boardUService) reg.lookup("sib_u");
			sib_a = (Sharing_info_boardService) reg.lookup("sib_a");
			sib_s = (Sharing_info_boardSService) reg.lookup("sib_s");
			rb_a = (Review_boardService) reg.lookup("rb_a");
			rb_u = (Review_boardUService) reg.lookup("rb_u");
			rb_s = (Review_boardSService) reg.lookup("rb_s");
			
			
			fis = (IFindIdService) reg.lookup("fis");
			fps = (IFindPwService) reg.lookup("fps");
			
			ma_u = (IMissing_animalService) reg.lookup("ma_u");
			ma_s = (IMissing_animalSService) reg.lookup("ma_s");
			ma_m = (IMissing_animalAService) reg.lookup("ma_m");
		
			cs = (CommentsService) reg.lookup("cs");
			bs = (BlacklistService) reg.lookup("bs");
			gm_u = (IGoodsMallUService) reg.lookup("gm_u");
			gm_a = (IGoodsMallAService) reg.lookup("gm_a");
			db_u = (IDonationBoardService) reg.lookup("db_u");
			db_s = (IDonationBoardSService) reg.lookup("db_s");
			
			//자원봉사
			vbus = (IVolunteerBoardUser_Service) reg.lookup("vbus");
			vbss = (IVolunteerBoardShelter_Service) reg.lookup("vbss");
			vbas = (IVolunteerBoardAdmin_Service) reg.lookup("vbas");
			vlus = (IVolunteerLogUser_Service) reg.lookup("vlus");
			vlss = (IVolunteerLogShelter_Serivce) reg.lookup("vlss");
			
			//보호동물
			pbus = (IProtectedBoardUserService) reg.lookup("pbus");
			pbss = (IProtectedBoardShelterService) reg.lookup("pbss");
			pbas = (IProtectedBoardAdminService) reg.lookup("pbas");
			
			//입양게시판 
			ab_u = (IAdoptBoardUserService) reg.lookup("ab_u");
			ab_s = (IAdoptBoardShelService) reg.lookup("ab_s");
			ab_a = (IAdoptBoardAdminService) reg.lookup("ab_a");
			
			//공지사항
			n_u = (INoticeUserService) reg.lookup("n_u");
			n_s = (INoticeShelService) reg.lookup("n_s");
			n_a = (INoticeAdminService) reg.lookup("n_a");
			
			sr_s = (ISponRecruitSService) reg.lookup("sr_s");
			sr_a = (ISponRecruitAService) reg.lookup("sr_a");
			
			vlmp = (IVolunteerLogMpService) reg.lookup("vlmp");
			
			// 1:1 문의
			qs = (QuestionService) reg.lookup("qs");
		
			
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		
		launch(args);
		
	}

}