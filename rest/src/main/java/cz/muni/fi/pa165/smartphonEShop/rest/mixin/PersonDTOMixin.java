package cz.muni.fi.pa165.smartphonEShop.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"passwordHash"})
public class PersonDTOMixin
{
}
