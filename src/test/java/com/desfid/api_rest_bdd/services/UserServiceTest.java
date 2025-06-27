package com.desfid.api_rest_bdd.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.desfid.api_rest_bdd.models.UserModel;
import com.desfid.api_rest_bdd.repositories.UserRepository;

public class UserServiceTest {

  @InjectMocks
  private UserService usuarioService;

  @Mock
  private UserRepository usuarioRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testObtenerUsers() {
    when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new UserModel( "John Doe", "tes@gmail.com", 5000)));
    UserModel found = usuarioService.getById(1L).orElse(null);
    assertEquals("Adrian", found.getName());
  }
}
