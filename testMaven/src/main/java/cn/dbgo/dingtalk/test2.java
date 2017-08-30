package cn.dbgo.dingtalk;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aspect.UserAccess;
import cn.dbgo.dingtalk.mapper.StudentMapper;
import cn.dbgo.dingtalk.model.Student2;

@Repository
@Service
@Document(collection="fdfdf")
public class test2 {
	@Autowired
	StudentMapper studentMapper;
	
	@Resource(name="mongoTemplate")
	MongoTemplate mongoTemplate;

	@Transactional
	@UserAccess
	public void tt(){
//		Student student = studentMapper.selectByPrimaryKey(12);
		System.out.print("service");
//		Student2 student = new Student2();
//		student.setId(1100);
//		student.setName("name");
//		student.setAge("123");
//		
//		mongoTemplate.insert(student);
//		studentMapper.insert(student);
//		throw new RuntimeException(); 
	}

}
