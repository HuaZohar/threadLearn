# 静态同步synchronized方法与synchronized(class)代码块

## 理论

- `synchronized还可以应用在static静态方法上，如果这样写，那是对当前的*.java文件对应的Class类进行持锁。`
- `synchronized关键字加到static静态方法上是给Class类上锁，而synchronized关键字加到非static静态静态方法上是给对象上锁。`
- `一个是对象锁，另外一个是Class锁，而Class锁可以对类的所有对象实例起作用。`
- `同步synchronized(class)代码块的作用其实和synchronized static方法的作用是一样的。`

## 落地

#### 自定义共享变量类

```java
package com.chapter02.thread2_2_9_1;

public class Service {
/*    synchronized public static void printA() {
        try {
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printA");
            Thread.sleep(3000);
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printA");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printB");
        System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printB");
    }*/

    public static void printA() {
        synchronized (Service.class) {
            try {
                System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printA");
                Thread.sleep(3000);
                System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printA");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void printB() {
        synchronized (Service.class) {
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printB");
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printB");
        }
    }
}
```

#### 自定义线程1

```java
package com.chapter02.thread2_2_9_1;

public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printA();
    }
}
```

#### 自定义线程2

```java
package com.chapter02.thread2_2_9_1;

public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printB();
    }
}
```

#### 启动类

```java
package com.chapter02.thread2_2_9_1;

public class Run {
    public static void main(String[] args) {
        Service service1 = new Service();
        Service service2 = new Service();

        ThreadA threadA = new ThreadA(service1);
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB(service2);
        threadB.setName("B");
        threadB.start();
    }
}
```

#### 执行结果

```
THREAD NAME = A, AT 1541658444300 ENTER printA
THREAD NAME = A, AT 1541658447301 OUT printA
THREAD NAME = B, AT 1541658447302 ENTER printB
THREAD NAME = B, AT 1541658447302 OUT printB
```

