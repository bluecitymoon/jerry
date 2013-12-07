
package com.jerry.jerry.application.core;

import java.util.List;
import com.dayatang.querychannel.support.Page;
import com.jerry.jerry.application.dto.*;

public interface AccountApplication {

	public AccountDTO getAccount(Long id);
	
	public AccountDTO saveAccount(AccountDTO account);
	
	public void updateAccount(AccountDTO account);
	
	public void removeAccount(Long id);
	
	public void removeAccounts(Long[] ids);
	
	public List<AccountDTO> findAllAccount();
	
	public Page<AccountDTO> pageQueryAccount(AccountDTO account, int currentPage, int pageSize);
	

}

