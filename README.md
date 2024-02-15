A database is created by the name library_management.

Changing the hibernate-configuration file to match the password and user name

In this project you can find two entitys with one to many relationship 
The @GeneratedValue(strategy = GenerationType.IDENTITY) will automatically generate a unique id. 
The use of mappedBy in the @OneToMany annotation helps to establish a bidirectional relationship and specifies that the author field in the Book entity manages the relationship. 
The @JoinColumn annotation is used in JPA (Java Persistence API) to specify the mapping of a foreign key column. It is typically used in associations between entities to define the column in the database table that represents the foreign key relationship.

Follow is the defining of the bidirectional one-to-many relationship between Author and Book entities using @JoinColumn annotation

	public class Author {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String name;
				
	    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
	    private List<Book> books;
	}


		public class Book {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
                     private String title;
	    private LocalDate publicationYear;
	    private double price;
			
	    @ManyToOne
	    @JoinColumn(name = "author_id")
	    private Author author;
	}

Each author can have multiple books, and each book belongs to one author.
Changes made in one side of the relationship will reflect in the other side as well.
@OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true) by cascade = CascadeType.ALL,orphanRemoval = true when a author is deleted the relavent associated books will be deleted automatically. orphanRemoval = true: This attribute specifies that if a child entity (e.g., Book) is removed from the collection in the parent entity (e.g., Author), it should be deleted from the database. This means that if you remove a Book from the collection of an Author, Hibernate will automatically delete the corresponding row from the database.

In summary, the combination of CascadeType.ALL and orphanRemoval = true ensures that all operations are cascaded to associated entities, and when an associated entity is removed from the collection of the parent entity, it is also deleted from the database.
