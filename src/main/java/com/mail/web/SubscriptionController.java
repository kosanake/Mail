package com.mail.web;

import com.mail.data.SubscriptionRepository;
import com.mail.entity.Subscription;
import com.mail.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @ModelAttribute(name = "subscription")
    public Subscription subscription() {
        return new Subscription();
    }

    @ModelAttribute(name = "userSubscriptions")
    public List<Subscription> subscription(@AuthenticationPrincipal User user) {
        return subscriptionRepository.findByUserOrderByDateDesc(user);
    }

    @GetMapping
    public String getUserSubscription() {
        return "subscriptions";
    }

    @PostMapping
    public String processSubscription(@Valid Subscription subscription, Errors errors, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "subscriptions";
        }

        subscription.setUser(user);
        subscription.setDate(Instant.now());
        subscriptionRepository.save(subscription);

        return "redirect:/subscriptions";
    }
}
