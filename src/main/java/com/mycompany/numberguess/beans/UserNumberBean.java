package com.mycompany.numberguess.beans;

import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mark Van Weelden
 */
@Named
@SessionScoped
public class UserNumberBean implements Serializable {

    private Integer randomInt;
    private Integer userNumber;
    private String response;

    public UserNumberBean() {

        Random randomGR = new Random();
        randomInt = new Integer(randomGR.nextInt(10));
        System.out.println("Duke's number: " + randomInt);
    }

    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {

            //invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();

            return "Yay! You got it!";
        } else {

            return "<p>Sorry, " + userNumber + " isn't it.</p>"
                    + "<p>Guess again...</p>";
        }
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
}
