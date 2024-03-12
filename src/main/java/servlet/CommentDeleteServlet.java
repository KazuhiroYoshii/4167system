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
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
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
		
		//リクエストスコープから削除を実行するコメントIDを取得
		int commentId = (int)request.getAttribute("commentId");
		
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

		// 転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("comment.jsp");
		rd.forward(request, response);
	}

}
