package com.ausy_technologies.demospring.Repository;


import com.ausy_technologies.demospring.Model.DAO.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository // @Repository este o adnotare de tip Stereotype( alaturi de @Component, @Service si @Controller) si indica faptul ca interfata(clasa) decorata este un repository .Un repository este un mecanism de încapsulare , extragere și de  căutare a datelor, sub forma unor  colecții de obiecte
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Override
    List<Role> findAll();

    @Override
    Optional<Role> findById(Integer integer);




    Role findByName(String name);

    //  Role findByUser(User user);

}
