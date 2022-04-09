import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

public class CabInvoiceGeneratorTest {
    CabInvoiceGenerator inVoiceGenerator = null;

    @Before
    public void setUp() throws Exception {
        inVoiceGenerator = new CabInvoiceGenerator();
    }

    /**
     * Test Case 1 : given distance and time should return total fare
     */

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 4.0;
        int time = 10;
        double totalFare = inVoiceGenerator.calculateFare(distance, time);

        Assert.assertEquals(50.0, totalFare, 0.0);
    }

    /**
     * Test Case 2 : given distance and time should return minimum fare
     */
    @Test
    public void givenDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 2;
        double totalFare = inVoiceGenerator.calculateFare(distance, time);

        Assert.assertEquals(5.0, totalFare, 0.0);
    }

    /**
     * Test Case 3 : To get total fare given multiple rides
     */
    @Test
    public void givenMultipleRides_ShouldReturnInVoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)};
        InvoiceSummary summary = inVoiceGenerator.calculateFare(rides);

        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);

        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    /**
     * Test Case 4 : To get list of rides from ride repository when user id is given
     */
    @Test
    public void givenUserID_ShouldGetTheListOfRidesFromRepo_ReturnInVoice() {
        int userID = 1;
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)};
        InvoiceSummary summary = inVoiceGenerator.calculateFare(rides);

        Hashtable<Integer,Ride[]> listOfRides = new Hashtable<>();

        listOfRides.put(userID,rides);

        InvoiceSummary expectedInVoice = inVoiceGenerator.inVoiceService(listOfRides);
        Assert.assertEquals(expectedInVoice,summary);
    }
}