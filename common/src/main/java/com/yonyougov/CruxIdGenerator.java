package com.yonyougov;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author yindwe@yonyou.com
 * @Date 2019/12/31
 */
public class CruxIdGenerator implements IdentifierGenerator {

    private static AtomicInteger seq = new AtomicInteger(0);

    private static long workId = new Random(System.nanoTime()).nextInt(2048);

    public long generate() {
        return System.currentTimeMillis() << 21 | workId << 10 | seq.getAndUpdate(operand -> ++operand % 1024);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return generate();
    }

    public static void main(String[] args) throws Exception {
        CruxIdGenerator cruxIdGenerator = new CruxIdGenerator();
        Function<Future<Long>, Long> futureGet = f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(1);
        IntStream.range(0, 1)
                .parallel()
                .mapToObj(i -> es.submit(() -> cruxIdGenerator.generate()))
                .map(futureGet)
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .parallel()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getValue() + " : " + e.getKey())
                .forEach(System.out::println);
        es.shutdown();
    }
}
