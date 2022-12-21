package com.example.transaction.service.impl;

import com.example.transaction.dao.UserDao;
import com.example.transaction.entity.User;
import com.example.transaction.exception.CustomException;
import com.example.transaction.service.ISpringTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @author 王正松
 * @since 2022/12/21 9:45
 */
@Slf4j
@Service
public class SpringTransactionImpl implements ISpringTransaction {

    @Resource
    private  UserDao userDao;


    /**
     * <h2>捕捉异常</h2>
     */
    @Override
    @Transactional
    public void catchExceptionCanNotRollback() {

        try {
            userDao.save(new User("张三"));
            throw new RuntimeException();
        } catch (Exception ex) {
            ex.printStackTrace();
            // 手动标记回滚
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * <h2>捕捉异常并转换异常</h2>
     */
    @Override
    @Transactional
    public void notRuntimeExceptionCanNotRollback() throws CustomException {

        try {
            userDao.save(new User("张三"));
            throw new RuntimeException();
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * <h2>RuntimeException</h2>
     */
    @Override
    @Transactional
    public void runtimeExceptionCanRollback() {

        userDao.save(new User("张三"));
        throw new RuntimeException();
    }

    /**
     * <h2>指定异常</h2>
     */
    @Override
//    @Transactional
    @Transactional(rollbackFor = {CustomException.class})
    public void assignExceptionCanRollback() throws CustomException {

        try {
            userDao.save(new User("张三"));
            throw new RuntimeException();
        } catch (Exception ex) {
            System.out.println("rollback-only：" + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            throw new CustomException(ex.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     */
    @Transactional
    public void oneSaveMethod() {

        userDao.save(new User("张三"));
    }

     /**
     * <h2>Rollback Only</h2>
     * org.springframework.transaction.UnexpectedRollbackException:
     * Transaction silently rolled back because it has been marked as rollback-only
     */
    @Override
    @Transactional
    public void rollbackOnlyCanRollback() throws CustomException {

        oneSaveMethod();

        try {
            userDao.save(new User());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("rollback-only：" + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            throw new CustomException("");
        }
    }



    @Transactional
    public void anotherOneSaveMethod() {

        userDao.save(new User("张三"));
        throw new RuntimeException();
    }

    /**
     * <h2>同一个类中, 一个不标注事务的方法去调用 transactional 的方法</h2>
     */
    @Override
//    @Transactional
    public void nonTransactionalCanNotRollback() {
        anotherOneSaveMethod();
    }
}
