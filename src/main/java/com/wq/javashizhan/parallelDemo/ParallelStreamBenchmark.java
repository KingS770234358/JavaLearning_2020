package com.wq.javashizhan.parallelDemo;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G","-Xms2G"})
@State(Scope.Benchmark)
/*
Benchmark                                      Mode  Cnt  Score   Error  Units
parallelDemo.ParallelStreamBenchmark.rangeSum  avgt   40  1.238 ± 0.007  ms/op
* */
public class ParallelStreamBenchmark {
    private static final long N = 10_000_000L;
    @Benchmark
    public long rangeSum(){
        return LongStream.rangeClosed(1,N)
                .parallel()
                .reduce(0L, Long::sum);
    }
    @TearDown(Level.Invocation)
    public void tearDown(){
        System.gc();
    }
}
