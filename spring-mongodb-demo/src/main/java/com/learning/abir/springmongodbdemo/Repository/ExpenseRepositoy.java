package com.learning.abir.springmongodbdemo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.learning.abir.springmongodbdemo.Model.Expense;

public interface ExpenseRepositoy extends MongoRepository<Expense, String> {

	@Query("{'name': ?0}")
	Optional<Expense>  findByName(String Name);
	
}
