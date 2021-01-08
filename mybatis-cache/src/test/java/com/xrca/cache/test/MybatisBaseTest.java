package com.xrca.cache.test;

import com.xrca.cache.entity.Subsidy;
import com.xrca.cache.mapper.SubsidyMapper;
import org.apache.ibatis.cache.CacheException;
import org.apache.ibatis.executor.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;

/**
 * @author xrca
 * @description mybatis基础测试用例
 * @date 2020/12/28 11:07
 */
public class MybatisBaseTest {

    @Test
    public void testSelectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SubsidyMapper subsidyMapper = sqlSession.getMapper(SubsidyMapper.class);
        Subsidy subsidy = subsidyMapper.selectById(1L);
        subsidyMapper.toString();
        subsidyMapper.init();
        //subsidy.setPersonName("hahaha");
        //sqlSession.clearCache();
        Subsidy subsidy2 = subsidyMapper.selectById(1L);
        /*Subsidy newSubsidy = new Subsidy();
        newSubsidy.setPersonName("xiaoming");
        subsidyMapper.insertOne(newSubsidy);*/
        //subsidyMapper.insertTest();
        //sqlSession.commit();
        // 清空一级缓存
        //sqlSession.clearCache();
        Subsidy subsidy3 = subsidyMapper.selectById(1L);
        System.out.println(subsidy);
        sqlSession.commit();
    }

    @Test
    public void testSelectById2() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Subsidy subsidy = sqlSession.selectOne("com.xrca.mapper.SubsidyMapper.selectById", 10010L);
        System.out.println(subsidy);
    }

    @Test
    public void testSecondCache() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SubsidyMapper subsidyMapper = sqlSession.getMapper(SubsidyMapper.class);
        Subsidy subsidy = subsidyMapper.selectById(1L);
        System.out.println(subsidy);
        sqlSession.commit();

        subsidyMapper.insertTest();
        sqlSession.commit();

        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        SubsidyMapper subsidyMapper2 = sqlSession2.getMapper(SubsidyMapper.class);
        Subsidy subsidy2 = subsidyMapper2.selectById(1L);
        System.out.println(subsidy2);



        sqlSession2.close();

        System.out.println(subsidy == subsidy2);
    }

    @Test
    public void testParams() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SubsidyMapper subsidyMapper = sqlSession.getMapper(SubsidyMapper.class);
        List<Subsidy> subsidys = subsidyMapper.selectAll(1L, "'xrca2' or 1 = 1", "'xrca2'");
        System.out.println(subsidys.size());
    }

    /**
     * @author xrca
     * @description mybatis原理分析
     * @date 2020/12/29 10:25
     **/
    @Test
    public void simpleTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Configuration configuration = sqlSessionFactory.getConfiguration();

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.200:3307/xrca_dev", "root", "123456");
        JdbcTransaction transaction = new JdbcTransaction(connection);

        MappedStatement ms = configuration.getMappedStatement("com.xrca.cache.mapper.SubsidyMapper.selectById");

        SimpleExecutor executor = new SimpleExecutor(configuration, transaction);

        List<Object> list = executor.doQuery(ms, 1L, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER,
                ms.getBoundSql(10));
        executor.doQuery(ms, 1L, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER,
                ms.getBoundSql(10));
        System.out.println(list.get(0));
    }

    /**
     * @author xrca
     * @description 可重复使用executor
     * @date 2020/12/29 10:39
     **/
    @Test
    public void reuseTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Configuration configuration = sqlSessionFactory.getConfiguration();

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.200:3307/xrca_dev", "root", "123456");
        JdbcTransaction transaction = new JdbcTransaction(connection);

        MappedStatement ms = configuration.getMappedStatement("com.xrca.cache.mapper.SubsidyMapper.selectById");

        ReuseExecutor executor = new ReuseExecutor(configuration, transaction);

        List<Object> list = executor.doQuery(ms, 1L, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER,
                ms.getBoundSql(10));
        executor.doQuery(ms, 2L, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER,
                ms.getBoundSql(10));
        System.out.println(list.get(0));
    }

    /**
     * @author xrca
     * @description 批处理执行器
     * @date 2020/12/29 10:39
     **/
    @Test
    public void batchTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Configuration configuration = sqlSessionFactory.getConfiguration();

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.200:3307/xrca_dev", "root", "123456");
        JdbcTransaction transaction = new JdbcTransaction(connection);

        MappedStatement ms = configuration.getMappedStatement("com.xrca.cache.mapper.SubsidyMapper.insertTest");

        BatchExecutor executor = new BatchExecutor(configuration, transaction);

        executor.doUpdate(ms, new HashMap<>());
        executor.doUpdate(ms, new HashMap<>());

        //刷新保存数据
        executor.doFlushStatements(false);
    }

    /**
     * @author xrca
     * @description CacheExecutor
     * @date 2020/12/30 11:12
     **/
    @Test
    public void cacheExecutorTest() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Configuration configuration = sqlSessionFactory.getConfiguration();

        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.200:3307/xrca_dev", "root", "123456");
        JdbcTransaction transaction = new JdbcTransaction(connection);

        MappedStatement ms = configuration.getMappedStatement("com.xrca.cache.mapper.SubsidyMapper.selectById");

        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration, transaction);

        // 装饰着模式
        CachingExecutor cachingExecutor = new CachingExecutor(simpleExecutor);

        cachingExecutor.query(ms, 1L, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        // 提交，使二级缓存生效
        cachingExecutor.commit(true);



        cachingExecutor.query(ms, 1L, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        cachingExecutor.query(ms, 1L, RowBounds.DEFAULT, new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                resultContext.getResultObject();
                System.out.println("handler");
            }
        });

        cachingExecutor.query(ms, 1L, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);

        Runtime.getRuntime().availableProcessors();
    }
}
