package com.wq.javashizhan.parallelDemo;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
/*
*
Benchmark                                        Mode  Cnt  Score   Error  Units
parallelDemo.SequentialStreamBenchmark.rangeSum  avgt   40  4.674 ± 0.050  ms/op
* */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G","-Xms2G"})
@State(Scope.Benchmark)
public class SequentialStreamBenchmark {
    private static final long N = 10_000_000L;
    @Benchmark
    public long rangeSum(){
        return LongStream.rangeClosed(1,N)
                .reduce(0L, Long::sum);
    }
    @TearDown(Level.Invocation)
    public void tearDown(){
        System.gc();
    }
}
