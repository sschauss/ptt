package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.core.calculator.Debit;
import de.unikoblenz.ptt.lord.ass03.core.calculator.DebitCalculator;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.UserDao;



@Path("/debits")
public class DebitResource {
		
	private final UserDao userDao;
	
	private DebitCalculator debitCalculator;
	
	public DebitResource(DBI jdbi) {
		userDao = jdbi.onDemand(UserDao.class);
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Debit> getDebits(@PathParam("id") UUID id) {
		debitCalculator = new DebitCalculator(userDao.getUsersInFlatShare(id));
		return debitCalculator.getDebits();
	}
}
