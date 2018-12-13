# synchronized验证3个理论

## 1.论证1

### 1.1理论

当多个线程同时执行synchronized(非this)同步代码块时呈同步效果。

### 1.2落地

#### 1.2.1自定义锁对象类

```java
package com.chapter02.thread2_2_8_1;
public class MyObject {
}
```

#### 1.2.2自定义共享变量类

```java
package com.chapter02.thread2_2_8_1;

public class Service {
    public void testMethod1(MyObject myObject) {
        synchronized (myObject) {
            try {
                System.out.println("testMethod1 , GET LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("testMethod1 , RELEASE LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
```

#### 1.2.3自定义线程1

```java
package com.chapter02.thread2_2_8_1;


public class MyThread1 extends Thread {
    private Service service;
    private MyObject myObject;

    public MyThread1(Service service, MyObject myObject) {
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        service.testMethod1(myObject);
    }
}
```

#### 1.2.4自定义线程2

```java
package com.chapter02.thread2_2_8_1;

public class MyThread2 extends Thread {
    private Service service;
    private MyObject myObject;

    public MyThread2(Service service, MyObject myObject) {
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        service.testMethod1(myObject);
    }
}
```

#### 1.2.5启动类

```java
package com.chapter02.thread2_2_8_1;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MyObject myObject = new MyObject();

        MyThread1 myThread1 = new MyThread1(service, myObject);
        myThread1.setName("A");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(service, myObject);
        myThread2.setName("B");
        myThread2.start();
    }
}
```

#### 1.2.6执行结果

```
testMethod1 , GET LOCK TIME = 1541647565104 , RUN THREAD NAME = A
testMethod1 , RELEASE LOCK TIME = 1541647567105 , RUN THREAD NAME = A
testMethod1 , GET LOCK TIME = 1541647567105 , RUN THREAD NAME = B
testMethod1 , RELEASE LOCK TIME = 1541647569106 , RUN THREAD NAME = B
```

`如上，同步输出。`

### 1.3补充

如果使用不同的对象监控器会出现什么效果呢？

#### 1.3.1修改启动类

```java
package com.chapter02.thread2_2_8_1;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MyObject myObject1 = new MyObject();

        MyThread1 myThread1 = new MyThread1(service, myObject1);
        myThread1.setName("A");
        myThread1.start();

        MyObject myObject2 = new MyObject();
        MyThread2 myThread2 = new MyThread2(service, myObject2);
        myThread2.setName("B");
        myThread2.start();
    }
}
```

#### 1.3.2执行结果

```
testMethod1 , GET LOCK TIME = 1541648070884 , RUN THREAD NAME = A
testMethod1 , GET LOCK TIME = 1541648070885 , RUN THREAD NAME = B
testMethod1 , RELEASE LOCK TIME = 1541648072885 , RUN THREAD NAME = A
testMethod1 , RELEASE LOCK TIME = 1541648072885 , RUN THREAD NAME = B
```

`不同步输出！！！`

## 2.论证2

### 2.1理论

当其他线程执行X对象中的synchronized同步方法时呈现同步效果

### 2.2落地

#### 2.2.1自定义锁对象类

```java
package com.chapter02.thread2_2_8_2;
public class MyObject {
    synchronized public void speedPrintString() {
        System.out.println("speedPrintString , GET LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
        System.out.println("***********************************");
        System.out.println("speedPrintString , RELEASE LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
    }
}

```

#### 2.2.2自定义共享变量类

```java
package com.chapter02.thread2_2_8_2;
public class Service {
    public void testMethod1(MyObject myObject) {
        synchronized (myObject) {
            try {
                System.out.println("testMethod1 , GET LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("testMethod1 , RELEASE LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

```

#### 2.2.3自定义线程1

```java
package com.chapter02.thread2_2_8_2;
public class MyThread1 extends Thread {
    private Service service;
    private MyObject myObject;

    public MyThread1(Service service, MyObject myObject) {
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        service.testMethod1(myObject);
    }
}

```

#### 2.2.4自定义线程2

```java
package com.chapter02.thread2_2_8_2;
public class MyThread2 extends Thread {
    private MyObject myObject;

    public MyThread2(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        myObject.speedPrintString();
    }
}

```

#### 2.2.5启动类

```java
package com.chapter02.thread2_2_8_2;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MyObject myObject = new MyObject();

        MyThread1 myThread1 = new MyThread1(service, myObject);
        myThread1.setName("A");
        myThread1.start();

        //注：与线程1握的同一把锁，执行不同的方法
        MyThread2 myThread2 = new MyThread2(myObject);
        myThread2.setName("B");
        myThread2.start();
    }
}
```

#### 2.2.6执行结果

```
testMethod1 , GET LOCK TIME = 1541648591356 , RUN THREAD NAME = A
testMethod1 , RELEASE LOCK TIME = 1541648596356 , RUN THREAD NAME = A
speedPrintString , GET LOCK TIME = 1541648596357 , RUN THREAD NAME = B
***********************************
speedPrintString , RELEASE LOCK TIME = 1541648596357 , RUN THREAD NAME = B
```

`如上，同步输出。`

## 3.论证3

### 3.1理论

当其他线程执行X对象方法里面的synchronized(this)代码块时也呈现同步效果。

### 3.2落地

#### 3.2.1在论证2的基础上修改`自定义锁对象类MyObject`

```java
package com.chapter02.thread2_2_8_2;
public class MyObject {
    public void speedPrintString() {
        synchronized(this){
            System.out.println("speedPrintString , GET LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
        System.out.println("***********************************");
        System.out.println("speedPrintString , RELEASE LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
        }
    }
}
```

#### 3.2.2执行结果

```
testMethod1 , GET LOCK TIME = 1541649000658 , RUN THREAD NAME = A
testMethod1 , RELEASE LOCK TIME = 1541649005658 , RUN THREAD NAME = A
speedPrintString , GET LOCK TIME = 1541649005659 , RUN THREAD NAME = B
***********************************
speedPrintString , RELEASE LOCK TIME = 1541649005659 , RUN THREAD NAME = B
```

