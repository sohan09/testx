package auth0;

import play.mvc.*;
import play.data.*;
import static play.mvc.Controller.*;

public class RequestNonceStorage implements NonceStorage {

    public RequestNonceStorage() {

    }

    public String getState () {
        return (String) session("state");
    }

    public void setState(String nonce) {
        session("state", nonce);
    }
}