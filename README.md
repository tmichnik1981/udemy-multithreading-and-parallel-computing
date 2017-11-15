# Multithreading and Parallel Computing in Java

#### Links

#### Notes and Commands

- Process

  - self-contained execution env
  - private set of basic run-time resources
  - own memory space
  - processec can communicate IPC
  - java: ProcessBuilder

- Thead

  - provide an execution environment
  - lightweight processes
  - exists within a process
  - every process has at least one thread
  - threads share processes resources

- List of processes (on Windows): `tasklist`

- Lifecycle

  - new - after instantiating
  - runnable - after starting
  - running
  - waiting - waiting for another thread to finish, sleeping
  - dead - finished

- Join - waiting for the thread to finish

  ```java
  Runner1 thread1 = new Runner1();
  Runner2 thread2 = new Runner2();
  		
  thread1.start();
  thread2.start();

  try {
  	//we will wait until threads 1 and 2 are finished
  	thread1.join();
  	thread2.join();
  } catch (InterruptedException e) {
  	e.printStackTrace();
  }
  ```

- Volatile

  - every CPU has its own cache which is much faster than RAM
  - **volatile** variables are stored and read from RAM
  - use volatile carefully, only when different threads need to have access and see current state of some variable  

- Synchronized method

  ```java
  protected static synchronized void increment() {
  	//shared resource	
    	++counter;
  }

  ```

- Synchronized block

  ```java
  private static Object lock1 = new Object();
  private static Object lock2 = new Object();

  /**It is better to use "a lock object" and synchronized blocks
  as two threads will be able to work on 2 different methods independently */
  public synchronized static void add(){
  	synchronized (lock1) {
  		count1++;
  	}
  }
  public synchronized static void addAgain(){
  	synchronized (lock2) {
  		count2++;
  }
  ```


- Wait and notify

  - wait() - stops thread and release previously acquired lock
  - notify() - wakes up another thread 
  - wait() and notify() should be used in synchronized blocks/methods  

- Locks - equivalent of `Synchronized`

  ```java
  /**
  fairness: true - the longest waiting thread will get the lock
  	      false - there is no access order
  */
  new ReentrantLock(boolean fairnessParameter);
  ```

- Semaphores

  - counting semaphores - allows an arbitrary resource count
  - binary semaphores - restricted to values 1 or 0
  - semaphores tracks only how many resources are free
  - may serve as a useful trigger for a number of different actions
  - possible producent-consumer solution

- Mutexes

  - like a binary semaphore
  - owner concept - only the process that locked the mutex is supposed to unlock it
  - possibility to promote the priority of the owner whenever a higher-priority task starts waiting on the mutex
  - may provide deletion safety

  ​

  ​

  ​

#### Instructions