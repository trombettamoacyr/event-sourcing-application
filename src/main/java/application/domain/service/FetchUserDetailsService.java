package application.domain.service;

import application.domain.entity.UserEntity;
import application.domain.repository.UserRepository;
import application.integration.client.MyFakeApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchUserDetailsService {

    @Autowired
    private MyFakeApiClient myFakeApiClient;

    @Autowired
    private UserRepository userRepository;

    public void process(String userId) {

        var userDetailsDto = myFakeApiClient.getUserDetails(userId);
        var name = userDetailsDto.getName();
        var email = userDetailsDto.getEmail();

        var user = UserEntity.builder()
                .name(name)
                .email(email)
                .build();

        userRepository.save(user);
    }
}
