package com.btit95.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.models.Account;
import com.common.repositories.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<Account>> getAccountList() {
		log.debug("Find all accounts");
		return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registry", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> registry(@RequestBody Account account) {
		log.debug("Check account existing...");
		if(accountRepository.findOne(account.getUsername()) != null) {
			log.debug("Username is existed");
			return new ResponseEntity<String>("Username is existed", HttpStatus.CONFLICT);
		}
		
		log.debug("Account does not exist, so we start to creating a new one");
		log.debug("Add new account to DB");
		account = accountRepository.save(account);
		log.debug("Finished add new account to DB without any error");
		
		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}
}
