# JDBC (Java Database Connectivity) - Overview and Explanation

## What is JDBC?

JDBC (Java Database Connectivity) is an API (Application Programming Interface) designed to enable Java applications to interact seamlessly with relational databases. It provides a standardized interface for accessing various databases, such as MySQL, PostgreSQL, Oracle, and more, using SQL queries.

### Key Features of JDBC:
- **Establish Database Connections**: Connect your Java application to a relational database.
- **Execute SQL Queries**: Perform operations like `INSERT`, `UPDATE`, `DELETE`, and `SELECT`.
- **Retrieve and Process Data**: Fetch data from the database and handle it within your application.
- **Manage Transactions**: Support for executing multiple statements in a transactional context.

JDBC is a cornerstone for database-driven applications in Java, ensuring flexibility and consistency in database operations across different systems.

----------------------------------------------------------------

# Spring JDBC using XML Configuration

Spring JDBC simplifies database interactions by reducing boilerplate code and managing resources efficiently. It provides an abstraction over traditional JDBC, making it easier to execute SQL queries and handle transactions.

## Steps to Use Spring JDBC with XML Configuration
- **Add Dependencies (For Maven users)**
- **Create a Database and Table**
- **Configure Spring JDBC in applicationContext.xml**
- **Create DAO Interface and Implementation**
- **Create a Model Class**
- **Write a Main Class to Test CRUD Operations**

✅ Spring JDBC with XML configuration provides declarative transaction management, resource handling, and cleaner code compared to raw JDBC.

----------------------------------------------------------------

# Spring JDBC Using Annotation-Based Configuration

Spring JDBC simplifies database interaction by eliminating boilerplate code. In this approach, we use annotations instead of XML to configure DataSource, JdbcTemplate, DAO, and transaction management.

## Steps to Implement Spring JDBC with Annotations
- **Add Dependencies (Maven)**
- **Create a Database and Table**
- **Configure Spring JDBC with Annotations**
- **Create DAO Interface and Implementation**
- **Create a Model Class**
- **Create a Main Class to Test CRUD Operations**

## Key Features

✅ **Annotation-Based Configuration** – Configure JDBC with minimal boilerplate code using annotations.  
✅ **@Transactional for Transaction Management** – Ensure data consistency with declarative transaction management.  
✅ **JdbcTemplate for Database Interaction** – Simplifies database operations, reducing boilerplate code.  
✅ **No XML Required** – Fully annotation-driven, eliminating the need for XML configurations.  
✅ **Component Scanning (@ComponentScan)** – Automatically detect and register components in the application context.  

----------------------------------------------------------------


