package com.rfc.rfcecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setName("John Doe");
        user.setRole(UserRole.ADMIN);
        user.setImg(new byte[]{1, 2, 3});
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, user.getId());
        user.setId(2L);
        assertEquals(2L, user.getId());

        assertEquals("test@example.com", user.getEmail());
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());

        assertEquals("password123", user.getPassword());
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());

        assertEquals("John Doe", user.getName());
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());

        assertEquals(UserRole.ADMIN, user.getRole());
        user.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN, user.getRole());

        assertArrayEquals(new byte[]{1, 2, 3}, user.getImg());
        byte[] newImg = new byte[]{4, 5, 6};
        user.setImg(newImg);
        assertArrayEquals(newImg, user.getImg());
    }
}

