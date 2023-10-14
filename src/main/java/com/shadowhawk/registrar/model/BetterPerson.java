package com.shadowhawk.registrar.model; //This is the "namespace" for this class

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Represents the same data as Person, but with way less boilerplate
 * 
 * @apiNote This class exists just to demonstrate why Lombok is powerful and industry standard!
 *          Notice how you can easily see what the true purpose of the class is now.
 *          No more getter/setter/constructor hell!
 * @author youngac
 */

//These @ signs are called "annotations". Don't worry about any other ones but Getter/Setter for now. You can try Builder, if you're comfortable
//By placing them on top of the class like this, all the boilerplate code is magically generated for you!

@Getter //Generates a "getter" for each field (e.g: getFirstName).
@Setter //Generates a "setter" for each field (e.g: setFirstName).
@Builder //Generates a "builder" for this class, which lets you pick which fields to set values for at will! More on this in makeAnotherPerson()
public class BetterPerson implements Serializable {
    private String firstName; 
    private String lastName;
    private int age;

    /**
     * Returns a basic greeting consisting of first and last name
     * @return
     */
    public String getBasicGreeting(){
        return "Hi, my name is: " + firstName + " " + lastName;
    }

    /**
     * Get an "advanced" greeting, which is full name and age
     * @return
     */
    public String getAdvancedGreeting(){
        //When you have to concatenate a bunch of strings together, you can optionally use a StringBuilder like this instead of + signs
        final String SPACE = " ";
        StringBuilder sb = new StringBuilder();
            sb.append("Hi! My name is")
                .append(firstName)
                .append(SPACE)
                .append(lastName)
                .append(SPACE)
                .append("Age: ")
                .append(age);
        return sb.toString();
    }

    /**
     * This is an example method that shows you how you can use the "builder".
     * For context, this is actually referring to the Builder design pattern!
     * If this is too complex right now, that's okay, skip this and come back to it another time.
     * The plain english version is that it lets you set only the fields you want, without needing to write constructors for every
     * single combination
     */
    public void makeAnotherPerson(){
        //Remember, each one of these are NEW instances of the BetterPerson class, not the instance we're in right now!
        BetterPerson yaBoiWhoGotCtrlAltDeleted = BetterPerson.builder().build(); //This how you'd call the default constructor
        BetterPerson aLiteralChild = BetterPerson.builder().age(10).build(); //Makes a BetterPerson with the age set

        BetterPerson bestPerson = BetterPerson.builder() //Makes the finest Pipi Man on all of Earth :D
        .firstName("Daniel")
        .lastName("Milstead")
        .age(26)
        .build();
    }
}

