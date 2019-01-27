//Author:Aayush Ghimire

package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import controller.ControllerInterface;
import controller.SystemController;
import excception.CheckException;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckOutRecord checkOutRecord ;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	/* creates the checkoutRecordEntry for the member and updates in the checkoutRecord */
	public void CheckOut(BookCopy bookCopy, LocalDate checkOutDate){
		long checkoutLength = bookCopy.getBook().getMaxCheckoutLength();

		List<CheckOutRecordEntry> checkOutRecordEntries = new ArrayList<>();
		ControllerInterface controller = new SystemController();
		List<CheckOutRecordEntry> checkOutRecordEntryList = controller.getAllCheckoutRecordEntry(memberId);
		if (checkOutRecordEntryList != null) {
			checkOutRecordEntries = checkOutRecordEntryList;
		}

		checkOutRecordEntries.add(new CheckOutRecordEntry(memberId, bookCopy, checkOutDate, checkOutDate.plusDays(checkoutLength), null));
		checkOutRecord = new CheckOutRecord(checkOutRecordEntries);
	}


	/*updates the checkoutRecordEntry with the return date of the book*/
	public void checkIn(Book book,int copyNo) throws CheckException {
		List<CheckOutRecordEntry> checkOutRecordEntries = getCheckOutRecord().getCheckOutRecordEntryList();

		BookCopy returnedBookCopy = new BookCopy(book, copyNo, true);
		boolean checkedIn = false;
		for (CheckOutRecordEntry checkOutRecordEntry : checkOutRecordEntries) {
			if (returnedBookCopy.equals(checkOutRecordEntry.getBookCopy()) &&
					!checkOutRecordEntry.getBookCopy().isAvailable()) {
				checkOutRecordEntry.setReturnDate(LocalDate.now());
				checkOutRecordEntry.setBookCopy(returnedBookCopy);
				book.updateCopies(returnedBookCopy);
				checkedIn = true;
				break;
			}
		}

		if (!checkedIn) throw new CheckException("Incorrect Info!!! ");
        checkOutRecord = new CheckOutRecord(checkOutRecordEntries);
    }

	public CheckOutRecord getCheckOutRecord() {
		return checkOutRecord;
	}

	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
