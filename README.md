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

  ​

#### Instructions