package vn.com.greenacademy.shopping.Model;

/**
 * Created by ADMIN on 7/6/2017.
 */

public class Md_Account {
    String Token;
    int Status;
    String Description;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
