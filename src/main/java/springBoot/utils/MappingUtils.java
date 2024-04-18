package springBoot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import springBoot.domain.User;
import springBoot.dto.UserDto;

@Service
public class MappingUtils {
    public UserDto mapToProductDto(User user){
        UserDto userDto = new UserDto();
        userDto.setCar(user.getCar());
        userDto.setId(user.getId());
        return userDto;
    }
}
