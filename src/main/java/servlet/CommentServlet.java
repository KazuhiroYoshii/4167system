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
 * コメント表示画面への遷移を制御する
 * @author 櫻井
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * タスク一覧画面からの遷移を制御する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));

		// DAOの生成
		CommentDAO commentDao = new CommentDAO();

		UserCategoryStatusTaskBean taskDetail = null;
		List<UserCommentBean> commentList  = new ArrayList<>();
		int processingNumber = 2; // 投稿実行件数
		int deleteResult = 2; // 削除実行件数

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
		// 投稿実行件数をリクエストスコープに設定
		request.setAttribute("processingNumber", processingNumber);
		// 削除実行件数をリクエストスコープに設定
		request.setAttribute("deleteResult", deleteResult);

		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		// タスクIDをセッションスコープに設定
		session.setAttribute("taskId", taskId);

		// 転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("comment.jsp");
		rd.forward(request, response);

	}

	/**
	 * コメント投稿をしたときの遷移を制御する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		String comment = request.getParameter("comment");

		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		// セッションスコープからの属性値の取得
		int taskId = (int) session.getAttribute("taskId");
		String userId = (String) session.getAttribute("userId");

		// 新規コメントをpostCommentにセット
		UserCommentBean postComment = new UserCommentBean();
		postComment.setTaskId(taskId);
		postComment.setUserId(userId);
		postComment.setComment(comment);

		// DAOの生成
		CommentDAO commentDao = new CommentDAO();

		UserCategoryStatusTaskBean taskDetail = null;
		List<UserCommentBean> commentList  = new ArrayList<>();
		int processingNumber = 0; // 投稿実行件数
		int deleteResult = 2; // 削除実行件数

		try {
			// DAOを利用してコメントを追加
			processingNumber = commentDao.insertComment(postComment);
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
		// 投稿実行件数をリクエストスコープに設定
		request.setAttribute("processingNumber", processingNumber);
		// 削除実行件数をリクエストスコープに設定
		request.setAttribute("deleteResult", deleteResult);

		// 転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("comment.jsp");
		rd.forward(request, response);
	}

}
