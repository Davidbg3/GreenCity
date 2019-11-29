package com.example.greencity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccesoTest {
    private Acceso mAcceso;
    @Before
    public  void setUp(){
        mAcceso = new Acceso();
    }
    @Test
    public void operacionNoNull(){
        assertNotNull(mAcceso);
    }
    @Test
    public void logeo() {
        assertEquals(true, mAcceso.Logeo("administrador.appgc@gmail.com", "Admin2019"));
    }
}

