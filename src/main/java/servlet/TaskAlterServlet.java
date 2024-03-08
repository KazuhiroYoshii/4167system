package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskAlterDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク情報の編集処理を制御する
 * @author 櫻井
 */
@WebServlet("/TaskAlterServlet")
public class TaskAlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAlterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// セッションオブジェクトの取得
		HttpSession session = request.getSession();

		// 変更前の商品詳細情報をセッションから取得
		UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean) session.getAttribute("task");

		// リクエストパラメータの取得
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		String taskName = request.getParameter("task_name");
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		String limitDate = request.getParameter("limit_date");
		String userId = request.getParameter("user_id");
		String statusCode = request.getParameter("status_code");
		String memo = request.getParameter("memo");

		// 変更情報をbeanにセット
		UserCategoryStatusTaskBean taskBean = new UserCategoryStatusTaskBean();
		taskBean.setTaskId(taskId);
		taskBean.setTaskName(taskName);
		taskBean.setCategoryId(categoryId);
		taskBean.setLimitDate(limitDate);
		taskBean.setUserId(userId);
		taskBean.setStatusCode(statusCode);
		taskBean.setMemo(memo);

		// DAOの生成
		TaskAlterDAO dao = new TaskAlterDAO();

		int processingNumber = 0; //処理件数
		String url = null; // 転送先
		UserCategoryStatusTaskBean alterTask = null;
		
		String limit = task.getLimitDate();
		if(limit == null) {
			limit = "";
		}

		try {
			// タスク情報が変更されている場合変更処理を行う
			if (!task.getTaskName().equals(taskBean.getTaskName())
					|| task.getCategoryId() != taskBean.getCategoryId()
					|| !taskBean.getLimitDate().equals(limit)
					|| !task.getUserId().equals(taskBean.getUserId())
					|| !task.getStatusCode().equals(taskBean.getStatusCode())
					|| !task.getMemo().equals(taskBean.getMemo()) ) {
				processingNumber = dao.update(taskBean); //変更処理
				alterTask = dao.selectTask(taskId); //変更後のタスク情報取得
			}

			if (processingNumber == 0) {
				// 転送先の設定
				url = "task-alter-failure.jsp";
			} else {
				// 転送先の設定
				url = "task-alter-success.jsp";
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープへの属性の設定
		request.setAttribute("alterTask", alterTask);
		
		// セッション情報を削除
		session.removeAttribute("task");

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
