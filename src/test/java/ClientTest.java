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
  public void getStylist_id_returnsStylist_id_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertEquals(client.getStylist_id(), 1);
  }

  @Test
  public void equals_returnsTrueIfAttributesAreTheSame() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    Client clientTwo = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    assertTrue(client.equals(clientTwo));
  }

  @Test
  public void all_returnsAllInstancesOfClient() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    Client clientTwo = new Client("Billy", "878-224-9237", "billy@gmail.com", 1);
    client.save();
    clientTwo.save();
    assertTrue(Client.all().get(0).equals(client));
    assertTrue(Client.all().get(1).equals(clientTwo));
  }

  @Test
  public void save_savesClientToDatabase() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    client.save();
    assertTrue(Client.all().get(0).equals(client));
  }

  @Test
  public void find_findsCorrectClient_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    Client clientTwo = new Client("Billy", "878-224-9237", "billy@gmail.com", 1);
    client.save();
    clientTwo.save();
    assertEquals(Client.find(client.getId()), client);
    assertEquals(Client.find(clientTwo.getId()), clientTwo);
  }

  @Test
  public void update_updatesClient_true() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    Client clientTwo = new Client("Billy", "878-224-9237", "billy@gmail.com", 1);
    client.save();
    clientTwo.save();
    clientTwo.update("Billy", "878-224-9237", "billywilliams@gmail.com", 2, clientTwo.getId());
    assertEquals(Client.find(clientTwo.getId()).getEmail(),  "billywilliams@gmail.com");
  }

  @Test
  public void delete_deletesClient() {
    Client client = new Client("Cindy", "878-484-2947", "cindyt@gmail.com", 1);
    client.save();
    int clientId = client.getId();
    client.delete(clientId);
    assertEquals(null, Client.find(clientId));
  }
}
