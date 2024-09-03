package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.UserRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUsersHandler {

    Page<CategoryResponse> getUsers(int page, int size, String sortDirection);

    void getUser(UserRequest userRequest);

    UserResponse getUserById(Long userId);

    List<UserResponse> getAllUsers(int page, int size);

    void updateUser(Long userId, UserRequest userRequest);

    void deleteUser(Long userId);
}
