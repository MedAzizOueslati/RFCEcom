package com.rfc.rfcecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setDescription("Category for electronic items");
    }

    @Test
    void testCategory() {
        // Test initial values
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
        assertEquals("Category for electronic items", category.getDescription());

        // Test setting new values
        category.setId(2L);
        category.setName("Home Appliances");
        category.setDescription("Category for home appliances");

        // Test updated values
        assertEquals(2L, category.getId());
        assertEquals("Home Appliances", category.getName());
        assertEquals("Category for home appliances", category.getDescription());
    }

}