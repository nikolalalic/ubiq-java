package com.ubiqsecurity;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.gson.Gson; 
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
 
class FPEProcessor extends AbstractScheduledService
{
    private boolean verbose= true;
    private int secondsToProcess= 1;
    UbiqFPEEncryptDecrypt fpeEncryptDecrypt;
    UbiqWebServices ubiqWebServices;
    FPETransactions bill;


    public FPEProcessor (UbiqFPEEncryptDecrypt fpeEncryptDecrypt, UbiqWebServices ubiqWebServices, FPETransactions bill, int secondsToProcess) {
        this.fpeEncryptDecrypt= fpeEncryptDecrypt;
        this.ubiqWebServices= ubiqWebServices;
        this.bill= bill;
        this.secondsToProcess= secondsToProcess;
    }
    
    @Override
    protected void startUp() {
        if (verbose) System.out.println("--$$$$$$$$$$$$$$$$$ Job started at: " + new java.util.Date());
    }
 
    @Override
    protected void runOneIteration() throws Exception {
        // perform periodic list processing here
        if (verbose) System.out.println("--$$$$$$$$$$$$$$$$$ Running: " + new java.util.Date());
        
        if (verbose) System.out.println("--$$$$$$$$$$$$$$$$$ BEFORE processCurrentBillsAsync");
        bill.processCurrentBillsAsync(ubiqWebServices, bill);
        if (verbose) System.out.println("--$$$$$$$$$$$$$$$$$ AFTER processCurrentBillsAsync");
        
    }
 
    @Override
    protected Scheduler scheduler() {
        if (verbose) System.out.println("--$$$$$$$$$$$$$$$$$ Running newFixedRateSchedule: " + new java.util.Date());
    
        // execute every period
        return Scheduler.newFixedRateSchedule(0, secondsToProcess, TimeUnit.SECONDS);
    }
 
    @Override
    protected void shutDown() {
        // perform final list processing here
        if (verbose) System.out.println("--$$$$$$$$$$$$$$$$$ Job terminated at: " + new java.util.Date());
        
    }
}










