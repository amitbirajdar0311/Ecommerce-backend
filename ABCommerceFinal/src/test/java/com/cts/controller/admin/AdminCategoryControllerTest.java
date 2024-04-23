package com.cts.controller.admin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.entity.Category;
import com.cts.service.admin.CategoryService;

@WebMvcTest(AdminCategoryController.class)
public class AdminCategoryControllerTest {

 
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

   
    
    @Test
    public void testGetAllCategory() throws Exception {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");
        category.setDescription("Test Description");
        categoryList.add(category);

        when(categoryService.getAllCategory()).thenReturn(categoryList);

        mockMvc.perform(get("/api/admin/categories")).andExpect(status().isOk());
    }
}