package com.movie.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.api.model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer>{

}
