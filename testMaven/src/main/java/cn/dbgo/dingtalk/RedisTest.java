package cn.dbgo.dingtalk;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dbgo.dingtalk.model.Student;

@Controller
public class RedisTest {
	
	public static final String SAASACCOUNT_KEY = "111111";

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> template;
	
	@RequestMapping(value = "/redis")
	public void redis(HttpServletRequest request, HttpServletResponse response){
		List stu = new ArrayList<>();
		for(int i=0;i<2;i++){
			Student d = new Student();
			d.setId(i);
			d.setName(i+"name2");
			d.setAge(i+"age2");
			stu.add(d);
		}
		saveAccount(stu);
		try {
			response.getWriter().append("dfasdfas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/read")
	public void read(HttpServletRequest request, HttpServletResponse response){
		Student d = getAccountInfo("0name2");
		try {
			response.getWriter().append(d.getAge());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveAccount(List<Student> list) {
		String key = SAASACCOUNT_KEY;
//		if (template.hasKey(key))
//			template.delete(key);

		if (list == null)
			return;
		
		HashOperations<String, String, Map<String, Object>> hashOper = template.opsForHash();
		for (int i = 0; i < list.size(); i++) {
			Student info = list.get(i);
			String hashKey = info.getName();
			Map<String, Object> hv = transBean2Map(info);
			hashOper.put(key, hashKey, hv);
		}
		ValueOperations<String, String>  valueOp = template.opsForValue();
		valueOp.set("test", "1213");
		valueOp.set("test1", "1213222");
	}
	
	private Map<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (value != null)
						map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
	
	private static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}
	
	public Student getAccountInfo(String account) {
		Student info = null;
		HashOperations<String, String, Map<String, Object>> hashOper = template.opsForHash();
		String key = SAASACCOUNT_KEY;
		if (template.hasKey(key)) {
			Map<String, Object> map = hashOper.get(key, account);
			if (map != null) {
				info = new Student();
				transMap2Bean(map, info);
			}
		}
		return info;
	}

}
