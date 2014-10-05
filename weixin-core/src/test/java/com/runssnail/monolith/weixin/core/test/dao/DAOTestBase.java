package com.runssnail.monolith.weixin.core.test.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.runssnail.monolith.weixin.core.test.FileContextLoader;

@ContextConfiguration(locations = { "data-source-beans.xml", "dao-beans.xml" }, loader = FileContextLoader.class)
public class DAOTestBase extends AbstractJUnit4SpringContextTests {

}
