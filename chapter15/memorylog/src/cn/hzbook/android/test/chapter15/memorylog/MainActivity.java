package cn.hzbook.android.test.chapter15.memorylog;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

public class MainActivity extends Activity {
	private int _sharedCounter = 0;
    private MemoryLog _log = new MemoryLog();

    private void dekker1() {
		_sharedCounter++;
		_log.add("[dekker1] _sharedCounter ++֮��Ϊ ", _sharedCounter);
	}

	private void dekker2() {
		_sharedCounter++;
		_log.add("[dekker2] _sharedCounter ++֮��Ϊ ", _sharedCounter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_log.init();
		final int loopCount = 100000;
		Thread thread1 = new Thread(new Runnable() {
		    public void run() {
				for ( int i = 0; i < loopCount; ++i ) {
					dekker1();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
		    public void run() {
				for ( int i = 0; i < loopCount; ++i ) {
					dekker2();
				}
			}
		});
		thread1.start();
		thread2.start();

		try {
			thread1.join();
		} catch (InterruptedException e1) {
		}
		try {
			thread2.join();
		} catch (InterruptedException e) {
		}

		int expected_sum = 2 * loopCount;
		if ( _sharedCounter != expected_sum ) {
		     Log.e("memorylog",
			     String.format("��Դ��������: ʵ�ʽ�� $1%d ������������� $2%d", _sharedCounter, expected_sum));
		}
	}
}
