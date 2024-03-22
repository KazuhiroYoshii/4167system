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

class TaskListDAOTest {

	@Test
	void testGetTaskList() throws SQLException, ClassNotFoundException {
		List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();
		TaskListDAO taskListDAO = new TaskListDAO();
		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		try {
			taskList = taskListDAO.getTaskList();
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("tasklistdao_gettasklist.csv");
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
					// arr = { "comment_id","task_id","user_id","user_name","comment","update_datetime" };
					arr = line.split(",");

				} else {

					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");

					//(期待値、実際の値)
					assertEquals(Integer.parseInt(data[0]), taskList.get(i - 1).getTaskId());
					assertEquals(data[1], taskList.get(i - 1).getTaskName());
					assertEquals(data[2], taskList.get(i - 1).getCategoryName());
					assertEquals(data[3], taskList.get(i - 1).getLimitDate());
					assertEquals(data[4], taskList.get(i - 1).getUserName());
					assertEquals(data[5], taskList.get(i - 1).getUserId());
					assertEquals(data[6], taskList.get(i - 1).getStatusName());
					assertEquals(data[7], taskList.get(i - 1).getMemo());

				}

				//行数のインクリメント
				i++;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	void testGetTaskData() throws SQLException, ClassNotFoundException{
		TaskListDAO taskListDAO = new TaskListDAO();
		UserCategoryStatusTaskBean bean = new UserCategoryStatusTaskBean();

		bean = taskListDAO.getTaskData(1);
		
		//(期待値、実際の値)
		assertEquals(1, bean.getTaskId());
		assertEquals("未着手サンプルタスク", bean.getTaskName());
		assertEquals("新商品A:開発プロジェクト", bean.getCategoryName());
		assertEquals("2024-04-02", bean.getLimitDate());
		assertEquals("山田", bean.getUserName());
		assertEquals("未着手", bean.getStatusName());
		assertEquals("サンプルメモ", bean.getMemo());
	}

}
