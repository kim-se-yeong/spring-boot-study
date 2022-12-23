## Servlet 과 JSP 의 활용

### Servlet
Servlet 은 **자바를 사용하여 웹 페이지를 동적으로 생성하는 서버 측 프로그램**이다.<br>
Servlet 으로 개발하게 된다면 view 를 위한 HTML 을 만드는 작업이 자바 코드로 진행되기 때문에 지저분하고 복잡하다.<br>
하위 예시처럼 클라이언트한테 보여줄 화면(view)을 자바 코드로 작성한다.
```
//com.example.study.servlet.web.servlet.PhoneFormServlet.java

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
```

### JSP
JSP(Jakarta Server Page)는 HTML 내에 **자바 코드를 삽입하여 웹 서버에서 동적으로 웹 페이지를 생성하여 웹 브라우저에 돌려주는 서버사이드 스크립트 언어**이다.<br>
JSP 로 개발하게 된다면 view 를 위한 HTML 작업은 쉬우며 깔끔해지고, 동적으로 변경이 필요한 부분에만 자바를 사용한다.<br>
하지만, 하위 예시를 보면 자바 코드로 된 부분은 비즈니스 로직이고 나머지는 view 를 위한 HTML 코드인데, 결국 두 가지 일(logic, view)을 하고 있다.
```
<%@ page import="com.example.study.servlet.domain.PhoneRepository" %>
<%@ page import="com.example.study.servlet.domain.Phone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PhoneRepository phoneRepository = PhoneRepository.getInstance();
    
    String name = request.getParameter("name");
    int price = Integer.parseInt(request.getParameter("price"));
    Phone phone = new Phone(phone, price);
    
    phoneRepository.save(phone);
%>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    성공
    <ul>
        <li>id=<%=phone.getId()%></li>
        <li>name=<%=phone.getName()%></li>
        <li>price=<%=phone.getPrice()%></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>
```

### 정리
Servlet 은 Java 코드 안에 HTML 을, JSP 는 HTML 코드 안에 Java 코드를 사용한다.<br>
위 내용을 기반으로 보면 결국 비즈니스 로직과 view 를 위한 작업이 같이 작업되었으며, 이는 유지보수가 힘들것이라는 짐작을 해볼 수 있다.
<br>
.<br>
.<br>
Servlet 은 비즈니스 로직을 처리하는 데 더 적합하고, JSP 는 view 를 위한 화면을 만들어 내는 데 적합한 것을 알 수 있었다.<br>
그래서 탄생한 것이 `MVC 패턴` 이며, 비즈니스 로직과 view 를 만드는 작업을 분리할 수 있도록 하였다.