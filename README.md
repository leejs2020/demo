### Spring boot 2.2.6 + JSF 2.2 + PrimeFaces 7.0

---

#### Project structure

- java

  - config 
  - controller
  - mapper
  - service
  - vo

- resource

  - mapper

  - application.yaml
  - logback-spring.xml
  - mybatis-config.xml

- webapp

  - WEB-INF
    - faces-config.xml
    - web.xml

---
#### Pom.xml 설정

```	xml

  <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.6.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.genians</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>demo</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<!-- MyBatis -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.1.2</version>
		</dependency>

		<!-- MySQL -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>

		<!-- gson -->
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.6</version>
		</dependency>

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>

		<dependency>
			<groupId>org.glassfish</groupId>
			<artifactId>javax.faces</artifactId>
			<version>2.2.12</version>
		</dependency>

		<!-- cdi-api -->
		<dependency>
			<groupId>javax.enterprise</groupId>
			<artifactId>cdi-api</artifactId>
			<version>2.0.SP1</version>
			<scope>compile</scope>
			<exclusions>
				<exclusion>
					<groupId>javax.el</groupId>
					<artifactId>el-api</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.el</groupId>
					<artifactId>javax.el-api</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>2.2.20</version>
		</dependency>

		<!-- jsf-impl -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>2.2.20</version>
		</dependency>

		<!-- primefaces -->
		<dependency>
			<groupId>org.primefaces</groupId>
			<artifactId>primefaces</artifactId>
			<version>7.0</version>
		</dependency>
    </dependencies>
```

---
#### Spring Annotation

**@SpringBootApplication**	

- 기존 Spring에서 사용하는 `@Configuration`, `@EnableAutoConfiguration`**,** `@EnableWebMvc`**,** `@ComponentScan` 을 포함하며 추가적인 설정파일 없이도 실행가능한 어플리케이션을 구성할 수 있도록 해준다.

**@Controller**

- 필요한 비즈니스 로직을 호출하여 전달할 모델(Model)과 이동할 뷰(View) 정보를  반환 한다.
- `@Component`의 구체화 된 어노테이션
- `@RequestMapping` 요청 URL을 어떤 메서드가 처리할지 여부를 결정
  - method = RequestMethod.GET,POST,PUT,DELETE... , value = "/{value}" or value = "URI" 
- JSON형태의 Data를 넘겨줄 때 Method 에 `@ResponseBody` 를 선언

  ```java
    @Controller
    @RequestMapping("/demo")
    public class SamepleController {

        @RequestMapping("/hello")
        public String hello() throws Exception{
            return "Hello world";
        }

        @RequestMapping("/world")
        public @ResponseBody String world() throws Exception{
            return "Hello JSON";
        }
    }
  ```

**@RestController**

- Controller에 @ResponseBody가 추가. 주용도는 Json/Xml 형태로 객체 데이터를 반환

- REST API를 제작할 때 주로 사용.
  ```	java
  @RestController
  @RequestMapping("/demo")
  public class SimpleRestController {
   
      @GetMapping("/user/{id}")
      public String hello(@PathVariable String id) throws Exception{
          // doSomething
      }
   
      @PostMapping("/user/{id}")
      public String world(@PathVariable String id) throws Exception{
          // doSomething
      }
      
      @PutMapping("/user/{id}")
      public @ResponseBody String Hellow(@PathVariable String id) throws Exception{
          // doSomething
      }
      
      @DeleteMapping("/user/{id}")
      public @ResponseBody String world(@PathVariable String id) throws Exception{
          // doSomething
      } 
  }
  ```



**@Named**

-  `@Named` 주된 역할은 애플리케이션 내에서 (JSF EL) EL 문을 해석 할 목적으로 Bean을 정의


**@Configuration**

- `@Configuration` Annotation은 어노테이션기반 환경구성을 설정.

- 클래스가 하나 이상의 `@Bean` 메소드를 제공하고 스프링 컨테이너가 Bean정의를 생성하고 런타임시 그 Bean들이 요청들을 처리할 것을 선언하게 된다. 

  ```java
  @Configuration
  @MapperScan(basePackages = "com.example.demo.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
  public class DataSourceConfig {
  
  	  @Bean
      @ConfigurationProperties(prefix = "spring.datasource.hikari")
      public DataSource dataSource() {
          return DataSourceBuilder.create()
              .type(HikariDataSource.class)
              .build();
      }
   
      @Bean
      public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
          SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
          sessionFactory.setDataSource(dataSource);
          sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
          return sessionFactory.getObject();
      }
   
      @Bean
      public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
          return new SqlSessionTemplate(sqlSessionFactory);
      }
  ```

**@Autowired**

- `@Inject`  의 역활, Spring Framework 종속

---

####	MyBatis 

**@Repository**

- DAO 구성시 (mapper) class 로 할 시에 선언

  ```java
  @Repository
  public class DemoMapper {
  
  	@Autowired
  	private SqlSession sqlSession;
  
  	public List<DemoVo> selectBoardList() {
  		return sqlSession.selectList("selectBoardList2");
  	}
  	
  	public void insertBoard(DemoVo vo){
  		sqlSession.insert("insertBoard", vo);
  	}
  	
  	public void deleteBoard(String no) {
  		sqlSession.update("deleteBoard", no);
  	}
  }
  ```

**@Mapper**

- DAO 구성시 (mapper) interface 로 할 시에 선언

---

**Junit5**
```java
package com.example.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private SqlSessionFactory sqlFactory;

	@Test
	void contextLoads() {
	}

	@Test
	void connTest() {
		try (SqlSession session = sqlFactory.openSession()) {

			System.out.println(session);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}

```
