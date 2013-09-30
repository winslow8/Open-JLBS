package com.lanx.app.lbs;
import javax.sql.DataSource;

//import net.paoding.rose.jade.annotation.DAO;
//import net.paoding.rose.jade.annotation.SQL;
//import net.paoding.rose.jade.context.application.JadeFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 集成DAO和JadeFactory，测试数据库连接
 * 
 * @author Ramboo
 * @date 2013-09-08
 *
 */
public class DBTest {
//    @DAO
//    interface UserDAO {
//
//        @SQL("create table user (id int, name varchar(200));")
//        void createTable();
//
//        @SQL("insert into user (id, name) values(:1, :2);")
//        void insert(int id, String name);
//
//        @SQL("select name from user where id=:1")
//        String getName(int id);
//
//        @SQL("select name from user order by id asc")
//        String[] findNames();
//    }
//
//    // init方法负责初始化dao
//    private UserDAO dao;
//
//    @Before
//    public void init() {
//    	this.initHsqldbDataSource();
//    	//this.initMySQLDataSource();
//    }
//
//    private void initMySQLDataSource() {
//    	
//    }
//    
//    private void initHsqldbDataSource() {
//        DataSource dataSource = DataSources.createUniqueHsqldbDataSource();
//        JadeFactory factory = new JadeFactory(dataSource);
//        dao = factory.create(UserDAO.class);
//        dao.createTable();
//        dao.insert(1, "zhiliang1");
//        dao.insert(2, "zhiliang2");    	
//    }
//    
//    @Test
//    public void testGetName() {
//        Assert.assertEquals("zhiliang1", dao.getName(1));
//    }
}
