package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.UserCategoryStatusTaskBean;

class SelectBoxDAOTest {

	@Test
	void testSelectCategory() {
		List<UserCategoryStatusTaskBean> categoryList = new ArrayList<UserCategoryStatusTaskBean>();
		SelectBoxDAO selectBoxDAO = new SelectBoxDAO();
		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		try {
			categoryList = selectBoxDAO.selectCategory();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("selectboxdao_selectcategory.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//列名を管理する為の配列
			String[] arr = null;
			
			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				
				//先頭行は列名
				if (i == 0) {

					//カンマで分割した内容を配列に格納する
					// arr = { category_id,category_name };
					arr = line.split(",");
			} else {
				
				//カンマで分割した内容を配列に格納する
				String[] data = line.split(",");

				assertEquals(Integer.parseInt(data[0]), categoryList.get(i - 1).getCategoryId());
				assertEquals(data[1], categoryList.get(i - 1).getCategoryName());
				
				}
				//行数のインクリメント
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// csvファイルを閉じる
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	void testSelectUser() {
		List<UserCategoryStatusTaskBean> userList = new ArrayList<UserCategoryStatusTaskBean>();
		SelectBoxDAO selectBoxDAO = new SelectBoxDAO();
		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		try {
			userList = selectBoxDAO.selectUser();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("selectboxdao_selectuser.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//列名を管理する為の配列
			String[] arr = null;
			
			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				
				//先頭行は列名
				if (i == 0) {

					//カンマで分割した内容を配列に格納する
					// arr = { user_id,user_name };
					arr = line.split(",");
			} else {
				
				//カンマで分割した内容を配列に格納する
				String[] data = line.split(",");

				assertEquals(data[0], userList.get(i - 1).getUserId());
				assertEquals(data[1], userList.get(i - 1).getUserName());
				
				}
				//行数のインクリメント
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// csvファイルを閉じる
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	void testSelectStatus() {
		List<UserCategoryStatusTaskBean> statusList = new ArrayList<UserCategoryStatusTaskBean>();
		SelectBoxDAO selectBoxDAO = new SelectBoxDAO();
		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		try {
			statusList = selectBoxDAO.selectStatus();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("selectboxdao_selectstatus.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);
			
			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//列名を管理する為の配列
			String[] arr = null;
			
			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {
				
				//先頭行は列名
				if (i == 0) {

					//カンマで分割した内容を配列に格納する
					// arr = { status_code,status_name };
					arr = line.split(",");
			} else {
				
				//カンマで分割した内容を配列に格納する
				String[] data = line.split(",");

				assertEquals(data[0], statusList.get(i - 1).getStatusCode());
				assertEquals(data[1], statusList.get(i - 1).getStatusName());
				
				}
				//行数のインクリメント
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// csvファイルを閉じる
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
}