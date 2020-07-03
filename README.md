# Take-away billing system

## Demands description

A fast food brand launched its own take-away app where users can directly order a meal on mobile phones. The app will make accumulations based on the selected "item", "count" and "promotion" and inform users the charge to be paid.

Promotions takes various forms. If a user can only use one promotion at one time, he may find it troublesome to determine the promotion which saves the most. Therefore for users' convenience, the app is designed to automatically select the most reasonable promotion and accumulate the final charges for user verification.

We need a function named `bestCharge`, which can receive the items and counts (displayed in certain format) a user choose and use these data as input to return the accumulated information.

We have known that:

- Eacg dish item in this restaurant has a unique ID
- We now have various promotions:
  - Deduct 6 yuan when the order reaches 30 yuan
  - Half price for certain dishes
- No other charges except dish items (such as delivery and package charges)
- If two promotion approaches save the same amount of money,the first promotion will be used

Input example
-------

```
["ITEM0001 x 1", "ITEM0013 x 2", "ITEM0022 x 1"]
```

Output example
-------

```
============= Order details =============
Braised chicken x 1 = 18 yuan
Chinese hamburger x 2 = 12 yuan
Cold noodles x 1 = 8 yuan
-----------------------------------
Promotion used:
Half price for certain dishes (braised chicken and cold noodles), saving 13 yuan
-----------------------------------
Total: 25 yuan
===================================
```

Example of using another promotion
------------------

Input:

```
["ITEM0013 x 4", "ITEM0022 x 1"]
```


Output:

```
============= Order details =============
Chinese hamburger x 4 = 24 yuan
Cold noodles x 1 = 8 yuan
-----------------------------------
Promotion used:
Deduct 6 yuan when the order reaches 30 yuan, saving 6 yuan
-----------------------------------
In total: 26 yuan
===================================
```

If there is no promotion applicable
---------------

Input:

```
["ITEM0013 x 4"]
```

Output:

```
============= Order details =============
Chinese hamburger x 4 = 24 yuan
-----------------------------------
In total: 24 yuan
===================================
```


## Environment required
- Java 8

## Exercise requirement

- Clone template library
- Write codes in the method of `bestCharge` under the class of `App.java` in the directory of `src/main`
- The finished codes should be able to pass the test under the directory of `src/main`

## How to take the test
- Go to the root directory and execute `./gradlew clean test` to check the test result

