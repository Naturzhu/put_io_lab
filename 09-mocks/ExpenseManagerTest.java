package put.io.testing.mocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExpenseManagerTest {
    private ExpenseRepository repo = mock(ExpenseRepository.class);
    //private FancyService fancyService;
    private FancyService fancyService = mock(FancyService.class);

    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testCalculateTotal() {
        Expense exp1 = new Expense();
        Expense exp2 = new Expense();
        Expense exp3 = new Expense();
        List<Expense> expList = new ArrayList<Expense>();
        expList.add(exp1);
        expList.add(exp2);
        expList.add(exp3);
        when(repo.getExpenses()).thenReturn(expList);
        ExpenseManager manager = new ExpenseManager(repo, fancyService);
        //manager.calculateTotal();
        Assertions.assertEquals(0,manager.calculateTotal());
    }
    @Test
    public void testCalculateTotalForCategory() {
        Expense exp1 = new Expense();
        exp1.setCategory("Home");
        exp1.setAmount(100000);
        Expense exp2 = new Expense();
        exp2.setCategory("Car");
        exp2.setAmount(35000);
        Expense exp3 = new Expense();
        exp3.setCategory("Car");
        exp3.setAmount(25000);
        List<Expense> expList = new ArrayList<Expense>();
        expList.add(exp1);
        expList.add(exp2);
        expList.add(exp3);
        List<Expense> homeList = new ArrayList<Expense>();
        homeList.add(exp1);
        List<Expense> carList = new ArrayList<Expense>();
        carList.add(exp2);
        carList.add(exp3);
        when(repo.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(repo.getExpensesByCategory("Home")).thenReturn(homeList);
        when(repo.getExpensesByCategory("Car")).thenReturn(carList);
        ExpenseManager manager = new ExpenseManager(repo, fancyService);
        //manager.calculateTotal();
        Assertions.assertEquals(100000,manager.calculateTotalForCategory("Home"));
        Assertions.assertEquals(60000,manager.calculateTotalForCategory("Car"));
        Assertions.assertEquals(0,manager.calculateTotalForCategory("Food"));
        Assertions.assertEquals(0,manager.calculateTotalForCategory("Sport"));
    }
    @Test
    public void testCalculateTotalInDollars() throws ConnectException {
        Expense exp1 = new Expense();
        Expense exp2 = new Expense();
        Expense exp3 = new Expense();
        List<Expense> expList = new ArrayList<Expense>();
        expList.add(exp1);
        exp1.setAmount(1);
        expList.add(exp2);
        exp2.setAmount(2);
        expList.add(exp3);
        exp3.setAmount(3);
        when(repo.getExpenses()).thenReturn(expList);
        //when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenReturn((double) 24);
        //when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());
        when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    @Override
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        return (double)((double)args[0]*4);
                    }
                }
        );
        ExpenseManager manager = new ExpenseManager(repo, fancyService);
        //manager.calculateTotal();
        Assertions.assertEquals(24,manager.calculateTotalInDollars());
        //Assertions.assertEquals(-1,manager.calculateTotalInDollars());
    }
}
