
package com.jerry.jerry.application.impl.core;

import java.util.List;
import java.util.ArrayList;
import java.text.MessageFormat;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.dayatang.querychannel.support.Page;
import com.dayatang.querychannel.service.QueryChannelService;
import com.jerry.jerry.application.dto.*;
import com.jerry.jerry.application.core.UsersApplication;
import com.jerry.jerry.core.*;

@Named
@Transactional
public class UsersApplicationImpl implements UsersApplication {

	@Inject
	private QueryChannelService queryChannel;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UsersDTO getUsers(Long id) {
		Users users = Users.load(Users.class, id);
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.domain2Dto(users);
		usersDTO.setId(users.getId());
		return usersDTO;
	}
	
	public UsersDTO saveUsers(UsersDTO usersDTO) {
		Users users = new Users();
		usersDTO.dto2Domain(users);
		users.save();
		usersDTO.setId(users.getId());
		return usersDTO;
	}
	
	public void updateUsers(UsersDTO usersDTO) {
		Users users = Users.get(Users.class, usersDTO.getId());
		usersDTO.dto2Domain(users);
	}
	
	public void removeUsers(Long id) {
		this.removeUserss(new Long[] { id });
	}
	
	public void removeUserss(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Users users = Users.load(Users.class, ids[i]);
			users.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UsersDTO> findAllUsers() {
		List<UsersDTO> list = new ArrayList<UsersDTO>();
		List<Users> all = Users.findAll(Users.class);
		for (Users users : all) {
			UsersDTO usersDTO = new UsersDTO();
			usersDTO.domain2Dto(users);
			list.add(usersDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<UsersDTO> pageQueryUsers(UsersDTO queryVo, int currentPage, int pageSize) {
		List<UsersDTO> result = new ArrayList<UsersDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _users from Users _users   where 1=1 ");
	
	
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _users.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	
	   	if (queryVo.getFirstName() != null && !"".equals(queryVo.getFirstName())) {
	   		jpql.append(" and _users.firstName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFirstName()));
	   	}		
	
	   	if (queryVo.getLastName() != null && !"".equals(queryVo.getLastName())) {
	   		jpql.append(" and _users.lastName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getLastName()));
	   	}		
	
	   	if (queryVo.getEmail() != null && !"".equals(queryVo.getEmail())) {
	   		jpql.append(" and _users.email like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getEmail()));
	   	}		
	   	if (queryVo.getAge() != null) {
	   		jpql.append(" and _users.age=?");
	   		conditionVals.add(queryVo.getAge());
	   	}	
	
        Page<Users> pages = queryChannel.queryPagedResultByPageNo(jpql.toString(), conditionVals.toArray(), currentPage, pageSize);
        for (Users users : pages.getResult()) {
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.domain2Dto(users);
            result.add(usersDTO);
        }
        return new Page<UsersDTO>(Page.getStartOfPage(currentPage, pageSize), pages.getTotalCount(), pages.getPageSize(), result);
	}
	
	
}
