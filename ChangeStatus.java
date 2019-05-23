3.4.1ItemMapper.xml
<!-- 上架reshelf下架instock map(status,array[1,2,3])-->
<update id="updateStatus" parameterType="map">
update tb_item set status=#{status} 
	where id in 
<foreach collection="ids" item="id" open="(" close=")" separator=",">
	#{id}
</foreach>
</update>
3.4.2ItemMapper.java
package com.jt.manage.mapper;

import java.util.List;
import java.util.Map;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{
	public List<Item> list();
	//上架，下架
	public void updateStatus(Map<String,Object> map);
}
3.4.1ItemService
public void updateStatus(Integer status, Long[] ids){
	Map<String,Object> params = new HashMap<String,Object>();
	params.put("status", status);
	params.put("ids", ids);
	
	itemMapper.updateStatus(params);
}
3.4.1ItemController
	//下架
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instock(Long[] ids){
		try{
			itemService.updateStatus(2, ids);		//2下架
			return SysResult.oK();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, "修改为下架状态失败!");
		}
	}
	
	//上架
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelf(Long[] ids){
		try{
			itemService.updateStatus(1, ids);		//1正常
			return SysResult.oK();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, "修改为上架状态失败!");
		}
	} 
