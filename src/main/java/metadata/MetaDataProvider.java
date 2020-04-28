package metadata;

public class MetaDataProvider implements Runnable{

    @Override
    public void run() {
        while(true){
            try{
                System.out.println("Files sent: "+MetaDataCollector.getInstance().getFilesSent());
                Thread.sleep(1000);
            }catch (InterruptedException interruptedException){
                interruptedException.getMessage();
            }
        }
    }
}
