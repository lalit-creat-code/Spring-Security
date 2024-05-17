package com.lalit.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lalit.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {


public Users findByName(String username);
public Optional<Users> findByEmailAndPassword(String email,String password);
public Optional<Users> findByEmailAndName(String email,String name);
@Query
(value="select *from user_txn where name=?1 or email=?2",nativeQuery=true)
public Users findUser(@Param("name")String name,@Param("email")String email);


}
