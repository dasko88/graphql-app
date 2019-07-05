package it.ts.dotcom.demo.graphqlapp.model.repository;

import it.ts.dotcom.demo.graphqlapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
