package server;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLockController {
    private static final ReadAndWriteLockController INSTANCE = new ReadAndWriteLockController();
    private final ReadWriteLock lock;
    private Lock readLock ;
    private Lock writeLock ;

    public ReadAndWriteLockController() {
        this.lock = new ReentrantReadWriteLock();
        this.readLock= lock.readLock();
        this.writeLock = lock.writeLock();
    }

    public ReadWriteLock getLock() {
        return lock;
    }

    public Lock getReadLock() {
        return readLock;
    }

    public Lock getWriteLock() {
        return writeLock;
    }

    public void setReadLock(Lock readLock) {
        this.readLock = readLock;
    }

    public void setWriteLock(Lock writeLock) {
        this.writeLock = writeLock;
    }

    public static ReadAndWriteLockController getInstance(){return INSTANCE;}

}
