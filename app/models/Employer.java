package models;
import models.projects;

import java.util.List;
/**
 * This is the Model Class for Employer Data
 * @author Shivam Mishra
 */
public class Employer {

    private String id;
    private String username;
    private String closed;
    private String profile_description;
    private String hourly_rate;
    private String registration_date;
    private String limited_account;
    private String display_name;
    private String tagline;

    private String country;
    private String city;

    private String role;
    private Emp_Status es;

    private String currency;
    private String language;
    private String portfolio_count;

    private String public_name;
    private String company;

    List<projects> pro;

    /**
     * Parameterised Constructor with the required parameters of the class.
     * @param id                        owner id
     * @param username                  username of Employer
     * @param closed                    project closed
     * @param profile_description       profile description
     * @param hourly_rate               hourly rate
     * @param registration_date         registration date
     * @param limited_account           limited account
     * @param display_name              display name
     * @param tagline                   tag line of the Employer
     * @param country                   Employer country
     * @param city                      Employer city
     * @param role                      role
     * @param es                        Employer Status
     * @param currency                  currency
     * @param language                  language
     * @param portfolio_count           portfolio count
     * @param public_name               public name
     * @param company                   company
     * @param pro                       project
     */
    public Employer(String id, String username, String closed, String profile_description, String hourly_rate, String registration_date, String limited_account, String display_name, String tagline, String country, String city, String role, Emp_Status es, String currency, String language, String portfolio_count, String public_name, String company, List<projects> pro) {
        this.id = id;
        this.username = username;
        this.closed = closed;
        this.profile_description = profile_description;
        this.hourly_rate = hourly_rate;
        this.registration_date = registration_date;
        this.limited_account = limited_account;
        this.display_name = display_name;
        this.tagline = tagline;
        this.country = country;
        this.city = city;
        this.role = role;
        this.es = es;
        this.currency = currency;
        this.language = language;
        this.portfolio_count = portfolio_count;
        this.public_name = public_name;
        this.company = company;
        this.pro = pro;
    }

    /**
     * This method gets owner id
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * This method gets Username
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method returns if project is closed or not
     * @return String
     */
    public String getClosed() {
        return closed;
    }

    /**
     * This method gets profile description
     * @return String
     */
    public String getProfile_description() {
        return profile_description;
    }

    /**
     * This method gets hourly rate
     * @return String
     */
    public String getHourly_rate() {
        return hourly_rate;
    }

    /**
     * This method gets registration date
     * @return String
     */
    public String getRegistration_date() {
        return registration_date;
    }

    /**
     * This method gets limited account
     * @return String
     */
    public String getLimited_account() {
        return limited_account;
    }

    /**
     * This method gets display name
     * @return String
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * This method gets tag line of the project
     * @return String
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * This method gets country of the employer
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method gets city of the employer
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * This method returns role
     * @return String
     */
    public String getRole() {
        return role;
    }

    /**
     * This method gets Employer Status
     * @return String
     */
    public Emp_Status getEs() {
        return es;
    }

    /**
     * This method gets currency
     * @return String
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * This method gets language
     * @return String
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This method gets portfolio count
     * @return String
     */
    public String getPortfolio_count() {
        return portfolio_count;
    }

    /**
     * This method gets public name
     * @return String
     */
    public String getPublic_name() {
        return public_name;
    }

    /**
     * This method gets company name
     * @return String
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method gets projects of employer
     * @return String
     */
    public List<projects> getPro() {
        return pro;
    }
}


