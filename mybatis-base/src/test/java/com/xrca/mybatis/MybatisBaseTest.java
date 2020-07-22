package com.xrca.mybatis;

import com.xrca.entity.Subsidy;
import com.xrca.mapper.SubsidyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author xrca
 * @description mybatis基础测试用例
 * @date 2020-07-22 23:22
 */
public class MybatisBaseTest {

    @Test
    public void testSelectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SubsidyMapper subsidyMapper = sqlSession.getMapper(SubsidyMapper.class);
        Subsidy subsidy = subsidyMapper.selectById(10086L);
        System.out.println(subsidy);
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
}
