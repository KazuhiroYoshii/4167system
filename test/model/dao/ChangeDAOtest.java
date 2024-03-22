package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ChangeDAOtest {
	
	@Test
	void test1CategoryChange() {
		ChangeDAO changeDao = new ChangeDAO();
		
		//実際に取得する値をnullで初期化
		String actual = null;
		
		try {
			//データベースに存在するカテゴリIDを引数に設定
			actual = changeDao.categoryChange(1);
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//期待値を変数に代入
		String expected = "新商品A:開発プロジェクト";
		
		//期待される結果と実際の結果が同じかどうか判定する
		assertEquals(expected, actual);
	}
	
	@Test
	void test2CategoryChange() {
		ChangeDAO changeDao = new ChangeDAO();
		
		//実際に取得する値をnullで初期化
		String actual = null;
		
		try {
			//データベースに存在しないカテゴリIDを引数に設定
			actual = changeDao.categoryChange(0);
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//実際の値がnullかどうか判定する
		assertNull(actual);
	}
	
	@Test
	void test1UserChange() {
		ChangeDAO changeDao = new ChangeDAO();
		
		//実際に取得する値をnullで初期化
		String actual = null;
		
		try {
			//データベースに存在するユーザIDを引数に設定
			actual = changeDao.userChange("admin");
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//期待されるユーザ名と実際のユーザ名が同じかどうか判定する
		assertEquals("山田", actual);
	}
	
	@Test
	void test2UserChange() {
		ChangeDAO changeDao = new ChangeDAO();
		
		//実際に取得する値をnullで初期化
		String actual = null;
		
		try {
			//データベースに存在しないユーザIDを引数に設定
			actual = changeDao.userChange("admin1");
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//実際の値がnullかどうか判定する
		assertNull(actual);
	}
	
	@Test
	void test1StatusChange() {
		ChangeDAO changeDao = new ChangeDAO();
		
		//実際に取得する値をnullで初期化
		String actual = null;
		
		try {
			//データベースに存在するステータスIDを引数に設定
			actual = changeDao.statusChange("00");
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//期待されるステータス名と実際のステータス名が同じかどうか判定する
		assertEquals("未着手", actual);
	}
	
	@Test
	void test2StatusChange() {
		ChangeDAO changeDao = new ChangeDAO();
		
		//実際に取得する値をnullで初期化
		String actual = null;
		
		try {
			//データベースに存在しないステータスIDを引数に設定
			actual = changeDao.statusChange("10");
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//実際の値がnullかどうか判定する
		assertNull(actual);
	}

}
