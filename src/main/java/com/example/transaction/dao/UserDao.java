package com.example.transaction.dao;

import com.example.transaction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 王正松
 * @since 2022/12/21 9:41
 */
public interface UserDao extends JpaRepository<User, Long> {
}
