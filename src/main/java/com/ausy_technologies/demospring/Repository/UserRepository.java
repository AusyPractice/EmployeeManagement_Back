package com.ausy_technologies.demospring.Repository;


import com.ausy_technologies.demospring.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Definim un repository(depozit) pentru entitatea User. Aceasta interfata poate extinde una din cele 3 interfete specializate (CrudRepository, PagingAndSortingRepository sau JpaRepository) si este folosita pentru efectuarea operațiilor CRUD, sortarea și paginarea datelor.
            // Contine metode care au deja o implementare in spate ,deci nu mai este nevoie sa urmam toti pasii pe care i-am facut  la JPA nativ ( pentru fiecare metoda ne definim o tranzactie,deschidem tranzactia , persistam datele, facem commit si la final inchidem tranzactia)
public interface UserRepository extends JpaRepository<User, Integer> {


    User findById(int id); // metoda predefinita care tine cont de atributele entitatii User ( in acest caz tine cont de id)


    User findByUsername(String username); // metoda predefinita care tine cont de atributele entitatii User ( in acest caz tine cont de username)


}
