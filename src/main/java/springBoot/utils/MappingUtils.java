package springBoot.utils;

import org.springframework.stereotype.Service;
import springBoot.domain.User;
import springBoot.dto.UserDto;

@Service
public class MappingUtils {
    public UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setCar(user.getCar());
        userDto.setId(user.getId());
        return userDto;
    }
}
