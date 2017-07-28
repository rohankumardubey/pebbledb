package com.pebbledb.benchmarks;

import com.pebbledb.Graph;
import org.junit.Ignore;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class GraphWriteBenchmarks {

    private Graph db;
    private Random rand = new Random();

    @Param({"1000"})
    private int userCount;

    @Param({"100"})
    private int personCount;

    @Param({"20000"})
    private int itemCount;

    @Param({"100"})
    private int friendsCount;

    @Param({"100"})
    private int likesCount;

    @Setup(Level.Iteration)
    public void prepare() throws IOException {
        db = new Graph();

        for (int item = 0; item < itemCount; item++) {
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("id", item);
            properties.put("itemname", "itemname" + item );
            db.addNode("item" + item, properties);
        }

        for (int person = 0; person < personCount; person++) {
            for (int like = 0; like < likesCount; like++) {
                db.addRelationship("LIKES", "person" + person, "item" + rand.nextInt(itemCount));
            }
        }
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureCreateEmptyNode() throws IOException {
        db.addNode("user" + rand.nextLong());
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Ignore
    public int measureCreateEmptyNodes() throws IOException {
        int user;
        for (user = 0; user < userCount; user++) {
            db.addNode("user" + user);
        }
        db.clear();
        return user;
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public int measureCreateNodesWithProperties() throws IOException {
        int user;
        for (user = 0; user < userCount; user++) {
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("username", "username" + rand.nextInt() );
            properties.put("age", + rand.nextInt(100) );
            properties.put("weight", rand.nextInt(300) );
            db.addNode( String.valueOf(rand.nextInt()), properties);
        }
        return user;
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureCreateNodeWithProperties() throws IOException {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("username", "username" + rand.nextInt() );
        properties.put("age", + rand.nextInt(100) );
        properties.put("weight", rand.nextInt(300) );
        db.addNode( String.valueOf(rand.nextInt()), properties);
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @Fork(1)
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public int measureCreateEmptyNodesAndRelationships() throws IOException {
        int user;
        for (user = 0; user < userCount; user++) {
            db.addNode("user" + user);
        }
        for (user = 0; user < userCount; user++) {
            for (int like = 0; like < friendsCount; like++) {
                db.addRelationship("FRIENDS", "user" + user, "user" + rand.nextInt(userCount));
            }
        }
        return user;
    }

}
