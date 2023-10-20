package com.kgisl.sb1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kgisl.sb1.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    
} 
