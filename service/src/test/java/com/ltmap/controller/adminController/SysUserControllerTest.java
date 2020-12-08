package com.gytech.controller.adminController;

import com.alibaba.fastjson.JSON;
import com.gytech.Base.BaseLogger;
import com.gytech.AppApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LQ on 2018/9/14.
 * com.gytech.controller.adminController
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
@WebAppConfiguration
public class SysUserControllerTest extends BaseLogger {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void before() throws Exception{
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @After
    public void after() throws Exception {

    }

    @Test
    public void test_query() throws Exception {
        String uri = "/admin/sysUser/query/";
        Map param = new HashMap();
        logger.info("test_query_param:" + JSON.toJSONString(param));
        RequestBuilder request = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("SESSIONNO", "");
        MvcResult mvcResult = mockMvc.perform(request).andReturn() ;
        String content = mvcResult.getResponse().getContentAsString();
        logger.info("test_query_result："+content);
    }

    @Test
    public void test_test() throws Exception {
        logger.info("test_query_result："+"test");
    }

}
