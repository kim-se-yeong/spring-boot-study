package com.example.study.servlet.web.servlet;

import com.example.study.servlet.domain.PhoneRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "phoneFormServlet", urlPatterns = "/servlet/phone/new-form")
public class PhoneFormServlet extends HttpServlet {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //자바 코드로 HTML 을 만드는 것은 매우 복잡하고 비효율적
        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/phone/save\" method=\"post\">\n" +
                "    name: <input type=\"text\" name=\"name\" />\n" +
                "    price:      <input type=\"text\" name=\"price\" />\n" +
                " <button type=\"submit\">전송</button>\n" + "</form>\n" +
                "</body>\n" +
                "</html>\n");

    }
}
