package repository;

import java.util.ArrayList;
import java.util.List;

import comon.DBConn;
import entity.MemberVo;

public class RepositoryImpl extends DBConn implements Repository<MemberVo>{
	
	
	public RepositoryImpl() {
		super();
	}

	public int insert(MemberVo member) {
		int rows = 0;
		String sql = """
				insert into score_member(name,department,kor,eng,math,mdate)
						values(?,?,?,?,?,now());
				""";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1,member.getName());
			pstmt.setString(2, member.getDepartment());
			pstmt.setInt(3, member.getKor());
			pstmt.setInt(4, member.getEng());
			pstmt.setInt(5, member.getMath());
			rows = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public List<MemberVo> list() {
		List<MemberVo> list = new ArrayList<>();
			
		return list;
	}
	
	
	public int getCount() {
		int rows = 0;
		String sql = """
				select count(*) from score_member;
				""";
		try {
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			rows = rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rows;
		
	}
	
}
