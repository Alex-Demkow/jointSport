package com.jointsport.jointSport.repository;

import com.jointsport.jointSport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

   User findUserByEmail(String email);

//   List<User>  getAllFriends(Integer id);// как это раотает?????
}
