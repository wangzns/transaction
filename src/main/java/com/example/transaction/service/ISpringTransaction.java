package com.example.transaction.service;

import com.example.transaction.exception.CustomException;

/**
 * <h1>Spring Transactional测试接口定义</h1>
 *
 * @author 王正松
 * @since 2022/12/21 9:44
 */
public interface ISpringTransaction {


     /**
     * <h2>1. 主动捕获了异常, 事务不会回滚</h2>
     * */
    void catchExceptionCanNotRollback();

     /**
     * <h2>2. 不是 unchecked 异常, 事务不会回滚</h2>
     * */
    void notRuntimeExceptionCanNotRollback() throws CustomException;

     /**
     * <h2>3. unchecked 异常, 事务会回滚</h2>
     * */
    void runtimeExceptionCanRollback();

     /**
     * <h2>4. 指定了 rollbackFor, 事务会回滚</h2>
     * */
    void assignExceptionCanRollback() throws CustomException;

     /**
     * <h2>5. Rollback Only,事务会回滚</h2>
     * */
    void rollbackOnlyCanRollback() throws CustomException;

     /**
     * <h2>6. 同一个类中, 一个不标注事务的方法去调用 transactional 的方法, 事务失效</h2>
     * */
    void nonTransactionalCanNotRollback();


}
