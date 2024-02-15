package lk.ijse.ormAssignment;

import lk.ijse.ormAssignment.config.FactoryConfiguration;
import lk.ijse.ormAssignment.entity.Author;
import lk.ijse.ormAssignment.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Book book_1 = new Book();
        book_1.setTitle("Feel The Heat");
        book_1.setPublicationYear(LocalDate.of(2009, 10, 01));
        book_1.setPrice(2000);

        Book book_2 = new Book();
        book_2.setTitle("Heart Wood");
        book_2.setPublicationYear(LocalDate.of(2000, 07, 01));
        book_2.setPrice(2500);

        Book book_3 = new Book();
        book_3.setTitle("Under The Radar");
        book_3.setPublicationYear(LocalDate.of(2009, 02, 01));
        book_3.setPrice(3000);

        ArrayList<Book> books = new ArrayList<>();
        books.add(book_1);
        books.add(book_2);
        books.add(book_3);

        Author author_1 = new Author();
        author_1.setName("Cindy Gerard");
        author_1.setCountry("USA");
        author_1.setBooks(books);

        Author author_2 = new Author();
        author_2.setName("James Lee Burke");
        author_2.setCountry("USA");
        author_2.setBooks(books);

        Author author_3 = new Author();
        author_3.setName("Fern Michaels");
        author_3.setCountry("USA");
        author_3.setBooks(books);

        book_1.setAuthor(author_1);
        book_2.setAuthor(author_2);
        book_3.setAuthor(author_3);

        /*1. Write an HQL query to retrieve all books published after the year 2010*/

        /*Query query = session.createQuery("from Book where year(publicationYear) = :year");

        query.setParameter("year", 2010);
        List<Book> bookList = query.list();

        for (Book book : bookList) {
            System.out.println("Book ID : " + book.getId());
            System.out.println("Price : " + book.getPrice());
            System.out.println("Publication Year : " + book.getPublicationYear());
            System.out.println("Name : " + book.getTitle() + "\n");
        }*/


        /*2. Write an HQL update query to increase the price of all books published by a specific author by 10%.*/

        /*Query query = session.createQuery("update Book set price = price * 1.1 where author.id IN (:authorIds)");
        query.setParameter("authorID", 3);
        int result = query.executeUpdate();
        System.out.println("Books price updated by 10% : " + result);*/


        /*3. Implement a method to delete an author and cascade the deletion to all associated books
        using appropriate cascade options.*/

        /*Create a query to delete books associated with a specific author*/
        /*Query deleteBooksQuery = session.createQuery("delete from Book b where b.author.id = :id");
        deleteBooksQuery.setParameter("id", 1); // Set the author ID here
        int deletedBooksCount = deleteBooksQuery.executeUpdate();
        System.out.println("Number of books deleted: " + deletedBooksCount);*/

        /*Create a query to delete the author*/
        /*Query deleteAuthorQuery = session.createQuery("delete from Author where id = :id");
        deleteAuthorQuery.setParameter("id", 1); // Set the author ID here
        int deletedAuthorCount = deleteAuthorQuery.executeUpdate();
        System.out.println("Number of authors deleted: " + deletedAuthorCount);*/


        /*4. Write an HQL query to find the average price of all books.*/

        /*Query query = session.createQuery("select avg(price) from Book");
        Double avg = (Double) query.uniqueResult();
        System.out.println("Average Price of a Book :" + avg);*/


        /*5. Write an HQL query to retrieve all authors along with the count of books they have written.*/

        /*Query query = session.createQuery("select a.name,count (b.id) from Author a " +
                "inner join a.books b group by a.id");
        List<Object []> list = query.list();

        for (Object [] objects : list) {
            String name = (String) objects[0];
            Long bookCount = (Long) objects[1];

            System.out.println("Author : " + name + "\tTotal Books : " +  bookCount);

        }*/


        /*6. Write an HQL query using named parameters to retrieve books written by authors from a
        specific country.*/

        /*Query query = session.createQuery("SELECT book FROM Book AS book WHERE book.author.country = :country");
        query.setParameter("country", "England");
        List<Book> results = query.list();

        for (Book book : results) {
            System.out.println("The books from England : " + book.getTitle());
        }*/

        session.save(author_1);
        session.save(book_1);
        session.save(author_2);
        session.save(book_2);
        session.save(author_3);
        session.save(book_3);

        transaction.commit();
        session.close();
    }
}