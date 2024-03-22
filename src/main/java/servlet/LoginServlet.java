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

import model.dao.LoginDAO;
import model.entity.UserBean;

/**
 * @author 古野
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
	 * @author 古野
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//UserBeanのインスタンスを生成
		UserBean user = null;
		
		//リクエストパラメータの取得
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// DAOの生成
		LoginDAO loginDao = new LoginDAO();
		
		try {
			// DAOの利用
			user = loginDao.selectName(userId, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String userName = user.getUserName();

		// リクエストの転送
		if(userName == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
			rd.forward(request, response);
		}else {
			// セッションスコープへの属性の設定
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("userId", userId);
			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
			rd.forward(request, response);
		}
	}

}
