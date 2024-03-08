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
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAlterFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));

		// DAOの生成
		TaskAlterFormDAO dao1 = new TaskAlterFormDAO();
		SelectBoxDAO dao2 = new SelectBoxDAO();

		UserCategoryStatusTaskBean task = null;
		List<UserCategoryStatusTaskBean> categoryList = null;
		List<UserCategoryStatusTaskBean> userList = null;
		List<UserCategoryStatusTaskBean> statusList = null;

		try {
			// DAOの利用
			task = dao1.selectTask(taskId);
			categoryList = dao2.selectCategory();
			userList = dao2.selectUser();
			statusList = dao2.selectStatus();

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
