package edu.ktu.ds.lab1b.Bendoraitis;

import edu.ktu.ds.lab1b.util.Ks;
import java.util.Comparator;

/**
 *
 * @author Antanas
 */
public class BookTest {
    BookList books = new BookList();
    BookList booksEquals = new BookList();
    void execute()
    {
       // createBooks();
       // createBookList();
       // countJK();
       // appendBookList();
       // checkBookMarketFilters();
          checkBookMarketSorting();
    }
    void createBooks()
    {
        Book b1 = new Book("JK", "Hr", 2000, 30, 20);
        Book b2 = new Book("ImanuelKant", "Critique", 2015, 50, 35);
        Book b3 = new Book("Sokratas", "Gorgijas", 2016, 40, 36);
        Book b4 = new Book();
        Book b5 = new Book();
        Book b6 = new Book();
        b4.parse("JK Hr 1500 35 15");
        b5.parse("ImanuelKant Critique 1999 55 65");
        b6.parse("Aristotelis NichomakoEtika 2001 70 30");
        Ks.oun(b1);
        Ks.oun(b2);
        Ks.oun(b3);
        Ks.oun("Pirmų 3 leidinių tiražo vidurkis= " 
                + (b1.getEdition() + b2.getEdition() + b3.getEdition()) / 3);
        Ks.oun(b4);
        Ks.oun(b5);
        Ks.oun(b6);
        Ks.oun("Kitų 3 knygų tiražo suma= "
                + (b4.getPrice() + b5.getPrice() + b6.getPrice()));
    }
    void createBookList() {
        Book b1 = new Book("JK", "Hr", 2000, 30, 20);
        Book b2 = new Book("ImanuelKant", "Critique", 2015, 50, 35);
        Book b3 = new Book("Sokratas", "Gorgijas", 2016, 40, 36);
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.println("Pirmos 3 knygos");
        books.add("JK Hr 1500 35 15");
        books.add("ImanuelKant Critique 1999 55 65");
        books.add("Aristotelis NichomakoEtika 2001 70 30");

        books.println("Visos 6 knygos");
        books.forEach(System.out::println);
        Ks.oun("Pirmų 3 knygų leidimo vidurkis= "
                + (books.get(0).getEdition() + books.get(1).getEdition()
                + books.get(2).getEdition()) / 3);

        Ks.oun("Kitų 3 knygų kainų suma= "
                + (books.get(3).getPrice() + books.get(4).getPrice()
                + books.get(5).getPrice()));
          books.add(0, new Book("JK","Stone",2007,500,270));
          books.add(1, new Book("Dekartes","Metafizika",1998,950,77));
          books.println("Po įterpimų");  
          books.set(2, b3);
          Ks.oun("Pakeitimas");
          books.remove(0);
          books.remove(4);
          System.out.println("Paskutinis toks elementas esantis sąraše " 
                  + books.lastIndexOf(books.get(1)));
          books.println("Po išmetimų");
          books.remove(0); books.remove(0); books.remove(0);
          books.remove(0); books.remove(0); books.remove(0);
          books.println("Po visų išmetimų");
          books.remove(0);
          books.println("Po visų išmetimų");
          books.addLast(b1);
          books.addLast(b2);
          books.println("Pridedame į pabaigą");
          System.out.println("Pirmasis sąraše esantis toks elementas " 
                  + books.indexOf(b1));
          booksEquals.add(b1); booksEquals.add(b2);
          System.out.println("Ar objektai yra lygūs - " 
                  + books.equals(booksEquals));
          System.out.println("Ar objektai yra lygūs - " 
                  + books.equals(""));
    }

    void countJK() {
        int sk = 0;
        for (Book c : books) {
            if (c.getWriter().compareTo("JK") == 0) {
                sk++;
            }
        }
        Ks.oun("JK knygų yra = " + sk);
    }
    
     void appendBookList() {
        for (int i = 0; i < 5; i++) {
            books.add(new Book("TomasMoras", "Utopija",
                    2009 - i, 40 + i * 10, 360 - i * 20));
        }
        books.add("Aristotelis NichomakoEtika 1999 70 30");
        books.add("ImanuelKant Critique 1999 55 65");
        books.add("Sokratas Gorgijas 2016 40 36");
        books.add("Aristotelis NichomakoEtika 2001 70 30");
        books.println("Testuojamų kmnygų sąrašas");
        books.save("book.txt");
    }
      void checkBookMarketFilters() {
        BookMarket market = new BookMarket();

        market.allBooks.load("book.txt");
        market.allBooks.println("Bandomasis rinkinys");

        books = market.getNewerBooks(2001);
        books.println("Pradedant nuo 2001");

        books = market.getByPrice(20, 50);
        books.println("Kaina tarp 30 ir 60");

        books = market.getHighestEditionBooks();
        books.println("Didžiausias tiražas");

        books = market.getByWriterAndBook("Aristotelis NichomakoEtika");
        books.println("AristotelisNichomakoEtika");

        books = market.getByWriterAndBook("Sokratas Gorgijas");

        books.println("Turi būti, tik Sokratas Gorgijas");
        int n = 0;
        for (Book c : books) {
                            n++;
                // testuojame ciklo veikimą
        }
        Ks.oun("Sokratas kiekis = " + n);
    }
    
    void checkBookMarketSorting() {
        BookMarket market = new BookMarket();

        market.allBooks.load("ban.txt");
        Ks.oun("========" + market.allBooks.get(0));
        market.allBooks.println("Bandomasis rinkinys");
        market.allBooks.sortBuble(Book.byByWriterAndBook);
        market.allBooks.println("Rūšiavimas pagal Rašytoją ir Knygą");
        market.allBooks.sortBuble(Book.byPrice);
        market.allBooks.println("Rūšiavimas pagal kainą");
        market.allBooks.sortBuble(Book.byYearAndPrice);
        market.allBooks.println("Rūšiavimas pagal Metus ir Kainą");
        market.allBooks.sortBuble(byEdition);
        market.allBooks.sortBuble((a, b) -> Integer.compare(a.getEdition(),
                b.getEdition()));
        market.allBooks.println("Rūšiavimas pagal Leidimą");
        market.allBooks.sortBuble();
        market.allBooks.println("Rūšiavimas pagal compareTo - Kainą");
    }

    static Comparator byEdition = (Object obj1, Object obj2) -> 
    {
            int m1 = ((Book) obj1).getEdition();
            int m2 = ((Book) obj2).getEdition();
            // knygos leidimas atvirkščia mažėjančia tvarka, 
            //pradedant nuo didžiausios
            if (m1 < m2) {
                return 1;
            }
            if (m1 > m2) {
                return -1;
            }
            return 0;
    };
    public static void main(String... args) {
        new BookTest().execute();
    }
    
}
