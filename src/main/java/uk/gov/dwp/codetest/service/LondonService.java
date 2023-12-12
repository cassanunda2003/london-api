package uk.gov.dwp.codetest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.gov.dwp.codetest.domain.User;
import uk.gov.dwp.codetest.util.DistanceCalculator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LondonService {

  @Autowired
  private final WebClient webClient;

  public Mono<List<User>> getUsers() {
    // TODO : get all users

    return webClient.get().uri("https://london-api.onrender.com/city/London/users")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<User>>() {});
  }

  public Mono<List<User>> getUsersInLondon() {

    return webClient.get().uri("https://london-api.onrender.com/users")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<User>>() {}).map(
                    users -> users.stream().filter(c -> DistanceCalculator.withinGivenMilesOfLondon(50, c.getLatitude(), c.getLongitude())).toList()
            );


  }


}
