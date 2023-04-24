class Account {

    private volatile long balance = 0;

    public synchronized boolean withdraw(long amount) {
        if(this.balance-amount>=0){
            this.balance = this.balance-amount;
            return true;
        }
        return false;

    }

    public synchronized void deposit(long amount) {
        this.balance=this.balance+amount;
    }

    public long getBalance() {
        return this.balance;
    }
}