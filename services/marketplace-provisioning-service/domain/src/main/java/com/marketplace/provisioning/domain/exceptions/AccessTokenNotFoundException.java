package com.marketplace.provisioning.domain.exceptions;

import com.marketplace.common.errors.enums.ErrorCode;
import com.marketplace.common.errors.exceptions.MpException;

import java.util.UUID;

public class AccessTokenNotFoundException extends MpException {

    public AccessTokenNotFoundException() {
        super(ErrorCode.ACCESS_TOKEN_NOT_FOUND);
    }

}