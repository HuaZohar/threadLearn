# synchronized一半同步一半异步

## 论证

不在synchronized块中就是异步执行，在synchronized块中就是同步执行。

## 示例

### 自定义共享类

```java
package com.chapter02.thread4;

public class Task {
    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("NO SYNCHRONIZED THREAD NAME = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
       System.out.println("************************************************************************");
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("SYNCHRONIZED THREAD NAME = " + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }
}
```

### 自定义线程1

```java
package com.chapter02.thread4;


public class MyThread1 extends Thread {
    private Task task;

    public MyThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}
```

### 自定义线程2

```java
package com.chapter02.thread4;


public class MyThread2 extends Thread {
    private Task task;

    public MyThread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.doLongTimeTask();
    }
}
```

### 启动类

```java
package com.chapter02.thread4;
public class Run {
    public static void main(String[] args) {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();
    }
}
```

### 执行结果

```
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 1
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 2
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 3
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 4
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 5
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 6
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 7
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 8
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 9
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 10
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 11
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 12
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 13
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 14
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 15
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 16
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 17
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 18
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 19
NO SYNCHRONIZED THREAD NAME = Thread-0 i = 20
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 1
************************************************************************************************
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 2
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 3
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 4
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 5
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 6
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 7
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 8
SYNCHRONIZED THREAD NAME = Thread-0 i = 1
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 9
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 10
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 11
SYNCHRONIZED THREAD NAME = Thread-0 i = 2
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 12
SYNCHRONIZED THREAD NAME = Thread-0 i = 3
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 13
SYNCHRONIZED THREAD NAME = Thread-0 i = 4
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 14
SYNCHRONIZED THREAD NAME = Thread-0 i = 5
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 15
SYNCHRONIZED THREAD NAME = Thread-0 i = 6
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 16
SYNCHRONIZED THREAD NAME = Thread-0 i = 7
SYNCHRONIZED THREAD NAME = Thread-0 i = 8
SYNCHRONIZED THREAD NAME = Thread-0 i = 9
SYNCHRONIZED THREAD NAME = Thread-0 i = 10
SYNCHRONIZED THREAD NAME = Thread-0 i = 11
SYNCHRONIZED THREAD NAME = Thread-0 i = 12
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 17
SYNCHRONIZED THREAD NAME = Thread-0 i = 13
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 18
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 19
NO SYNCHRONIZED THREAD NAME = Thread-1 i = 20
************************************************************************************************
SYNCHRONIZED THREAD NAME = Thread-0 i = 14
SYNCHRONIZED THREAD NAME = Thread-0 i = 15
SYNCHRONIZED THREAD NAME = Thread-0 i = 16
SYNCHRONIZED THREAD NAME = Thread-0 i = 17
SYNCHRONIZED THREAD NAME = Thread-0 i = 18
SYNCHRONIZED THREAD NAME = Thread-0 i = 19
SYNCHRONIZED THREAD NAME = Thread-0 i = 20
SYNCHRONIZED THREAD NAME = Thread-1 i = 1
SYNCHRONIZED THREAD NAME = Thread-1 i = 2
SYNCHRONIZED THREAD NAME = Thread-1 i = 3
SYNCHRONIZED THREAD NAME = Thread-1 i = 4
SYNCHRONIZED THREAD NAME = Thread-1 i = 5
SYNCHRONIZED THREAD NAME = Thread-1 i = 6
SYNCHRONIZED THREAD NAME = Thread-1 i = 7
SYNCHRONIZED THREAD NAME = Thread-1 i = 8
SYNCHRONIZED THREAD NAME = Thread-1 i = 9
SYNCHRONIZED THREAD NAME = Thread-1 i = 10
SYNCHRONIZED THREAD NAME = Thread-1 i = 11
SYNCHRONIZED THREAD NAME = Thread-1 i = 12
SYNCHRONIZED THREAD NAME = Thread-1 i = 13
SYNCHRONIZED THREAD NAME = Thread-1 i = 14
SYNCHRONIZED THREAD NAME = Thread-1 i = 15
SYNCHRONIZED THREAD NAME = Thread-1 i = 16
SYNCHRONIZED THREAD NAME = Thread-1 i = 17
SYNCHRONIZED THREAD NAME = Thread-1 i = 18
SYNCHRONIZED THREAD NAME = Thread-1 i = 19
SYNCHRONIZED THREAD NAME = Thread-1 i = 20
```

## 结论

如下所示，在进入synchronized代码块后则排队执行。