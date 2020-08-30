package com.ausy_technologies.demospring.Mapper;

import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Model.DTO.UserDto;
import com.ausy_technologies.demospring.Repository.RoleRepository;
import com.ausy_technologies.demospring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // Adnotare generica de tip Stereotype.Ii spunem contextului ca aceasta este doar o simpla componenta ( un simplu bean).
           // Practic ne declaram propriul mapper ( cel care se ocupa de maparea unui User la un UserDto) !
public class UserMapper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserDto convertToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        List<String> roles= user.getRoleList().stream().map(u->u.getName()).collect(Collectors.toList());

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoleList(roles);
        return userDto;
    }
}
