package com.xrca.mybatis;

import com.xrca.entity.InvoiceLog;
import com.xrca.mapper.InvoiceLogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * @author xrca
 * @description 测试（mybatis版本：3.2.4）
 * @date 2020-08-08 10:24
 */
public class Test_Mybatis3_2_4 {

    /**
     * @Author xrca
     * @Description 测试mybatis根据id查询
     * @Date 2020-08-08 10:23
     * @Param []
     * @return void
     **/
    @Test
    public void testSelect() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        InvoiceLogMapper invoiceLogMapper = sqlSession.getMapper(InvoiceLogMapper.class);
        InvoiceLog invoiceLog = invoiceLogMapper.selectById(1L);
        System.out.println(invoiceLog);
    }

    /**
     * @Author xrca
     * @Description 将mybatis版本号更改为3.2.4，测试update，最终结果出错
     * @Date 2020-08-08 10:44
     * @Param []
     * @return void
     **/
    @Test
    public void testUpdate() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        InvoiceLogMapper invoiceLogMapper = sqlSession.getMapper(InvoiceLogMapper.class);
        int result = invoiceLogMapper.updateResponse(1L, "successful", LocalDateTime.now());
        System.out.println(result);
    }

    /**
     * @Author xrca
     * @Description 将xml中的parameterType去除，让mybatis自动识别，执行成功
     * @Date 2020-08-08 10:47
     * @Param []
     * @return void
     **/
    @Test
    public void testUpdate2() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        InvoiceLogMapper invoiceLogMapper = sqlSession.getMapper(InvoiceLogMapper.class);
        int result = invoiceLogMapper.updateResponse2(1L, "successful", LocalDateTime.now());
        // 提交事务
        sqlSession.commit();
        System.out.println(result);
    }
}
