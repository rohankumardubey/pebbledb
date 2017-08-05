package com.pebbledb.actions;

public enum Action {
    GET_RELATIONSHIP_TYPES, GET_RELATIONSHIP_TYPES_COUNT, GET_RELATIONSHIP_TYPE_COUNT,

    GET_NODE, POST_NODE, DELETE_NODE,
    PUT_NODE_PROPERTIES, DELETE_NODE_PROPERTIES,
    GET_NODE_PROPERTY, PUT_NODE_PROPERTY, DELETE_NODE_PROPERTY,

    GET_RELATIONSHIP, POST_RELATIONSHIP, DELETE_RELATIONSHIP,
    PUT_RELATIONSHIP_PROPERTIES, DELETE_RELATIONSHIP_PROPERTIES,
    GET_RELATIONSHIP_PROPERTY, PUT_RELATIONSHIP_PROPERTY, DELETE_RELATIONSHIP_PROPERTY,NOOP;
}
