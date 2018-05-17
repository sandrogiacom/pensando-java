package com.giacom.demo.univali.v1.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.giacom.demo.univali.exception.InvalidUserException;
import com.giacom.demo.univali.service.SampleUserService;
import com.giacom.demo.univali.v1.converter.UserConverter;
import com.giacom.demo.univali.v1.dto.UserDTO;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private SampleUserService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UserDTO> getUsers() {
        return UserConverter.toUserDTO(service.findAll());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    UserDTO getUserById(@PathVariable("id") String id) {
        return UserConverter.toUserDTO(service.findById(id));
    }

    @RequestMapping(value = "{name}/name", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UserDTO> getUserByName(@PathVariable("name") String name) {
        return UserConverter.toUserDTO(service.findByNameContainingIgnoreCase(name));
    }

    @RequestMapping(value = "{lastName}/last", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<UserDTO> getUserByLastName(@PathVariable("lastName") String lastName) {
        return UserConverter.toUserDTO(service.findByLastNameContainingIgnoreCase(lastName));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserDTO user) {
        try {
            UserDTO createUser = UserConverter.toUserDTO(
                    service.create(UserConverter.toSampleUser(user)));
            return ResponseEntity.created(new URI("/v1/users/" + createUser.getId())).body(createUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody UserDTO user) {
        try {
            user.setId(id);
            UserDTO update = UserConverter.toUserDTO(
                    service.update(UserConverter.toSampleUser(user)));
            return ResponseEntity.ok(update);
        } catch (InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            service.delete(id);
        } catch (InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
