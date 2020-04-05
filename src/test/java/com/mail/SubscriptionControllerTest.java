package com.mail;

import com.mail.subscription.data.SubscriptionRepository;
import com.mail.subscription.data.UserRepository;
import com.mail.subscription.dto.SubscriptionDto;
import com.mail.subscription.model.Subscription;
import com.mail.subscription.model.User;
import com.mail.subscription.service.SubscriptionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    private User user;
    private Subscription subscription;
    private SubscriptionDto subscriptionDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private SubscriptionRepository subscriptionRepository;
    @MockBean
    private SubscriptionService subscriptionService;

    @BeforeAll
    void beforeAll() {
        user = new User();
        user.setUsername("user");
        user.setPassword("pass");
        subscription = new Subscription();
        subscriptionDto = new SubscriptionDto();
    }

    @Test
    @WithMockUser(username="user", password="pass", authorities="ROLE_USER")
    public void shouldShowSubscriptionForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/subscriptions"))
                .andExpect(status().isOk())
                .andExpect(view().name("subscriptions"));
    }

    @Test
    @WithMockUser(username="user", password="pass", authorities="ROLE_USER")
    public void shouldProcessSubscription() throws Exception {

        when(userRepository.findByUsername("user")).thenReturn(user);
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
        when(subscriptionService.createSubscription(subscriptionDto)).thenReturn(subscription);

        mockMvc.perform(post("/subscriptions").with(csrf())
                .content("name=Alex+SubscriptionDto&email=alex@mail.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/subscriptions"));
    }
}
