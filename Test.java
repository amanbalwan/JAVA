import java.util.ArrayList;
import java.util.List;

class Book {
    private int id;
    private String name;
    private String author;
    private double price;


    public Book(int id, String name, String author, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }
    public String toString() {
        return name ;
    }
}

class DAO {
    private List<Book> books = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();

    public String addBook(Book book) {
        int index = books.indexOf(book);

        if (index != -1) {
            quantities.set(index, quantities.get(index) + 1);
        } else {
            books.add(book);
            quantities.add(1);
        }

        return "Book added successfully!";
    }

    public Book removeBook(int Id) {
        Book removedBook = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == Id) {
                int rembook = quantities.get(i);
                if (rembook > 1) {
                    quantities.set(i, rembook - 1);
                } else {
                    removedBook = books.get(i);
                    books.remove(i);
                    quantities.remove(i);
                }
                break;
            }
        }
    
        return removedBook;
    }
    
    
    public List<Integer> getBooksAvailable() {
        return new ArrayList<>(quantities); 
    }

    public Book getBookDetails(int Id) {
        for (Book book : books) {
            if (book.getId() == Id) {
                return book;
            }
        }
        return null; 
    }
}

public class Test {
    public static void main(String[] args) {
        DAO DAO = new DAO();
        Book book1 = new Book(1, "Book1", "Aman", 50.34);
        System.out.println(DAO.addBook(book1));
        Book book2 = new Book(2, "Book2", "Aman2", 70.50);
        System.out.println(DAO.addBook(book2));
        System.out.println("Removed Book: " + DAO.removeBook(1));
        System.out.println("Books Available: " + DAO.getBooksAvailable());
        int Id = 2;
        Book bookDetails = DAO.getBookDetails(Id);
        if (bookDetails != null) {
            System.out.println("Book: " + bookDetails.getName() + " by " + bookDetails.getAuthor() + " and price is: " +bookDetails.getPrice());
        } else {
            System.out.println("Book Id " + Id + " not present.");
        }
    }
}
