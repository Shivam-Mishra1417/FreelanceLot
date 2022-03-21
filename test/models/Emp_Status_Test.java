package models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the Employer Status Model
 * @author Shivam Mishra
 */
public class Emp_Status_Test {

    private static Emp_Status emp;

    /**
     * This method is used to setup the testing data
     */
    @BeforeClass
    public static void setup(){

        emp=new Emp_Status("verify","yes","no","yes","no","yes","yes","yes","no");
    }

    /**
     * This method is used to test the getter method for Payment Status
     */
    @Test
    public void getPaymentTest() {
        assertEquals("verify",emp.isPayment_verified());
    }

    /**
     * This method is used to test the getter method for Email Status
     */
    @Test
    public void getEmail() {
        assertEquals("yes",emp.isEmail_verified());
    }

    /**
     * This method is used to test the getter method for Deposit Status
     */
    @Test
    public void getDeposit() {
        assertEquals("no",emp.isDeposit_made());
    }

    /**
     * This method is used to test the getter method for Profile Verification Status
     */
    @Test
    public void getProfile(){
        assertEquals("yes",emp.isProfile_complete());
    }

    /**
     * This method is used to test the getter method for Phone Status
     */
    @Test
    public void getPhone(){
        assertEquals("no",emp.isPhone_verified());
    }

    /**
     * This method is used to test the getter method for Identity Verification Status
     */
    @Test
    public void getIdentity(){
        assertEquals("yes",emp.isIdentity_verified());
    }

    /**
     * This method is used to test the getter method for Facebook Status
     */
    @Test
    public void getFacebook(){
        assertEquals("yes",emp.isFacebook_connected());
    }

    /**
     * This method is used to test the getter method for Freelancer Status
     */
    @Test
    public void getFreelancer(){
        assertEquals("yes",emp.isFreelancer_verified_user());
    }

    /**
     * This method is used to test the getter method for Linked Status
     */
    @Test
    public void getLinked(){
        assertEquals("no",emp.isLinkedin_connected());
    }
}
