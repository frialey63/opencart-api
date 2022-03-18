package org.pjp.opencart.api.bean;

/**
 * Bean to represent an address.
 * @author developer
 *
 */
public class Address {

    private final String firstname;

    private final String lastname;

    private final String address1;

    private final String city;

    private final String countryId;

    private final String zoneId;

    /**
     * @param firstname First name
     * @param lastname Last name
     * @param address1 Address 1
     * @param city City
     * @param countryId Country ID
     * @param zoneId Zone ID
     */
    public Address(String firstname, String lastname, String address1, String city, String countryId, String zoneId) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address1 = address1;
        this.city = city;
        this.countryId = countryId;
        this.zoneId = zoneId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public String getCity() {
        return city;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getZoneId() {
        return zoneId;
    }

    @Override
    public String toString() {
        return "Address [firstname=" + firstname + ", lastname=" + lastname + ", address1=" + address1
                + ", city=" + city + ", countryId=" + countryId + ", zoneId=" + zoneId + "]";
    }


}
