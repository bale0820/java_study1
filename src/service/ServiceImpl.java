package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.MgmSystem;
import comon.DBConn;
import entity.MemberVo;
import repository.Repository;
import repository.RepositoryImpl;

public class ServiceImpl implements Service {
	Scanner scan;
	MgmSystem ms;
	Repository<MemberVo> rp;

	public ServiceImpl(MgmSystem sms) {
		this.ms = sms;
		this.scan = sms.scan;
		rp = new RepositoryImpl();
	}

	public List createMemberInfo() {
		String[] labels = { "학생명", "전공", "국어", "영어", "수학" };
		List memberInfo = new ArrayList();
//		Random rd = new Random();
//		String no = "2025-" + rd.nextInt(1000, 9999); // 학번 생성
//		memberInfo.add(no);

		for (int i = 0; i < labels.length; i++) {
			if (i >= 2) {
				System.out.print(labels[i] + "> ");
				memberInfo.add(scan.nextInt());
			} else {
				System.out.print(labels[i] + "> ");
				memberInfo.add(scan.next());
			}
		}
		return memberInfo;
	}
	

	public void register() {
		List memberInfo = createMemberInfo();
		MemberVo member = new MemberVo();
		member.setName((String) memberInfo.get(0));
		member.setDepartment((String) memberInfo.get(1));
		member.setKor((int) memberInfo.get(2));
		member.setEng((int) memberInfo.get(3));
		member.setMath((int) memberInfo.get(4));

		int rows = rp.insert(member);
		if (rows != 0) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		ms.showMenu();
		ms.selectMenu();
		
	}
	
	public void list() {
		List<MemberVo> list = rp.list();
		
		list.forEach((member) -> {
			System.out.print(member.getMid()+"\t");
			System.out.print(member.getName()+"\t");
			System.out.print(member.getDepartment()+"\t");
			System.out.print(member.getKor()+"\t");
			System.out.print(member.getEng()+"\t");
			System.out.print(member.getMath()+"\t");
			System.out.print(member.getMdate()+"\n");
		});
		ms.showMenu();
		ms.selectMenu();
		
	}
	public void search() {
		System.out.println("찾으시는 학생의 id를 입력해주세요");
		MemberVo member = rp.search(scan.next());
		if(member != null) {
			System.out.print(member.getMid()+"\t");
			System.out.print(member.getName()+"\t");
			System.out.print(member.getDepartment()+"\t");
			System.out.print(member.getKor()+"\t");
			System.out.print(member.getEng()+"\t");
			System.out.print(member.getMath()+"\t");
			System.out.print(member.getMdate()+"\n");
		}else {
			System.out.println("조회된 결과가 없습니다.");
		}
		ms.showMenu();
		ms.selectMenu();
	}
	
	public void update() {
		System.out.println("수정할려는 학생의 mid를 입력해주세요");
		MemberVo member = rp.search(scan.next());
		if(member != null) {
			System.out.println("수정할려는 학생의 부서");
			member.setDepartment(scan.next());
			System.out.println("수정할려는 학생의 국어점수");
			member.setKor(scan.nextInt());
			System.out.println("수정할려는 학생의 영어점수");
			member.setEng(scan.nextInt());
			System.out.println("수정할려는 학생의 수학점수");
			member.setMath(scan.nextInt());
			
		}else {
			System.out.println("찾으시는 학생이 존재하지 않습니다.");
		}
		int rows = rp.update(member);
		if(rows != 0) {
			System.out.println("수정 완료!!");
		}else {
			System.out.println("수정 실패!!");
		}
		ms.showMenu();
		ms.selectMenu();
		
	}
	
	
	public void delete() {
		System.out.println("삭제할려는 학생의 mid를 입력해주세요");
		MemberVo member = rp.search(scan.next());
		if(member != null) {
			
			int rows = rp.delete(member.getMid());
			
			if(rows != 0) {
				System.out.println("삭제 완료!");
			}else {
				System.out.println("삭제 실패!");
			}
		}else {
			System.out.println("찾으시는 학생이 존재하지 않습니다.");
		}
		ms.showMenu();
		ms.selectMenu();
	}

	public int getCount() {
		int rows = 0;
		rows = rp.getCount();
		return rows;
	};
	
	public void exit() {
		System.out.println("시스템을 종료합니다");
		System.exit(0);
	}

}
