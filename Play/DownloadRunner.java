package Play;

public class DownloadRunner implements Runnable {

	private String filename;
	private static String internalFilename;

	@Override
	public void run() {
		// Synchronizing on the actual class object (rather than "this", the instance)
		// will allow us to also protect access to the static field "filename" from multithreaded
		// access: http://stackoverflow.com/questions/2120248/how-to-synchronize-a-static-variable-among-threads-running-different-instances-o
		
		// synchronized (this) { // this fails, since it causes a race condition
		synchronized (DownloadRunner.class) {  
			internalFilename = getFilename();
			
			System.out.printf("About to download file %s\n", internalFilename);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
			System.out.printf("File %s downloaded\n", internalFilename);
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
