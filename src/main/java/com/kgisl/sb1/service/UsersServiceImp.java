package com.kgisl.sb1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.sb1.entity.Users;
import com.kgisl.sb1.repository.UsersRepository;

@Service
public class UsersServiceImp implements UsersService{

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public List<Users> getAll() {
       return usersRepository.findAll();
    }

    @Override
    public Users save(Users users) {
       return usersRepository.saveAndFlush(users);
    }

   
    @Override
    public Optional<Users> find(long id) {
        return usersRepository.findById(id);
    }


    @Override
    public void delete(Long id) {
       usersRepository.deleteById(id);
    }
    
}
