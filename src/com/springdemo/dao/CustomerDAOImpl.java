package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;
import com.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// Need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(int theSortField) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// determine sort field
		String theFieldName = null;
		
		switch (theSortField) {
		case SortUtils.FIRST_NAME:
			theFieldName = "firstName";
			break;
		case SortUtils.LAST_NAME:
			theFieldName = "lastName";
			break;
		case SortUtils.EMAIL:
			theFieldName = "email";
			break;

		default:
			theFieldName = "lastName";
			break;
		}
		
		// Create a query, and sort by Last Name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + theFieldName, 
																Customer.class);
		
		// Execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Retrieve data from DB using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
		
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete data from DB using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		currentSession.delete(theCustomer);
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Search by name if name is not empty
		Query<Customer> theQuery = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// Search for first name or last name, case insensitive
			theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName" + 
												" or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			// Display all customers if the name is empty
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		
		// Execute the query
		List<Customer> theCustomers = theQuery.getResultList();
		
		return theCustomers;
		
	}

}


