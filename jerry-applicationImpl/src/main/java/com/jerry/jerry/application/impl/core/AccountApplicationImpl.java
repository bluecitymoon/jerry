
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
import com.jerry.jerry.application.core.AccountApplication;
import com.jerry.jerry.core.*;

@Named
@Transactional
public class AccountApplicationImpl implements AccountApplication {

	@Inject
	private QueryChannelService queryChannel;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountDTO getAccount(Long id) {
		Account account = Account.load(Account.class, id);
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.domain2Dto(account);
		accountDTO.setId(account.getId());
		return accountDTO;
	}
	
	public AccountDTO saveAccount(AccountDTO accountDTO) {
		Account account = new Account();
		accountDTO.dto2Domain(account);
		account.save();
		accountDTO.setId(account.getId());
		return accountDTO;
	}
	
	public void updateAccount(AccountDTO accountDTO) {
		Account account = Account.get(Account.class, accountDTO.getId());
		accountDTO.dto2Domain(account);
	}
	
	public void removeAccount(Long id) {
		this.removeAccounts(new Long[] { id });
	}
	
	public void removeAccounts(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Account account = Account.load(Account.class, ids[i]);
			account.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountDTO> findAllAccount() {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		List<Account> all = Account.findAll(Account.class);
		for (Account account : all) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.domain2Dto(account);
			list.add(accountDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<AccountDTO> pageQueryAccount(AccountDTO queryVo, int currentPage, int pageSize) {
		List<AccountDTO> result = new ArrayList<AccountDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _account from Account _account   where 1=1 ");
	
	
        Page<Account> pages = queryChannel.queryPagedResultByPageNo(jpql.toString(), conditionVals.toArray(), currentPage, pageSize);
        for (Account account : pages.getResult()) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.domain2Dto(account);
            result.add(accountDTO);
        }
        return new Page<AccountDTO>(Page.getStartOfPage(currentPage, pageSize), pages.getTotalCount(), pages.getPageSize(), result);
	}
	
	
}
