package auth0;

import play.mvc.*;
import play.data.*;
import static play.mvc.Controller.*;

public interface NonceStorage {
    String getState();

    void setState(String nonce);
}