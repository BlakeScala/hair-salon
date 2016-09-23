import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertTrue(client instanceof Client);
  }

  @Test
  public void getName_returnsName_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertEquals(client.getName(), "Cindy");
  }

  @Test
  public void getEmail_returnsEmail_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertEquals(client.getEmail(), "cindyt@gmail.com");
  }

  @Test
  public void getPhone_returnsPhone_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertEquals(client.getPhone(), "878-484-2947");
  }

  @Test
  public void getStylistId_returnsStylistId_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertEquals(client.getStylistId(), 1);
  }
}
