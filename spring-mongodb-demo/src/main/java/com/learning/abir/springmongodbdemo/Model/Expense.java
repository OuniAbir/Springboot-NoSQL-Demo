package com.learning.abir.springmongodbdemo.Model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document("expense")
public class Expense {
	
	@Id
	private String id;
	
	@Field(name = "name")
	/* To be able to retreive a document or a record inside a table in NonRelationnal DB
	 * we can define an index using @Indexed 
	 * and we can map it as unique so make sure we can't save more that one index with the same expenseName 
	*/
	@Indexed(unique = true)
	private String expenseName;
	
	@Field(name = "category")
	private ExpenseCategory expenseCategory ;
	
	@Field(name = "amount")
	private BigDecimal expenseAmount;
	
	
	
}
