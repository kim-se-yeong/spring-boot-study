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

---

## 프론트 컨트롤러 활용 내용

### Package v1
#### ✔ 프론트 컨트롤러 도입

<br>

### Package v2
#### ✔ View 의 분류a

단순 반복되는 View 로직 분류한다.
- 프론트 컨트롤러를 활용한 v1 예시에서 모든 컨트롤러가 **view 렌더링 하는 코드를 반복**하였으며 아래와 같이 사용하고 있다.
  ```
  RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
  dispatcher.forward(request, response);
  ```
따라서 MyView 객체를 생성하였고, 이 객체를 통해 view 렌더링을 실행한다.

<br>

### Package v3
#### ✔ 서블릿 종속성 제거
#### ✔ ModelView 추가
각 컨트롤러는 ModelView 객체를 생성하여, 뷰의 논리적 이름과 모델을 반환한다.
#### ✔ 뷰 이름 중복 제거
프론트 컨트롤러를 활용한 v2 예시에서 모든 컨트롤러는 뷰의 물리적 위치를 작성하고 있다. <br>
예) `/view-path/new-form.jsp`, `/view-path/save-result.jsp`, `/view-path/phones.jsp`<br>
컨트롤러는 `뷰의 논리적 이름`만 반환하고, `실제 물리적 이름은 프론트 컨트롤러에서 viewResolver 를 통해 처리`하도록 한다.

<br>

### Package v4
#### ✔ 단순하고 실용적인 컨트롤러

컨트롤러는 ModelView 를 직접 생성해서 반환하지 않는다.<br>
즉, **프론트 컨트롤러가 모델을 직접 생성**하여 컨트롤러를 호출하며 컨트롤러는 모델에 값만 담고 **뷰 논리 이름**만 반환한다.<br>
따라서 컨트롤러에서 직접 모델을 생성하지 않아 더 간결하게 코드를 작성할 수 있게 되었다.

<br>

### Package v5
#### ✔ Adapter 도입

Adapter 를 추가해서 프레임워크를 유연하고 확장성 있게 설계한다.

---

## HandlerMapping & HandlerAdapter & ViewResolver
스프링부트가 자동 등록하는 대표적인 HandlerMapping & HandlerAdapter & ViewResolver<br>
<br>
### HandlerMapping
**✔ RequestMappingHandlerMapping** : 어노테이션 기반의 컨트롤러인 `@RequestMapping` 에서 사용

**✔ BeanNameUrlHandlerMapping** : 스프링 빈의 이름으로 핸들러를 찾는다.
```
// 스프링 빈의 이름이 /springmvc/handler 인 핸들러를 찾는다. 
@Component("/springmvc/handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
}
```

<br>

### HandlerAdapter
**✔ RequestMappingHandlerAdapter** : 어노테이션 기반의 컨트롤러인 `@RequestMapping` 에서 사용

**✔ HttpRequestHandlerAdapter** : HttpRequestHandler 처리
```
//HttpRequestHandler 인터페이스를 구현하여 사용한다.
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
}
```
**✔ SimpleControllerHandlerAdapter** : Controller 인터페이스 처리
```
//Controller 인터페이스를 구현하여 사용한다.
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
```

<br>


### ViewResolver

**✔ BeanNameViewResolver** : 빈 이름으로 뷰를 찾아서 반환한다.

**✔ InternalResourceViewResolver** : JSP 를 처리할 수 있는 뷰를 반환한다.