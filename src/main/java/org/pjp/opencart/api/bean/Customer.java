package org.pjp.opencart.api.bean;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public final class Customer {

    private static final int NAME_LENGTH = 32;

    private static final int TELEPHONE_MAX_LENGTH = 32;

    private static final int TELEPHONE_MIN_LENGTH = 3;

    private final String firstname;

    private final String lastname;

    private final String email;

    private final String telephone;

    public Customer(String firstname, String lastname, String email, String telephone) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;

        Preconditions.checkArgument(!Strings.isNullOrEmpty(firstname) && (firstname.length() <= NAME_LENGTH), "First Name must be between 1 and 32 characters!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(lastname) && (lastname.length() <= NAME_LENGTH), "Last Name must be between 1 and 32 characters!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(email) &&  email.matches("^(.+)@(\\S+)$"), "E-Mail Address does not appear to be valid!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(telephone) && (telephone.length() >= TELEPHONE_MIN_LENGTH) && (telephone.length() <= TELEPHONE_MAX_LENGTH), "Telephone must be between 3 and 32 characters!");
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Customer [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", telephone=" + telephone + "]";
    }


}
