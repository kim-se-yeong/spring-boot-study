## HandlerMapping & HandlerAdapter
스프링부트가 자동 등록하는 HandlerMapping & HandlerAdapter<br>
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

스프링부트가 자동 등록하는 ViewResolver

### ViewResolver

**✔ BeanNameViewResolver** : 빈 이름으로 뷰를 찾아서 반환한다.

**✔ InternalResourceViewResolver** : JSP 를 처리할 수 있는 뷰를 반환한다.
