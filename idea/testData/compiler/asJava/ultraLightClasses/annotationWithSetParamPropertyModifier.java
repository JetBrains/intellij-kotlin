@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public abstract @interface Anno /* Anno*/ {
}

public final class TestClass /* TestClass*/ {
  private int hello;

  @null()
  public  TestClass(int);

  public final int getHello();

  public final void setHello(@Anno() int);

}