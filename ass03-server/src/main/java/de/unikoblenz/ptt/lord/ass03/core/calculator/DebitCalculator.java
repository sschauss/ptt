package de.unikoblenz.ptt.lord.ass03.core.calculator;

import java.util.List;

import de.unikoblenz.ptt.lord.ass03.jdbi.user.User;

public class DebitCalculator {
	
	private final List<User> users;
		
	public DebitCalculator(List<User> users) {
		this.users = users;
	}
	
	public List<Debit> getDebits(){
		List<Debit> debits = null;
		//TODO actually calculate something
		return debits;
	}
}
