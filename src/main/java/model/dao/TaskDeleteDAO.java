package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * タスク削除機能で使用するDAO
 * @author 吉井
 */
public class TaskDeleteDAO {
	
	/**
	 * 削除実行用メソッド
	 * @param taskId 削除したいタスクのID
	 * @return 該当レコードの削除実行件数
	 */
	public int delete(int taskId) {
		
		//実行件数を0で初期化
		int result = 0;
		
		String sql = "DELETE\n"
				+ "    FROM t_task\n"
				+ "WHERE\n"
				+ "    task_id = ?";
		
		try {
			//データベースへの接続、sql文の用意
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				
				//プレースホルダに値を設定、実行
				pstmt.setInt(1, taskId);
				result = pstmt.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * タスクに付けられたコメント数を取得するメソッド
	 * @param taskId タスクID
	 * @return タスクIDに対応するt_commentテーブルのレコード数
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int countComments(int taskId) throws ClassNotFoundException, SQLException {
		
		//コメント数を0で初期化
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM t_comment WHERE task_id = ?";
		
		//データベースへの接続、SQL文の用意
		try(Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
			
			//プレースホルダに値を設定、実行
			pstmt.setInt(1, taskId);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			//取得データの1カラム目にある数値を取得
			result = rs.getInt(1);
		}
		
		return result;
	}
	
	/**
	 * タスクIDに該当するタスクに付随するコメントを全て削除するメソッド
	 * @param taskId タスクID
	 * @return コメントの削除実行件数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteAllComments(int taskId) throws ClassNotFoundException, SQLException {
		
		//削除実行件数を0で初期化
		int result = 0;
		
		String sql = "DELETE\n"
				+ "    FROM t_comment\n"
				+ "WHERE\n"
				+ "    task_id = ?";
		
		//データベースへの接続、SQL文の用意
		try(Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
			
			//プレースホルダに値を設定、実行
			pstmt.setInt(1, taskId);
			result = pstmt.executeUpdate();
		}
		
		return result;
	}

}
