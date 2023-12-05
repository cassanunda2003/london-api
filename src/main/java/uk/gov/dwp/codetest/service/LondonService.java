package uk.gov.dwp.codetest.service;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.gov.dwp.codetest.domain.User;
import uk.gov.dwp.codetest.util.DistanceCalculator;

@RequiredArgsConstructor
@Service
public class LondonService {

  private final WebClient londonRegisteredUsers;
  private final WebClient allUsers;

  public List<User> getUsers() {
    // TODO : get all users

    Mono<List<User>> response = londonRegisteredUsers.get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<User>>() {});

      return response.block();
  }

  public List<User> getUsersInLondon() {
    // TODO : get all users in London

    Mono<List<User>> response = allUsers.get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<User>>() {});

      return Objects.requireNonNull(response.block())
            .stream()
            .filter(c -> DistanceCalculator.withinGivenMilesOfLondon(50, c.getLatitude(),c.getLongitude() )).toList();

  }


}
