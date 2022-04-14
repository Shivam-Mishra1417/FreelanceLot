package models;

/**
 * This is the Model Class for Employer status
 * @author Shivam Mishra
 */

public class Emp_Status {
    private String payment_verified;
    private String email_verified;
    private String deposit_made;
    private String profile_complete;
    private String phone_verified;
    private String identity_verified;
    private String facebook_connected;
    private String freelancer_verified_user;
    private String linkedin_connected;

    /**
     *Parameterised Constructor with the required parameters of the class.
     *
     * @param payment_verified              Payment Status
     * @param email_verified                Email Status
     * @param deposit_made                  Deposit Status
     * @param profile_complete              Profile Status
     * @param phone_verified                Phone Verification Status
     * @param identity_verified             Identity Verification Status
     * @param facebook_connected            Facebook Status
     * @param freelancer_verified_user      Verifies User Status
     * @param linkedin_connected            Linked Status
     */

    public Emp_Status(String payment_verified, String email_verified, String deposit_made, String profile_complete, String phone_verified, String identity_verified, String facebook_connected, String freelancer_verified_user, String linkedin_connected) {
        this.payment_verified = payment_verified;
        this.email_verified = email_verified;
        this.deposit_made = deposit_made;
        this.profile_complete = profile_complete;
        this.phone_verified = phone_verified;
        this.identity_verified = identity_verified;
        this.facebook_connected = facebook_connected;
        this.freelancer_verified_user = freelancer_verified_user;
        this.linkedin_connected = linkedin_connected;
    }

    /**
     * This method verifies the payment verification status
     * @return String
     */

    public String isPayment_verified() {
        return payment_verified;
    }

    /**
     * This method verifies the Email of the Employer
     * @return String
     */
    public String isEmail_verified() {
        return email_verified;
    }

    /**
     * This method verifies the Deposit made by the employer
     * @return String
     */
    public String isDeposit_made() {
        return deposit_made;
    }

    /**
     * This method verifies that if the profile of the employer is complete or not
     * @return String
     */
    public String isProfile_complete() {
        return profile_complete;
    }

    /**
     * This method verifies the phone number
     * @return String
     */
    public String isPhone_verified() {
        return phone_verified;
    }

    /**
     * This method verifies the identity of the employer
     * @return String
     */
    public String isIdentity_verified() {
        return identity_verified;
    }


    /**
     * This method verifies if the user is connected to facebook or not
     * @return String
     */
    public String isFacebook_connected() {
        return facebook_connected;
    }

    /**
     * This method verifies if user is verified by Freelancer or not
     * @return String
     */
    public String isFreelancer_verified_user() {
        return freelancer_verified_user;
    }

    /**
     * This method verifies if the user is connected to linkedIn or not
     * @return
     */
    public String isLinkedin_connected() {
        return linkedin_connected;
    }

}
