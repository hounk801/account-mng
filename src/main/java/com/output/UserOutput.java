package com.output;

import com.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/2 12:55 AM
 */
@Setter
@Getter
@ToString
public class UserOutput {
    private String userId;
    private String userName;
    private String email;

    public UserOutput() {
    }

    public UserOutput(UserInfo info) {
        if (info != null) {
            this.email = info.getEmail();
            this.userId = info.getUserId();
            this.userName = info.getUserName();
        }
    }
}
