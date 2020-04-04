package com.yonyougov.gateway;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        Flux<String> flux1 = Flux.just("one", "two", "three");
        Flux<String> flux2 = Flux.fromStream(Stream.of("one", "two", "three"));
        List<String> iterable = Arrays.asList("one", "two", "three");
        Flux<String> flux3 = Flux.fromIterable(iterable);
        Flux<Integer> flux4 = Flux.range(1, 3);
        System.out.println(flux1);
        System.out.println(flux2);
        System.out.println(iterable);
        System.out.println(flux3);
        System.out.println(flux4);
    }

    public static void main(String[] args) {
//        Flux<String> flux1 = Flux.just("one", "two", "three");
//        Flux<String> flux2 = Flux.fromStream(Stream.of("one", "two", "three"));
//        List<String> iterable = Arrays.asList("one", "two", "three");
//        Flux<String> flux3 = Flux.fromIterable(iterable);
//        Flux<Integer> flux4 = Flux.range(1, 3);
//        System.out.println(flux1);
//        System.out.println(flux2);
//        System.out.println(iterable);
//        System.out.println(flux3);
//        System.out.println(flux4);
        /*Flux.create((t) -> {
            t.next("create");
            t.next("create1");
            t.complete();
        }).subscribe(System.out::println);
        Flux.just("just", "just1", "just2").subscribe(System.out::println);
        Flux.fromArray(new String[] { "arr", "arr", "arr", "arr" })
                .subscribe(System.out::println);*/
        String str = "{pattern=/testhh/**}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
    }

}
