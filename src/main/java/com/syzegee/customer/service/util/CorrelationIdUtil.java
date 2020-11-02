package com.syzegee.customer.service.util;

import com.syzegee.customer.service.exception.CustomerServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Pattern;


/**
 * @author Sagar
 */
@Component
public class CorrelationIdUtil {


    public static String generateCorrelationId() {
        String correlationId = UUID.randomUUID().toString();
        return correlationId;
    }

    public static String generateCorrelationId(String uuid) throws CustomerServiceException {
        try {
            if (uuid.isEmpty()) {
                return generateCorrelationId();
            } else {
                if (!isUUID(uuid)) {
                    throw new CustomerServiceException(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "correlationId must be valid UUID format");
                }
            }
        } catch (Exception e) {
			throw new CustomerServiceException(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "correlationId must be valid UUID format");
        }
        return uuid;
    }

    static final Pattern UUIDs = Pattern
            .compile("(?i)^[0-9a-z]{8}-?[0-9a-z]{4}-?[0-9a-z]{4}-?[0-9a-z]{4}-?[0-9a-z]{12}$");

    public static boolean isUUID(String string) {
        try {
            return UUIDs.matcher(string).matches();
        } catch (Exception ex) {
            return false;
        }
    }

}
