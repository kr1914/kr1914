package com.daily.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.daily.dto.BankAccount;
import com.daily.dto.Customer;
import com.daily.dto.Manager;
import com.daily.dto.MyCompany;
import com.daily.dto.Stock;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class DbAcesse {
	

	
	private static DbAcesse dao;
	private static SqlSession session;
	
	public static DbAcesse getInstance() throws IOException {
		if(dao == null) {
			dao = new DbAcesse();
			InputStream inputStream = Resources.getResourceAsStream("com/mybatis/Mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		}
		return dao;
	}
	

	
	List<Map<String, Object>> check = null;
	int result;
	Boolean dupl;
	MyCompany coInfo;
	Customer custInfo;
	Stock stkInfo;
	BankAccount acctInfo;
	
	
	public List<Map<String, Object>> login (String id, String pwd){
		Map<String, String> userData = new HashMap<String, String>();
		userData.put("id", id);
		userData.put("pwd", pwd);
		check = session.selectList("login", userData);
		
		return check;
	}
//아이디 중복확인
	public Boolean duplicateCheck(String id) {
		
		dupl = session.selectOne("duplCheck", id);
		
		return dupl;
	}
//회사 생성
	public int myCoInsert(MyCompany co) {
		result = session.insert("MyCoInsert", co);
		return result;
	}
//회사 정보 조회
	public MyCompany myCoView(Map<String, Object> co) {
		try {
			coInfo = session.selectOne("MyCoView", co);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return coInfo;
	}
//회사 정보 수정
	public int myCoModify(MyCompany co) {
		result = session.update("MyCoModify", co);
		return result;
	}

//매니저 정보 추가
	public int managerInsert(Manager mgr) {
		result = session.insert("InsertManager", mgr);
		
		return result;
	}
	
//사용자 확인
	public int myCoCheckAdminFree(Map<String, Object> co) {	
		//이메일, key값, 모든 권한 가능
		result = session.selectOne("myCoAdminCheck1", co);
		return result;
	}
	public int myCoCheckManager(Map<String, Object> co) {	
		//이메일, key값, 매니저부터 가능
		result = session.selectOne("myCoAdminCheck2", co);
		return result;
	}
	public int myCoCheckMasterOnly(Map<String, Object> co) {	
		//이메일, key값, 마스터만 가능
		result = session.selectOne("myCoAdminCheck3", co);
		return result;
	}
//거래처 등록
	
	public int custInsert(Customer cs) {
		result = session.insert("custInsert", cs);
		return result;
	}
//거래처 조회
	public List<Map<String, Object>> custListView(String key){
		check = session.selectList("custListView",key);
		return check;
	}
// 거래처 상세조회
	public Customer custViewDetail(Map<String, String> cs) {
		custInfo = session.selectOne("custViewDetail",cs);
		return custInfo;
	}
// 거래처 삭제
	public int custDelete(Map<String, String> cs) {
		result = session.delete("custDelete", cs);
		return result;
	}
// 거래처 정보 수정
	public int custModify(Customer cs) {
		result = session.update("custModify", cs);
		return result;
	}
// 거래처 정보 검색
	public List<Map<String, Object>> custSearchList(Map<String, String> set){
		String t = set.get("option");
		if(t.equals("all")&&!set.get("keyword").equals("")){
			check = session.selectList("custSearchAll", set);
		}else if(set.get("keyword").equals("")) {
			check = session.selectList("custSearchSpace",set);
		}else {
			check = session.selectList("custSearchList", set);
		}
		return check;
	}
//상품 목록 조회
	public List<Map<String, Object>> stkListView(String key){
		check = session.selectList("stkListView",key);
		return check;
	}
//상품 생성
	public int stkInsert(Stock stk) {
		result = session.insert("stkInsert", stk);
		return result;
	}
//상품 상세 조회
	public Stock stkViewDetail(Map<String, String> stk) {
		stkInfo = session.selectOne("stkViewDetail",stk);
		return stkInfo;
	}
//상품 정보 수정
	public int stkModify(Stock stk) {
		result = session.update("stkModify", stk);
		return result;
	}
//상품 정보 삭제
	public int stkDelete(Map<String, String> cs) {
		result = session.delete("stkDelete", cs);
		return result;
	}
//상품 정보 검색
	public List<Map<String, Object>> stkSearchList(Map<String, String> set){
		String t = set.get("option");
		if(t.equals("all")&&!set.get("keyword").equals("")){
			check = session.selectList("stkSearchAll", set);
		}else if(set.get("keyword").equals("")) {
			check = session.selectList("stkSearchSpace",set);
		}else {
			check = session.selectList("stkSearchList", set);
		}
		return check;
	}
//계좌 목록 조회
	public List<Map<String, Object>> acctListView(String key){
		check = session.selectList("acctListView",key);
		return check;
	}
//계좌 생성
	public int acctInsert(BankAccount acct) {
		result = session.insert("acctInsert", acct);
		return result;
	}
//계좌 상세 조회
	public BankAccount acctViewDetail(Map<String, String> stk) {
		acctInfo = session.selectOne("acctViewDetail",stk);
		return acctInfo;
	}
//계좌 정보 수정
	public int acctModify(BankAccount acct) {
		result = session.update("acctModify", acct);
		return result;
	}
//계좌 정보 삭제
	public int acctDelete(Map<String, String> cs) {
		result = session.delete("acctDelete", cs);
		return result;
	}
//계좌 정보 검색
	public List<Map<String, Object>> acctSearchList(Map<String, String> set){
		String t = set.get("option");
		if(t.equals("all")&&!set.get("keyword").equals("")){
			check = session.selectList("acctSearchAll", set);
		}else if(set.get("keyword").equals("")) {
			check = session.selectList("acctSearchSpace",set);
		}else {
			check = session.selectList("acctSearchList", set);
		}
		return check;
	}
		
		
		
		
		
		
		
	
	
	public void commit() {
		session.commit();
		
	}
	public void rollback() {
		session.rollback();
	}
	public void close() {
		session.close();
	}
}
