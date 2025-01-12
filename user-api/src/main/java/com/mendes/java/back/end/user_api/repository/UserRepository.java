package com.mendes.java.back.end.user_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendes.java.back.end.user_api.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByCpfAndKey(String cpf, String key);

	List<User> queryByNomeLike(String name);

}
