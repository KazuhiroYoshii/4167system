package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * カテゴリ情報、ユーザ情報、ステータス情報を取得するDAO
 * @author 森田
 */
public class CategoryUserStatusChangeDAO {

	/**
	 * カテゴリIDからカテゴリ名を取得する
	 * @param categoryId 取得したいカテゴリID
	 * @return categoryName 対象のカテゴリ名が存在しない場合、nullを返す。
	 * @throws SQLException 
	 * @throws ClassNotFoundException
	 */
	public String categoryChange(int categoryId) throws SQLException, ClassNotFoundException {

		// 戻り値
		String categoryName = null;
		String sql = "SELECT category_name FROM m_category WHERE category_id = ?";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, categoryId);
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				categoryName = res.getString("category_name");
			}
		}
		return categoryName;
	}
	
	/**
	 * ユーザIDからユーザ名を取得する
	 * @param userId 取得したいユーザID
	 * @return userName 対象のユーザ名が存在しない場合、nullを返す。
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public String userChange(String userId) throws SQLException, ClassNotFoundException {

		// 戻り値
		String userName = null;
		String sql = "SELECT user_name FROM m_user WHERE user_id = ?";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				userName = res.getString("user_name");
			}	
		}
		return userName;
	}
	
	/**
	 * ステータスコードからステータス名を取得する
	 * @param statusCode 取得したいステータスコード
	 * @return statusName 対象のステータス名が存在しない場合、nullを返す。
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String statusChange(String statusCode)throws SQLException, ClassNotFoundException {

		// 戻り値
		String statusName = null;
		String sql = "SELECT status_name FROM m_status WHERE status_code = ?";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, statusCode);
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				statusName = res.getString("status_name");
			}	
		}
		return statusName;
	}
}
