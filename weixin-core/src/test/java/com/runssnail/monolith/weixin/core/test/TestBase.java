package com.runssnail.monolith.weixin.core.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "data-source-beans.xml", "dao-beans.xml", "service-beans.xml", }, loader = FileContextLoader.class)
public class TestBase extends AbstractJUnit4SpringContextTests {

}
