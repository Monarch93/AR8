package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
