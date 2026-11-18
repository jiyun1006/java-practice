package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank{

	private List<Account> accounts;
	private int nextAccountNumber;
	
	public Bank() {
		accounts = new ArrayList<>();
		nextAccountNumber = 1000;
	}
	
	public String createSavingsAccount(String ownerName, double initialBalance, double interestRate){
		String accountNumber = "AC" + nextAccountNumber;
		nextAccountNumber++;
		SavingsAccount savingAccount = new SavingsAccount(accountNumber, ownerName, initialBalance, interestRate);
		accounts.add(savingAccount);
		System.out.println("저축 계좌가 생성되었습니다: " + savingAccount);
		return accountNumber;
	}
	
	public String createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit){
		String accountNumber = "AC" + nextAccountNumber;
		nextAccountNumber++;
		CheckingAccount checkingAccount = new CheckingAccount(accountNumber, ownerName, initialBalance, withdrawalLimit);
		accounts.add(checkingAccount);
		System.out.println("체킹 계좌가 생성되었습니다: " + checkingAccount);
		return accountNumber;
	}
	
	public Account findAccount(String accountNumber) throws AccountNotFoundException{
		for(Account account : this.accounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
	}
	
	public void deposit(String accountNumber, double amount) throws AccountNotFoundException{
		Account account = this.findAccount(accountNumber);
		account.deposit(amount);
	}
	
	public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException{
		Account account = this.findAccount(accountNumber);
		account.withdraw(amount);
	}
	
	public void transfer(String accountNumber1, String accountNumber2, double amount) throws AccountNotFoundException, InsufficientBalanceException{
		Account account1 = this.findAccount(accountNumber1);
		Account account2 = this.findAccount(accountNumber2);
		account1.withdraw(amount);
		account2.deposit(amount);
		System.out.println(amount + "원이 " + accountNumber1 + "에서 " + accountNumber2 + "로 송금되었습니다.");
		
	}
	
	public void printAllAccounts() {
		System.out.println("=== 모든 계좌 목록 ===");
		for(Account account : accounts) {
			System.out.println(account);
		}
		System.out.println("===================");
	}
	
}
