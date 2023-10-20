package com.kgisl.sb1.service;

import java.util.List;
import java.util.Optional;

import com.kgisl.sb1.entity.Users;


public interface UsersService {

    public List<Users> getAll();

    public Users save(Users  users);

    public  Optional<Users>  find(long id);

    public void delete(Long id);

}
