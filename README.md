# Work Manager
This is simple Work Manager project.

## Basic
An API that make it easy to schedule deferrable, asynchronous task that are expected to run reliable.

### Deferrable 
means Schedule mechanism means you can use for one time only or periodly
   Compitible with Doze mode, power saving mode.

### Reliable 
means we can add condition (contraints) for wifi availablity, sufficiean storage available.
   Always finish the work even if app exist or device restart
   
### There are two types of work manager	
    	1. One time Work Request
	    2. Periodic Work Request

## Worker
We have to extend worker class which has two callback method.

	1>doWork
	2>onStopped

## Types of Constraint in Work Manager
#### NetworkType
#### BatteryNotLow
#### RequiresCharging
#### DeviceIdle

StorageNotLow
## WorkRequest
This is abstract class which has some configaration such as
#### OneTimeWorkRequest
#### PeriodicWorkRequest

## WorkManager
In which below execution will be perform
#### Enqueueing the work
#### Cancelling the work

## Work Chaining
In work chaning, we can add multiple WorkRequest.
### Note:- 
In Work Chaining, we can add only OneTimeWorkRequest NOT a PeriodicWorkRequest.

#### If we want to perform multiple workrequest sequencly then

workManager.beginWith((OneTimeWorkRequest) 
workRequest1).then((OneTimeWorkRequest)workRequest2).then((OneTimeWorkRequest)workRequest3).enqueue();

#### If we want to perform two work request first parrallelly then next the use below code.
workManager.beginWith(Arrays.asList((OneTimeWorkRequest)workRequest1, (OneTimeWorkRequest)workRequest2)).then((OneTimeWorkRequest)workRequest3).enqueue();

### Note:-
If we start worker one and worker two first then worker three and before start worker three if we cancel work two then worker three will never execute. 
Means in work chaning, if chain get break then next worker will not be executed.

## Long Running Worker
### ForegroundInfo
It is nothing but object that hold notification that you want to trigger.

We have to pass ForegroundInfo inside setForegroundAsync like below.

setForegroundAsync (
		ForegroundInfo
	);

Note: We have to declair in work manager into manifest.

## FAQ
- Different between a Coroutine and WorkManager ?

    Coroutine is running only when app is running.
- Can we create custom constraint to work manager ? (WIFI is available etc)
		
        Not yet available.
- Can we restart the failed Worker ?
		
        No, but we can use retry (return Result.Retry in doWork method)
- In MVVM archetecture where does WorkManager fit in?
		
        In View Model
- What happen to Worker when app gets killed?
		
        If it was running then it will get resumed when conditions are met later.
		
        If it is NOT running then jobScheduler will enqueue it when to be run when condition are met.
- Can we work chain dynamically ?
		
        Yes, you can use append method does this purpose at run  time.
- Does device reboot affect Work manager execution ?
		
        No 