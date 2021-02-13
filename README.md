This project aims to explore the Spring Data MongoDB.
We build a REST API for an Expense Manager application that allow tracking the expenses.

- Java version : 8.
- Spring Boot: Version 2.4.2.
- Dependencies:
    * Spring Web: To be able to build RESTful API using Spring MVC
    * Spring Data MongoDB: To interact with MongoDB from the Spring Boot Application
    * Lombok: Java Annotation Library which helps to reduce boiler plate code.
    * Testcontainers: Provides lightweight instances of the Mongo Database which we can run inside a Docker Container.

(bellow some definitions to avoid some conflicts i have)
# Spring Data: 
It's an umbrella project to simplify persisting POJOS to varios forms of storage.
Sub-projects exist for different backend technologies :

- SQL : JDBC, JPA.
- NoSQL : MongoDB, CouchDB, Gemfire
- Others : REST, Hadoop, Elasticsearch

![SpringDataSubProjects](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/SpringDataSubProjects.PNG)

### Goals :
- Reduce boilerplate code.
- Automate common tasks and code.
- automate configuration.

### Basics :

`1. Repository : `
- convert retreived data into POJOs.
- convert POJOs into saved data.

`2.  Mapping : `
- interferred from conventions.
- Annotations to help automate field mapping.

`3.  Template : `
- provide simplified direct*access to database that automatically manage internal resources.

`4.  Query : `
- apply provided native queries direcctly.
- convert QueryDSL into native queries.

### Components :

`1. Repository : `
- based on the design on the 3 layer architecture (seperation of concerns design).![3layerarchitecture](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/3layerarchitecture.PNG)
- implemention of the "adopter" pattern.
- application side of the adaptor deals "what".
- database side of the adaptor deals with "how".

Note : check in the end the repository design pattern.

`2.  Mapping : `
-  map data to/from storage and POJOs fields
- based on exsisting mapping systems : ORM, POx, marshaller/unmarshaller.
- Convention over configuration, annotations in class to define mappings.
    ![SpringDataMapping](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/SpringDataMapping.PNG)

`3.  Template : `
- implementation of the “template” pattern
- provide high-level API for backend whilst hiding internal backend complexity 
- High-level methods match capabilities of the underlying technology : load(), save(), find() ...
- Backend resources managed transparently
- Passively integrate into Spring components when configured with Spring through Dependency Injection

`4.  Query : `
- declarative native queries : programmer declares query too use, and spring Data runs it when needed.
- QueryDSL : prgrammer writes in generic DSL(Domain Specific Language), and Spring Data converts DSL to native query of backend.
    ![SpringDataQueries](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/SpringDataQueries.PNG)


## Spring Data Repositories 

- Autogeneration : No boilerplate code.
- Defined by interface declaration.
- Methods can be overridden (by annotaion).
- Can be extended: define extra methodes in an interface, implement the meth in an impl class.

How to ?

`1. Declare your repository extending Spring Data. `

Note that repository is declared as an interface and you define features by declaring additional methods.

![Repository](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/Repository.PNG)

`2. Predefined Repository Interfaces.`
![PredefinedRepositoryInterfaces](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/PredefinedRepositoryInterfaces.PNG)

`3. Extending CRUDRepository. `

Simple repositories define no custom methods! Rely solely on predefined methods.
![ExtendingCRUDRepository](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/ExtendingCRUDRepository.PNG)


- Auto-generated finders obey naming convention
  *  findBy<DataMember><Op>
  *  <Op> can be Gt, Lt, Ne, Between, Like … etc

![Definingfinders](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/Definingfinders.PNG)


- CRUDRepository provide :
![CRUDRepository](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/CRUDRepository.PNG)


- Another interface : PagingAndSortingRepository provide :
![PagingAndSortingRepository](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/PagingAndSortingRepository.PNG)


## JPA : Java Persistence API 

- The Java Persistence API provides a specification for persisting/reading/managing data from your Java object to relational tables in the database.
it's a the Java ORM standanrd.

- Every JPA implementation provides an ORM layer, layer responsible of converting Java classes and objects so that they can be stored/managed in a relationnel DB.

## What Is Hibernate Framework?

- Hibernate provides a reference implementation of the Java Persistence API.
- Hibernate is a  Java ORM tool that provides a framework for mapping application domain objects to the relational database tables and vice versa.

Note that JPA is a specification and Hibernate is a JPA provider or implementation.

## What Is Spring Data JPA?

- Spring Data is a part of the Spring Framework. The goal of Spring Data repository abstraction is to significantly reduce the amount of boilerplate code required to implement data access layers for various persistence stores.

- Spring Data JPA is not a JPA provider. It is a library/framework that adds an extra layer of abstraction on the top of our JPA provider (like Hibernate, Eclipse Link).

### What Is the Difference Between Hibernate and Spring Data JPA?

- Let's say you are using spring + hibernate for your application. Now you need to have dao interface and implementation where you will be writing crud operation using SessionFactory of hibernate. Let say you are writing dao class for Employee class, tomorrow in your application you might need to write similiar crud operation for any other entity. So there is lot of boilerplate code we can see here.

- Now Spring data jpa allow us to define dao interfaces by extending its repositories(crudrepository, jparepository) so it provide you dao implementation at runtime. You don't need to write dao implementation anymore.Thats how spring data jpa makes your life easy.

To sum up : Hibernate is a JPA implementation, while Spring Data JPA is a JPA Data Access Abstraction. Spring Data offers a solution to GenericDao custom implementations. It can also generate JPA queries on your behalf through method name conventions.


## Spring JDBC?

- Spring JDBC is an abstraction framework for JDBC that provides easier access to datasources without all the exception handling and parsing of SQL fetch results.
Use Spring JDBC only when you need to use much Joins or when you need to use Spring having multiple datasource connections. Generally, avoid JPA for Joins.

- Strengths of Spring JDBC : 

    * JdbcDaoSupport - Convenient super class for JDBC data access objects, providing a JdbcTemplate based on it to subclasses
    * Spring provides an abstract exception layer, moving verbose and error-prone exception handling out of application code into the framework. The framework takes care of all  exception handling; application code can concentrate on extracting results by using appropriate SQL.
    * Spring provides a significant exception hierarchy for your application code to work with in place of SQLException.
    * The Spring framework provides the org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor interface and some implementations (such as SimpleNativeJdbcExtractor) of this interface. These are useful for accessing Oracle features via an Oracle connection or ResultSet when the connection is "wrapped" by another DataSource (such as that used with some application servers) or obtained through certain connection pools.
    * For creating instances of oracle.sql.BLOB (binary large object) and oracle.sql.CLOB(character large object), Spring provides the class org.springframework.jdbc.support.lob.OracleLobHandler.
    * The Spring-provided OracleSequenceMaxValueIncrementer class provides the next value of an Oracle sequence. It effectively provides the same information that would be provided if you used the following command directly: select someSequence.nextval from dual (where someSequence is the name of your sequence in the Oracle database). An advantage of this approach is that the DataFieldMaxValueIncrementer interface can be used in a DAO hierarchy without tight coupling of the Oracle-specific implementation.

## Spring Data MongoDB

- Generated repositories map mongodb Documents to POJO objects.
- Mapping can be completely automic (Annotation used to override or clarify).
- Template class allows simplified, direct access to mongi database
- @Query supports mongodb queries (JSON syntax)
- QueryDSL can be used to avoid writing mongo queries.
- Differences of MongoDB : ObjectID , Transactions.

`1.  MongoDB ObjectIDs` 

- MongoDB creates object ids in the client, Not in the server.
- Object ids are a UUID (Universally Unique ID)
    * 12 bytes: TTTTmmmPPccc
    * Can be converted to/from String and BigInteger, not Integer or Long
    * Integer and Long ids can be stored and retrieved, but they cannot be initialised automatically (Must do it manually in code)
- Simplest approach: don't fight it (Managing db-wide unique keys is hard work).

`2.  MongoDB and transactions `
- MongoDB does not support an explicit transactional model: 
    * @Transactional not used by MongoDB.
    * TransactionManager bean not needed.
    * If you are using JPA and MongoDB together, then you would still configure @Transactional and TransactionManager for JPA.

## Spring Data JPA for NoSQL ?

- JPA is an inherently relational API. The first two sentences of the spec state, that it's an API for object-relational mapping. This is also embodied in core themes of the API: it talks about tables, columns, joins, transactions. Concepts that are not necessarily transferable into the NoSQL world.
- You usually choose a NoSQL store because of it's special traits (e.g. geo-spatial queries on MongoDB, being able to execute graph traversals for Neo4j). None of them are (and will be) available in JPA, hence you'll need to provide proprietary extensions anyway.
- Even worse, JPA features concepts that will simply guide users into wrong directions if they assume them to work on a NoSQL store like they were defined in JPA: how should a transaction rollback be implemented reasonably on top of a MongoDB?
- Hibernate OGM provides Java Persistence (JPA) support for NoSQL solutions. It reuses Hibernate ORM’s engine but persists entities into a NoSQL datastore instead of a relational database. It also aims to provide access to specific datastore features when JPA does not have a good fit.
- Some of the concepts of JPA are not easily mapped to the NoSQL world: transactions for example. While you will have access to transaction demarcation methods, you won't be able to rollback on data stores that don't support transactions natively (transactions, in this case, will be used to group operations and try to optimize the number of calls to the db).

# Sum Up 
- Spring Data focuses on mapping POJOs to/from data backends (typically databases).
- Based on the adaptor pattern : Convert “what” into “how”
- Implemented using dynamically generated repositories.
- Repositories are declared as interfaces ( Annotations allow tailoring of the generated implementation and any implementation can be manually overridden).
- Annotations in domain classes help drive the mapping.
- Range of query support (Dynamically generate finders, Declarative query in native syntax, DSL translated into native syntax).


# Design the infrastructure persistence layer : Use The Repository pattern

- Repositories are classes or components that encapsulate the logic required to access data sources. They centralize common data access functionality, providing better maintainability and decoupling the infrastructure or technology used to access databases from the domain model layer. If you use an Object-Relational Mapper (ORM) like Entity Framework, the code that must be implemented is simplified.

- A repository performs the tasks of an intermediary between the domain model layers and data mapping, acting in a similar way to a set of domain objects in memory. Client objects declaratively build queries and send them to the repositories for answers. Conceptually, a repository encapsulates a set of objects stored in the database and operations that can be performed on them, providing a way that is closer to the persistence layer. Repositories, also, support the purpose of separating, clearly and in one direction, the dependency between the work domain and the data allocation or mapping. 

# Define one repository per aggregate
- For each aggregate or aggregate root, you should create one repository class. In a microservice based on Domain-Driven Design (DDD) patterns, the only channel you should use to update the database should be the repositories. This is because they have a one-to-one relationship with the aggregate root, which controls the aggregate's invariants and transactional consistency. It's okay to query the database through other channels (as you can do following a CQRS approach), because queries don't change the state of the database. However, the transactional area (that is, the updates) must always be controlled by the repositories and the aggregate roots.

- Basically, a repository allows you to populate data in memory that comes from the database in the form of the domain entities. Once the entities are in memory, they can be changed and then persisted back to the database through transactions.

- As noted earlier, if you're using the CQS/CQRS architectural pattern, the initial queries are performed by side queries out of the domain model, performed by simple SQL statements using Dapper. This approach is much more flexible than repositories because you can query and join any tables you need, and these queries aren't restricted by rules from the aggregates. That data goes to the presentation layer or client app.

- If the user makes changes, the data to be updated comes from the client app or presentation layer to the application layer (such as a Web API service). When you receive a command in a command handler, you use repositories to get the data you want to update from the database. You update it in memory with the data passed with the commands, and you then add or update the data (domain entities) in the database through a transaction.

- It's important to emphasize again that you should only define one repository for each aggregate root, as shown in Figure bellow. To achieve the goal of the aggregate root to maintain transactional consistency between all the objects within the aggregate, you should never create a repository for each table in the database.
![repositoryPerAggregate](https://github.com/OuniAbir/Springboot-NoSQL-Demo/blob/master/repositoryPerAggregate.PNG)
- The above diagram shows the relationships between Domain and Infrastructure layers: Buyer Aggregate depends on the IBuyerRepository and Order Aggregate depends on the IOrderRepository interfaces, these interfaces are implemented in the Infrastructure layer by the corresponding repositories that depend on UnitOfWork, also implemented there, that accesses the tables in the Data tier.

Note that Spring Data offers a solution to this Design pattern.