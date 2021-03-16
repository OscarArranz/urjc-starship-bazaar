package com.urjcstarshipbazaar;

public class Application {

  private static Application application;

  private Application() {}

  public static Application get() {
    if (application == null) application = new Application();
    return application;
  }

  public void setup() {
//    TODO setup code
  }

  public void run() {
//    TODO run code
  }

}
