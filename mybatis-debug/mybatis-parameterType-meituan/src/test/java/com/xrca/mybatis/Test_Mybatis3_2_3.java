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
 * @description 测试（mybatis版本：3.2.3）
 * @date 2020-08-08 10:35
 */
public class Test_Mybatis3_2_3 {

    /**
     * @Author xrca
     * @Description 将mybatis版本号降至3.2.3，测试更新语句
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
}
