package cz.muni.fi.pa165.smartphonEShop.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Roman Nahalka
 * Class represents: Data transfer object for authentication of Person.
 * @author xnahalka
 */

@Getter
@Setter
public class PersonAuthDTO
{
    private Long personID;
    private String pass;
}
