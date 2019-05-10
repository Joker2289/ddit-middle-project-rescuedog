package kr.or.ddit.rms.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.rms.admin.adopt_board.AdoptBoardAdminServiceImpl;
import kr.or.ddit.rms.admin.adopt_board.IAdoptBoardAdminService;
import kr.or.ddit.rms.admin.donation_board.ISponRecruitAService;
import kr.or.ddit.rms.admin.donation_board.SponRecruitAServiceImpl;
import kr.or.ddit.rms.admin.free_board.Free_boardService;
import kr.or.ddit.rms.admin.free_board.Free_boardServiceImpl;
import kr.or.ddit.rms.admin.goods_mall.GoodsMallAServiceImpl;
import kr.or.ddit.rms.admin.goods_mall.IGoodsMallAService;
import kr.or.ddit.rms.admin.missing_animal_board.IMissing_animalAService;
import kr.or.ddit.rms.admin.missing_animal_board.Missing_animalAServiceImpl;
import kr.or.ddit.rms.admin.protected_animal_board.IProtectedBoardAdminService;
import kr.or.ddit.rms.admin.protected_animal_board.ProtectedBoardAdminServiceImpl;
import kr.or.ddit.rms.admin.review_board.Review_boardService;
import kr.or.ddit.rms.admin.review_board.Review_boardServiceImpl;
import kr.or.ddit.rms.admin.service_center.notice.INoticeAdminService;
import kr.or.ddit.rms.admin.service_center.notice.NoticeAdminServiceImpl;
import kr.or.ddit.rms.admin.sharing_info_board.Sharing_info_boardService;
import kr.or.ddit.rms.admin.sharing_info_board.Sharing_info_boardServiceImpl;
import kr.or.ddit.rms.admin.volunteer_board.IVolunteerBoardAdmin_Service;
import kr.or.ddit.rms.admin.volunteer_board.VolunteerBoardAdmin_ServiceImpl;
import kr.or.ddit.rms.mainpage.find_id.FindIdServiceImpl;
import kr.or.ddit.rms.mainpage.find_id.IFindIdService;
import kr.or.ddit.rms.mainpage.find_pw.FindPwServiceImpl;
import kr.or.ddit.rms.mainpage.find_pw.IFindPwService;
import kr.or.ddit.rms.mainpage.login.ILoginService;
import kr.or.ddit.rms.mainpage.login.LoginServiceImpl;
import kr.or.ddit.rms.mainpage.sign_up.ISignupService;
import kr.or.ddit.rms.mainpage.sign_up.SignupServiceImpl;
import kr.or.ddit.rms.member.MemberService;
import kr.or.ddit.rms.member.MemberServiceImpl;
import kr.or.ddit.rms.member.board_class.BoardClassService;
import kr.or.ddit.rms.member.board_class.BoardClassServiceImpl;
import kr.or.ddit.rms.member.comments.CommentsService;
import kr.or.ddit.rms.member.comments.CommentsServiceImpl;
import kr.or.ddit.rms.member.report.BlacklistService;
import kr.or.ddit.rms.member.report.BlacklistServiceImpl;
import kr.or.ddit.rms.question.QuestionService;
import kr.or.ddit.rms.question.QuestionServiceImpl;
import kr.or.ddit.rms.shelter.adopt_board.AdoptBoardShelServiceImpl;
import kr.or.ddit.rms.shelter.adopt_board.IAdoptBoardShelService;
import kr.or.ddit.rms.shelter.donation_board.DonationBoardSServiceImpl;
import kr.or.ddit.rms.shelter.donation_board.IDonationBoardSService;
import kr.or.ddit.rms.shelter.donation_board.recruit.ISponRecruitSService;
import kr.or.ddit.rms.shelter.donation_board.recruit.SponRecruitSServiceImpl;
import kr.or.ddit.rms.shelter.free_board.Free_boardSService;
import kr.or.ddit.rms.shelter.free_board.Free_boardSServiceImpl;
import kr.or.ddit.rms.shelter.missing_animal_board.IMissing_animalSService;
import kr.or.ddit.rms.shelter.missing_animal_board.Missing_animalSServiceImpl;
import kr.or.ddit.rms.shelter.mypage.break_away.Break_AwaySServiceImpl;
import kr.or.ddit.rms.shelter.mypage.break_away.IBreak_AwaySService;
import kr.or.ddit.rms.shelter.mypage.updateshelter.IUpdateShelterService;
import kr.or.ddit.rms.shelter.mypage.updateshelter.UpdateShelterServiceImpl;
import kr.or.ddit.rms.shelter.protected_animal_board.IProtectedBoardShelterService;
import kr.or.ddit.rms.shelter.protected_animal_board.ProtectedBoardShelterServiceImpl;
import kr.or.ddit.rms.shelter.review_board.Review_boardSService;
import kr.or.ddit.rms.shelter.review_board.Review_boardSServiceImpl;
import kr.or.ddit.rms.shelter.service_center.notice.INoticeShelService;
import kr.or.ddit.rms.shelter.service_center.notice.NoticeShelServiceImpl;
import kr.or.ddit.rms.shelter.sharing_info_board.Sharing_info_boardSService;
import kr.or.ddit.rms.shelter.sharing_info_board.Sharing_info_boardSServiceImpl;
import kr.or.ddit.rms.shelter.volunteer_board.IVolunteerBoardShelter_Service;
import kr.or.ddit.rms.shelter.volunteer_board.VolunteerBoardShelter_ServiceImpl;
import kr.or.ddit.rms.shelter.volunteer_log.IVolunteerLogShelter_Serivce;
import kr.or.ddit.rms.shelter.volunteer_log.VolunteerLogShelter_SerivceImpl;
import kr.or.ddit.rms.user.adopt_board.AdoptBoardUserServiceImpl;
import kr.or.ddit.rms.user.adopt_board.IAdoptBoardUserService;
//import kr.or.ddit.rms.user.adopt_board.AdoptBoardServiceImpl;
//import kr.or.ddit.rms.user.adopt_board.IAdoptBoardService;
import kr.or.ddit.rms.user.donation_board.DonationBoardServiceImpl;
import kr.or.ddit.rms.user.donation_board.IDonationBoardService;
import kr.or.ddit.rms.user.free_board.Free_boardUService;
import kr.or.ddit.rms.user.free_board.Free_boardUServiceImpl;
import kr.or.ddit.rms.user.goods_mall.GoodsMallUServiceImpl;
import kr.or.ddit.rms.user.goods_mall.IGoodsMallUService;
import kr.or.ddit.rms.user.missing_animal_board.IMissing_animalService;
import kr.or.ddit.rms.user.missing_animal_board.Missing_animalServiceImpl;
import kr.or.ddit.rms.user.mypage.activeList.ActiveListServiceImpl;
import kr.or.ddit.rms.user.mypage.activeList.IActiveListService;
import kr.or.ddit.rms.user.mypage.break_away.Break_AwayServiceImpl;
import kr.or.ddit.rms.user.mypage.break_away.IBreak_AwayService;
import kr.or.ddit.rms.user.mypage.buylist.BuyListServiceImpl;
import kr.or.ddit.rms.user.mypage.buylist.IBuyListService;
import kr.or.ddit.rms.user.mypage.note.INoteService;
import kr.or.ddit.rms.user.mypage.note.NoteServiceImpl;
import kr.or.ddit.rms.user.mypage.updatecustom.IUpdateCustomService;
import kr.or.ddit.rms.user.mypage.updatecustom.UpdateCustomServiceImpl;
import kr.or.ddit.rms.user.mypage.volunteer_log.IVolunteerLogMpService;
import kr.or.ddit.rms.user.mypage.volunteer_log.VolunteerLogMp_ServiceImpl;
import kr.or.ddit.rms.user.protected_animal_board.IProtectedBoardUserService;
import kr.or.ddit.rms.user.protected_animal_board.ProtectedBoardUserServiceImpl;
import kr.or.ddit.rms.user.review_board.Review_boardUService;
import kr.or.ddit.rms.user.review_board.Review_boardUServiceImpl;
import kr.or.ddit.rms.user.service_center.notice.INoticeUserService;
import kr.or.ddit.rms.user.service_center.notice.NoticeUserServiceImpl;
import kr.or.ddit.rms.user.sharing_info_board.Sharing_info_boardUService;
import kr.or.ddit.rms.user.sharing_info_board.Sharing_info_boardUServiceImpl;
import kr.or.ddit.rms.user.volunteer_board.IVolunteerBoardUser_Service;
import kr.or.ddit.rms.user.volunteer_board.VolunteerBoardUser_ServiceImpl;
import kr.or.ddit.rms.user.volunteer_log.IVolunteerLogUser_Service;
import kr.or.ddit.rms.user.volunteer_log.VolunteerLogUser_ServiceImpl;

public class RemoteMain {

	public static void main(String[] args) {

		try {
			MemberService mems = new MemberServiceImpl();

			// 게시판 시퀀스를 위한 보드 클래스 서비스
			BoardClassService bcs = new BoardClassServiceImpl();
			// 댓글
			CommentsService cs = new CommentsServiceImpl();

			BlacklistService bs = new BlacklistServiceImpl();

			ILoginService li = new LoginServiceImpl();

			ISponRecruitSService sr_s = new SponRecruitSServiceImpl();
			ISponRecruitAService sr_a = new SponRecruitAServiceImpl();
			
			Free_boardService fb_a = new Free_boardServiceImpl();
			Free_boardUService fb_u = new Free_boardUServiceImpl();
			Free_boardSService fb_s = new Free_boardSServiceImpl();
			Sharing_info_boardUService sib_u = new Sharing_info_boardUServiceImpl();
			Sharing_info_boardSService sib_s = new Sharing_info_boardSServiceImpl();
			Sharing_info_boardService sib_a = new Sharing_info_boardServiceImpl();
			Review_boardService rb_a = new Review_boardServiceImpl();
			Review_boardUService rb_u = new Review_boardUServiceImpl();
			Review_boardSService rb_s = new Review_boardSServiceImpl();
			// 보호동물게시판
			IProtectedBoardUserService pbus = new ProtectedBoardUserServiceImpl();
			IProtectedBoardShelterService pbss = new ProtectedBoardShelterServiceImpl();
			IProtectedBoardAdminService pbas = new ProtectedBoardAdminServiceImpl();

			INoteService ns = new NoteServiceImpl();
			IFindIdService fis = new FindIdServiceImpl();
			// 입양게시판
			IAdoptBoardUserService ab_u = new AdoptBoardUserServiceImpl();
			IAdoptBoardShelService ab_s = new AdoptBoardShelServiceImpl();
			IAdoptBoardAdminService ab_a = new AdoptBoardAdminServiceImpl();

			IDonationBoardService ds = new DonationBoardServiceImpl();
			ISignupService ss = new SignupServiceImpl();
			IFindPwService fps = new FindPwServiceImpl();
			// 자원봉사
			IVolunteerBoardUser_Service vbus = new VolunteerBoardUser_ServiceImpl();
			IVolunteerBoardAdmin_Service vbas = new VolunteerBoardAdmin_ServiceImpl();
			IVolunteerBoardShelter_Service vbss = new VolunteerBoardShelter_ServiceImpl();

			IVolunteerLogUser_Service vlus = new VolunteerLogUser_ServiceImpl();
			IVolunteerLogShelter_Serivce vlss = new VolunteerLogShelter_SerivceImpl();
			
			IVolunteerLogMpService vlmp = new VolunteerLogMp_ServiceImpl();

			// 마이페이지_user
			IUpdateCustomService cus = new UpdateCustomServiceImpl();
			IBreak_AwayService ba = new Break_AwayServiceImpl();
			IBuyListService buy = new BuyListServiceImpl();
			IActiveListService al = new ActiveListServiceImpl();

			// 마이페이지_shelters
			IUpdateShelterService up_s = new UpdateShelterServiceImpl();
			IBreak_AwaySService ba_s = new Break_AwaySServiceImpl();
			
			
			// 실종동물 게시판_user
			IMissing_animalService ma_u = new Missing_animalServiceImpl();
			// 실종동물 게시판_shelter
			IMissing_animalSService ma_s = new Missing_animalSServiceImpl();
			//실종동물 게시판_manager
			IMissing_animalAService ma_m = new Missing_animalAServiceImpl();
			
			// 굿즈몰
			IGoodsMallUService gm_u = new GoodsMallUServiceImpl();
			IGoodsMallAService gm_a = new GoodsMallAServiceImpl();

			// 후원모집 게시판
			IDonationBoardService db_u = new DonationBoardServiceImpl();
			IDonationBoardSService db_s = new DonationBoardSServiceImpl();
			
			

			// 공지사항
			INoticeUserService n_u = new NoticeUserServiceImpl();
			INoticeAdminService n_a = new NoticeAdminServiceImpl();
			INoticeShelService n_s = new NoticeShelServiceImpl();
			QuestionService qs = new QuestionServiceImpl();

			// registry 등록할때 주의사항
			// 1. alias명 짧게 줄것!!!
			// 2. 길게 설정한사람들 다시 짧게 수정!!
			// 3. ex) FindIdService -> fis 단어 앞글자 따서 생성
			// 이유 -> registry가 인식을 못해서 NotBoundException 발생해요!!

			Registry rg = LocateRegistry.createRegistry(7823);
			rg.rebind("bcs", bcs);
			rg.rebind("li", li);

			// 커뮤니티
			rg.rebind("fb_s", fb_s);
			rg.rebind("fb_a", fb_a);
			rg.rebind("fb_u", fb_u);
			rg.rebind("sib_u", sib_u);
			rg.rebind("sib_s", sib_s);
			rg.rebind("sib_a", sib_a);
			rg.rebind("rb_a", rb_a);
			rg.rebind("rb_u", rb_u);
			rg.rebind("rb_s", rb_s);

			rg.rebind("note", ns);
			rg.rebind("fis", fis);

			// 입양하기
			rg.rebind("ab_u", ab_u);
			rg.rebind("ab_s", ab_s);
			rg.rebind("ab_a", ab_a);

			rg.rebind("ds", ds);
			rg.rebind("ss", ss);
			rg.rebind("fps", fps);
			
			//실종동물찾기
			rg.rebind("ma_u", ma_u);
			rg.rebind("ma_s", ma_s);
			rg.rebind("ma_m", ma_m);
			
			rg.rebind("cs", cs); // 댓글
			rg.rebind("bs", bs); // 신고하기
			rg.rebind("mems", mems); // 신고하기

			rg.rebind("db_u", db_u); // 후원모집게시판
			rg.rebind("db_s", db_s); // 후원모집게시판

			// 자원봉사
			rg.rebind("vbus", vbus);
			rg.rebind("vbas", vbas);
			rg.rebind("vbss", vbss);

			rg.rebind("vlus", vlus);
			rg.rebind("vlss", vlss);
			// 보호동물
			rg.rebind("pbus", pbus);
			rg.rebind("pbss", pbss);
			rg.rebind("pbas", pbas);

			// 마이페이지
			rg.rebind("custom", cus);
			rg.rebind("ba", ba);
			rg.rebind("buy", buy);
			rg.rebind("al", al);
			
			rg.rebind("up_s", up_s);
			rg.rebind("ba_s", ba_s);

			// 굿즈몰
			rg.rebind("gm_u", gm_u);
			rg.rebind("gm_a", gm_a);

			// 공지사항
			rg.rebind("n_u", n_u);
			rg.rebind("n_a", n_a);
			rg.rebind("n_s", n_s);

			rg.rebind("sr_s", sr_s);
			rg.rebind("sr_a", sr_a);
			
			rg.rebind("vlmp", vlmp);
			
			// 1 : 1 문의
			rg.rebind("qs", qs);

			System.out.println("성공 : " + rg);

		} catch (RemoteException e) {
			System.out.println("오류");
			e.printStackTrace();
		}

	}
}