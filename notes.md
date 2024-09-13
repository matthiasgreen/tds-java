# TD1 Notes

## Accessing the current thread

- Log function prints name of the current thread and the object passed to it.
- The thread in which the main function is running is called main
- Can print out thread.getPriority() to see that main thread has priority 5 (Thread.NORM_PRIORITY)


## Creating Java Threads

### Method 1: Extending the Thread class

- Name of Greeter thread: "Thread-0"
- If you execute the run() method directly, it will run in the main thread instead of creating a new thread.

### ...

### Scheduling tasks

- Adds many guarantees:
  - Precision and accuracy
  - Both delayed and fixed rate execution
  - Better control over task management
  - Better Exception handling
- Yes
- No



# TD2 Notes

## Questions

- Reception
  - Lines `socket.receive(inPacket); String received = new String(inPacket.getData(), 0, inPacket.getLength());` are responsible for receiving messages
  - Server is listening on port 4445
  - Max length of datagramPacket is 256
  - Servers prints message to System.out after "Received: " and terminates if message content is "end"
- Sending
  - Lines `DatagramPacket outPacket = new DatagramPacket(buf, buf.length, senderAddress, senderPort); socket.send(outPacket);` are responsible for sending
  - Buffer is unchanged therefore we echo input
  - Message is sent back to original sender
- Misc
  - InetAddress
  - Program terminates on "end" or on ctrl+c interrupt
  - socket.receive is blocking


# TD3 Notes

## Questions

- Removing Exit on close causes terminal process to continue running after closing window.
- `emptyLabel.setPreferredSize(new Dimension(175, 100));` decides the size of the label
  - Removing it makes the window pack to the size of the text.
  - Making it smaller than the text cuts the text off the window.
- `frame.pack()` decides the size of the window. Removing it makes the window of size 0 0