package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Mapper.UserMapper;
import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Model.DTO.UserDto;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController  //  Este o adnotare pentru crearea unui Api de tip REST
@RequestMapping("/api/users")  // Aceasta adnotare mapeaza  cereri( request-uri) HTTP pe un controller (de tip REST). Putem aplica apoi adnotări suplimentare (@GetMapping,@PostMapping ,etc) la nivel de metodă pentru a face mapările mai specifice metodelor de gestionare (get,post,update,delete).
public class UserController {

    @Autowired  // dependency injection
    private UserService userService;

    @Autowired  // dependency injection ( trebuie sa injectam si bean-ul de tip Mapper , altfel nu s ar face maparea in controller!)
    private UserMapper userMapper;



    @PostMapping("/addRole")  // De exemplu adnotarea @PostMapping asigura ca request-urile HTTP de tip POST sunt mapate la metoda saveRole!!!
    public Role saveRole(@RequestBody Role role) {


        Role roleAdded = this.userService.saveRole(role);
        return roleAdded;
    }


    @PostMapping("/addRole2")  // Impachetam request-ul pentru a obtine un raspuns complet HTTP ( cu body, headers si status)
    public ResponseEntity<Role> saveRole2(@RequestBody Role role)
    {
       Role roleAdded= this.userService.saveRole(role);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","addNewRole");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(roleAdded);

    }


    @PostMapping("/addUser")
    public User saveUser(@RequestBody User user) {   //  adnotarea @RequestBody mapează corpul request-ului HTTP la un obiect de transfer , permițând deserializarea automată  a corpului (body-ul) request-ului, intr-un obiect Java. ( din JSON intr-un obiect Java)
        User userAdded = this.userService.saveUser(user);
        return userAdded;
    }

    @PostMapping("/addUser2/{idRole}")
    public User saveUser2(@RequestBody User user, @PathVariable int idRole)  //@PathVariable extrage aici valorea id -ului  din URL si il mapeaza la parametrul idRole din metoda saveUser2
    {
        return this.userService.saveUser2(user,idRole);

    }

    @PostMapping("/addUser3/{roleList}")
    public User saveUser3(@RequestBody User user , @PathVariable List<Role> roleList)
    {
        return this.userService.saveUser3(user,roleList);
    }

    @GetMapping("/findRoleBy/{id}")
    public Role findRoleById(@PathVariable int id)
    {
  return this.userService.findRoleById(id);
    }

    @GetMapping("/findAllRoles")
    public List<Role> findAllRoles()
    {
        return  userService.findAllRoles();
    }


    @GetMapping("/allUsers")
    public List<User> findAllUsers()
    {
        return this.userService.findAllUsers();
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);
    }

    @GetMapping("/getUserById/{id}")
    public User  getUserById(@PathVariable int id)
    {
        return this.userService.findUserById(id);
    }


    @GetMapping("/getUserDtoById/{id}") // extragem un UserDto dupa id
    public UserDto getUserDtoById(@PathVariable int id)
    {
       User user = this.userService.findUserById(id);
       UserDto userDto = this.userMapper.convertToUserDto(user);
       return userDto;

    }

    @GetMapping("/getAllUsersDto")  // returnam lista de UseriDto
    public List<UserDto> getAllUsersDto()
    {
       List<User> users= this.userService.findAllUsers();
       List<UserDto> usersDto = users.stream().map(u->this.userMapper.convertToUserDto(u)).collect(Collectors.toList());
       return usersDto;
    }

}
