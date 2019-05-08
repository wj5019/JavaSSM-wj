//5.【部门管理】--【保存按钮】
	@RequestMapping("/sysadmin/dept/save")
	public String save(Dept dept,Model model) {
		//获取deptId
		String deptId = dept.getDeptId();
		//非空校验
		if(deptId ==null || "".equals(deptId)) {
			//如果没有id信息，那么返回当前页面
			return "/sysadmin/dept/jDeptSave";
		}
		//主键冲突的校验
		Dept check = deptService.queryById(deptId);
		if(check != null) {//如果查出已经存在该数据那么不能插入
			return "/sysadmin/dept/jDeptSave";
		}
		//如果校验通过，那么插入数据库
		deptService.save(dept);
		//最终重定向到list请求刷新列表
		return "redirect:/sysadmin/dept/list";
	}
}
