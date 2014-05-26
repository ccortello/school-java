package assignment2;

/**
 * Created by cortello on 5/26/2014.
 */
public class BookTest {
    public static void main(String[] args) {
        new BookTest();
    }

    BookTest() {
        LibraryBook testLibraryBook = new LibraryBook(013123, "J.K. Rowling", "Harry Potter!");
        System.out.println(testLibraryBook);
    }
}
