# 有状态和无状态的Servlet

## 无状态Servlet

```java
package zzhq;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test1Servlet extends HttpServlet{
    
    
    @Override
    public void service(HttpServletRequest request,HttpServletResponse response){
        Person person;
        person = new Hero();
        request.setAttribute("h", h);
    }

}
```

如上代码，既不包含任何域，也不包含任何对其他类中域的引用。计算过程中的临时状态仅存于线程栈上的局部变量中，并且只能由正在执行的线程访问。

`无状态对象一定是线程安全的`

## 有状态Servlet

```java
package zzhq;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test2Servlet extends HttpServlet{
    public Person person;
    
    @Override
    public void service(HttpServletRequest request,HttpServletResponse response){
        person = new Person();
        request.setAttribute("h", h);
    }

}
```

如上，TestServelt中具有一个实例变量，所有对于TestServlet在被多个客户端访问时，会对该实例变量进行多次赋值，该实例变量就不是线程安全的。 