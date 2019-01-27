//Author:Aayush Ghimire

package controller;

import business.*;
import dataaccess.*;
import excception.CheckException;
import excception.LoginException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {
    public static Auth currentAuth = null;
    public static Action currentAction = null;

    /*
     login using user id and password. for the invalid id or password, it throws the login exception */

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();

    }

    /*creates the checkout record for the member with the memberId and the book of isbn in the file*/
    @Override
    public void checkout(String memberId, String isbn) throws CheckException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> mapMember = da.readMemberMap();
        if (!mapMember.containsKey(memberId)) {
            throw new CheckException("Member with ID :" + memberId + " not found");
        }
        HashMap<String, Book> mapBook = da.readBooksMap();
        if (!mapBook.containsKey(isbn)) {
            throw new CheckException("Book with isbn: " + isbn + " is not found");
        }
        LibraryMember libraryMember = mapMember.get(memberId);

        Book book = mapBook.get(isbn);
        if (!book.isAvailable()) throw new CheckException(" Book is not available");

        int copyNo = book.AvailableCopyNo();
        BookCopy bookCopy = book.getCopy(copyNo);
        bookCopy.setAvailable(false);

        // update the checkout info of member
        libraryMember.CheckOut(bookCopy,LocalDate.now());
        book.updateCopies(bookCopy);
        mapMember.put(memberId, libraryMember);
        mapBook.put(isbn, book);

        //List<LibraryMember> updatedList = getPreviousLibraryMemberAndUpdate(mapMember, libraryMember, memberId);

        // save the updated member;
        da.loadMemberMap(mapMember);

        //save the updated book
        da.loadBookMap(mapBook);

    }

    /*updates the member's record of returning the book*/
    @Override
    public void checkIn(String memberId, String isbn, int copyNo) throws CheckException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> mapMember = da.readMemberMap();
        if (!mapMember.containsKey(memberId)) {
            throw new CheckException("Member with ID :" + memberId + " not found");
        }
        HashMap<String, Book> mapBook = da.readBooksMap();
        if (!mapBook.containsKey(isbn)) {
            throw new CheckException("Book with isbn: " + isbn + " is not found");
        }


        LibraryMember libraryMember = mapMember.get(memberId);
        if (libraryMember.getCheckOutRecord() == null) throw new CheckException("Incorrect Info!!!");

        // update the checkout info of member
        Book book = mapBook.get(isbn);
        libraryMember.checkIn(book,copyNo);

        mapMember.put(memberId, libraryMember);
        mapBook.put(isbn, book);

        // save the updated member;
        da.loadMemberMap(mapMember);

        //save the updated book;
        da.loadBookMap(mapBook);

    }


    /*generates the list of all available members*/
    @Override
    public List<String> allMemberIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    /*generates the list of all the available books in the record*/
    @Override
    public List<String> allBookIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }

    /*Creates the list of the checkoutRecordEntry of a member with id as memberId*/
    @Override
    public List<CheckOutRecordEntry> getAllCheckoutRecordEntry(String memberId) {

        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> mapMember = da.readMemberMap();
        if (!mapMember.containsKey(memberId)) {
            return null;
        }
        if (mapMember.get(memberId).getCheckOutRecord() == null) return null;

        return mapMember.get(memberId).getCheckOutRecord().getCheckOutRecordEntryList();
    }


}
