/**
 * Enum: ErrorCode
 * <p>
 * Description: Enumerates the set of...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.api.errors;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {

  USER_NOT_FOUND("E001", "User not found", HttpStatus.NOT_FOUND, "The user you are trying to access does not exist."),
  USER_ALREADY_EXISTS("E002", "User already exists", HttpStatus.CONFLICT, "The user with the provided email/username is already registered."),
  INVALID_CREDENTIALS("E003", "Invalid credentials", HttpStatus.UNAUTHORIZED, "The provided credentials do not match our records."),
  USER_NOT_ACTIVE("E004", "User not active", HttpStatus.FORBIDDEN, "The user account is not activated or has been blocked."),

  OTP_EXPIRED("E005", "OTP expired", HttpStatus.GONE, "The provided OTP has expired."),
  OTP_VALIDATION_FAILURE("E006", "OTP validation failed", HttpStatus.UNAUTHORIZED, "The provided OTP does not match our records."),
  OTP_GENERATION_FAILURE("E007", "Failed to generate OTP", HttpStatus.INTERNAL_SERVER_ERROR, "There was an issue generating the OTP."),

  DEVICE_NOT_RECOGNIZED("E008", "Device not recognized", HttpStatus.FORBIDDEN, "The current device is not registered."),
  DEVICE_LIMIT_EXCEEDED("E009", "Device limit exceeded", HttpStatus.TOO_MANY_REQUESTS, "You've logged in from too many devices."),

  TOKEN_EXPIRED("E010", "Token expired", HttpStatus.UNAUTHORIZED, "The provided token has expired."),
  INVALID_TOKEN("E011", "Invalid token", HttpStatus.UNAUTHORIZED, "The provided token is invalid or tampered with."),

  ROLE_NOT_FOUND("E012", "Role not found", HttpStatus.NOT_FOUND, "The specified role does not exist."),
  PERMISSION_NOT_FOUND("E013", "Permission not found", HttpStatus.NOT_FOUND, "The specified permission does not exist."),
  UNAUTHORIZED_ACCESS("E014", "Unauthorized access", HttpStatus.FORBIDDEN, "You do not have permission to access this resource."),

  DATA_ACCESS_ERROR("E015", "Data access error", HttpStatus.INTERNAL_SERVER_ERROR, "There was an issue accessing the data."),
  DATA_INTEGRITY_VIOLATION("E016", "Data integrity violation", HttpStatus.CONFLICT, "The provided data violates database constraints."),

  PASSWORD_ENCRYPTION_FAILURE("E017", "Password encryption failed", HttpStatus.INTERNAL_SERVER_ERROR, "There was an error during password encryption."),
  NOTIFICATION_FAILURE("E018", "Notification sending failed", HttpStatus.SERVICE_UNAVAILABLE, "Failed to send the notification."),

  INTERNAL_SERVER_ERROR("E019", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, "There was an unexpected error."),
  BAD_REQUEST("E020", "Bad request", HttpStatus.BAD_REQUEST, "The request is malformed."),
  RESOURCE_NOT_FOUND("E021", "Resource not found", HttpStatus.NOT_FOUND, "The requested resource is not available."),

  TOO_MANY_REQUESTS("E022", "Too many requests", HttpStatus.TOO_MANY_REQUESTS, "You have made too many requests in a short period."),
  CONCURRENT_ACCESS("E023", "Concurrent access error", HttpStatus.CONFLICT, "Resource is being accessed by multiple entities concurrently."),

  INVALID_OTP("E024", "Invalid OTP", HttpStatus.BAD_REQUEST, "The provided OTP is invalid."),
  DEVICE_NOT_FOUND("E025", "Device not found", HttpStatus.NOT_FOUND, "The specified device does not exist."),
  RATE_LIMIT_EXCEEDED("E026", "Rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS, "You have exceeded the rate limit."),
  SERVICE_UNAVAILABLE("E027", "Service unavailable", HttpStatus.SERVICE_UNAVAILABLE, "The requested service is unavailable."),
  AUTHORIZATION_FAILED("E028", "Authorization failed", HttpStatus.UNAUTHORIZED, "The request is not authorized."),;

  String code;
  String message;
  HttpStatus status;
  String detail;

  ErrorCode(String code, String message, HttpStatus status, String detail) {
    this.code = code;
    this.message = message;
    this.status = status;
    this.detail = detail;
  }

}