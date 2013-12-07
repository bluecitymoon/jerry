
package com.jerry.jerry.application.core;

import java.util.List;
import com.dayatang.querychannel.support.Page;
import com.jerry.jerry.application.dto.*;

public interface UsersApplication {

	public UsersDTO getUsers(Long id);
	
	public UsersDTO saveUsers(UsersDTO users);
	
	public void updateUsers(UsersDTO users);
	
	public void removeUsers(Long id);
	
	public void removeUserss(Long[] ids);
	
	public List<UsersDTO> findAllUsers();
	
	public Page<UsersDTO> pageQueryUsers(UsersDTO users, int currentPage, int pageSize);
	

}

