
//Author:Aayush Ghimire
package business;

import java.io.Serializable;
import java.util.List;

public class CheckOutRecord implements Serializable {
    private List<CheckOutRecordEntry> checkOutRecordEntryList;

    public CheckOutRecord(List<CheckOutRecordEntry> checkOutRecordEntryList) {
        this.checkOutRecordEntryList = checkOutRecordEntryList;
    }

    public List<CheckOutRecordEntry> getCheckOutRecordEntryList() {
        return checkOutRecordEntryList;
    }
}
