package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ChangeDAO;
import model.dao.SelectBoxDAO;
import model.dao.TaskAddDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク登録機能のサーブレット
 * @author 森田
 */
/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/TaskAddServlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * タスク登録画面の表示の際に使用するカテゴリ情報、担当者情報、ステータス情報を渡す
	 * メニュー画面からタスク登録画面に遷移する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// カテゴリ情報を格納するリスト型の変数
		List<UserCategoryStatusTaskBean> CategoryList = null;
		// 担当者情報を格納するリスト型の変数
		List<UserCategoryStatusTaskBean> UserList = null;
		// ステータス情報を格納するリスト型の変数
		List<UserCategoryStatusTaskBean> StatusList = null;

		// DAOのインスタンス化
		SelectBoxDAO selectBoxDao = new SelectBoxDAO();

		try {
			// プルダウン用のカテゴリ一覧を取得
			// カテゴリ情報
			CategoryList = selectBoxDao.selectCategory();
			// 担当者情報
			UserList = selectBoxDao.selectUser();
			// ステータス情報
			StatusList = selectBoxDao.selectStatus();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープへの属性の設定
		request.setAttribute("CategoryList", CategoryList);
		request.setAttribute("UserList", UserList);
		request.setAttribute("StatusList", StatusList);

		// 商品登録画面への転送
		RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * タスク登録画面からタスク情報を取得し、DB上で登録する。
	 * タスク登録画面からタスク登録完了画面もしくはタスク登録失敗画面に遷移する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// DAOのインスタンス化
		TaskAddDAO taskAddDao = new TaskAddDAO();
		ChangeDAO changeDao = new ChangeDAO();
		
		// Beanのインスタンス化
		UserCategoryStatusTaskBean taskInfo = new UserCategoryStatusTaskBean();

		// リクエストパラメータを取得しbeanにセット
		taskInfo.setTaskName(request.getParameter("task_name"));
		taskInfo.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
		taskInfo.setLimitDate(request.getParameter("limit_date"));
		taskInfo.setUserId(request.getParameter("user_id"));
		taskInfo.setStatusCode(request.getParameter("status_code"));
		taskInfo.setMemo(request.getParameter("memo"));

		// メソッドの処理件数
		int processingNumber = 0;
		//カテゴリ名を設定
		String categoryName = null;
		//ユーザ名を設定
		String userName = null;
		//ステータス名を設定
		String statusName = null;
		
		try {
			// DAOの利用
			processingNumber = taskAddDao.insertTask(taskInfo); //登録処理

			// カテゴリ名を取得しbeanにセット
			categoryName = changeDao.categoryChange(taskInfo.getCategoryId());
			taskInfo.setCategoryName(categoryName);
			
			// ユーザ名を取得しbeanにセット
			userName = changeDao.userChange(taskInfo.getUserId());
			taskInfo.setUserName(userName);
			
			// ステータス名を取得しbeanにセット
			statusName = changeDao.statusChange(taskInfo.getStatusCode());
			taskInfo.setStatusName(statusName);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 転送先
		String url = "";
		// 遷移先画面の分岐
		if (processingNumber > 0) {
			// 登録成功画面
			url = "task-register-success.jsp"; 
		} else {
			// 登録失敗画面
			url = "task-register-failure.jsp";
		}

		// リクエストスコープへの属性の設定
		request.setAttribute("taskInfo", taskInfo);
		

		// 画面遷移
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
