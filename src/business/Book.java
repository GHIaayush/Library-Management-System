//Author:Aayush Ghimire
package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
final public class Book implements Serializable {

    private static final long serialVersionUID = 6110690276685962829L;
    private BookCopy[] copies;
    private List<Author> authors;
    private String isbn;
    private String title;
    private int maxCheckoutLength;

    public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = Collections.unmodifiableList(authors);
        copies = new BookCopy[]{new BookCopy(this, 1, true)};

    }

    public void updateCopies(BookCopy copy) {
        for (int i = 0; i < copies.length; ++i) {
            BookCopy c = copies[i];
            if (c.equals(copy)) {
                copies[i] = copy;

            }
        }
    }


    public List<Integer> getCopyNums() {
        List<Integer> retVal = new ArrayList<>();
        for (BookCopy c : copies) {
            retVal.add(c.getCopyNum());
        }
        return retVal;

    }

    public void addCopy() {
        BookCopy[] newArr = new BookCopy[copies.length + 1];
        System.arraycopy(copies, 0, newArr, 0, copies.length);
        newArr[copies.length] = new BookCopy(this, copies.length + 1, true);
        copies = newArr;
    }


    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Book b = (Book) ob;
        return b.isbn.equals(isbn);
    }


    public int getNumCopies() {
        return copies.length;
    }

    public String getTitle() {
        return title;
    }

    public BookCopy[] getCopies() {
        return copies;
    }
    public void setCopies(BookCopy[] copies){
        this.copies = copies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookCopy getCopy(int copyNum) {
        for (BookCopy c : copies) {
            if (copyNum == c.getCopyNum()) {
                return c;
            }
        }
        return null;
    }

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }


    public boolean isAvailable() {
        for (BookCopy c : copies) {
            if (c.isAvailable())
                return true;
        }
        return false;
    }

    public int AvailableCopyNo(){
        for (BookCopy c : copies) {
            if (c.isAvailable())
                return c.getCopyNum();
        }
        return 0;
    }

    public BookCopy getAvailableCopy(){
        for(BookCopy b: copies){
            if(b.isAvailable()){
                return b;
            }
        }
        return null;
    }


}
