package com.nedap.archie.aom.utils;

import com.nedap.archie.definitions.AdlCodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AOMUtilsTest {

    @Test
    public void codeAtLevel() {


        assertEquals("id1", AdlCodeUtils.codeAtLevel("id1", 0));
        assertEquals("id1", AdlCodeUtils.codeAtLevel("id1.1", 0));
        assertEquals("id1.1", AdlCodeUtils.codeAtLevel("id1.1", 1));
        assertEquals("id1.1", AdlCodeUtils.codeAtLevel("id1.1.1", 1));
        assertEquals("id1", AdlCodeUtils.codeAtLevel("id1.0.1", 1));
    }

}
