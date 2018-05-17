package com.giacom.demo.univali.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.giacom.demo.univali.domain.SampleUser;

public interface SampleUserRepository extends CrudRepository<SampleUser, String> {

    public List<SampleUser> findByNameContainingIgnoreCase(String name);

    public List<SampleUser> findByLastNameContainingIgnoreCase(String lastName);

}
