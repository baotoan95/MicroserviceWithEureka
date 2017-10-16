package com.btit95.consumer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.common.models.Account;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
	@Autowired
	private RestTemplate restTemplate;
	private static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";

	@GetMapping
	public String index(ModelMap model) {
		log.debug("Get all accounts");
		ResponseEntity<List<Account>> result = restTemplate.exchange(ACCOUNTS_SERVICE_URL.concat("/accounts"),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
				});
		if (result.getStatusCode().is2xxSuccessful()) {
			List<Account> accounts = result.getBody();
			log.debug("Accounts list size: " + accounts.size());
			model.addAttribute("accounts", accounts);
		} else {
			model.addAttribute("error", result.getStatusCode().getReasonPhrase());
		}
		return "index";
	}

	@GetMapping("registry")
	public String registry(ModelMap model) {
		model.addAttribute("account", new Account());
		return "registry";
	}

	@PostMapping("registry")
	public String registry(@ModelAttribute Account account, ModelMap model) {
		HttpEntity<Account> request = new HttpEntity<>(account);
		ResponseEntity<Account> result = restTemplate.exchange(ACCOUNTS_SERVICE_URL.concat("/accounts/registry"),
				HttpMethod.POST, request, new ParameterizedTypeReference<Account>() {
				});
		if (result.getStatusCode().is2xxSuccessful()) {
			return "redirect:/";
		}
		model.addAttribute("error", result.getStatusCode().getReasonPhrase());
		return "redirect:/registry";
	}
}
