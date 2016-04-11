package com.softserve.webtester.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.softserve.webtester.exception.ResourceNotFoundException;
import com.softserve.webtester.mapper.EnvironmentMapper;
import com.softserve.webtester.model.Environment;

public class EnvironmentServiceTest {

    private Environment environment;
    
    @Mock
    private EnvironmentMapper environmentMapper;

    @InjectMocks
    private EnvironmentService environmentService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidLoad() {
        int validEnvironmentId = 10;
        environment = new Environment();
        environment.setName("valid Name");
        when(environmentMapper.load(validEnvironmentId)).thenReturn(environment);
        assertEquals("valid Name", environmentService.load(validEnvironmentId).getName());
    }
    
    @Test(expected=ResourceNotFoundException.class)
    public void testInValidLoad() {
        int inValidEnvironmentId = 1000;
        environment = null;
        when(environmentMapper.load(inValidEnvironmentId)).thenReturn(environment);
        when(environmentService.load(inValidEnvironmentId));
    }

    @Test
    public void testLoadAll() {
        List<Environment> listEnvironmetns = new ArrayList<Environment>();
        environment = new Environment();
        environment.setName("First");
        listEnvironmetns.add(environment);
        environment.setName("Second");
        listEnvironmetns.add(environment);
        
        when(environmentMapper.loadAll()).thenReturn(listEnvironmetns);
        assertTrue(environmentService.loadAll().size() == 2);
        
    }

    @Test
    public void testSave() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testIsNameFree() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testGetPooledDataSource() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testInitConnectionPools() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testInitConnectionPool() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testCheckConnection() {
        fail("Not yet implemented"); // TODO
    }

}
