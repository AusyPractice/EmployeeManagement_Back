package com.ausy_technologies.demospring.Service;

import com.ausy_technologies.demospring.Error.UserNotFoundException;
import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Repository.RoleRepository;
import com.ausy_technologies.demospring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service  //  Marcam aceasta clasa( care este un bean!) cu @Service ,pentru a-i spune contextului de Spring ca UserService este clasa unde ne definim toata logica aplicatiei noastre. Practic aici e indicat sa scriem cat mai mult cod !!! Nu e obligatoriu , ci mai degraba e un standard !!!
public class UserService {

    @Autowired // Injectam un bean de tip UserRepository intr-un bean de tip UserService. Este un design pattern care se numeste dependency injection ,care la randul lui  se bazeaza pe un alt design pattern numit Inversion of Control
               // Inversion of Control sau IoC pe scurt, este un proces în care un obiect își definește dependențele fără a le crea. Acest obiect deleagă sarcina de a construi astfel de dependențe ,  unui container IoC.(context de Spring).Practic contextul va avea controlul asupra flow-ului aplicatiei in sine ( nu programatorul )! El stie sa isi gestioneze bean-urile  si ce sa faca cu ele !
    private UserRepository userRepository;

    @Autowired // dependency injection
    private RoleRepository roleRepository;


    public Role saveRole(Role role) {


        return this.roleRepository.save(role);
    }


    public User saveUser(User user) {   // Prima metoda de a salva un User . Atentie ca aceasta metoda va adauga de fiecare data  noi roluri in baza de date !!! Nu va sti sa le ia  explicit pe cele deja existente ( ma refer la roluri)


        return this.userRepository.save(user);
    }

    public User saveUser2(User user ,int idRole) // A doua metoda de a adauga un User . De data aceasta ii spunem in mod explicit ! Ii oferim ca parametru id-ul rolului (deja existent in baza de date) pe care vrem ca user-ul sa il aiba
    {

       Role role= this.roleRepository.findById(idRole).get();

       List<Role> roleList =new ArrayList<>();
       roleList.add(role);

       if(role!=null) {

           user.setRoleList(roleList);
           return this.userRepository.save(user);
       }
       else
       {
           throw new RuntimeException("Role not found");
       }


    }


    public User saveUser3(  User user ,List<Role> roleList) // A treia metoda de a adauga un User. Tot in mod explicit ii oferim lista de roluri ( existente in baza de date) ! Update-ul seamana aproape  identic cu aceasta metoda ( mai trebuie doar sa ii punem ca parametru id-ului user-ului pe care vrem sa il modificam )
    {
        user.setRoleList(roleList);
        return this.userRepository.save(user);

    }



    public Role findRoleById(int id)
    {
        return this.roleRepository.findById(id).get();

    }

    public List<Role> findAllRoles()
    {
        return this.roleRepository.findAll();
    }


    public List<User> findAllUsers()
    {
        return this.userRepository.findAll();
    }


    public void deleteUserById(int id)
    {
         this.userRepository.deleteById(id);
    }

    public User findUserById(int id)
    {
        User user =this.userRepository.findById(id);
        if(user==null)
        {
            throw  new UserNotFoundException(id);
        }

        return this.userRepository.findById(id);
    }


}
