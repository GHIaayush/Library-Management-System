
//Author:Aayush Ghimire
package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckOutRecordEntry implements Serializable {
    private String memberId;
    private BookCopy bookCopy;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public CheckOutRecordEntry(String memberId, BookCopy bookCopy, LocalDate todayDate,
                               LocalDate dueDate, LocalDate returnDate) {
        this.memberId = memberId;
        this.bookCopy = bookCopy;
        this.checkOutDate = todayDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
