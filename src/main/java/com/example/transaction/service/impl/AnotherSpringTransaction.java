package com.example.transaction.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王正松
 * @since 2022/12/21 9:45
 */
@Slf4j
@Service
public class AnotherSpringTransaction {

    private final SpringTransactionImpl springTransaction;

    @Autowired
    public AnotherSpringTransaction(SpringTransactionImpl springTransaction) {
        this.springTransaction = springTransaction;
    }

      /**
     * <h2>不同类中, 一个不标注事务的方法去调用 transactional 的方法，事务会回滚</h2>
     * */
    public void transactionalCanRollback() {

        springTransaction.anotherOneSaveMethod();
    }
}
