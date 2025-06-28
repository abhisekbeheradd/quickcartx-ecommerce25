package com.jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.demo.modelentity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
