# synchronized数据类型String的常量池特性

`在JVM虚拟机中有String常量池缓存的功能，将synchronized(String)同步块与String联合起来使用时，注意常量池带来的一些例外。`

## 示例

### 自定义共享变量类

```java
package com.chapter02.thread2_2_10;
public class Service {
    public static void print(String strParam) {
        try {
            synchronized (strParam) {
                while (true) {
                    System.out.println("CURRENT THREAD NAME = " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

### 自定义线程A

```java
package com.chapter02.thread2_2_10;
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.print("AA"); //注意！！！！！
    }
}
```

### 自定义线程B

```java
package com.chapter02.thread2_2_10;
public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.print("AA");//注意！！！！！
    }
}
```

### 启动类

```java
package com.chapter02.thread2_2_10;
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadB.start();
    }
}
```

### 执行结果

```
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
CURRENT THREAD NAME = A
......
```

# 结论

`如上示例，出现这样的原因就是因为String的两个值都是AA，两个线程持有相同的锁，所以造成线程B无法执行。`

`这就是String常量池所带来的问题。`

`因此大部分情况下，同步synchronized代码块都不使用String作为锁对象，而改用其他，如new Object()实例化一个Object对象。`