package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.UserDAOException;
import com.urjcstarshipbazaar.models.Client;
import org.junit.Test;

import static org.junit.Assert.*;
public class UserDAOTest {

    @Test
    public void saveAndGet() {
        Client client = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        UserDAO userDAO = new UserDAO();

        try {
            userDAO.save(client, "pass123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Client retrievedClient1 = null;
        Client retrievedClient2 = null;
        try {
            retrievedClient1 = (Client) userDAO.getByNickname("robiin");
            retrievedClient2 = (Client) userDAO.getByEmail("robiin@email.com");
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        try {
            userDAO.deleteById(retrievedClient1.getId()); // Clean-up
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        assertEquals(client.getEmail(), retrievedClient1.getEmail());
        assertEquals(client.getNickname(), retrievedClient2.getNickname());
    }

    @Test
    public void deleteById() {
        Client client = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        UserDAO userDAO = new UserDAO();

        try {
            userDAO.save(client, "pass123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Client retrievedClient1 = null;
        try {
            retrievedClient1 = (Client) userDAO.getByNickname("robiin");
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        assertEquals(client.getEmail(), retrievedClient1.getEmail());

        try {
            userDAO.deleteById(retrievedClient1.getId());
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        Client retrievedClient2 = null;
        try {
            retrievedClient2 = (Client) userDAO.getByNickname("robiin");
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        assertNull(retrievedClient2.getEmail());
    }

    @Test(expected = UserDAOException.class)
    public void saveGetAndDeleteLicense() throws UserDAOException {
        Client client = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        String license = "9127AHJGSA";
        UserDAO userDAO = new UserDAO();

        try {
            userDAO.save(client, "pass123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Client retrievedClient = null;
        try {
            retrievedClient = (Client) userDAO.getByNickname("robiin");
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        try {
            userDAO.saveLicense(license, retrievedClient.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String retrievedLicense1 = null;
        try {
            retrievedLicense1 = userDAO.getLicenseById(retrievedClient.getId());
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        assertEquals(license, retrievedLicense1);

        userDAO.deleteById(retrievedClient.getId());

        String retrievedLicense2 = userDAO.getLicenseById(retrievedClient.getId());

        assertNull(retrievedLicense2);
    }
}