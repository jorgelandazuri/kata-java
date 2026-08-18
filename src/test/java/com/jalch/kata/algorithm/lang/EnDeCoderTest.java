package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnDeCoderTest {

    @Test
    public void encode_null() {
        assertEquals("", EnDeCoder.encode(null));
    }

    @Test
    public void encode_empty() {
        assertEquals("", EnDeCoder.encode(""));
    }

    @Test
    public void encode_not_empty() {
        assertEquals("f2o", EnDeCoder.encode("foo"));
        assertEquals("f2of2o", EnDeCoder.encode("foofoo"));
        assertEquals("f12o", EnDeCoder.encode("foooooooooooo"));
        assertEquals("bar", EnDeCoder.encode("bar"));
        assertEquals("he2lo", EnDeCoder.encode("hello"));
    }

    @Test
    public void decode_null() {
        assertEquals("", EnDeCoder.decode(null));
    }

    @Test
    public void decode_empty() {
        assertEquals("", EnDeCoder.decode(""));
    }

    @Test
    public void decode_not_empty() {
        assertEquals("foo", EnDeCoder.decode("f2o"));
        assertEquals("foofoo", EnDeCoder.decode("f2of2o"));
        assertEquals("foooooooooooo", EnDeCoder.decode("f12o"));
        assertEquals("bar", EnDeCoder.decode("bar"));
        assertEquals("hello", EnDeCoder.decode("he2lo"));
    }
}