package org1.acme.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
    public class UserModel {

        private long id;
        private String name;
        private String emailId;
        private String mobileNo;


    }
