
//Author:Aayush Ghimire
package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.LibraryMember;
import business.MyStage;

public interface DataAccess {
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void loadMemberMap(HashMap<String, LibraryMember> members);
	public void loadBookMap(HashMap<String, Book> books);
	public void addMember(LibraryMember member);
}
