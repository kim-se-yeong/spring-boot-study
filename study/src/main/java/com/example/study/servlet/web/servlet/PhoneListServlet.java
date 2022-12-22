package com.example.study.servlet.web.frontcontroller.servlet;

import com.example.study.servlet.domain.Phone;
import com.example.study.servlet.domain.PhoneRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "phoneListServlet", urlPatterns = "/servlet/phones")
public class PhoneListServlet extends HttpServlet {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Phone> phones = phoneRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //자바 코드로 HTML 을 만드는 것은 매우 복잡하고 비효율적
        PrintWriter writer = response.getWriter();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("    <meta charset=\"UTF-8\">");
        writer.write("    <title>Title</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<a href=\"/index.html\">메인</a>");
        writer.write("<table>");
        writer.write("    <thead>");
        writer.write("    <th>id</th>");
        writer.write("    <th>name</th>");
        writer.write("    <th>price</th>");
        writer.write("    </thead>");
        writer.write("    <tbody>");

        for (Phone phone : phones) {
            writer.write("    <tr>");
            writer.write("        <td>" + phone.getId() + "</td>");
            writer.write("        <td>" + phone.getName() + "</td>");
            writer.write("        <td>" + phone.getPrice() + "</td>");
            writer.write("    </tr>");
        }
        writer.write("    </tbody>");
        writer.write("</table>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
