# MeBank Balance Calculator

A java program calculate the relative balance.



### Prerequisites
The code used Java 8 to program and used Maven to construct, 
so please make sure you have install all the prerequisites.

```
Java 8
Maven
```

### Build


```
mvn clean package
```

## Test


```
mvn test -Dmaven.test.skip=false
```

## Run

```
java -jar target/balance-1.0-SNAPSHOT.jar
```

## Structure of the program
The program is mainly a REPL.
The structure is according to a MVC pattern.

The controller includes the direct business logic, 
and currently, there is only one controller, which is BalanceController.
It's easy to extend. You can add any other controllers in future.

The DAO includes the elements of data management system(indexes).

The model includes the Transaction model.

The service includes the main logic and speak directly to dao.

The view includes the objects for presentation.

As a word, the system is well structured, and easy to extend.
Just add new controllers to the control flow of REPL.
## A Quick WalkThrough
When you look at the main entry class of the program,
you will know how it works.

1. First, parse the csv file, which contains the transaction history, into a 
list.

    I use the [opencsv](http://opencsv.sourceforge.net/) lib to parse the csv, it's a efficient and easy to 
    use 
library, and it helps me directly convert the csv into beans.

    The csv file is configurable, which you can change the configure in config
.properties under the resource folder.

2. Second, build the data system index.
 
    Since the csv file could be large, and the data could be large, we do 
    not want to do a brute force traverse whenever we'd like to calculate a 
    balance. So, I build some index structure for that. 
    
    There are 3 index structures in total. A primary index, whose key is the
     transactionId, a fromAccountId index, and a toAccountId index.
     In order to do a query about transaction periods, I use TreeSet to 
     store the transactions in fromAccountId and toAccountId index.

3. Last but not the Least, run the REPL.
    
    The main program is a REPL, since the initializing process could be 
    slow, and we do not want to redo it every time. As a result, the REPL is
     a good choice for me.

## Libs used
Except the opencsv, I used the [lombok](https://projectlombok.org/) lib to simplify the construction of 
classes. By using annotations like` @Data`, `@NoArgsConstructor`, it can add
 some code for me automatically. If you are using IDE, to avoid warnings, 
 you may want to install a plugin. 
 

## Authors

* **Tim Li**
