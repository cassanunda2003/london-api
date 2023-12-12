package uk.gov.dwp.codetest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uk.gov.dwp.codetest.domain.User;
import uk.gov.dwp.codetest.service.LondonService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private LondonService londonService;

    @GetMapping("/users")
    public Mono<List<User>> users() {
        return londonService.getUsers();
    }

    @GetMapping("/usersInLondon")
    public Mono<List<User>> usersInLondon() {
        return londonService.getUsersInLondon();
    }
}
