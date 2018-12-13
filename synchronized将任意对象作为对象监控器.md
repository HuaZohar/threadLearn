# 将任意对象作为对象监控器

多线程调用同一个对象中的不通名称的synchronized同步方法或synchronized(this)同步代码块时，调用的效果就是按顺序执行，也就是同步的，阻塞的。

## (1.1)理论：synchronized(非this)...

锁非this对象具有一定的有优点：如果一个类中有很多个synchronized方法，这时虽然能实现同步，但会受到阻塞，所以影响运行效率；但如果使用同步代码块锁非this对象，则synchronized(非this)代码块中的程序与同步方法是异步的，不与其他锁this同步方法争抢this锁，则可大大提高运行效率。

## (1.2)落地论证

### (1.2.1)自定义共享变量类

```java
package com.chapter02.thread2_2_7_2;

public class Service {
    private String anyString = new String();
    public void a() {
        try {
            //synchronized(非this)锁
            synchronized (anyString) {
                System.out.println("A BEGIN!");
                Thread.sleep(3000);
                System.out.println("A END");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //synchronized(this)锁
    synchronized public void b() {
        System.out.println("B BEGIN!");
        System.out.println("B END!");
    }
}
```

### (1.2.2)自定义线程1

```java
package com.chapter02.thread2_2_7_2;
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.a();
    }
}
```

### (1.2.3)自定义线程2

```java
package com.chapter02.thread2_2_7_2;

public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.b();
    }
}
```

### (1.2.4)启动类

```java
package com.chapter02.thread2_2_7_2;

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

### (1.2.5)执行结果

```
A BEGIN!
B BEGIN!
B END!
A END
```

### (1.2.6)结论

`synchronized(非this)代码块中的程序与同步方法是异步的，不与其他锁this同步方法争抢this锁`

## (2.1)synchronized(非this)出现脏读情况

### （2.1.1）理论

`一个线程握一把自己的锁，就会出现脏读情况。如果多个线程使用同一把锁，就会避免脏读。`

### （2.1.2）落地论证

#### 自定义共享变量类

```java
package com.chapter02.thread2_2_7_3;

import java.util.ArrayList;
import java.util.List;

public class MyOneList {
    private List list = new ArrayList();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized public int getSize() {
        return list.size();
    }
}
```

```java
package com.chapter02.thread2_2_7_3;

public class MyService {
    public MyOneList addServiceMethod(MyOneList list, String data) {
        try {
            if (list.getSize() < 1) {
                Thread.sleep(2000);
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
```

#### 自定义线程1

```java
package com.chapter02.thread2_2_7_3;

public class MyThread1 extends Thread {
    private MyOneList myOneList;

    public MyThread1(MyOneList myOneList) {
        this.myOneList = myOneList;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(myOneList, "A");
    }
}
```

#### 自定义线程2

```java
package com.chapter02.thread2_2_7_3;

public class MyThread2 extends Thread {
    private MyOneList myOneList;

    public MyThread2(MyOneList myOneList) {
        this.myOneList = myOneList;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(myOneList, "B");
    }
}
```

#### 启动类

```java
package com.chapter02.thread2_2_7_3;

public class Run {
    public static void main(String[] args) {
        MyOneList list = new MyOneList();

        MyThread1 myThread1 = new MyThread1(list);
        myThread1.setName("A");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(list);
        myThread2.setName("B");
        myThread2.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("LIST SIZE : " + list.getSize());
    }
}
```

#### 执行结果

```
//出现脏读，正确因该为1
LIST SIZE : 2
```

### （2.1.3）避免脏读

#### 修改MyService类

如下：

```java
package com.chapter02.thread2_2_7_3;

public class MyService {
    public MyOneList addServiceMethod(MyOneList list, String data) {
        try {
            synchronized (list){
                if (list.getSize() < 1) {
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
```

#### 执行结果

```
//结果正常
LIST SIZE : 1
```

