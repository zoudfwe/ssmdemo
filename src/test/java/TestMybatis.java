import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rml.model.MUser;
import rml.model.SysPermission;
import rml.model.SysRole;
import rml.model.SysUser;
import rml.service.MUserServiceI;
import rml.service.SysUserServiceI;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-context-mybatis.xml" })
public class TestMybatis {

	private static final Logger logger = Logger.getLogger(TestMybatis.class);

	private MUserServiceI muserService;
	private SysUserServiceI sysUserService;
	

	public SysUserServiceI getSysUserService() {
		return sysUserService;
	}
	@Autowired
	public void setSysUserService(SysUserServiceI sysUserService) {
		this.sysUserService = sysUserService;
	}

	public MUserServiceI getMuserService() {
		return muserService;
	}

	@Autowired
	public void setMuserService(MUserServiceI muserService) {
		this.muserService = muserService;
	}
	
	@Test
	public void test1() {
		
		/*List<MUser> list = muserService.getAll();
		logger.info(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));*/
		
		System.out.println("---------start---------------");
		/*MUser user = muserService.selectByPrimaryKey("0002");
		System.out.println(user.getName());*/
		
//		SysUser user = sysUserService.getSysUserById("1");
		SysUser user = sysUserService.getSysUserByUsername("admin");
		if(null!=user){
			List<SysRole> roles = sysUserService.getSysRolesBySysUserId(user.getId());
			if(null!=roles && roles.size()>0){  
				//获取当前登录用户的角色  
				for(SysRole role : roles){  
					System.out.println("---role---"+role.getName());
					//实体类SysRole中包含有角色权限的实体类信息  
					List<SysPermission> sysPermissions = sysUserService.getSysPermissionsBySysRoleId(role.getId());
					if(null!=sysPermissions && sysPermissions.size()>0){  
						//获取权限  
						for(SysPermission pmss : sysPermissions){  
							if(!StringUtils.isEmpty(pmss.getPermission())){  
								System.out.println("---permission---"+pmss.getName()+ "---" +pmss.getPermission());
							}  
						}  
					}  
				}  
			}  
		}
		System.out.println("---------end---------------");
	}
	
//	@Test
	public void test2() {
	
		MUser muser = new MUser();
		muser.setId("0003");
		muser.setName("aaaa");
		muser.setAge(1234);
		muser.setAddress("ABCD");
		int i = muserService.insert(muser);
		logger.info(JSON.toJSONStringWithDateFormat("add "+i, "yyyy-MM-dd HH:mm:ss"));
	}
	
//	@Test
	public void test3() {
		
		MUser muser = new MUser();
		muser.setId("0000");
		muser.setName("bbbb");
		muser.setAge(1234);
		muser.setAddress("ABCD");
		int i = muserService.update(muser);
		logger.info(JSON.toJSONStringWithDateFormat("update " +i, "yyyy-MM-dd HH:mm:ss"));
	}
	
	//@Test
	public void test4() {
		
		MUser muser = new MUser();
		muser.setId("0000");
		muser.setName("bbbb");
		muser.setAge(1234);
		muser.setAddress("ABCD");
		int i = muserService.delete("0000");
		logger.info(JSON.toJSONStringWithDateFormat("delete "+i, "yyyy-MM-dd HH:mm:ss"));
	}
//	@Test
	public void selectMUserByWhere(){
		String name = "张";
		int age = 22;
		String address = "公关";
		List<MUser> users = muserService.selectMUserByWhere(name, age, address);
		logger.info("--------users----------"  + users.size());
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("age", age+"");
		map.put("address", address);
		List<MUser> users2 = muserService.selectMUserByWhereMap(map);
		logger.info("--------users2---------"  + users2.size());
	}
	
}
