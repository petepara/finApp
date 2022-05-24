package com.andersenlab.atink.controller.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@ApiModel( description="Model for getting notifications status")
public class NotificationResponse {
    private String email;
    private Boolean smsNotification;
    private Boolean pushNotification;
    private Boolean emailSubscription;

    @Override
    public String toString() {
        return "Your email:" + email +
                ", sms notification enabled:" + smsNotification +
                ", push notification enabled:" + pushNotification +
                ", email subscription enabled" + emailSubscription;
    }
}
