package com.learning.abir.springmongodbdemo.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.abir.springmongodbdemo.Model.Expense;
import com.learning.abir.springmongodbdemo.Service.ExpenseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
	private final ExpenseService expenseService;

	@PostMapping
	public ResponseEntity<Void> addExpense(@RequestBody Expense expense) {
		this.expenseService.addExpense(expense);

		/*
		 * following the standards REST API convention for PostMapping we must returnn
		 * the status as Created
		 */
		return ResponseEntity.status(HttpStatus.CREATED).build();
		/*
		 * Instead of instantiating objects directly with new, the object creation is
		 * delegated to a builder, which allows you to have control over the steps of
		 * the construction process.
		 */
	};

	@PutMapping
	public ResponseEntity<Void> updateExpense(@RequestBody Expense expense) {
		this.expenseService.updateExpense(expense);
		return ResponseEntity.ok().build();
	};

	@GetMapping
	public ResponseEntity<List<Expense>> getAllExpenses() {

		return ResponseEntity.ok(this.expenseService.getAllExpenses());

	};

	@GetMapping("/{name}")
	public ResponseEntity<Expense> getExpenseByName(@PathVariable String name) {
		return ResponseEntity.ok(this.expenseService.getExpenseByName(name));
	};

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
		this.expenseService.deleteExpense(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		/* 204 No Content success status response code indicates that a request has succeeded, 
		 * but that the client doesn't need to navigate away from its current page.*/
	};

}
