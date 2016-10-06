package four33.simpleboard.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {

	public static HttpSession getSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if(session == null){
			session = request.getSession(true);
		}
		return session;
	}
	
}
