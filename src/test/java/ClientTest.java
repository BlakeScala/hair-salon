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
}
