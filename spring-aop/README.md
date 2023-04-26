1. What is AOP (aspect programming)?
AOP is a technique which allows developers to add functionalities (security, logging, cache, etc.) to an existing method without changing the source code. 
2. What is an aspect?
An aspect is the additional functionality added to existing methods 
3. What is an advice?
A snippet of code executed at a certain time. Kinds of advice are before advice, after returning advice, after throwing advice, etc. Multiple advices can be applied on one method. The order of advices' execution can be configured in Spring AOP.   
4. What is a join point?
Generally, a join point represents a specific point in the execution of a program where an aspect can "join" in and apply some additional functionality. In Spring AOP, it represents a method execution. 
5. What is a point cut?
A point cut is predicate expression to check where the advice should be applied. In Spring AOP, a point cut expression can be applied to multiple methods and multiple point cuts can be combined.  
6. How does code injection work in spring aop?
It uses dynamic proxy (java reflection). A dynamic proxy is created like following: 
```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyInterface {
    void myMethod();
}

class MyTargetObject implements MyInterface {
    public void myMethod() {
        System.out.println("MyTargetObject.myMethod()");
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After method " + method.getName());
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        MyInterface target = new MyTargetObject();
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class[] { MyInterface.class },
                new MyInvocationHandler(target)
        );
        proxy.myMethod();
    }
}
```
A method in spring is called like following: 
```java
public class MainDemoApp {

    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call the business method
        theAccountDAO.addAccount();

        // close the context
        context.close();
    }
}
```
When querying the bean by `context.getBean("accountDAO", AccountDAO.class)`, the spring container checks if any method in this bean is intercepted. If not, it returns the original object. Otherwise, it creates a proxy and the returns the proxy.  

