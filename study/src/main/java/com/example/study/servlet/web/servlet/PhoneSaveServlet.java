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

@WebServlet(name = "phoneSaveServlet", urlPatterns = "/servlet/phone/save")
public class PhoneSaveServlet extends HttpServlet {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Phone phone = new Phone(name, price);
        phoneRepository.save(phone);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+phone.getId()+"</li>\n" +
                "    <li>name="+phone.getName()+"</li>\n" +
                " <li>price="+phone.getPrice()+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");

    }
}
