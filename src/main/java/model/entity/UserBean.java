package model.entity;

/**
 * ログイン画面で入力する情報を保持するクラス
 * @author 古野
 */
public class UserBean {
	// ユーザID
	private String userID;
	
	// パスワード
	private String password;
	
	// ユーザ名
	private String userName;
	

	/**
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID セットする userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
