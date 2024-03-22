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
import javax.servlet.http.HttpSession;

import model.dao.SelectBoxDAO;
import model.dao.TaskAlterFormDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク編集画面への遷移を制御する
 * @author 櫻井
 */
@WebServlet("/TaskAlterFormServlet")
public class TaskAlterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 不正なアクセスがあったときの遷移を制御する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * タスク編集をしたときの遷移を制御する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));

		// DAOの生成
		TaskAlterFormDAO taskAlterFormDao = new TaskAlterFormDAO();
		SelectBoxDAO selectBoxDao = new SelectBoxDAO();

		UserCategoryStatusTaskBean task = null;
		List<UserCategoryStatusTaskBean> categoryList = null;
		List<UserCategoryStatusTaskBean> userList = null;
		List<UserCategoryStatusTaskBean> statusList = null;

		try {
			// DAOを利用して該当タスクの詳細情報を取得
			task = taskAlterFormDao.selectTask(taskId);
			
			// DAOを利用してフォームに表示する各選択肢のデータを取得
			categoryList = selectBoxDao.selectCategory();
			userList = selectBoxDao.selectUser();
			statusList = selectBoxDao.selectStatus();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// セッションオブジェクトの取得
		HttpSession session = request.getSession();

		// セッションスコープへの属性の設定
		session.setAttribute("task", task);

		// リクエストスコープへの属性の設定
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("userList", userList);
		request.setAttribute("statusList", statusList);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-form.jsp");
		rd.forward(request, response);
	}

}
