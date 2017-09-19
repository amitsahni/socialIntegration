package fbconnect.callback;

import com.facebook.FacebookException;

/**
 * Created by amit on 4/2/17.
 */

public class FBException extends FacebookException {

    public FBException(String message) {
        super(message);
    }
}
