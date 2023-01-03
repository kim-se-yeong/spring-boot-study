## HandlerMapping & HandlerAdapter & ViewResolver
FrontController 패턴에 대해 공부할 때 여러 유형의 컨트롤러를 `유연하게` 사용하기 위해 `HandlerMapping` 과 `HandlerAdapter` 를 구현해보았다.<br>
스프링도 다양한 컨트롤러 방식을 지원하는데, 스프링에서 자동으로 등록해주는 대표적인 HandlerMapping, HandlerAdapter 그리고 ViewResolver 에 대해서 알아본다. 

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

**✔ HttpRequestHandlerAdapter** : [HttpRequestHandler](#HttpRequestHandler) 처리
```
//HttpRequestHandler 인터페이스를 구현하여 사용하면, HttpRequestHandlerAdapter 를 통해 handleRequest 함수가 호출된다.
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
}
```

**✔ SimpleControllerHandlerAdapter** : [Controller](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/package-summary.html) 인터페이스 처리
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

---

### HttpRequestHandler

**서블릿과 가장 유사한 형태의 핸들러**이다.<br>
이 말이 무엇일까? 하고 생각해 본다면 `handleRequest` 함수의 리턴 타입이 `void`이다.<br>
```
public interface HttpRequestHandler {

	/**
	 * Process the given request, generating a response.
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @throws ServletException in case of general errors
	 * @throws IOException in case of I/O errors
	 */
	void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
```
`Model` 객체도 없고 그럼 사용자한테 보여줘야 할 데이터는 어떻게 처리해야 할까 ?<br>
바로 다음과 같이 `HttpServletResponse` 객체만을 활용해야 하는 것이다.<br>
```
// package : com.example.servlet.web.springmvc.old

@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");

        response.getWriter().write("지극히 제 생각입니다 :_)");
    }
}
```
