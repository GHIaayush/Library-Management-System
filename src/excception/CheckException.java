
//Author:Aayush Ghimire
package excception;

import java.io.Serializable;

public class CheckException extends Exception implements Serializable {
    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }
}
