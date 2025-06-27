package com.desfid.api_rest_bdd.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.desfid.api_rest_bdd.models.UserModel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  void testGetUser() {
    UserModel user = userRepository.save(new UserModel("Adrian", "test@gmail.com", 1));
    UserModel fetchedUser = userRepository.findById(user.getId()).orElse(null);
    assertNotNull(fetchedUser);
    assertEquals(user.getId(), fetchedUser.getId());
  }

  @Test
  void testSaveUser() {
    UserModel user = new UserModel("Adrian", "test@gmail.com", 1);
    UserModel savedUser = userRepository.save(user);
    assertNotNull(savedUser);
    assertEquals("Adrian", savedUser.getName());
  }

  @Test
  void testGetAllUsers() {
    userRepository.save(new UserModel("Adrian", "test@gmail.com", 1));
    userRepository.save(new UserModel("Torres", "test2@gmail.com", 1));
    List<UserModel> fetchedUser = userRepository.findAll();
    assertNotNull(fetchedUser);
    assertEquals(2, fetchedUser.size());
  }

  @Test
  void testUpdateUser() {
    UserModel user = userRepository.save(new UserModel("Adrian", "test@gmail.com", 1));
    user.setName("Juan");
    userRepository.save(user);
    UserModel updatedUser = userRepository.findById(user.getId()).orElse(null);
    assertNotNull(updatedUser);
    assertEquals("Juan", updatedUser.getName());
  }

  @Test
  void testDeleteUser() {
    UserModel user = userRepository.save(new UserModel("Adrian", "test@gmail.com", 1));
    userRepository.deleteById(user.getId());
    UserModel fetchedUser = userRepository.findById(user.getId()).orElse(null);
    assertNull(fetchedUser);
  }
}
