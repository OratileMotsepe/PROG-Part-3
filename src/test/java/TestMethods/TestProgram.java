/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestMethods;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class TestProgram {
    
    @Test
    public void testUsernameValid() {
         
        Login username = new Login();
        
        String expected = "kyle_1";
        String actual = username.checkUserName();
        assertEquals(expected, actual,"Welcome<user first name>,<user last name> it was great to see you.");
    }
    
    @Test
    public void testUsernameInvalid() {
         
        Login username = new Login();
        
        String expected = "kyle!!!!!!!";
        String actual = username.checkUserName();
        assertEquals(expected, actual,"Username is not correctly formatted; please ensure that your username caontains an underscore and is no more than five characters in length.");
    }
    
    @Test
    public void testPasswordcomplexity() {
         
        Login username = new Login();
        
        String expected = "Ch&&sec@ke99!";
        String actual = username.checkPasswordComplexity();
        assertEquals(expected, actual,"Password successfully captured.");
    }
    
    @Test
    public void checkCellPhoneNumberValid() {
         
        Login username = new Login();
        
        String expected = "+27838968976";
        String actual = username.checkPasswordComplexity();
        assertEquals(expected, actual,"Cell number successfully captured.");
    }
    
    @Test
    public void checkCellPhoneNumberInvalid() {
         
        Login username = new Login();
        
        String expected = "08966553";
        String actual = username.checkPasswordComplexity();
        assertEquals(expected, actual,"Cell number is incorrectly formatted or does not caontain an international code; please correct the number and try again.");
    }
    
    @Test
    void testLoginSuccessful() {
        
        Login username = new Login("Kyle", "Doe", "kyle_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(Login.loginUser("kyle_1", "Ch&&sec@ke99!", username));
    }

    @Test
    void testLoginFailed() {
        Login username = new Login("Kyle", "Doe", "kyle_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(Login.loginUser("kyle_1", "wrongPass", username));
    }

    @Test
    void testUsernameCorrectlyFormatted() {
        assertTrue(Login.checkUserName("kyle_1"));
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        assertFalse(Login.checkUserName("njiuop"));
    }

    @Test
    void testPasswordMeetsComplexity() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        assertFalse(Login.checkPasswordComplexity("fail"));
    }

    @Test
    void testPhoneNumberCorrectlyFormatted() {
        assertTrue(Login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testPhoneNumberIncorrectlyFormatted() {
        assertFalse(Login.checkCellPhoneNumber("08966553"));
    }
    
    @Test
    void testGenerateMessageID() {
        assert
    }
    
    @Test 
    void testCheckRecipientCell() {
        assert
    }
    
    @Test 
    void testCreateMessageHash() {
        assert
    }
    
    @Test 
    void testSentMessage() {
        assert
    }
    
    @Test
    void testPrintMessages() {
        assert
    }
    
    
}
