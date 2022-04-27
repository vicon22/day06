package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {

    @Test
    public void AuthenticationUserHasThrowException() {
        UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
        User user = new User(1,"login", "password", true);
        Mockito.when(usersRepositoryMock.findByLogin("login")).thenReturn(user);

        UsersServiceImpl usersService = new UsersServiceImpl(usersRepositoryMock);

        Assertions.assertThrows(AlreadyAuthenticatedException.class , () -> usersService.authenticate("login", "password"));
    }

    @Test
    public void correctLoginAndPasswordHaveToReturnTrue() {
        UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
        User user = new User(1,"login", "password", false);

        Mockito.when(usersRepositoryMock.findByLogin("login")).thenReturn(user);

        UsersServiceImpl usersService = new UsersServiceImpl(usersRepositoryMock);

        Assertions.assertTrue(usersService.authenticate("login", "password"));
    }

    @Test
    public void correctLoginAndPasswordUserIsAuthenticationHasToBeTrue() {
        UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
        User user = new User(1,"login", "password", false);

        Mockito.when(usersRepositoryMock.findByLogin("login")).thenReturn(user);

        UsersServiceImpl usersService = new UsersServiceImpl(usersRepositoryMock);

        usersService.authenticate("login", "password");

        Assertions.assertTrue(user.isAuthentication());
    }


    @Test
    public void incorrectLogin() {
        UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
        Mockito.when(usersRepositoryMock.findByLogin("incorrect")).thenThrow(EntityNotFoundException.class);

        UsersServiceImpl usersService = new UsersServiceImpl(usersRepositoryMock);

        Assertions.assertThrows(EntityNotFoundException.class, () -> usersService.authenticate("incorrect", "qwerty"));

    }

    @Test
    public void incorrectPassword() {
        UsersRepository usersRepositoryMock = Mockito.mock(UsersRepository.class);
        Mockito.when(usersRepositoryMock.findByLogin("login")).thenReturn(new User(1,"login", "password", false));

        UsersServiceImpl usersService = new UsersServiceImpl(usersRepositoryMock);

        Assertions.assertFalse(usersService.authenticate("login", "qwerty"));
    }

}
