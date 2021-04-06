package com.tts.Commerce.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.tts.Commerce.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
User findByUsername(String username);
}
