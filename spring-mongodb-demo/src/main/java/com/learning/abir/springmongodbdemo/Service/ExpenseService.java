package com.learning.abir.springmongodbdemo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.abir.springmongodbdemo.Model.Expense;
import com.learning.abir.springmongodbdemo.Repository.ExpenseRepositoy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseService {

	private final ExpenseRepositoy expenseRepositoy;

	public void addExpense(Expense expense) {
		this.expenseRepositoy.insert(expense);
	};

	public void updateExpense(Expense expense) {
		// first retreive the expense from DB using the id field, then update it
		Expense savedExpense = this.expenseRepositoy.findById(expense.getId()).orElseThrow(
				() -> new RuntimeException(String.format("Cannot find Expence by ID %s", expense.getId())));

		savedExpense.setExpenseName(expense.getExpenseName());
		savedExpense.setExpenseCategory(expense.getExpenseCategory());
		savedExpense.setExpenseAmount(expense.getExpenseAmount());

		this.expenseRepositoy.save(savedExpense);

	};

	public List<Expense> getAllExpenses() {
		return this.expenseRepositoy.findAll();
	};

	public Expense getExpenseByName(String name) {
		return this.expenseRepositoy.findByName(name)
				.orElseThrow(() -> new RuntimeException(String.format("Cannot find Expense By Name %s", name)));

	};

	public void deleteExpense(String id) {
		this.expenseRepositoy.deleteById(id);
	};
}
