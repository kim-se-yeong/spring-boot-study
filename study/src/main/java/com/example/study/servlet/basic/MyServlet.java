package com.example.study.servlet.basic;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "myServlet", urlPatterns = "/my") //urlPatterns("/my")의 URL 이 호출되면 서블릿 코드가 실행된다.
public class MyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("MyServlet.service");
        log.info("request = {}" + request);
        log.info("response = {}" + response);

        String os = request.getParameter("os");
        log.info("os = {}" + os);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("os = " + os);
    }
}