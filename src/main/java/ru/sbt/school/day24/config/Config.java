package ru.sbt.school.day24.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.sbt.school.day24.dao.RecipeDao;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.sbt.jschool.day24")
@PropertySource("classpath:h2.properties")
public class Config {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        if (environment.getProperty("h2db").equals("mem"))
            return new DriverManagerDataSource("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql';DB_CLOSE_DELAY=-1");
        return new DriverManagerDataSource("jdbc:h2:tcp://localhost/~/test;","sa", "");

    }
    @Bean
    public RecipeDao recipeDao(){
//        return new RecipeDao(dataSource());
        return new RecipeDao();
    }

}
