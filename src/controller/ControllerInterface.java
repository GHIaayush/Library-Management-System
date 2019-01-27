
//Author:Aayush Ghimire
package controller;

import excception.CheckException;
import business.CheckOutRecordEntry;
import excception.LoginException;

import java.util.List;

public interface ControllerInterface {
    public void login(String id, String password) throws LoginException;

    public void checkout(String memberId, String isbn) throws CheckException;

    public void checkIn(String memberId, String isbn, int copyNo) throws CheckException;

    public List<String> allMemberIds();

    public List<String> allBookIds();

    public List<CheckOutRecordEntry> getAllCheckoutRecordEntry(String memberId);

}
