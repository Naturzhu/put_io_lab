package put.io.testing.mocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

import static org.mockito.Mockito.*;



public class ExpenseRepositoryTest{
    private ExpenseRepository repo;
    private IFancyDatabase base = mock(IFancyDatabase.class);

    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testLoadExpenses() {
        repo = new ExpenseRepository(base);
        when(base.queryAll()).thenReturn(Collections.emptyList());
        repo.loadExpenses();
        InOrder inOrder = inOrder(base);
        inOrder.verify(base).connect();
        inOrder.verify(base).queryAll();
        inOrder.verify(base).close();
        Assertions.assertEquals(0,repo.getExpenses().size());
    }
    @Test
    public void testSaveExpenses() {
        repo = new ExpenseRepository(base);
        Expense exp = new Expense();
        when(base.queryAll()).thenReturn(Collections.emptyList());
        repo.addExpense(exp);
        repo.addExpense(exp);
        repo.addExpense(exp);
        repo.addExpense(exp);
        repo.addExpense(exp);
        repo.saveExpenses();
        InOrder inOrder = inOrder(base);
        inOrder.verify(base, times(5)).persist(any(Expense.class));
    }
}
