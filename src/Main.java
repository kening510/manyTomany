import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Book book1 = new Book();
        book1.setTitle("ABC");
        book1.setPages(288);

        Book book2 = new Book();
        book2.setTitle("123");
        book2.setPages(98);

        Book book3 = new Book();
        book3.setTitle("Hello world");
        book3.setPages(330);

        Author author1 = new Author();
        author1.setName("Kening Fohlin");
        author1.setCountry("China");

        Author author2 = new Author();
        author2.setName("Hanna Fohlin");
        author2.setCountry("Sweden");
        //Kenings book
        List<Book> bookList1 = new ArrayList<>();
        bookList1.add(book1);
        bookList1.add(book2);
        //Hannas book
        List<Book> bookList2 = new ArrayList<>();
        bookList2.add(book1);
        bookList2.add(book3);

        author1.setBookList(bookList1);  // ManyToMany relation
        author2.setBookList(bookList2);
        //ABC
        List<Author> authorList1= new ArrayList<>();
        authorList1.add(author1);
        authorList1.add(author2);
        book1.setAuthorList(authorList1);

        List<Author> authorList2= new ArrayList<>();
        authorList2.add(author1);
        book2.setAuthorList(authorList2);

        List<Author> authorList3= new ArrayList<>();
        authorList3.add(author1);
        book3.setAuthorList(authorList3);

        try {
            entityManager.persist(author1);
            entityManager.persist(author2); // save authors

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }


    }
}
