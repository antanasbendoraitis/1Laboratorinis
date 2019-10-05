package edu.ktu.ds.lab1b.Bendoraitis;

import edu.ktu.ds.lab1b.util.Ks;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import edu.ktu.ds.lab1b.util.Parsable;

public class Book implements Parsable<Book>
{
    final static private int minYear = 1994;
    final static private double minPrice = 10.0;
    final static private double maxPrice = 120_000.0;
    
    private String writer;
    private String bookName;
    private int year;
    private int edition;
    private double price;
    


    @Override
    public final void parse(String data)
    {
        try {//ed-tai elementarūs duomenys atskirti tarpais
            Scanner ed = new Scanner(data);
            writer = ed.next();
            bookName = ed.next();
            year = ed.nextInt();
            edition = ed.nextInt();
            setPrice(ed.nextDouble());
        }
        catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie leidinį -> " + data);
        }
    }
    
     @Override
    public String toString() {  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s %4d %7d %8.1f %s",
                writer, bookName, year, edition, price, validate());
    }
        public String validate() {
        String error = "";
        int currentYear = LocalDate.now().getYear();
        if (year < minYear || year > currentYear) {
            error = "Netinkami leidimo metai, turi būti ["
                    + minYear + ":" + currentYear + "]";
        }
        if (price < minPrice || price > maxPrice) {
            error += " Kaina už leistinų ribų [" + minPrice
                    + ":" + maxPrice + "]";
        }
        return error;
    }
        @Override
    public int compareTo(Book otherBook) {
        // lyginame pagal svarbiausią požymį - kainą
        double otherPrice = otherBook.getPrice();
        if (price < otherPrice) {
            return -1;
        }
        if (price > otherPrice) {
            return +1;
        }
        return 0;
    }
    // sarankiškai priderinkite prie Lambda funkcijų
    public final static Comparator<Book> byByWriterAndBook
            = (Book book1, Book book2)->{
            // pradžioje pagal markes, o po to pagal modelius
            int cmp = (book1.getWriter() + " " + book1.getBookName()).
                    compareTo(book2.getWriter() + " " + book2.getBookName());
            if (cmp != 0) {
                return cmp;
            }
            return cmp = (book1.getWriter() + " " + book1.getBookName()).
                    compareTo(book2.getWriter() + " " + book2.getBookName());
    };
    public final static Comparator<Object> byPrice = 
            (Object obj1, Object obj2) -> {
            double price1 = ((Book) obj1).getPrice();
            double price2 = ((Book) obj2).getPrice();
            // didėjanti tvarka, pradedant nuo mažiausios
            if (price1 < price2) {
                return -1;
            }
            if (price1 > price2) {
                return 1;
            }
            return 0;
    };
    public final static Comparator<Object> byYearAndPrice = 
        // sarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
        (Object obj1, Object obj2) -> {
            Book b1 = (Book) obj1;
            Book b2 = (Book) obj2;
            // metai mažėjančia tvarka, esant vienodiems lyginama price
            if (b1.getYear() < b2.getYear()) {
                return 1;
            }
            if (b1.getYear() > b2.getYear()) {
                return -1;
            }
            if (b1.getPrice() < b2.getPrice()) {
                return 1;
            }
            if (b1.getPrice() > b2.getPrice()) {
                return -1;
            }
            return 0;
    };
    
    @Override
    public boolean equals(Object obj)
    {
        Book bk = (Book)obj;
        if (writer.equals(bk.writer) && bookName.equals(bk.bookName)) {
            if (year == bk.year && edition == bk.edition && price == bk.price) {
                return true;
            }
            return false;
        }
        return false;
    }
    public Book(String writer, String bookName, int year, int edition, double price) 
    {
        this.writer = writer;
        this.bookName = bookName;
        this.year = year;
        this.edition = edition;
        this.price = price;
    }

    public Book(String data){parse(data);}
    
    public Book(){}
        public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void main(String... args) {
        //System.out.print("sf");
        Locale.setDefault(new Locale("LT"));
        Book k = new Book("JK", "Hr", 2000, 30, 20);
        Book k1 = new Book();
        k1.parse("JK HarryPotter 2000 20 101");
        Ks.oun(k);
        Ks.oun(k1);
        System.out.println(k.bookName + "");
        Book a2 = new Book("ImanuelKant", "Critique", 2015, 50, 35);
        Book a3 = new Book();
        Book a4 = new Book();
        a3.parse("JK HarryPotter 2000 20 5,0");
        a4.parse("Sokratas NichomakoEt 1990 15  10,8");
        Ks.oun(k1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun(a4);
    }
}
