This repo includes draft code for spring boot to show how to use it. 

1. What is spring boot? 

Spring boot is a framework which helps setup configurations for other spring frameworks. It also provides actuator to monitor the app and dev-tools to help development. 

2. What are spring beans? 

Spring beans are java objects managed by the spring container. There are 2 ways to add a bean to the spring container. One way is to use the `@component` annotation, e.g. 
```java
@Component
public class MyBean {
  // class implementation here
}
```
The other way is to use `@Configuration` annotation, e.g. 
```java
@Configuration
public class MyConfiguration {

  @Bean
  public MyBean myBean() {
    return new MyBean();
  }
}
```
In this way, we are able to add a class from other packages (library) as a bean. 

3. What is Inversion of Control (IOC)? 

IOC means the spring container takes care of spring beans' initialization and management. By default, the spring container creates a spring bean eagerly, ie., as soon as the application starts. To set lazy initialization, one can cover `spring.main.lazy-initialization=true` in `application.properties` file. By default, the spring container creates the spring bean once and uses it everywhere (singleton scope). One can add `@Scope("prototype")` annotation to a spring bean to make the spring container create multiple instances. If so, each instance is created lazily.

4. What is Dependency Injection (DI)? 

DI means one spring bean are injected to another spring bean as a property using `@Autowired` annotation, instead of being initialized in the other bean. There are 2 ways to inject a bean, ie., constructor injection and setter injection. 
Constructor injection: 
```java
@Component
public class MyBean {

  private final MyDependency myDependency;

  @Autowired
  public MyBean(MyDependency myDependency) {
    this.myDependency = myDependency;
  }

  // class implementation here
}

```
Setter injection: 
```java
@Component
public class MyBean {

  private MyDependency myDependency;

  @Autowired
  public void setMyDependency(MyDependency myDependency) {
    this.myDependency = myDependency;
  }

  // class implementation here
}

```
Constructor injection is used for required dependencies while setter injection is used for optional dependencies injection. 

