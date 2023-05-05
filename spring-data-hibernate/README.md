1. What is Hibernate?

Hibernate is an object-relational mapping (ORM) Java framework, which maps Java classes to database tables. 

2. What is Spring Data? 

Spring data provides APIs to execute common queries on Hibernate entities. For example, findAll() selects all the records of the entity in database. Additionally, it provides free controllers for these API, which can be disabled in application.properties file. An example is shown as follow: 

```java
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
```

3. What is a Hibernate session? How is different `EntityManager` in spring? 

A hibernate session builds a connection with the database. It can be used to execute queries and transactions on database. `EntityManager` does the same thing. It is a spring bean in spring framework. So it is better to use `EntityManager` in spring framework. 

4. What are operations for Entity lifecycle?

- Detach: remove an entity from a hibernate session. 
- Merge: load an entity from a hibernate session. 
- Persist: save/update an entity into the database. 
- Remove: delete an entity from the database. 
- Refresh: reload an entity from a hibernate session. 

5. What is cascading? 

Cascading means the operation on one entity will be cascaded to another associated entity. 

6. What are the relationships between associated entities ("Associated" here means foreign key relationship in database)?

- one to one
- one to many
- many to one
- many to many

7. What is `@JoinColumn`? 

`@JoinColumn` introduces one associated entity as a property to another entity. `@JoinColumn` annotation can be either put on parent or child entity. It is used for `@OneToOne`, `@OneToMany` and `@ManyToOne` relationship. 

8. What is `@JoinTable`? 

It is similar to `@JoinColumn` but used for `@ManyToMany` relationship. It introduces a join table to one entity. 

9. What is the `mappedBy` property in `@OneToOne`, `@OneToMany`, `@ManyToOne` and `@ManyToMany` relationship? 

If you already specified `@JoinColumn` or `@JoinTable` in one entity, then in the associated entity, you can introduce the first entity by using `mappedBy` property. 

10. What are uni-directional and bi-directional relationships? 

Uni-directional relationship means only one of the 2 associated entities has reference to the other. Bi-directional relationship means both of the 2 associated entities have reference to each other. Bi-directional relationship exists only when `mappedBy` exists.

11. What are lazy fetch and eager fetch? 

Eager fetch means we fetch associated entities in load time. Lazy fetch means we fetch associated entities when we call we use it. 

12. With lazy fetch, what if the session is already closed and we want to fetch data? 

There are 3 ways to solve this: 
- We change the fetch type to be eager fetch. 
- We write a join fetch query to load the 2 associated entities together.
- We open a new session and write a new query to fetch the associated entity. 