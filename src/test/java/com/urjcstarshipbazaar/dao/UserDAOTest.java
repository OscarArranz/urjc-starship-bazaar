package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
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

            Client retrievedClient1 = (Client) userDAO.getByNickname("robiin");
            Client retrievedClient2 = (Client) userDAO.getByEmail("robiin@email.com");

            userDAO.deleteById(retrievedClient1.getId()); // Clean-up

            assertEquals(client.getEmail(), retrievedClient1.getEmail());
            assertEquals(client.getNickname(), retrievedClient2.getNickname());
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteById() {
        Client client = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        UserDAO userDAO = new UserDAO();

        try {
            userDAO.save(client, "pass123");

            Client retrievedClient1 = (Client) userDAO.getByNickname("robiin");
            assertEquals(client.getEmail(), retrievedClient1.getEmail());

            userDAO.deleteById(retrievedClient1.getId());

            Client retrievedClient2 = (Client) userDAO.getByNickname("robiin");
            assertNull(retrievedClient2.getEmail());
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = DAOException.class)
    public void saveGetAndDeleteLicense() throws DAOException {
        Client client = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        String license = "9127AHJGSA";
        UserDAO userDAO = new UserDAO();

        Client retrievedClient = new Client();

        try {
            userDAO.save(client, "pass123");

            retrievedClient = (Client) userDAO.getByNickname("robiin");

            userDAO.saveLicense(license, retrievedClient.getId());

            String retrievedLicense1 = userDAO.getLicenseById(retrievedClient.getId());
            assertEquals(license, retrievedLicense1);

            userDAO.deleteById(retrievedClient.getId());
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }

        String retrievedLicense2 = userDAO.getLicenseById(retrievedClient.getId());
        assertNull(retrievedLicense2);
    }
}