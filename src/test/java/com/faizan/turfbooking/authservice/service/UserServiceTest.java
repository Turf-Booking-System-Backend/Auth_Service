//package com.faizan.turfbooking.authservice.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.faizan.turfbooking.authservice.dto.CreateUserRequest;
//import com.faizan.turfbooking.authservice.dto.CreateUserResponse;
//import com.faizan.turfbooking.authservice.entity.User;
//import com.faizan.turfbooking.authservice.repository.UserRepository;
//import com.faizan.turfbooking.authservice.service.impl.UserServiceimpl;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private UserServiceimpl userService;
//
//    
//    @SuppressWarnings("unchecked")
//    @Test
//    void shouldRegisterUserSuccessfully() {
//
//        // 1️⃣ Input
//        CreateUserRequest request = new CreateUserRequest();
//        request.setName("Faizan");
//        request.setEmail("faizan@test.com");
//        request.setPassword("password123");
//
//        // 2️⃣ Password encoding
//        when(passwordEncoder.encode("password123"))
//                .thenReturn("encodedPassword");
//
//        // 3️⃣ Repository save
//        User savedUser = new User();
//        savedUser.setId(1L);
//        savedUser.setEmail("faizan@test.com");
//        savedUser.setPassword("encodedPassword");
//
//        when(userRepository.save(any(User.class)))
//                .thenReturn(savedUser);
//
//        // 4️⃣ ModelMapper mock
//        CreateUserResponse response = new CreateUserResponse();
//        response.setEmail("faizan@test.com");
//
//        when(modelMapper.map(any(User.class), any(Class.class)))
//                .thenReturn(response);
//
//        // 5️⃣ Call servicea
//        CreateUserResponse result = userService.createUser(request);
//
//        // 6️⃣ Assertions
//        assertNotNull(result);
//        assertEquals("faizan@test.com", result.getEmail());
//
//        // 7️⃣ Verifications
//        verify(passwordEncoder).encode("password123");
//        verify(userRepository).save(any(User.class));
//        verify(modelMapper).map(any(User.class), any(CreateUserResponse.class));
//    }
//}
