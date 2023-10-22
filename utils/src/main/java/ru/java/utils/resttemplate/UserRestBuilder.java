package ru.java.utils.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.java.entityservice.entity.User;

@Component
public class UserRestBuilder {

    private static final String baseUrl = "http://localhost:8765/users-service/user/";

    public boolean userExists(Long userId){

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> request = new HttpEntity(userId);
        ResponseEntity<User> response = null;
        try {
            response = restTemplate.exchange(baseUrl+"/id", HttpMethod.POST, request, User.class);
            if (response.getStatusCode() == HttpStatus.OK) { // если статус был 200
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
