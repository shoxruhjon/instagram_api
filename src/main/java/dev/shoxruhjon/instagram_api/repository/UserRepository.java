package dev.shoxruhjon.instagram_api.repository;

import dev.shoxruhjon.instagram_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select u from users u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    List<UserEntity> searchUser(@Param("query") String query);

    @Query("select u from users u where upper(u.email) = upper(?1)")
    Optional<UserEntity> findUserByEmail(String email);
}
