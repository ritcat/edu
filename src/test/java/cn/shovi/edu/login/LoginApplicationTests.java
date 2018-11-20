package cn.shovi.edu.login;

import cn.shovi.edu.service.UserService;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.annotation.Resource;
import javax.naming.Name;
import javax.sql.DataSource;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {
	@Resource(name = "druid")
	DataSource dataSource;
	@Autowired
	UserService userService;
	/*
	* test druid datasource
	* */
	@Test
	public void contextLoads() throws SQLException{

		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}

	@Test
	public void testUserServiceRegister() throws SQLException{


	}
}
