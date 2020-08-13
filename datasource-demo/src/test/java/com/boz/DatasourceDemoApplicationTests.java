package com.boz;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceDemoApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getData(){
		System.out.println(dataSource.getClass());

		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from customer");
		maps.forEach(System.out::println);

	}
}
