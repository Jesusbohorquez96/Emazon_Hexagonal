package com.jbohorquez.emazon_hexagonal.domain.spi;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

        void saveUser(User user);

        List<User> getAllUser();

        User getUserById(Long userId);

        void updateUser(User user);

        void deleteUser(Long userId);

        Page<User> findAllUser(Pageable pageable);

        Page<User> getUsers(PageRequest pageRequest);

        Optional<User> findByEmail(String email);
}
