package com.desfid.api_rest_bdd.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.desfid.api_rest_bdd.models.UserModel;
import com.desfid.api_rest_bdd.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllUsers() throws Exception {
    List<UserModel> users = new ArrayList<>();
    users.add(new UserModel("John Doe", "tes@gmail.com", 5000));
    users.add(new UserModel("Jane Doe", "test2@gmail.com", 4500));
    when(userService.getAllUsers()).thenReturn(users);
    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("John Doe"))
        .andExpect(jsonPath("$[0].email").value("tes@gmail.com"))
        .andExpect(jsonPath("$[1].name").value("Jane Doe"))
        .andExpect(jsonPath("$[1].email").value("test2@gmail.com"));
  }

  @Test
  void testSaveUser() throws Exception {
    UserModel user = new UserModel("Adrian", "test@gmail.com", 1);
    mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(user)))
        .andExpect(status().isOk());
  }

  @Test
  void testGetUserById() throws Exception {
    UserModel user = new UserModel("John Doe", "tes@gmail.com", 5000);
    when(userService.getById(1L)).thenReturn(Optional.of(user));
    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("John Doe"))
        .andExpect(jsonPath("$.email").value("tes@gmail.com"));
  }

  @Test
  void testDeleteById() throws Exception {
    Long idToDelete = 1L;
    when(userService.deleteUser(idToDelete)).thenReturn(true);
    mockMvc.perform(delete("/users/{id}", idToDelete))
        .andExpect(status().isOk())
        .andExpect(content().string("true"));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
