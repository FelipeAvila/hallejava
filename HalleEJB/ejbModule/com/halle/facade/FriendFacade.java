package com.halle.facade;

import java.util.List;

import javax.ejb.Local;

import com.halle.exception.ApplicationException;
import com.halle.model.Friend;

@Local
public interface FriendFacade {
	
	void inviteByPhone(final String token, final String name, final String phoneFriend) throws ApplicationException;
	
	List<Friend> findAllFriend(final String token, String status) throws ApplicationException;
	
}
