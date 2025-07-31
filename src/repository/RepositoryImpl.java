package repository;

import java.util.ArrayList;
import java.util.List;

import comon.DBConn;
import entity.MemberVo;

public class RepositoryImpl extends DBConn implements Repository<MemberVo> {

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
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getDepartment());
			pstmt.setInt(3, member.getKor());
			pstmt.setInt(4, member.getEng());
			pstmt.setInt(5, member.getMath());
			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	public List<MemberVo> list() {
		List<MemberVo> list = new ArrayList<>();
		String sql = """
				select mid,name,department,kor,eng,math,mdate from score_member;
				""";
		try {
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVo member = new MemberVo();
				member.setMid(rs.getString(1));
				member.setName(rs.getString(2));
				member.setDepartment(rs.getString(3));
				member.setKor(rs.getInt(4));
				member.setEng(rs.getInt(5));
				member.setMath(rs.getInt(6));
				member.setMdate(rs.getString(7));
				list.add(member);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public MemberVo search(String mid) {
		MemberVo member = null;
		String sql = """
				select mid,name,department,kor,eng,math,mdate from score_member where mid = ?
				""";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new MemberVo();
				member.setMid(rs.getString(1));
				member.setName(rs.getString(2));
				member.setDepartment(rs.getString(3));
				member.setKor(rs.getInt(4));
				member.setEng(rs.getInt(5));
				member.setMath(rs.getInt(6));
				member.setMdate(rs.getString(7));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;

	}
	
	
	public int  update(MemberVo member) {
		int rows = 0;
		String sql = """
				update score_member
					set department = ?,
						kor = ?,
						eng = ?,
						math = ?,
						mdate = now()
					where mid = ?
				""";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, member.getDepartment());
			pstmt.setInt(2, member.getKor());
			pstmt.setInt(3, member.getEng());
			pstmt.setInt(4, member.getMath());
			pstmt.setString(5, member.getMid());
			rows = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rows;
	}
	
	public int  delete(String id) {
		int rows = 0;
		String sql = """
				delete from score_member where mid = ?
				""";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rows =pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rows;
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows;

	}

}
