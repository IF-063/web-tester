package com.softserve.webtester.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.UserMapper;
import com.softserve.webtester.model.User;

/**
 * UserService class implements CRUD operation on {@link User} instance in the database.<br>
 * The service uses Spring DataSourceTransactionManager for managing transaction with the database and log4j for
 * logging.
 * 
 * @author Taras Oglabyak
 * @version 1.2
 */
@Service
@Transactional
public class UserService /*implements UserDetailsService */{

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * Loads {@link User} instance from the database.
     * 
     * @param userId identifier of User instance
     * @return {@link User} instance
     * @throws DataAccessException
     */
    // TODO Taras O. throw UsernameNotFoundException if the username hasn't been found?
    public User load(String userId) {
	try {
	   return userMapper.load(userId);
	} catch (DataAccessException e) {
	    LOGGER.error("User not found, userId: " + userId, e);
	    throw e;
	}
    }
    
    /**
     * Updates {@link User} instance in the database.
     * 
     * @param user user instance should be updated
     * @return number of rows affected by the statement
     * @throws DuplicateKeyException if the user with the username exists in the database.
     * @throws DataAccessException
     */
    public int update(User user) {
	try {
	   return userMapper.update(user);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to update the user, username: " + user.getUsername(), e);
	    throw e;
	}
    }
        
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	com.softserve.webtester.model.User u;
//	try {
//	    u = userMapper.loadUserByUsername(username);
//	    if (u == null)
//		throw new UsernameNotFoundException("Username not found");
//
//	} catch (DataAccessException e) {
//	    throw new UsernameNotFoundException("Database error");
//	}
//	return convertUserToUserDetails(u);
//    }
//
//    private User convertUserToUserDetails(com.softserve.webtester.model.User userEntity) {
//	String username = userEntity.getUsername();
//	String password = userEntity.getPassword();
//	boolean enabled = true;
//	boolean accountNonExpired = true;
//	boolean credentialsNonExpired = true;
//	boolean accountNonLocked = true;
//	List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
//	authList.add(new SimpleGrantedAuthority(userEntity.getRole().name()));
//	User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
//			     accountNonLocked, authList);
//	return user;
//    }
}