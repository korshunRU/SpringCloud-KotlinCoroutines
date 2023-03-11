package ru.korshun.commons.model.dto.personData

import ru.korshun.commons.model.dto.countryData.Country

data class Person(
    val results: List<PersonInfo>
) {

    class PersonInfo(
        val gender: String,
        val name: Name,
        val location: Location,
        val email: String
    )

    class Name(
        val first: String,
        val last: String,
        val title: String
    )

    class Location(
        val city: String,
        val country: String,
        var countryInfo: Country? = null,
        val postcode: String
    )

}