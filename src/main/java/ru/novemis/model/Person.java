package ru.novemis.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@DatabaseTable
@Data
public class Person {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String firstName;
    @DatabaseField
    private String lastName;

}
