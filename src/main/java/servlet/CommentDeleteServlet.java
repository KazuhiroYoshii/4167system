package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDAO;
import model.entity.UserCategoryStatusTaskBean;
import model.entity.UserCommentBean;

/**
 * コメントを削除する機能を持つサーブレット
 * @author 吉井
 */
@WebServlet("/CommentDeleteServlet")
public class CommentDeleteServlet extends HttpServlet {
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
	 * コメントを削除したときの遷移を制御する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		//リクエストスコープから削除を実行するコメントIDを取得
		int commentId = Integer.parseInt(request.getParameter("commentId"));

		//セッションオブジェクトを取得、セッションスコープからtaskIdを取得
		HttpSession session = request.getSession();
		int taskId = (int)session.getAttribute("taskId");

		//DAOをインスタンス化
		CommentDAO commentDao = new CommentDAO();

		//commentIdに該当する項目を削除し、実行件数を取得
		int deleteResult = 0;
		try {
			deleteResult = commentDao.deleteComment(commentId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		//タスクの詳細情報、コメントリストをインスタンス化
		UserCategoryStatusTaskBean taskDetail = new UserCategoryStatusTaskBean();
		List<UserCommentBean> commentList  = new ArrayList<>();

		try {
			// DAOを利用して該当タスクの詳細情報を取得
			taskDetail = commentDao.selectTask(taskId);
			// DAOを利用して該当タスクのコメント情報を取得
			commentList = commentDao.selectComment(taskId);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// タスク詳細情報をリクエストスコープに設定
		request.setAttribute("taskDetail", taskDetail);
		// コメント一覧表示用リストをリクエストスコープに設定
		request.setAttribute("commentList", commentList);

		// 削除実行件数をリクエストスコープに設定
		request.setAttribute("deleteResult", deleteResult);

		//登録実行件数を初期化、リクエストスコープに設定
		int processingNumber = 2;
		request.setAttribute("processingNumber", processingNumber);

		// 転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("comment.jsp");
		rd.forward(request, response);
	}

}
