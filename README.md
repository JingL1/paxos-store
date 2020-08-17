# paxos-store
Practice on implementing paxos to realize fault-tolerant consensus amongst replicated servers.

I. Overview
--------------------
This program builds upon `RMI` for client-server connections as well as Paxos operations.
In the `Paxos` implementation, there are three entities/classes:

- Proposer: Receive requests from client and try to convince acceptors to accept the proposed values
- Acceptor: Accept certain proposed values from proposers and let proposers know if something else was accepted. A response from an acceptor represents a vote for a particular proposal.
- Learner: Announce the outcome and commit the final result.

During each request process, two phase are involved:

- Basically, the proposer interacts with the acceptors twice. 
- In the Phase 1, a proposer asks all the working acceptors whether anyone already received a proposal. If the answer is no, propose a value.
- In the Phase 2, if a majority of acceptors agree to this value then that is `consensus`.


II. Implementation
--------------------

Paxos Implementation: 

- `Proposer`, `Acceptor` and `Learner` are implemented for paxos operations. 
- 5 Server Class `Server1 ~ Server5` are 5 servers running and processing requests as the kv store. 
- User could select which server is the proposer via command, e.g., `java -classpath paxos_store.jar Client localhost Server3`. In this 
case, Server3 is the proposer.
- All paxos-based processes are displayed in the server log started with `>>>`

Acceptor Fault Tolerance:

- In the `Proposer Class`, fault testing functions has been implemented.
- For every 20 times process, a server would be shunt down (`thread.sleep()`) for 200 ms. 
Every server thread shares the same chance to sleep.
- `SocketetTimeOut` are used to continued the operation.
- Please check logs like `ERR-FALT-TOLERANT-TEST: Server [1] has shut down for 200 milliseconds.` in each server's log.

Others:

- Config file set a few basic constants for the program, such as server ports (8080~8084)


III. Testing
--------------------


- Run 5 servers in 5 different terminal:

```
Terminal 1: java -classpath paxos_store.jar Server1
Terminal 2: java -classpath paxos_store.jar Server2
Terminal 3: java -classpath paxos_store.jar Server3
Terminal 4: java -classpath paxos_store.jar Server4
Terminal 5: java -classpath paxos_store.jar Server5
```

- Run Client in the 6th terminal, you could randomly select a server from `Server1 to Server5` as the second argument

```
Terminal 6: java -classpath paxos_store.jar Client Server3
```

- Waiting for testing results, and then type in commands manually for further testing.

