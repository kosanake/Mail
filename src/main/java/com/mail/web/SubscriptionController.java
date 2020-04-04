package com.mail.web;

import com.mail.subscription.data.SubscriptionRepository;
import com.mail.subscription.dto.SubscriptionDto;
import com.mail.subscription.dto.mapper.SubscriptionMapper;
import com.mail.subscription.model.Subscription;
import com.mail.subscription.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private SubscriptionRepository subscriptionRepository;
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionController(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @ModelAttribute(name = "subscriptionDto")
    public SubscriptionDto subscription() {
        return new SubscriptionDto();
    }

    @ModelAttribute(name = "userSubscriptionsDto")
    public List<SubscriptionDto> subscription(@AuthenticationPrincipal User user) {
        return subscriptionRepository.findByUserOrderByDateDesc(user).stream()
                .map(subscriptionMapper::subscriptionToSubscriptionDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public String getUserSubscription() {
        return "subscriptions";
    }

    @PostMapping
    public String processSubscription(@Valid SubscriptionDto subscriptionDto, Errors errors) {
        if (errors.hasErrors()) {
            return "subscriptions";
        }

        Subscription subscription = subscriptionMapper.subscriptionDtoToSubscription(subscriptionDto);
        try {
            subscriptionRepository.save(subscription);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("email", "", "Subscription for Email: " + subscription.getEmail() + " already exists");
            return "subscriptions";
        }

        return "redirect:/subscriptions";
    }
}
