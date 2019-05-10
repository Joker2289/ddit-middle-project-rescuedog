package kr.or.ddit.rms.user.mypage.note;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.rms.vo.NoteVO;
import kr.or.ddit.rms.xmls.XmlConnection;

public class NoteDaoImpl implements INoteDao {

	private static NoteDaoImpl dao;

	private SqlMapClient smc;

	private NoteDaoImpl() {
		smc = XmlConnection.getConnection();
	}

	public static NoteDaoImpl getInstance() {
		if (dao == null) {
			dao = new NoteDaoImpl();
		}
		return dao;
	}

	@Override
	public List<NoteVO> getNoteAll() {
		List<NoteVO> list = new ArrayList<NoteVO>();
		try {
			list = smc.queryForList("note.getNoteAll");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<NoteVO> getNoteTo(String note_id_to) {
		List<NoteVO> listTo = new ArrayList<NoteVO>();
		try {
			listTo = smc.queryForList("note.getNoteTo", note_id_to);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTo;
	}

	@Override
	public int deleteNote(NoteVO vo) {
		int cnt = 0; // 지워지는 개수
		try {
			cnt = smc.delete("note.deleteNote", vo);
		} catch (SQLException e) { // delete 성공하면 성공한 레코드 수
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteAllNote(NoteVO vo) {
		int cnt = 0; // 지워지는 개수
		try {
			cnt = smc.delete("note.deleteAllNote", vo);
		} catch (SQLException e) { // delete 성공하면 성공한 레코드 수
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateNote(NoteVO vo) {
		int cnt = 0;
		try {
			cnt = smc.update("note.updateNote", vo);
		} catch (SQLException e) { // delete 성공하면 성공한 레코드 수
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertNote(NoteVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("note.insertNote", vo);
			if (obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
