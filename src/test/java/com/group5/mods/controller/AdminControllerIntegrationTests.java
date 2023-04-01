package com.group5.mods.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.group5.mods.model.Order;
import com.group5.mods.model.OrderStatus;
import com.group5.mods.model.User;
import com.group5.mods.repository.OrderRepository;
import com.group5.mods.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/mods_test",
    "spring.datasource.username=root",
    "spring.datasource.password=",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.hibernate.ddl-auto=create",
})
@ActiveProfiles("test")
@ContextConfiguration
@AutoConfigureMockMvc
public class AdminControllerIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testUpdateOrderStatusWithUserRoleExpectForbidden() throws Exception {

        // Prepare a POST request to update the order status
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/orders").with(csrf());

        // Perform the POST request and assert the response
        mockMvc.perform(request)
       .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateOrderStatusWithAdminRoleExpectRedirectionToOrdersPage() throws Exception {

        // Create a new user and save it to the database
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User("TestName", "TestUsername", "test@gmail.com", passwordEncoder.encode("testPassword"), "ROLE_USER");
        if(userRepository.existsById(user.getId())){
            userRepository.deleteAll();
        }
        userRepository.save(user);

        // Create a new order and save it to the database
        Order order = new Order(user, LocalDateTime.now(), LocalDateTime.now().plusDays(1), new BigDecimal(100), OrderStatus.ORDERED);
        if(orderRepository.existsById(order.getId())){
            orderRepository.deleteAll();
        }
        orderRepository.save(order);

        // Prepare a POST request to update the order status
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/admin/orders/updateStatus")
                .param("orderId", String.valueOf(order.getId()))
                .param("orderStatus", OrderStatus.DELIVERED.name())
                .with(csrf());

        // Perform the POST request and assert the response
        mockMvc.perform(request)
       .andExpect(status().is3xxRedirection())
       .andExpect(redirectedUrl("/admin/orders"));

        // Retrieve the updated order from the database and assert its status has been updated
        Optional<Order> updatedOrder = orderRepository.findById(order.getId());
        assertTrue(updatedOrder.isPresent());
        assertEquals(OrderStatus.DELIVERED, updatedOrder.get().getStatus());
    }    

}
