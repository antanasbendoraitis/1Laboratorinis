
package edu.ktu.ds.lab1b.Bendoraitis;

/**
 *
 * @author Antanas
 */
public class BookMarket {
    public BookList allBooks = new BookList();
    
    //This method back book that yearlier made than limit
    public BookList getNewerBooks(int fromYear)
    {
        BookList books = new BookList();
        for(Book b : allBooks)
        {
            if(b.getYear() >= fromYear)
            {
                books.add(b);
            }
        }
        return books;
    }
    
    //formating list of books that price are between limits
    public BookList getByPrice(int fromPrice, int toPrice)
    {
                BookList books = new BookList();
                for(Book b : allBooks)
                {
                    if (b.getPrice() >= fromPrice && b.getPrice() <= toPrice) {
                        books.add(b);
                    }
                }
                return books;
    }
    
    //formating list of books, that have the biggest edition
    public BookList getHighestEditionBooks()
    {
        BookList books = new BookList();
        //formating list with a maximal meaning in one checking time
        int maxEdition = 0;
        for (Book b : allBooks) {
            int edition = b.getEdition();
            if (edition >= maxEdition) {
                if (edition > maxEdition) {
                    books.clear();
                    maxEdition = edition;
                }
                books.add(b);
            }
        }
        return books;
    }
    
    //formating list of books, that was writen this writer
    public BookList getByWriterAndBook(String writer)
    {
        BookList books = new BookList();
        for (Book b : allBooks) {
            String bookWriterAndEdition = b.getWriter() + " " + b.getBookName();
            if (bookWriterAndEdition.startsWith(writer)) {
                books.add(b);
            }
        }
        return books;
    }
}
