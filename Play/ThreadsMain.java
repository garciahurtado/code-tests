package Play;

public class ThreadsMain {
	public static void main(String args[]){
		System.out.println("Program Started");
		
		DownloadRunner download1 = new DownloadRunner();
		DownloadRunner download2 = new DownloadRunner();
		DownloadRunner download3 = new DownloadRunner();
		
		Thread thread1 = new Thread(download1);
		Thread thread2 = new Thread(download2);
		Thread thread3 = new Thread(download3);
		
		download1.setFilename("file 1");
		download2.setFilename("file 2");
		download3.setFilename("file 3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println("Program Finished");
	}
}
