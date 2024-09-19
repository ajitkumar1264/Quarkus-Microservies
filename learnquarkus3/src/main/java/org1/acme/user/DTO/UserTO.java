package org1.acme.user.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {

    private String name;
    private  String emailId;
    private  String mobileNo;


}
