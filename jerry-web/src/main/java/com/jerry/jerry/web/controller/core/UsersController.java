
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
import com.jerry.jerry.application.core.UsersApplication;
import com.jerry.jerry.application.dto.*;

@Controller
@RequestMapping("/Users")
public class UsersController {
		
	@Inject
	private UsersApplication usersApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(UsersDTO usersDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		usersApplication.saveUsers(usersDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(UsersDTO usersDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		usersApplication.updateUsers(usersDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Map<String, Object> pageJson(UsersDTO usersDTO, @RequestParam int page, @RequestParam int pagesize) {
		Map<String, Object> result = new HashMap<String, Object>();
		Page<UsersDTO> all = usersApplication.pageQueryUsers(usersDTO, page, pagesize);
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
        usersApplication.removeUserss(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", usersApplication.getUsers(id));
		return result;
	}
	
		
}