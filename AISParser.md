# Introduction #

The usage of the AIS Parser is very simple. Just implement an AISObserver and register the object at the parser. The observers would be notified on every complete AIS message (some AIS messages consist of more than one part).

The following code shows a minimal setup which covers all parts of the public API.

## Receiver ##
```
public final class Receiver implements AISObserver, Runnable {

   public static void main(String[] args) {
      new Thread(new Receiver()).start();
   }

   @Override
   public void run() {
      ServerSocket serverSocket;
      try {
         serverSocket = new ServerSocket(8000);
      } catch(final IOException e) {
        return;
      }

      while(true) {
         try {
            // Create a new parser for every connected antenna
            // otherwise multi part messages could get currupted
            final AISParser parser = new AISParser();
            parser.register(this);
            Handler handler = new Handler(parser, serverSocket.accept());
            new Thread(handler).start();
         } catch(final IOException e) {
         }
      }
   }

   @Override
   public void update(final PositionReportDTO positionReport) {
      // do something with the position report
   }

   @Override
   public void update(final VesselDataDTO vesselData) {
      // do something with the vessel data
   }
   
}
```

## Antenna Handler ##

```
public class AntennaHandler implements Runnable {

   private AISParser parser;
   private Scanner scanner;

   public AntennaHandler(final AISParser parser, final Socket socket) {
      this.parser = parser;
      this.scanner = new Scanner(socket.getInputStream());
   }

   @Override
   public void run() {
      while(scanner.hasNext()) {
         try {
            parser.process(scanner.nextLine);
         } catch(InvalidChecksumException e) {
         } catch(InvalidPacketException e) {
         }
      }
   }

}
```