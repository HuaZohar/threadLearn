# synchronized方法与语句块

## 弊端凸显

### 定义共享变量实例类

```java
package com.chapter02.thread3;

public class Task {
    private String getData1;
    private String getData2;

    synchronized public void doLongTimeTask() {
        try {
            System.out.println("BEGIN TASK!");

            Thread.sleep(3000);
            getData1 = "COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = " + Thread.currentThread().getName();
            getData2 = "COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = " + Thread.currentThread().getName();

            System.out.println(getData1);
            System.out.println(getData2);

            System.out.println("END TASK!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

```

### 自定义线程1

```java
package com.chapter02.thread3;

public class MyThread1 extends Thread {
    private Task task;

    public MyThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}
```

### 自定义线程2

```java
package com.chapter02.thread3;

public class MyThread2 extends Thread {
    private Task task;

    public MyThread2(Task task) {
        this.task = task;
    }
    @Override
    public void run() {
        super.run();
        CommonUtils.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}
```

### CommonUtils类定义

```java
package com.chapter02.thread3;
public class CommonUtils {
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}
```

启动类

```java
package com.chapter02.thread3;

public class Run {
    public static void main(String[] args) {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime2 < CommonUtils.beginTime1) {
            beginTime = CommonUtils.beginTime2;
        }
        long endTime = CommonUtils.endTime1;
        if (CommonUtils.endTime2 > CommonUtils.endTime1) {
            endTime = CommonUtils.endTime2;
        }
        System.out.println("COST TIME : " + (endTime - beginTime) / 1000 + " S ！！");
    }
}
```

### 执行结果

```
BEGIN TASK!
COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = Thread-0
COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = Thread-0
END TASK!
BEGIN TASK!
COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = Thread-1
COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = Thread-1
END TASK!
COST TIME : 6 S ！！
```

### 结论

`在使用synchronized关键字来声明方法public synchronized void doLongTimeTask()方法时，从运行的时间上来看，弊端很明显，可以使用synchronized同步块。`

## 解决弊端

### 修改

修改共享变量实例类，把synchronized同步方法改为同步块。。

```java
package com.chapter02.thread3;
public class Task {
    private String getData1;
    private String getData2;

    public void doLongTimeTask() {
        try {
            System.out.println("BEGIN TASK!");

            Thread.sleep(3000);
            String Data1 = "COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = " + Thread.currentThread().getName();
            String Data2 = "COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = " + Thread.currentThread().getName();

            synchronized (this) {
                getData1 = Data1;
                getData2 = Data2;
            }

            System.out.println(getData1);
            System.out.println(getData2);

            System.out.println("END TASK!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

### 执行结果

```
BEGIN TASK!
BEGIN TASK!
COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = Thread-1
COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = Thread-0
END TASK!
COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = Thread-0
COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = Thread-0
END TASK!
COST TIME : 3 S ！！
```

### 结论

时间减短。。