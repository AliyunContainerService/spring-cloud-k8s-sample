package com.example.hystrix.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hystrix.command.CommandHelloWorld;

import rx.Observable;
import rx.Observer;

@RestController
public class HystrixController {
  private static final Log log = LogFactory.getLog(HystrixController.class);

  @RequestMapping("/sample")
  public String index() throws InterruptedException, ExecutionException {
    StringBuffer sb = new StringBuffer();
    log.info("Hi,Greetings from Spring Boot!");
    String s1 = new CommandHelloWorld("Bob").execute();
    System.out.println(s1);
    sb.append(s1);
    
    Future<String> s2 = new CommandHelloWorld("Bob").queue();
    try {
      System.out.println(s2.get());
      sb.append(s2.get());
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Observable<String> s3 = new CommandHelloWorld("Bob").observe();
    s3.subscribe(new Observer<String>() {

      @Override
      public void onCompleted() {
        System.out.println("onCompleted: ");
      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override
      public void onNext(String v) {
        System.out.println("onNext: " + v);
      }

    });
    return "Hi,Greetings from Spring Boot!" + s1 + "," + s2.get() ;
  }

}
