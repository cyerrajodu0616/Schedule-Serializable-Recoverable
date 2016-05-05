package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testclass.ParsingInput;
import com.testclass.ParsingInputException;
import com.testclass.ResultObject;
import com.testclass.SerializationException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputSchedule = request.getParameter("user");
		//ParsingInput pi = new ParsingInput();
		if (inputSchedule == null || inputSchedule.isEmpty()) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Error!!!</title>");
	         out.println("</head>");
	          out.println("<body>");
	          out.println("<h1>Empty schedule is not accepted</h1>");
	            out.println("</body>");
	            out.println("</html>");
	            return;
		}
		ResultObject ro=null;
		try {
			ro = ParsingInput.getTransaction(inputSchedule);
			/*response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Result!!!</title>");
	         out.println("</head>");
	          out.println("<body>");
	          String msg = ro.getMessage();
	          out.println("<h1>"+msg+"</h1>");
	            out.println("</body>");
	            out.println("</html>");*/
	            RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/Second.jsp");
	            request.setAttribute("ro", ro);
	    		rd.forward(request, response);
	    		
	            return;
		} catch (ParsingInputException pe) {
			// TODO Auto-generated catch block
			/*response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Error!!!</title>");
	         out.println("</head>");
	          out.println("<body>");
	          String str = pe.getExceptionMessage();
	          out.println("<h1>"+str+"</h1>");
	            out.println("</body>");
	            out.println("</html>");*/
	            
	            //return;
		}catch ( SerializationException e){
			/*response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Error!!!</title>");
	         out.println("</head>");
	          out.println("<body>");
	          String str = e.getExceptionMessage();
	          out.println("<p>"+str+"</p>");
	            out.println("</body>");
	            out.println("</html>");
	            return;*/
		}catch ( Exception e){
			/*response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Error!!!</title>");
	         out.println("</head>");
	          out.println("<body>");
	          String str = e.getLocalizedMessage();
	          out.println("<p>"+str+"</p>");
	            out.println("</body>");
	            out.println("</html>");
	            return;*/
		}
		/*request.setAttribute("cycleStack", ro.getNodes());
		request.setAttribute("adjacencyMatrix", ro.getAdjacecyMatrix());
		request.setAttribute("message", ro.getMessage());
		request.setAttribute("flag", ro.isFlag());*/
		
		
		//request.getRequestDispatcher("").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
