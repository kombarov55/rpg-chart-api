package ru.novemis.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Person {

    @DatabaseField(generatedId = true)
    public Long id;
    @DatabaseField
    public String firstName;
    @DatabaseField
    public String lastName;

}
