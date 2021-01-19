package sber;

import java.io.Serializable;
import java.util.Date;

public class Message  implements Serializable {
    private String login;
    private String message;
    private Date date;

    public Message() {
        this.login = "";
        this.message = "";
        this.date = new Date();
    }

    public Message(String auth, String message, Date date) {
        this.login = auth;
        this.message = message;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String auth) {
        this.login = auth;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
