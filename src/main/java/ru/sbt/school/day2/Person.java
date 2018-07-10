package ru.sbt.school.day2;

import java.util.Objects;

public class Person {
    private final boolean man;
    private final String name;
    private Person spouse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return man == person.man &&
                Objects.equals(name, person.name) &&
                Objects.equals(spouse, person.spouse);
    }

    @Override
    public int hashCode() {

        return Objects.hash(man, name, spouse);
    }

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }
    /**
    * This method checks gender of persons. If genders are not equal - tries to marry.
    * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
    * @param person - new husband/wife for this person.
    * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
    */
    public boolean marry(Person person){
//        System.out.println("1) Try to marred " + this.name + " & " + person.name);
        if (man == person.man)
            return false;
        if ((spouse != null) && spouse.equals(person))
            return false;
        if (this.spouse != null || person.spouse != null) {
            if (!person.spouse.equals(this))
                person.divorce();
            this.divorce();
        }
        spouse = person;
        person.marry(this);
//        System.out.println("2) Marry! " + this.name + " & " + person.name);
        return true;
    }

    /**
    * Sets spouse = null if spouse is not null
    * @return true - if person status has been changed
    */
    public boolean divorce() {
        if (spouse !=null){
//            System.out.println("--Divorced " + name + " & " + spouse.name);
            Person temp = spouse;
            spouse = null;
            temp.divorce();
            return true;
        }
        return false;
    }
}
