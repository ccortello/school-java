package assignment2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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

        GregorianCalendar testGregorianCalendar = new GregorianCalendar();
        Date testDate = testGregorianCalendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        System.out.println(sdf.format(testDate));
    }
}
