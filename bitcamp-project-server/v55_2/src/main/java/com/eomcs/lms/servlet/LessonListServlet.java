package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/list")
public class LessonListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      LessonService lessonService = iocContainer.getBean(LessonService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>강의 목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1>강의</h1>");
      out.println("  <a href='addForm'>새 강의</a><br>");
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>강의</th>");
      out.println("    <th>기간</th>");
      out.println("    <th>총강의시간</th>");
      out.println("  </tr>");

      List<Lesson> lessons = lessonService.list();
      for (Lesson l : lessons) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td><a href='detail?no=%d'>%s</a></td> "//
            + "<td>%s ~ %s</td> "//
            + "<td>%d</td>"//
            + "</tr>\n", //
            l.getNo(), //
            l.getNo(), //
            l.getTitle(), //
            l.getStartDate(), //
            l.getEndDate(), //
            l.getTotalHours() //
        );
      }
      out.println("</table>");

      out.println("<hr>");

      out.println("<form action='search'>");
      out.println("강의명: <input name='title' type='text'><br>");
      out.println("강의 시작일: <input name='startDate' type='date'><br>");
      out.println("강의 종료일: <input name='endDate' type='date'><br>");
      out.println("총 강의시간: <input name='totalHours' type='number'><br>");
      out.println("일 강의시간: <input name='dayHours' type='number'><br>");
      out.println("<button>검색</button>");

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
