package com.IlhamJmartMH.controller;


import com.IlhamJmartMH.Account;
import com.IlhamJmartMH.Algorithm;
import com.IlhamJmartMH.Store;
import com.IlhamJmartMH.dbjson.JsonTable;
import com.IlhamJmartMH.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	@JsonAutowired(value = Account.class, filepath = "")
	public static JsonTable<Account> accountTable;


	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	@PostMapping("/login")
	public Account login(@RequestParam String email,@RequestParam String password){
		for(Account account : accountTable){
			if(account.email.equals(email) && account.password.equals(password)){
				return account;
			}
		}
		return null;
	}

	@PostMapping("/register")
	Account register(@RequestParam String name,@RequestParam String email,@RequestParam String password) {
		Matcher matcherEmail = REGEX_PATTERN_EMAIL.matcher(email);
		Matcher matcherPassword = REGEX_PATTERN_PASSWORD.matcher(password);

		boolean checker = true;

		for(Account account : accountTable) {
			if(account.email.equals(email))
				checker = false;
		}

		if(!name.isBlank() && matcherEmail.find() && matcherPassword.find() && checker) {
			Account newAccount = new Account(name, email, password, 0.0);
			accountTable.add(newAccount);
			return newAccount;
		}
		return null;
	}

	@PostMapping("/{id}/registerStore")
	Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber)
	{
		Account a = Algorithm.<Account> find(accountTable, obj -> obj.id == id);
		if(a == null || a.store != null)
		{
			return null;
		}
		a.store = new Store(name, address, phoneNumber, 0.0);
		return a.store;
	}

	@PostMapping("/{id}/topUp")
	boolean topUp(@PathVariable int id, @RequestParam double balance)
	{
		Account account = getById(id);
		if(account != null){
			account.balance += balance;
			return true;
		}
		return false;
	}

//	@GetMapping
//	String index() { return "account page"; }
//
//	@PostMapping("/register")
//	Account register
//			(
//					@RequestParam String name,
//					@RequestParam String email,
//					@RequestParam String password
//			)
//	{
//		return new Account(name, email, password, 0);
//	}
//
//	@GetMapping("/{id}")
//	public String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}