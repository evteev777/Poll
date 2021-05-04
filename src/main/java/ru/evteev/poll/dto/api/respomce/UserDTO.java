package ru.evteev.poll.dto.api.respomce;

import lombok.Data;

@Data
public class UserDTO {

    String username;
    String password;
    boolean enabled;
}
