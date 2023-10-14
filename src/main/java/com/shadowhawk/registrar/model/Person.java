package com.shadowhawk.registrar.model; //This is the "namespace" for this class

/**
 * Represents a person.
 * @apiNote This object is written in a Java style that might be somewhat familiar to you.
 * The issue is that there are LOTS of boilerplate code
 */

public class Person {
    private String firstName; //Variable names in Java are always camelCase
    private char middleInitial; 
    private String lastName;
    private int age; //It's always good to briefly describe what each variable represents. I ain't doing it for this one though hehe
    
    //These two fields have "default" values, meaning that if one isn't assigned in the constructor (on object creation),
    //then this is the value it will "default" to.
    private int bankAccountBalance = 0;
    private String culture = "FRENCH";

    private final boolean smileWhenGreeting = false;

    /**
     * This is the constructor, which you use to make new Greeting objects.
     * To refresh your memory, an object is an INSTANCE of a CLASS.
     * Classes have "fields", which are variables stored inside of it, and may be public/private and/or constant!
     * 
     * In Java, you make more instances ("instantiate") by calling new()
     * e.g: SimpleGreeting testGreeting = new SimpleGreeting();
     * 
     * @apiNote You'll see that in {@link GreetingController}, we make some SimpleGreeting objects and call some of its functions:
     * Greeting
     */

    //Default constructor, called when no args are given. This means that if you do new Person() and print lastName, it will always be Dong
    public Person(){
        firstName = "John";
        lastName = "Smith";
    }

    //Another constructor, in case we want to set some of the data.
    public Person(String firstName, String lastName){
        firstName = "Hugh";
        lastName = "Dong";
    }

    /**
    * ANOTHER constructor, with a whopping 6 arguments!
    * This constructor is nice, because it guarantees we set all values, but what a HUGE waste of space!
    * Look at all this code just to accomplish a required step!
    * These are one of many things you WON'T have to write anymore, thanks to Lombok!
    * @param firstName
    * @param middleInitial
    * @param lastName
    * @param age
    * @param bankAccountBalance
    * @param culture
    * @param smileWhenGreeting
    */
    public Person(
        String firstName,
        char middleInitial,
        String lastName,
        int age,
        int bankAccountBalance,
        String culture){
            this.firstName = firstName;
            this.middleInitial = middleInitial;
            this.lastName = lastName;
            this.age = age;
            this.bankAccountBalance = bankAccountBalance;
            this.culture = culture;
        }

    //Anyone can call public methods
    public String getFirstName(){
        return firstName;
    }

    public char getMiddleInitial(){
        return middleInitial;
    }

    //NOTE: Look at the access level of updateBankAccountBalance(), it's private!
    //Only the object itself can call these private methods, usually within a private method here.
    //You can see that we don't want an angry frenchman to freely update his bank balance, but we DO generally want people to know their balance!
    //This is where you implement business logic, and provide access to the finished logic as a public method!
    public int getAccountBalance(){
        updateBankAccountBalance();
        return bankAccountBalance;
    }

    private int updateBankAccountBalance(){
        //If we're French and miserable, the bank pays...
        if(culture.equals("FRENCH") && smileWhenGreeting == false){
            setBankAccountBalance(bankAccountBalance * 5000);
        }
        return bankAccountBalance;
    }

    //Note that the parameter is "final", which is good practice because it tells whoever called the function
    //that NO MATTER WHAT, we will not accidentally modify that value. However, you are more than welcome to
    //omit "final", just know that it could be an indicator that the function might intend to modify it.
    private void setBankAccountBalance(final int newBalance){
        bankAccountBalance = newBalance;
    }

    /**
     * Returns the first and last name of this person
     * @return
     */
    public String sayBasicGreeting(){
        //TODO: Implement this
        return "";
    }

    /**
     * Using
     * @return
     */
    private String sayAdvancedGreeting(){
        //When you have to concatenate a bunch of strings together, you can optionally use a StringBuilder like this instead of + signs
        StringBuilder sb = new StringBuilder();
        sb.append("Hi! My name is")
        .append(firstName)
        .append(middleInitial)
        .append(lastName);
        return sb.toString();
    }
}
