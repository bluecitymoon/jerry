
package com.jerry.jerry.web.controller.core;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.dayatang.querychannel.support.Page;
import com.jerry.jerry.application.core.AccountApplication;
import com.jerry.jerry.application.dto.*;

@Controller
@RequestMapping("/Account")
public class AccountController {
		
	@Inject
	private AccountApplication accountApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(AccountDTO accountDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		accountApplication.saveAccount(accountDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(AccountDTO accountDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		accountApplication.updateAccount(accountDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Map<String, Object> pageJson(AccountDTO accountDTO, @RequestParam int page, @RequestParam int pagesize) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<AccountDTO> all = accountApplication.pageQueryAccount(accountDTO, page, pagesize);
		result.put("Rows", all.getResult());
		result.put("start", page * pagesize - pagesize);
		result.put("limit", pagesize);
		result.put("Total", all.getTotalCount());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	idArrs[i] = Long.parseLong(value[i]);
        }
        accountApplication.removeAccounts(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", accountApplication.getAccount(id));
		return result;
	}
	
		
}