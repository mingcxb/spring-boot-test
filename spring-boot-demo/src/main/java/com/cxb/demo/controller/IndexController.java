package com.cxb.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

	/**
	 * Go Index
	 * 
	 * @return
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = { "", "/", "index" })
	public String index() {
		Subject subject = SecurityUtils.getSubject();
//		DefaultWebSecurityManager
        log.info(subject.getPrincipal().toString());
		return "index";
	}

	/**
	 * unauthor
	 * 
	 * @return
	 */
	@RequestMapping("unauthor")
	public String unauthor() {
		return "unauthor";
	}

	/**
	 * reports
	 * 
	 * @return
	 */
	@RequestMapping("reports")
	public String reports() {
		return "reports";
	}
}
