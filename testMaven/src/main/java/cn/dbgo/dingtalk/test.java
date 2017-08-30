package cn.dbgo.dingtalk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import aspect.UserAccess;
import cn.dbgo.dingtalk.mapper.StudentMapper;
import cn.dbgo.dingtalk.model.Student;



@Controller
public class test {
	
	@Autowired
	test2 test ;
	
	@Autowired
	StudentMapper studentMapper;

	public String tt(){
		Student student = studentMapper.selectByPrimaryKey(12);
		return student.getName();
	}

	private Logger logger = Logger.getLogger(test.class);
	@UserAccess(needLogin=false)
	@Transactional
	@RequestMapping(value = "/test")
	public void test2(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("test2");
//		try {
//			response.sendRedirect("http://www.baidu.com");
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		try {
			try {
				request.getRequestDispatcher("/test").forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Student student = studentMapper.selectByPrimaryKey(12);
//		try {
//			test.tt();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		System.out.print(student.getName());
		
		
//		System.out.print("\ntestssss\n");
//		logger.error("error11111111");
		try {
			response.getWriter().append("dfasdfas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		throw new RuntimeException(); 
		
	}
	
//	public static void main(String[] args) {
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        org.apache.cxf.endpoint.Client client = dcf.createClient("http://192.168.3.183:81/vatbe/soap/appImport?wsdl");
//        try {
//            Object[] objects = client.invoke("appInInvoiceImport", "123,123,213,123,123");
//            System.out.println(objects[0].getClass());
//            System.out.println(objects[0].toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
