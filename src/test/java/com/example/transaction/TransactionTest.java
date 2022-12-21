package com.example.transaction;

import com.example.transaction.exception.CustomException;
import com.example.transaction.service.ISpringTransaction;
import com.example.transaction.service.impl.AnotherSpringTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>事务回滚测试类</h1>
 * Created by 王正松.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TransactionTest {

    @Autowired
    private ISpringTransaction springTransaction;

    @Autowired
    private AnotherSpringTransaction anotherSpringTransaction;

    @Test
    public void testCatchExceptionCanNotRollback() {

        springTransaction.catchExceptionCanNotRollback();
    }

    @Test
    public void testNotRuntimeExceptionCanNotRollback() throws CustomException {

        springTransaction.notRuntimeExceptionCanNotRollback();
    }

    @Test
    public void testRuntimeExceptionCanRollback() {

        springTransaction.runtimeExceptionCanRollback();
    }

    @Test
    public void testAssignExceptionCanRollback() throws CustomException {

        springTransaction.assignExceptionCanRollback();
    }

    @Test
    public void testRollbackOnlyCanRollback() throws CustomException {

        springTransaction.rollbackOnlyCanRollback();
    }

    @Test
    public void testNonTransactionalCanNotRollback() {

        springTransaction.nonTransactionalCanNotRollback();
    }

    @Test
    public void testTransactionalCanRollback() {

        anotherSpringTransaction.transactionalCanRollback();
    }
}
