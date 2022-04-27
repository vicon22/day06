package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {

    UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password){

        User foundedUser = usersRepository.findByLogin(login);
        if (foundedUser.isAuthentication()){
            throw new AlreadyAuthenticatedException("Authentication already done");
        }
        if (foundedUser.getPassword().equals(password)){
            foundedUser.setAuthentication(true);
            return true;
        }
        return false;
    }
}
