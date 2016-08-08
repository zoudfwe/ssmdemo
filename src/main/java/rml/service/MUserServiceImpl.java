package rml.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.MUserMapper;
import rml.model.MUser;

@Service("muserService")
public class MUserServiceImpl implements MUserServiceI{

	private MUserMapper muserMapper;
		
	public MUserMapper getMuserMapper() {
		return muserMapper;
	}

	@Autowired
	public void setMuserMapper(MUserMapper muserMapper) {
		this.muserMapper = muserMapper;
	}
	
	@Override
	public List<MUser> getAll() {
		
		return muserMapper.getAll();
	}

	@Override
	public int insert(MUser muser) {
		
		return muserMapper.insert(muser);
	}

	@Override
	public int update(MUser muser) {
		
		return muserMapper.updateByPrimaryKey(muser);
	}

	@Override
	public int delete(String id) {
	
		return muserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public MUser selectByPrimaryKey(String id) {
		
		return muserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<MUser> selectMUserByWhere(String name, int age, String address) {
		MUser muser = new MUser();
		muser.setName(name);
		muser.setAddress(address);
		muser.setAge(age);
//		return muserMapper.selectMUserByWhere(name, age, address);
		return muserMapper.selectMUserByWhere(muser);
	}

	@Override
	public List<MUser> selectMUserByWhereMap(Map<String,String> map) {
		return muserMapper.selectMUserByWhereMap(map);
	}

}
