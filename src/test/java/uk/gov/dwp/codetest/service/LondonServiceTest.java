package uk.gov.dwp.codetest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LondonServiceTest {
  private LondonService londonService;

      private static final String londonUsersJson ="[{\"id\":135,\"first_name\":\"Mechelle\",\"last_name\":\"Boam\",\"email\":\"mboam3q@thetimes.co.uk\",\"ip_address\":\"113.71.242.187\",\"latitude\":-6.5115909,\"longitude\":105.652983}]";
    private static final String allUsersJson ="[{\"id\":135,\"first_name\":\"John\",\"last_name\":\"Boam\",\"email\":\"john3boam@thetimes.co.uk\",\"ip_address\":\"113.71.242.185\",\"latitude\":51.50722,\"longitude\":-0.1275}]";

   private final WebClient londonRegisteredUsersMock = WebClient.builder().baseUrl("https://london-api.onrender.com/city/London/users")
      .exchangeFunction(clientRequest -> Mono.just(ClientResponse.create(HttpStatus.OK)
          .header("content-type", "application/json")
        .body(londonUsersJson)
        .build()))
          .build();

   private final WebClient allUsersMock = WebClient.builder().baseUrl("https://london-api.onrender.com/users")
           .exchangeFunction(clientRequest -> Mono.just(ClientResponse.create(HttpStatus.OK)
                   .header("content-type", "application/json")
                   .body(allUsersJson)
                   .build()))
           .build();
  @BeforeEach
  public void setup() {
    londonService = new LondonService(londonRegisteredUsersMock);
  }

  @Test
  void getUsers() {
    londonService.getUsers().subscribe( users -> {
        assertEquals(1, (long) users.size(), "The number of users should be 1");
        assertNotNull(users);
            }

    );


  }

  @Test
   void getUsersInLondon() {
    londonService.getUsersInLondon().subscribe(
            users -> {
                assertEquals(1, (long) users.size(), "The number of users should be 1");
                assertNotNull(users);
            }
    );

  }
}