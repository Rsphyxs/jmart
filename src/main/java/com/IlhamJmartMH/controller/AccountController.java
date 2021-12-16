package com.IlhamJmartMH.controller;


import com.IlhamJmartMH.Account;
import com.IlhamJmartMH.Algorithm;
import com.IlhamJmartMH.Store;
import com.IlhamJmartMH.dbjson.JsonTable;
import com.IlhamJmartMH.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class AccountController yang akan mengatur seluruh aktivitas terkait akun
 * @author Muhammad Ilham M S
 * @version 16 Desember 2021
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	@JsonAutowired(value = Account.class, filepath = "D:\\Ilham\\UI\\Smt5\\Oop\\Prak\\Modul 1\\jmart\\src\\GoldenSample\\account.json")
	public static JsonTable<Account> accountTable;

	/**
	 * Method getJsonTable yang akan mengembalikan keseluruhan tabel akun
	 */
	public JsonTable<Account> getJsonTable(){
		return accountTable;
	}

	/**
	 * Method yang akan melakukan verifikasi terhadap informasi kredensial yang akan dimasukan
	 * @param email adalah email yang diinput user
	 * @param password adalah password yang diinput user
	 */
	@PostMapping("/login")
	public Account login(@RequestParam String email,@RequestParam String password){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest = messageDigest.digest(password.getBytes());
		BigInteger No = new BigInteger(1, digest);
		String hash = No.toString(16);
		while (hash.length() < 32) hash = "0" + hash;
		String finalHash = hash;

		return Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email) && obj.password.equals(finalHash));
	}

	/**
	 * Method yang akan mendaftarkan akun baru
	 * @param name adalah nama yang didaftarkan
	 * @param email adalah email yang didaftarkan
	 * @param password adalah password yang didaftarkan
	 */
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
			String hash = "";
			try{
				hash = MD5enkrip(password);
			}
			catch (NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			Account newAccount = new Account(name, email, hash, 0.0);
			accountTable.add(newAccount);
			return newAccount;
		}
		return null;
	}

	/**
	 * Method yang akan mendaftarkan toko baru dari akun terkait
	 * @param id adalah id dari akun
	 * @param name adalah nama dari toko
	 * @param address adalah alamat dari toko
	 * @param phoneNumber adalah nomor telepon dari toko
	 */
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

	/**
	 * Method yang akan menambahkan balance dari user
	 * @param id adalah id dari akun
	 * @param balance adalah jumlah balance yang akan ditambahkan
	 */
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

	/**
	 * Method yang akan melakukan enkripsi dengan metode MD5 terhadap password
	 * @param input adalah password yang akan dienkripso
	 */
	public static String MD5enkrip(String input) throws NoSuchAlgorithmException{
		try {
			//Memanggil metgod getInstance dengan tipe MD5 untuk mengunakan algoritma MD5
			MessageDigest method = MessageDigest.getInstance("MD5");

			//Memanggil string input dan mengkalkulasikannya kedalam bentuk array String
			byte[] messageDigest = method.digest(input.getBytes());

			//Mengubah array menjadi signum
			BigInteger signum = new BigInteger(1, messageDigest);

			//Mengubah kedalam format heksadesimal
			String varHash = signum.toString(16);

			//Menambahkan 0 untuk mengisi kekosongan sehingga panjang menjadi 32 bit
			while (varHash.length() < 32) {
				varHash = "0" + varHash;
			}
			return varHash;
		}

		//Menspesifikasikan algoritma pesan digest yang salah
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping
	String index() { return "account page"; }

	/**
	 * Method yang akan mendapatkan informasi akun berdasarkan id
	 * @param id adalah id akun
	 */
	@GetMapping("/{id}")
	public Account getDataAccount(@PathVariable int id) { return getById(id); }
}