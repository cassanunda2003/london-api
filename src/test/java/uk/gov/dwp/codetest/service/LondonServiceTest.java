package uk.gov.dwp.codetest.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.dwp.codetest.domain.User;

class LondonServiceTest {
  private LondonService londonService;

  @BeforeEach
  public void setup() {
    londonService = new LondonService();
  }

  @Test
  void getUsers() {
    final List<User> users = londonService.getUsers();
    System.out.println("\n\nThis is the user list" + users.toString());
    assertNotNull(users);
  }

  @Test
  void getUsersInLondon() {
    final List<User> users = londonService.getUsersInLondon();
    System.out.println("\n\nThis is the user list" + users.toString());
    assertNotNull(users);
  }
}