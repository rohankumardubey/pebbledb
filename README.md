# Pebble DB


    Pebble DB is a Proof of Concept in building an in-memory, single threaded, replicated database system.
    Following some of the same ideas as VoltDB, as explained http://slideshot.epfl.ch/play/suri_stonebraker
    with a short version https://www.youtube.com/watch?v=0wcNqez6bMU
    
    It is meant to handle small graphs or lots of small isolated sub-graphs (like those found in SaaS Applications).

[![Coverage Status](https://coveralls.io/repos/github/maxdemarzi/pebbledb/badge.svg?branch=master)](https://coveralls.io/github/maxdemarzi/pebbledb?branch=master)
[![Build Status](https://travis-ci.org/maxdemarzi/pebbledb.svg?branch=master)](https://travis-ci.org/maxdemarzi/pebbledb)
    
### Todos
     
- [ ] Build Graph
- [ ] Switch relationships to an Array. Use [nodeId,relId]
- [ ] Finish Graph Tests
- [ ] Add Server
- [ ] Verify all return codes for http requests
- [ ] Add Labels
- [ ] Add Swagger UI
- [ ] Add Query Language (Cypher, Graphql)
- [ ] Compare Search Capabilities (http://javatechniques.com/blog/lucene-in-memory-text-search-example/ and http://mg4j.di.unimi.it/)
- [ ] Add Metrics ( http://metrics.dropwizard.io/3.1.0/getting-started/ )
- [ ] Add Dagger for DI of Metrics and others
    

### Benchmarks

Reads:

    Benchmark                                                   (friendsCount)  (itemCount)  (likesCount)  (personCount)  (userCount)   Mode  Cnt          Score          Error  Units
    GraphReadBenchmarks.measureFixedSingleTraversalAndGetNodes             100        20000           100            100         1000  thrpt   10    2454065.793 ±    58106.632  ops/s
    GraphReadBenchmarks.measureFixedSingleTraversalIds                     100        20000           100            100         1000  thrpt   10   10697736.498 ±   441124.693  ops/s
    GraphReadBenchmarks.measureGetRelationshipTypeCounts                   100        20000           100            100         1000  thrpt   10  366589920.294 ± 12827830.607  ops/s
    GraphReadBenchmarks.measureRandomSingleTraversalIds                    100        20000           100            100         1000  thrpt   10    5669644.824 ±   256630.630  ops/s
    GraphReadBenchmarks.measureSingleTraversalAndGetNodes                  100        20000           100            100         1000  thrpt   10    1529085.651 ±   277345.581  ops/s
    GraphReadBenchmarks.measureTraverse                                    100        20000           100            100         1000  thrpt   10      76809.970 ±     2813.943  ops/s
    GraphReadBenchmarks.measureTraverseAndGetNodes                         100        20000           100            100         1000  thrpt   10      15688.782 ±     2492.089  ops/s        


Writes:

    Benchmark                                                     (friendsCount)  (itemCount)  (likesCount)  (personCount)  (userCount)   Mode  Cnt        Score        Error  Units
    GraphWriteBenchmarks.measureCreateEmptyNode                              100        20000           100            100         1000  thrpt   10  1497050.946 ± 113563.892  ops/s
    GraphWriteBenchmarks.measureCreateEmptyNodes                             100        20000           100            100         1000  thrpt   10    19459.899 ±    551.819  ops/s
    GraphWriteBenchmarks.measureCreateEmptyNodesAndRelationships             100        20000           100            100         1000  thrpt   10       30.166 ±      1.902  ops/s
    GraphWriteBenchmarks.measureCreateNodeWithProperties                     100        20000           100            100         1000  thrpt   10   935777.229 ± 622202.214  ops/s
    GraphWriteBenchmarks.measureCreateNodesWithProperties                    100        20000           100            100         1000  thrpt   10     1054.875 ±    518.271  ops/s

Traversal:

    Benchmark                                                                      (itemCount)  (likesCount)  (personCount)   Mode  Cnt      Score       Error  Units
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)     200            10           1000  thrpt   10  24316.347 ±  3053.061  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)     200            10          10000  thrpt   10   1727.841 ±   206.528  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)     200           100           1000  thrpt   10     26.716 ±     2.758  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)     200           100          10000  thrpt   10      2.483 ±     0.978  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)    2000            10           1000  thrpt   10  87614.324 ± 10836.725  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)    2000            10          10000  thrpt   10  13897.220 ±  1696.789  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)    2000           100           1000  thrpt   10    436.264 ±    41.306  ops/s
    GraphTraversalBenchmarks.measureRecommendationRelationshipPropertiesTraversal (>8)    2000           100          10000  thrpt   10     44.421 ±     6.708  ops/s
    Without filter, it is very slow. 726 ops/s for 200/10/1000, then 63 ops/s, 0.326 and finally 0.029 for 200/100/10000
    
    GraphTraversalBenchmarks.measureRecommendationTraversal                                200            10           1000  thrpt   10   4807.570 ±   202.806  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                                200            10          10000  thrpt   10    464.484 ±    35.171  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                                200           100           1000  thrpt   10      8.246 ±     0.298  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                                200           100          10000  thrpt   10      0.710 ±     0.015  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                               2000            10           1000  thrpt   10  15637.761 ±   668.101  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                               2000            10          10000  thrpt   10   1789.728 ±   116.874  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                               2000           100           1000  thrpt   10     61.024 ±     2.187  ops/s
    GraphTraversalBenchmarks.measureRecommendationTraversal                               2000           100          10000  thrpt   10      5.115 ±     0.190  ops/s

Aggregation:

    Benchmark                                (personCount)   Mode  Cnt       Score       Error  Units
    AggregationBenchmark.measureAggregation            100  thrpt   10  310148.180 ± 17152.734  ops/s
    AggregationBenchmark.measureAggregation        1632803  thrpt   10      17.588 ±     1.178  ops/s

### Server Benchmarks

First Create some data:

    curl -H "Content-Type: application/json" -X POST -d '{"name":"Max"}' http://localhost:8080/db/node/max

Then query it:  

    wrk -t1 -c100 -d30s http://127.0.0.1:8080/db/node/max
    Running 30s test @ http://127.0.0.1:8080/db/node/max
      1 threads and 100 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     1.67ms  341.09us  13.85ms   81.09%
        Req/Sec    57.23k     3.31k   68.50k    68.33%
      1708257 requests in 30.01s, 237.85MB read
      Socket errors: connect 0, read 1, write 0, timeout 0
    Requests/sec:  56931.39
    Transfer/sec:      7.93MB

Shouldn't 4 worker threads get more r/s? 
What am I doing wrong?
    
    wrk -t4 -c100 -d30s http://127.0.0.1:8080/db/node/max
    Running 30s test @ http://127.0.0.1:8080/db/node/max
      4 threads and 100 connections
      Thread Stats   Avg      Stdev     Max   +/- Stdev
        Latency     1.79ms  271.05us  12.05ms   87.64%
        Req/Sec    14.04k     1.04k   16.46k    77.33%
      1681519 requests in 30.10s, 234.13MB read
    Requests/sec:  55859.02
    Transfer/sec:      7.78MB



max.json = {"name":"Tim"}
ab -n 100000 -c 32 -T "application/json"  -u max.json http://127.0.0.1:8080/db/node/max/properties
