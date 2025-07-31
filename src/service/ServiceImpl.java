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
		this.ms = ms;
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
	}
	
	public void list() {
		
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
