// �������
//    javac DeadLockDemo.java
//

public class DeadLockDemo {
    public static void main(String[] args) {
		final Object lock1 = new Object();
		final Object lock2 = new Object();
 
		Thread thread1 = new Thread(new Runnable() {
			@Override public void run() {
				synchronized (lock1) {
					System.out.println("�߳�1��ȡlock1");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {}
					synchronized (lock2) {
						System.out.println("�߳�1��ȡlock2");
					}
				}
			}
 
		});
		thread1.start();
 
		Thread thread2 = new Thread(new Runnable() {
			@Override public void run() {
				synchronized (lock2) {
					System.out.println("�߳�2��ȡlock2");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {}
					synchronized (lock1) {
						System.out.println("�߳�2��ȡlock1");
					}
				}
			}
		});
		thread2.start();
 
		try {
		    thread1.join();
		    thread2.join();
		} catch (InterruptedException e) {}

		System.out.println("����ִ����ϣ������ϲ��ᷢ����");
    }
}
