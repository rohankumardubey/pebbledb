package com.pebbledb.server;

import com.jsoniter.spi.TypeLiteral;

import java.util.HashMap;
import java.util.Set;

public final class Types {
    public static final TypeLiteral<HashMap<String, Object>> MAP = new TypeLiteral<HashMap<String, Object>>(){};
    public static final TypeLiteral<Set<String>> SET = new TypeLiteral<Set<String>>(){};
    public static final  TypeLiteral<Object> OBJECT = new TypeLiteral<Object>(){};
}
