# Cascade Types in Hibernate/JPA

Cascade operations in JPA/Hibernate are used to propagate certain operations (like save, delete, update, etc.) from one entity to its related entities. They allow you to automatically apply operations on related entities when performing an operation on the parent entity. This can save you from manually handling each related entity for common tasks.

## CascadeType.PERSIST
   Purpose: It automatically persists the related entities when the parent entity is persisted (saved).
   Example Use Case: When you save an Order, and the Order has multiple Items, using CascadeType.PERSIST will automatically save the Items as well when the Order is saved. 
   
## CascadeType.MERGE
   Purpose: It automatically merges the state of the related entities when the parent entity is merged (updated).
   Example Use Case: If you update the Customer entity and it has a Profile, using CascadeType.MERGE will automatically update the Profile as well.

## CascadeType.REMOVE
   Purpose: It automatically removes the related entities when the parent entity is removed (deleted).
   Example Use Case: When deleting a Course, if it has multiple Students (in a many-to-many relationship), CascadeType.REMOVE will automatically delete the associated Student entities as well (if they are no longer needed).

## CascadeType.REFRESH
   Purpose: It automatically refreshes the state of the related entities when the parent entity is refreshed.
   Example Use Case: If you refresh an Author entity from the database and it has associated Books, using CascadeType.REFRESH will automatically refresh the associated Books as well. 
   
## CascadeType.DETACH
   Purpose: It automatically detaches the related entities when the parent entity is detached from the persistence context (e.g., when you clear the session or use EntityManager.detach()).
   Example Use Case: If you detach a User entity, using CascadeType.DETACH will automatically detach the related Address entities as well.

## CascadeType.ALL
   Purpose: This is a shortcut for applying all of the above cascading operations (PERSIST, MERGE, REMOVE, REFRESH, and DETACH) on the related entities.
   Example Use Case: When working with strongly related entities, and you want all operations to cascade across the entire object graph, you can use CascadeType.ALL.


# Relationships in JPA

- **One-To-One**

  1. Unidirectional
     - One side knows about the other. Only one entity maintains a reference to the other.
       No need for the other entity to hold a reference back. The related entity is not aware of the entity that owns the relationship.
       Typically used when the relationship is not required to be navigated in both directions.

  2. Bidirectional
     - Both sides know about each other. Each entity has a reference to the other, making the relationship bidirectional.
       Consistency between both sides. If both sides are mapped, it is important to keep them in sync (i.e., if you set a reference on one side, you should set the corresponding reference on the other side).
       Typically used when you need to access the related entity in both directions.
     - Cascade needs to be done on the owning side
     - Owning side needs to set itself on the inverse side from the setter method to ensure that the Passport entity is
       also aware of the relationship and also to avoid **TransientObjectException**
     - Also we need to set the reference of the owning side in the inverse side (i.e. setUser() in setPassport() in
       User class)
     - @OneToOne(cascade = CascadeType.ALL) in User: This defines the relationship and makes User the owner of
       the relationship. The passport field in User contains the foreign key (passport_id) that points to Passport.
     - @JoinColumn(name = "passport_id", unique = true) in User: This explicitly defines the foreign key column in
       the User table. It ensures that each Employee can have one and only one Passport.
     - @OneToOne(mappedBy = "passport") in Passport: The mappedBy attribute tells Hibernate that the Passport class is
       the inverse side of the relationship and the relationship is maintained by the passport field in the User
       class. Since User owns the relationship, Passport doesn't need a foreign key column. The relationship is
       already defined in User.

- **One-To-Many and Many-To-One**
     - In JPA/Hibernate, OneToMany and ManyToOne define a parent-child (one-to-many) relationship between two entities.
     - @OneToMany (Parent → Children): One parent entity is related to multiple child entities. Example: One Author has many Books.
     - @ManyToOne (Children → Parent): Multiple child entities belong to one parent entity. Example: Many Books belong to one Author.
     - You define a bidirectional relationship with @OneToMany on the parent side and @ManyToOne on the child side.
     - The mappedBy attribute tells Hibernate that the relationship is already mapped by the author field in the Book entity.
       It prevents Hibernate from creating an extra join table (author_books).
       ✔ Without mappedBy, Hibernate creates an extra join table for the relationship.
       ✔ With mappedBy, the foreign key is stored in the Book table.
     - Best practices for OneToMany and ManyToOne:
       ✅ Always set the relationship on both sides (in both Author and Book).
       ✅ Use mappedBy to avoid unnecessary join tables.
       ✅ Use orphanRemoval=true carefully, as it automatically deletes orphaned child records.
       ✅ Avoid EAGER fetching on OneToMany (can cause performance issues).
       ✅ Use @Transactional when modifying relationships to avoid lazy loading issues.
   
- **Many-To-Many**
     - A Many-to-Many relationship means that one entity can be associated with multiple records of another entity and vice versa.
       For example, a Student can enroll in multiple Courses, and a Course can have multiple Students.
     - @ManyToMany → Defines the many-to-many relationship.
       @JoinTable → Specifies the join table where relationships are stored.
     - If we don’t use @JoinTable, Hibernate will generate a default join table with naming conventions. However, explicitly defining @JoinTable is recommended for better clarity and control.
     - Example of bidirectional mapping:
  
       // In Student class
       @ManyToMany
       private List<Course> courses = new ArrayList<>();

       // In Course class
       @ManyToMany(mappedBy = "courses")
       private List<Student> students = new ArrayList<>(); //mappedBy = "courses" → This means Course does not own the relationship; Student does.
  
     - mappedBy is used to avoid duplicate join tables. If it is missing, Hibernate will create two separate join tables.
     - If we don’t set both sides (student.getCourses().add(course) AND course.getStudents().add(student)), only one side will be updated.
       Hibernate does NOT automatically synchronize both sides unless we do it manually.
     - By default, Many-to-Many uses FetchType.LAZY. This means: 
       - student.getCourses() will not load courses immediately. 
       - Hibernate uses lazy loading proxies and fetches data only when needed.
     - JOIN FETCH or batch fetching. can be used to optimize Many-To-Many queries