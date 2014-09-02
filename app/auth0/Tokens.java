package auth0;

import play.mvc.*;
import play.data.*;
import static play.mvc.Controller.*;

public class Tokens {
    private String idToken;
    private String accessToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tokens tokens = (Tokens) o;

        if (accessToken != null ? !accessToken.equals(tokens.accessToken) : tokens.accessToken != null) return false;
        if (idToken != null ? !idToken.equals(tokens.idToken) : tokens.idToken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idToken != null ? idToken.hashCode() : 0;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        return result;
    }

    public Tokens(String idToken, String accessToken) {
        this.idToken = idToken;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getIdToken() {
        return idToken;
    }
}