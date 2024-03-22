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
 * タスク情報の変更を実行するためのサーブレット
 * @author 吉井
 */
@WebServlet("/TaskAlterExecuteServlet")
public class TaskAlterExecuteServlet extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// セッションオブジェクトの取得
		HttpSession session = request.getSession();

		// 変更前の商品詳細情報をセッションスコープから取得、taskにセット
		UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean) session.getAttribute("task");
		
		//変更後の商品詳細情報をセッションスコープから取得、newTaskにセット
		UserCategoryStatusTaskBean newTask = (UserCategoryStatusTaskBean) session.getAttribute("newTask");
		
		// DAOの生成
		TaskAlterDAO taskAlterDao = new TaskAlterDAO();

		int processingNumber = 0; //処理件数
		String url = "task-alter-failure.jsp"; // 転送先
		UserCategoryStatusTaskBean alteredTask = null;
		
		//変更前データに期限が設定されていない場合、空文字に変換
		String limit = task.getLimitDate();
		if(limit == null) {
			limit = "";
		}
		
		//変更後データに期限が設定されていない場合、空文字に変換
		String newLimit = newTask.getLimitDate();
		if(newLimit == null) {
			newLimit = "";
		}

		try {
			// タスク情報が変更されている場合変更処理を行う
			if (!task.getTaskName().equals(newTask.getTaskName())
					|| task.getCategoryId() != newTask.getCategoryId()
					|| !limit.equals(newLimit)
					|| !task.getUserId().equals(newTask.getUserId())
					|| !task.getStatusCode().equals(newTask.getStatusCode())
					|| !task.getMemo().equals(newTask.getMemo()) ) {
				
				//変更処理を行い、実行件数を取得
				processingNumber = taskAlterDao.update(newTask);
				
				//変更実行後のタスク情報をデータベースから取得
				alteredTask = taskAlterDao.selectTask(newTask.getTaskId());
			}

			//実行件数によってurlを変更
			if (processingNumber == 1) {
				// 転送先の設定
				url = "task-alter-success.jsp";
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープに変更実行後のタスク情報を設定
		request.setAttribute("alteredTask", alteredTask);
		
		// 変更前セッション情報を削除
		session.removeAttribute("task");
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
