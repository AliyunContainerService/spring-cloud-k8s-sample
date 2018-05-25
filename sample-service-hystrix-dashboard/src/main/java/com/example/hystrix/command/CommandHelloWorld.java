package com.example.hystrix.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import rx.Observable;
import rx.Observer;

public class CommandHelloWorld extends HystrixCommand<String> {

  private final String name;

  public CommandHelloWorld(String name) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloServiceGroup"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
    this.name = name;
  }

  @Override
  protected String run() throws InterruptedException {
    Thread.sleep(600);

    return "Hello " + name + "!";
  }

  @Override
  protected String getFallback() {
    return String.format("[FallBack]Hello %s!", name);
  }

//  private static void main(String[] args) {
//    String s1 = new CommandHelloWorld("Bob").execute();
//    System.out.println(s1);
//    Future<String> s2 = new CommandHelloWorld("Bob").queue();
//    try {
//      System.out.println(s2.get());
//    } catch (InterruptedException | ExecutionException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    Observable<String> s3 = new CommandHelloWorld("Bob").observe();
//    s3.subscribe(new Observer<String>() {
//
//      @Override
//      public void onCompleted() {
//        System.out.println("onCompleted");
//      }
//
//      @Override
//      public void onError(Throwable e) {
//        e.printStackTrace();
//      }
//
//      @Override
//      public void onNext(String v) {
//        System.out.println("onNext: " + v);
//      }
//
//    });
//  }
}
